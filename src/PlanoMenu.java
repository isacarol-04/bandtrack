import java.time.LocalDate;
import java.util.Scanner;

public class PlanoMenu {
    public static PlanoInternet Escolher(Scanner scanner) {
        System.out.println("\n--- Escolher Plano ---");
        System.out.println("1. Basico - 100GB por 79,90");
        System.out.println("2. Completo - 200GB por 139,90");
        System.out.println("3. Familia - 500GB por 249,90");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        PlanoInternet plano;

        switch (opcao) {
            case 1:
                plano = new PlanoBasico();
                System.out.println("Plano basico selecionado!");
                break;
            case 2:
                plano = new PlanoCompleto();
                System.out.println("Plano completo selecionado!");
                break;
            case 3:
                plano = new PlanoFamilia();
                System.out.println("Plano familia selecionado!");
                break;
            default:
                plano = new PlanoBasico();
                System.out.println("Opção inválida, optando por plano padrão.\nPlano basico selecionado!");
                break;
        }

        return plano;
    }
}
