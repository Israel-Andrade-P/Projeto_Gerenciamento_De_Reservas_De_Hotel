package repository;

import exception.ReservaNotFoundException;
import model.Reserva;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.RESERVA_NAO_ENCONTRADA;

public class ReservaRepository {
    private List<Reserva> reservas;

    public ReservaRepository() {
        reservas = new ArrayList<>();
    }

    public void saveReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void deleteReserva(Reserva reserva) {
        if (!reservas.contains(reserva)) throw new ReservaNotFoundException(RESERVA_NAO_ENCONTRADA);
        reservas.remove(reserva);
    }

    public void deleteById(String id) {
        reservas.remove(getById(id));
    }

    public List<Reserva> findByCpf(String cpf) {
        return reservas.stream().filter(reserva -> reserva.getHospede().getCpf().equals(cpf)).toList();
    }

    private Reserva getById(String id) {
        return reservas.stream()
                .filter(reserva -> reserva.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ReservaNotFoundException(RESERVA_NAO_ENCONTRADA));
    }
}
