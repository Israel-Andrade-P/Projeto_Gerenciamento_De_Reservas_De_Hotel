package service;

import exception.HospedeNotFoundException;
import model.Hospede;
import repository.Repository;

public class HospedeService {
    private final Repository<Hospede, String> hospedeRepository;

    public HospedeService(Repository<Hospede, String> hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public String createHospede(Hospede hospede) {
        if (!validateHospede(hospede)) {
            return String.format("Hóspede com CPF %s já cadastrado!", hospede.getCpf());
        }
        hospedeRepository.save(hospede);
        return "Hóspede cadastrado com sucesso!";
    }

    public void deleteHospede(String cpf) {
        hospedeRepository.deleteById(cpf);
    }

    private boolean validateHospede(Hospede hospede) {
        boolean isValid = false;
        try {
            hospedeRepository.findById(hospede.getCpf());
            isValid = true;
        }catch (HospedeNotFoundException _) {

        }
        return isValid;
    }
}
