package model;

public class PlanoCompleto extends PlanoInternet {
    public PlanoCompleto() {
        super("Completo", 200, 139.9, 3);
    }

    @Override
    public double calcularValorTotal(double consumoGB) {
        if (temExcedente(consumoGB)) {
            return precoMensal + calcularExcedente(consumoGB);
        }
        return precoMensal;
    }
}

