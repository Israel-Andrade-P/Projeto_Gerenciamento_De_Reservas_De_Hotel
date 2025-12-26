package repository.impl;

import exception.ReservaNotFoundException;
import model.Reserva;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.RESERVA_NAO_ENCONTRADA;

public class ReservaRepository implements Repository<Reserva, String> {
    private List<Reserva> reservas;
    private int capacity;

    public ReservaRepository() {
        reservas = new ArrayList<>();
    }

    public Reserva save(Reserva reserva) {
        reservas.add(reserva);
        capacity++;
        return reserva;
    }

    public void delete(Reserva reserva) {
        if (!reservas.contains(reserva)) throw new ReservaNotFoundException(RESERVA_NAO_ENCONTRADA);
        reservas.remove(reserva);
    }

    public void deleteById(String id) {
        reservas.remove(findById(id));
    }

    public Reserva findById(String id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReservaNotFoundException(RESERVA_NAO_ENCONTRADA));
    }

    public List<Reserva> findAll(String cpf) {
        return reservas.stream().filter(reserva -> reserva.getHospede().getCpf().equals(cpf)).toList();
    }

    @Override
    public List<Reserva> findAll() {
        return reservas;
    }

    @Override
    public boolean existsById(String id) {
        return reservas.stream().anyMatch(reserva -> reserva.getId().equals(id));
    }

    public int getCapacity() {
        return capacity;
    }
}
