package model;

public abstract class PlanoInternet implements Faturavel {
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

    public String getNome() {
        return nome;
    }

    @Override
    public abstract double calcularValorTotal(double consumoGB);

    public boolean temExcedente(double consumoGB) {
        return consumoGB > limiteMensalGB;
    }

    public double calcularExcedente(double consumoGB) {
        if (!temExcedente(consumoGB)) return 0;
        return (consumoGB - limiteMensalGB) * precoExcedentePorGB;
    }
}
