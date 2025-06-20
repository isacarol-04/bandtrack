public class PlanoBasico extends PlanoInternet {
    public PlanoBasico() {
        super("Básico", 100, 79.90, 2.50);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (consumoGB <= limiteMensalGB) return precoMensal;
        double excedente = consumoGB - limiteMensalGB;
        return precoMensal + excedente * precoExcedentePorGB;
    }
}
