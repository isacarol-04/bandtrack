package model;

import java.time.LocalDate;

public class RegistroUso {
    private final LocalDate data;
    private final double consumoGB;

    public RegistroUso(LocalDate data, double consumoGB) {
        this.data = data;
        this.consumoGB = consumoGB;
    }

    public LocalDate getData() {
        return data;
    }

    public double getConsumoGB() {
        return consumoGB;
    }
}
