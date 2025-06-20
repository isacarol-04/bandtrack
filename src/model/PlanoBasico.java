package model;

public class PlanoBasico extends PlanoInternet {
    public PlanoBasico() {
        super("Basico", 100, 79.90, 2.50);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (temExcedente(consumoGB)) {
            return precoMensal + calcularExcedente(consumoGB);
        }
        return precoMensal;
    }
}
