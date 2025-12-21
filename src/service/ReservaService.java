package service;

import exception.InvalidReservaException;
import model.Reserva;
import repository.ReservaRepository;

import java.util.List;

public class ReservaService {
    ReservaRepository repository = new ReservaRepository();

    public void addReserva(Reserva reserva) {
        if (!ValidarReserva(reserva)) {
            throw new InvalidReservaException("Reserva tem que ter pelo menos uma diária");
        }
        repository.saveReserva(reserva);
    }

    public void cancelaReserva(String reservaId) {
        repository.deleteById(reservaId);
    }

    public List<Reserva> pegarReservasPorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    private boolean ValidarReserva(Reserva reserva) {
        return reserva.getNumeroDias() > 0;
    }
}
