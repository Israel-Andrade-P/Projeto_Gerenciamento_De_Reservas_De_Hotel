package service;

import exception.HospedeAlreadyExistsException;
import model.Hospede;
import repository.Repository;

import java.util.List;

import static constants.Constants.HOSPEDE_JA_EXISTE;

public class HospedeService {
    private final Repository<Hospede, String> hospedeRepository;

    public HospedeService(Repository<Hospede, String> hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public void createHospede(Hospede hospede) {
        hospedeRepository.save(hospede);
    }

    public List<Hospede> getAll() {
        return hospedeRepository.findAll();
    }

    public void deleteHospede(String cpf) {
        hospedeRepository.deleteById(cpf);
    }

    private boolean validateHospede(Hospede hospede) {
        return hospedeRepository.existsById(hospede.getCpf());
    }
}
