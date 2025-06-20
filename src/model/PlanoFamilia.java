package model;

public class PlanoFamilia extends PlanoInternet {
    public PlanoFamilia() {
        super("Familia", 500, 249.9, 10);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (temExcedente(consumoGB)) {
            return precoMensal + calcularExcedente(consumoGB);
        }
        return precoMensal;
    }
}
