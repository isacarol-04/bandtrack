import java.time.LocalDate;

public class RegistroUso {
    private LocalDate data;
    private double consumoGB;

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
