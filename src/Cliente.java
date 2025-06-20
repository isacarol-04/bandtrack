import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private String cpf;
    private String endereco;
    private PlanoInternet plano;
    protected List<RegistroUso> usoMensal;

    public Cliente(String nome, String cpf, String endereco, PlanoInternet plano) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.plano = plano;
        this.usoMensal = new ArrayList<>();
    }

    public void registrarUso(LocalDate data, double consumoGB) {
        usoMensal.add(new RegistroUso(data, consumoGB));
    }

    public double calcularUsoTotal() {
        return usoMensal.stream()
                .mapToDouble(RegistroUso::getConsumoGB)
                .sum();
    }

    public double gerarValorFatura() {
        return plano.calcularValorTotal(calcularUsoTotal());
    }

    public String getCpf() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEndereco() { return this.endereco; }

    public PlanoInternet getPlano() { return this.plano; }

    // Getters, setters, toString
}
