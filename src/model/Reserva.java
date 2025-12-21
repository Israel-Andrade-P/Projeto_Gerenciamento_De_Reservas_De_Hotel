package model;

import enumeration.QuartoTipo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Reserva {
    private String id;
    private final Hospede hospede;
    private QuartoTipo tipo;
    private int numeroDias;
    private final LocalDateTime dataDoCadastro;

    public Reserva(Hospede hospede, QuartoTipo tipo, int numeroDias, LocalDateTime dataDoCadastro) {
        id = UUID.randomUUID().toString();
        this.hospede = hospede;
        this.tipo = tipo;
        this.numeroDias = numeroDias;
        this.dataDoCadastro = dataDoCadastro;
    }

    public String getId() {
        return id;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public QuartoTipo getTipo() {
        return tipo;
    }

    public void setTipo(QuartoTipo tipo) {
        this.tipo = tipo;
    }

    public int getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(int numeroDias) {
        this.numeroDias = numeroDias;
    }

    public LocalDateTime getDataDoCadastro() {
        return dataDoCadastro;
    }
}
