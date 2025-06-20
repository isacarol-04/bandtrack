package service;

import model.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTelecom {
    private static final String CLIENTES_CSV = "clientes.csv";
    private static final String CONSUMOS_CSV = "consumos.csv";
    private final List<Cliente> clientes;

    public GerenciadorTelecom() {
        clientes = new ArrayList<>();
        this.carregarClientes();
        this.carregarConsumos();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clientes.stream().filter(c -> c.getCpf().equals(cpf)).findFirst().orElse(null);
    }

    public void registrarUso(String cpf, LocalDate data, double consumoGB) {
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente != null) {
            cliente.registrarUso(data, consumoGB);
        } else {
            System.out.println("Cliente não encontrado!");
        }
    }

    public void gerarRelatorioMensal() {
        for (Cliente cliente : clientes) {
            System.out.println("-----");
            System.out.println("Cliente: " + cliente.getNome());
            System.out.println("Consumo: " + cliente.calcularUsoTotal() + " GB");
            System.out.printf("Fatura: R$ %.2f%n", cliente.gerarValorFatura());
        }
    }

    private void carregarClientes() {
        try {
            Path path = Paths.get(CLIENTES_CSV);
            boolean arquivoExiste = Files.exists(path);
            if (!arquivoExiste) {
                System.out.println("Arquivo de clientes não encontrado!");
                return;
            }
            System.out.println("Arquivo de clientes encontrado com sucesso!");

            List<String> linhas = Files.readAllLines(path);
            for (String linha : linhas) {
                String[] partes = linha.split(",");
                if (partes.length >= 4) {
                    String nome = partes[0];
                    String cpf = partes[1];
                    String endereco = partes[2];
                    String tipoPlano = partes[3];

                    PlanoInternet plano;
                    switch (tipoPlano) {
                        case "Basico":
                            plano = new PlanoBasico();
                            break;
                        case "Completo":
                            plano = new PlanoCompleto();
                            break;
                        case "Familia":
                            plano = new PlanoFamilia();
                            break;
                        default:
                            plano = new PlanoBasico();
                            System.out.println("Plano invalido, selecionando plano default.");
                            break;
                    }

                    Cliente cliente = new Cliente(nome, cpf, endereco, plano);
                    clientes.add(cliente);
                } else {
                    System.out.println("Arquivo de clientes corrompido!");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes! Mensagem: " + e.getMessage());
        }
    }

    private void carregarConsumos() {
        try {
            Path path = Paths.get(CONSUMOS_CSV);
            if (!Files.exists(path)) {
                System.out.println("Arquivo de consumos não encontrado!");
                return;
            }
            System.out.println("Arquivo de consumos encontrado com sucesso!");

            List<String> linhas = Files.readAllLines(path);
            for (String linha : linhas) {
                String[] partes = linha.split(",");
                if (partes.length >= 3) {
                    String cpf = partes[0];
                    LocalDate data = LocalDate.parse(partes[1]);
                    double consumo = Double.parseDouble(partes[2]);
                    this.registrarUso(cpf, data, consumo);
                } else {
                    System.out.println("Linha de consumo inválida: " + linha);
                }
            }

            System.out.println("Consumos carregados com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao carregar consumos! Mensagem: " + e.getMessage());
        }
    }

    public boolean clienteExiste(String cpf) {
        return clientes.stream().anyMatch(c -> c.getCpf().equals(cpf));
    }

    public void salvar() {
        try (BufferedWriter clientesWriter = Files.newBufferedWriter(Paths.get(CLIENTES_CSV)); BufferedWriter consumoWriter = Files.newBufferedWriter(Paths.get(CONSUMOS_CSV))) {
            for (Cliente cliente : clientes) {
                String tipoPlano = cliente.getPlano().getNome();

                clientesWriter.write(String.format("%s,%s,%s,%s%n", cliente.getNome(), cliente.getCpf(), cliente.getEndereco(), tipoPlano));


                for (RegistroUso uso : cliente.getUsoMensal()) {
                    String consumo = String.format("%f", uso.getConsumoGB()).replace(",", ".");
                    consumoWriter.write(String.format("%s,%s,%s%n", cliente.getCpf(), uso.getData().toString(), consumo));
                }
            }

            System.out.println("Dados salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes! Mensagem: " + e.getMessage());
        }
    }
}
