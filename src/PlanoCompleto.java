public class PlanoCompleto extends PlanoInternet {
    public PlanoCompleto() {
        super("Completo", 200, 139.9, 5);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (consumoGB <= limiteMensalGB) return precoMensal;
        double excedente = consumoGB - limiteMensalGB;
        return precoMensal + excedente * precoExcedentePorGB;
    }
}
