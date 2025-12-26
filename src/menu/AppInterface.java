package menu;

import enumeration.QuartoTipo;
import exception.OpcaoInvalidaException;
import model.Hospede;
import model.Reserva;
import repository.impl.HospedeRepository;
import repository.impl.ReservaRepository;
import service.HospedeService;
import service.ReservaService;

import java.util.List;
import java.util.Scanner;

import static constants.Constants.OPCAO_INVALIDA;
import static enumeration.QuartoTipo.*;

public class AppInterface {
    private Scanner sc = new Scanner(System.in);
    private HospedeService hospedeService = new HospedeService(new HospedeRepository());
    private ReservaService reservaService = new ReservaService(new ReservaRepository());
    private boolean isDone = false;

    public void menuUsuario() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("----------------");
        System.out.println("Nova Reserva -                    1");
        System.out.println("Buscar reserva por CPF -          2");
        System.out.println("Cancelar reserva -                3");
        System.out.println("Sair -                            4");
        System.out.print("Digite o número da opção que deseja realizar: ");
        processar(sc.nextLine());
    }

    public void menuAdmin() {
        System.out.println("MENU PRINCIPAL");
        System.out.println("----------------");
        System.out.println("Listar hóspedes -          1");
        System.out.println("Listar reservas -          2");
        System.out.print("Digite o número da opção que deseja realizar: ");
        processarAdmin(sc.nextLine());
    }

    public boolean getAppState() {
        return isDone;
    }

    private void processar(String escolha) {
        switch (escolha) {
            case "1" -> registrarUsuario();
            case "2" -> mostrarReservasPorCpf();
            case "3" -> cancelarReserva();
            case "4" -> isDone = true;
            default -> throw new OpcaoInvalidaException(OPCAO_INVALIDA);
        }
    }

    private void processarAdmin(String escolha) {
        switch (escolha) {
            case "1" -> listarHospedes();
            case "2" -> listarReservas();
            default -> throw new OpcaoInvalidaException(OPCAO_INVALIDA);
        }
    }

    private void registrarUsuario() {
        Hospede hospede = new Hospede();
        System.out.print("Nome: ");
        hospede.setNome(sc.nextLine());
        System.out.print("Sobrenome: ");
        hospede.setSobrenome(sc.nextLine());
        System.out.print("CPF: ");
        hospede.setCpf(sc.nextLine());
        hospedeService.createHospede(hospede);

        Reserva reserva = new Reserva(hospede);
        System.out.println("TIPO DO QUARTO");
        System.out.println("----------------");
        System.out.println("Standard(R$50) -          1");
        System.out.println("Luxo(R$100) -             2");
        System.out.println("Presidencial(R$300) -     3");
        reserva.setTipo(getQuartoTipo(sc.nextLine()));
        System.out.print("Quantas diárias? ");
        reserva.setNumeroDias(sc.nextInt());
        sc.nextLine();
        System.out.printf("Custo total: R$%.2f\nDeseja confirmar a reserva? (s / n) ", reserva.getTotalCost());
        String out = sc.nextLine();
        if (out.equalsIgnoreCase("n")) {
            menuUsuario();
            return;
        }
        reservaService.addReserva(reserva);
        System.out.println("Reserva realizada com sucesso!");
        retornar();
    }

    private void mostrarReservasPorCpf() {
        System.out.print("Digite seu CPF: ");
        List<Reserva> reservas = reservaService.pegarReservasPorCpf(sc.nextLine());
        reservas.forEach(reserva -> {
            System.out.printf("Reserva ID: %s\nNome do cliente: %s\nNúmero de diárias: %d\nQuarto: %s\nCusto total: %.2f\nReserva realizada dia: %s\n",
                    reserva.getId(), reserva.getHospede().getFullName(), reserva.getNumeroDias(), reserva.getTipo(), reserva.getTotalCost(), reserva.getDataDoCadastro());
            System.out.println("----------");
        });
        retornar();
    }

    private void cancelarReserva() {
        System.out.print("Digite o ID da reserva que deseja cancelar: ");
        reservaService.cancelaReserva(sc.nextLine());
        System.out.println("Reserva cancelada com sucesso!");
        retornar();
    }

    private QuartoTipo getQuartoTipo(String tipo) {
        QuartoTipo quarto;
        switch (tipo) {
            case "1" -> quarto = STANDARD;
            case "2" -> quarto = LUXO;
            case "3" -> quarto = PRESIDENCIAL;
            default -> throw new OpcaoInvalidaException(OPCAO_INVALIDA);
        }
        return quarto;
    }

    private void retornar() {
        System.out.println("Retornar para menu principal -   1");
        System.out.println("Sair -                           2");
        switch (sc.nextLine()) {
            case "1" -> menuUsuario();
            case "2" -> isDone = true;
            default -> throw new OpcaoInvalidaException(OPCAO_INVALIDA);
        }
    }

    private void listarHospedes() {
        hospedeService.getAll().forEach(hospede -> {
            System.out.printf("Nome: %s\nCPF: %s\n", hospede.getFullName(), hospede.getCpf());
        });
    }

    private void listarReservas() {
        reservaService.pegarTodas().forEach(reserva -> {
            System.out.printf("Reserva ID: %s\nNome do cliente: %s\nNúmero de diárias: %d\nQuarto: %s\nCusto total: %.2f\nReserva realizada dia: %s\n",
                    reserva.getId(), reserva.getHospede().getFullName(), reserva.getNumeroDias(), reserva.getTipo(), reserva.getTotalCost(), reserva.getDataDoCadastro());
            System.out.println("----------");
        });
    }
}
