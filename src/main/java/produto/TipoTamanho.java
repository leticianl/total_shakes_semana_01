package produto;

public enum TipoTamanho {
    //IMPLEMENTE A LOGICA DO ENUM
    P(0.0),
    M(0.3),
    G(0.5);
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
