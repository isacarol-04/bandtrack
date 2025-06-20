public class PlanoFamilia extends PlanoInternet {
    public PlanoFamilia() {
        super("Familia", 500, 249.9, 10);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (consumoGB <= limiteMensalGB) return precoMensal;
        double excedente = consumoGB - limiteMensalGB;
        return precoMensal + excedente * precoExcedentePorGB;
    }
}
