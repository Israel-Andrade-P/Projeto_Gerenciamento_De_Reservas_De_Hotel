package service;

import exception.InvalidReservaException;
import model.Reserva;
import repository.ReservaRepository;

import java.util.List;

public class ReservaService {
    private ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void addReserva(Reserva reserva) {
        if (!ValidarReserva(reserva)) {
            throw new InvalidReservaException("Reserva tem que ter pelo menos uma diária");
        }
        reservaRepository.saveReserva(reserva);
    }

    public void cancelaReserva(String reservaId) {
        reservaRepository.deleteById(reservaId);
    }

    public List<Reserva> pegarReservasPorCpf(String cpf) {
        return reservaRepository.findByCpf(cpf);
    }

    private boolean ValidarReserva(Reserva reserva) {
        return reserva.getNumeroDias() > 0;
    }
}
