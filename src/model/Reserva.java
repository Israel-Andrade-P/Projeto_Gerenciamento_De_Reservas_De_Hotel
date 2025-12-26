package model;

import enumeration.QuartoTipo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reserva {
    private String id;
    private final Hospede hospede;
    private QuartoTipo tipo;
    private int numeroDias;
    private final String dataDoCadastro;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reserva(Hospede hospede, QuartoTipo tipo, int numeroDias) {
        id = UUID.randomUUID().toString();
        dataDoCadastro = LocalDate.now(ZoneId.of("America/Sao_Paulo")).format(formatter);
        this.hospede = hospede;
        this.tipo = tipo;
        this.numeroDias = numeroDias;
    }

    public Reserva(Hospede hospede) {
        this.hospede = hospede;
        id = UUID.randomUUID().toString();
        dataDoCadastro = LocalDate.now(ZoneId.of("America/Sao_Paulo")).format(formatter);
    }

    public double getTotalCost() {
        return tipo.getPreco() * numeroDias;
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

    public String getDataDoCadastro() {
        return dataDoCadastro;
    }
}
