package main;

import model.Cliente;
import model.PlanoInternet;
import service.GerenciadorTelecom;
import view.PlanoMenu;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTelecom sistema = new GerenciadorTelecom();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nEncerrando programa... Salvando dados...");
            sistema.salvar();
        }));

        while (true) {
            try {
                System.out.println("\n--- BandaTrack ---");
                System.out.println("1. Cadastrar cliente");
                System.out.println("2. Registrar uso");
                System.out.println("3. Gerar relatório");
                System.out.println("4. Sair");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                String nome, cpf, end;
                double gb;
                PlanoInternet plano;
                Cliente cliente;

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        nome = scanner.nextLine();
                        System.out.print("CPF: ");
                        cpf = scanner.nextLine();
                        if (sistema.clienteExiste(cpf)) {
                            System.out.println("Já existe um cliente cadastrado com esse CPF.");
                            break;
                        }
                        System.out.print("Endereço: ");
                        end = scanner.nextLine();

                        plano = PlanoMenu.Escolher(scanner);
                        cliente = new Cliente(nome, cpf, end, plano);
                        sistema.cadastrarCliente(cliente);
                        System.out.println("Cliente cadastrado!");
                        break;

                    case 2:
                        System.out.print("CPF: ");
                        cpf = scanner.nextLine();
                        System.out.print("Consumo (GB): ");
                        gb = scanner.nextDouble();
                        sistema.registrarUso(cpf, LocalDate.now(), gb);
                        break;

                    case 3:
                        sistema.gerarRelatorioMensal();
                        break;

                    case 4:
                        System.exit(0);
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite número de acordo com as opções.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }

    }
}
