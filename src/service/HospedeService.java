package service;

import exception.HospedeNotFoundException;
import model.Hospede;
import repository.HospedeRepository;

public class HospedeService {
    private HospedeRepository hospedeRepository;

    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public String createHospede(Hospede hospede) {
        if (!validateHospede(hospede)) {
            return String.format("Hóspede com CPF %s já cadastrado!", hospede.getCpf());
        }
        return "Hóspede cadastrado com sucesso!";
    }

    public void deleteHospede(String cpf) {
        hospedeRepository.deleteByCpf(cpf);
    }

    private boolean validateHospede(Hospede hospede) {
        boolean isValid = false;
        try {
            hospedeRepository.getByCpf(hospede.getCpf());
            isValid = true;
        }catch (HospedeNotFoundException _) {

        }
        return isValid;
    }
}
