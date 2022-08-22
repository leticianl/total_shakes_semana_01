package produto;

public enum TipoTamanho {
    //IMPLEMENTE A LOGICA DO ENUM
    P(1.0),
    M(1.3),
    G(1.5);
    //IMPLEMENTE A LOGICA DO ENUM
    //TODO

    TipoTamanho(Double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador(){
        return multiplicador;
    }

    public final double multiplicador;

}
