public abstract class PlanoInternet {
    protected String nome;
    protected double limiteMensalGB;
    protected double precoMensal;
    protected double precoExcedentePorGB;

    public PlanoInternet(String nome, double limite, double preco, double excedente) {

        this.nome = nome;
        this.limiteMensalGB = limite;
        this.precoMensal = preco;
        this.precoExcedentePorGB = excedente;
    }

    public abstract double calcularValorTotal(double consumoGB);
}
