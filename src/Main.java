import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorTelecom sistema = new GerenciadorTelecom();

        while (true) {
            System.out.println("\n--- BandaTrack ---");
            System.out.println("1. Cadastrar cliente");
            System.out.println("2. Registrar uso");
            System.out.println("3. Gerar relatório");
            System.out.println("4. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String end = scanner.nextLine();

                    PlanoInternet plano = new PlanoBasico(); // pode criar um menu para escolher plano
                    Cliente cliente = new Cliente(nome, cpf, end, plano);
                    sistema.cadastrarCliente(cliente);
                    System.out.println("Cliente cadastrado!");
                    break;

                case 2:
                    System.out.print("CPF: ");
                    cpf = scanner.nextLine();
                    System.out.print("Consumo (GB): ");
                    double gb = scanner.nextDouble();
                    sistema.registrarUso(cpf, LocalDate.now(), gb);
                    break;

                case 3:
                    sistema.gerarRelatorioMensal();
                    break;

                case 4:
                    System.exit(0);
            }
        }
    }
}
