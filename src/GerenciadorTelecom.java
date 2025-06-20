import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTelecom {
    private List<Cliente> clientes;

    public GerenciadorTelecom() {
        clientes = new ArrayList<>();
    }

    public void cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
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

    // Salvar/carregar arquivos pode ser adicionado aqui também
}
