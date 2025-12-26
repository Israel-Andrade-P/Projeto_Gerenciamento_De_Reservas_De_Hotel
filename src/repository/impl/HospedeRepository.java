package repository.impl;

import exception.HospedeNotFoundException;
import model.Hospede;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.HOSPEDE_NAO_ENCONTRADA;

public class HospedeRepository implements Repository<Hospede, String> {
    private List<Hospede> hospedes;

    public HospedeRepository() {
        hospedes = new ArrayList<>();
    }

    public Hospede save(Hospede hospede) {
        hospedes.add(hospede);
        return hospede;
    }

    public void delete(Hospede hospede) {
        if (!hospedes.contains(hospede)) throw new HospedeNotFoundException(HOSPEDE_NAO_ENCONTRADA);
        hospedes.remove(hospede);
    }

    public void deleteById(String cpf) {
        hospedes.remove(findById(cpf));
    }

    public Hospede findById(String cpf) {
        return hospedes.stream()
                .filter(h -> h.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new HospedeNotFoundException(HOSPEDE_NAO_ENCONTRADA));
    }

    @Override
    public List<Hospede> findAll(String s) {
        return hospedes;
    }

    @Override
    public List<Hospede> findAll() {
        return hospedes;
    }

    @Override
    public boolean existsById(String cpf) {
        return hospedes.stream()
                .anyMatch(h -> h.getCpf().equals(cpf));
    }
}
