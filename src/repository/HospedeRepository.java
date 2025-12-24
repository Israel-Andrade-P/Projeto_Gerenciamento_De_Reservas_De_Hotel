package repository;

import exception.HospedeNotFoundException;
import model.Hospede;

import java.util.ArrayList;
import java.util.List;

import static constants.Constants.HOSPEDE_NAO_ENCONTRADA;

public class HospedeRepository {
    private List<Hospede> hospedes;

    public HospedeRepository() {
        hospedes = new ArrayList<>();
    }

    public void saveHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public void deleteHospede(Hospede hospede) {
        if (!hospedes.contains(hospede)) throw new HospedeNotFoundException(HOSPEDE_NAO_ENCONTRADA);
        hospedes.remove(hospede);
    }

    public void deleteByCpf(String cpf) {
        hospedes.remove(getByCpf(cpf));
    }

    public Hospede getByCpf(String cpf) {
        return hospedes.stream()
                .filter(h -> h.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new HospedeNotFoundException(HOSPEDE_NAO_ENCONTRADA));
    }
}
