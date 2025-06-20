package model;

public class PlanoFamilia extends PlanoInternet {
    public PlanoFamilia() {
        super("Familia", 500, 249.9, 2);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        double valorBase = precoMensal;
        if (temExcedente(consumoGB)) {
            valorBase += calcularExcedente(consumoGB);
            return valorBase;
        }

        if (consumoGB < (limiteMensalGB / 2)) {
            double desconto = 0.10;
            return valorBase * (1 - desconto);
        }

        return valorBase;
    }
}
