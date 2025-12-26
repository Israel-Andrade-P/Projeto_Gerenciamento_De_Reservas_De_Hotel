package enumeration;

public enum QuartoTipo {
    STANDARD(50.0),
    LUXO(100.0),
    PRESIDENCIAL(300.0);

    private final double preco;

    QuartoTipo(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return preco;
    }
}
