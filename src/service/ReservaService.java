package service;

import exception.HotelCapacityException;
import exception.InvalidReservaException;
import model.Reserva;
import repository.Repository;
import repository.impl.ReservaRepository;

import java.util.List;

import static constants.Constants.*;
import static java.util.Comparator.comparing;

public class ReservaService {
    private final Repository<Reserva, String> reservaRepository;

    public ReservaService(Repository<Reserva, String> reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void addReserva(Reserva reserva) {
        ReservaRepository reservaRepo = (ReservaRepository) reservaRepository;
        if (reservaRepo.getCapacity() > 10) {
            throw new HotelCapacityException(HOTEL_LOTADO);
        }
        if (!ValidarReserva(reserva)) {
            throw new InvalidReservaException(RSERVA_DIARIA_INVALIDA);
        }

        reservaRepository.save(reserva);
    }

    public void cancelaReserva(String reservaId) {
        reservaRepository.deleteById(reservaId);
    }

    public List<Reserva> pegarReservasPorCpf(String cpf) {
        List<Reserva> reservas = reservaRepository.findAll(cpf).stream().sorted(comparing(Reserva::getNumeroDias).reversed()).toList();
        if (reservas.isEmpty()) {
            throw new InvalidReservaException(RESERVA_NAO_ENCONTRADA);
        }
        return reservas;
    }

    public List<Reserva> pegarTodas() {
        return reservaRepository.findAll();
    }

    public boolean ValidarReserva(Reserva reserva) {
        return reserva.getNumeroDias() > 0;
    }
}
