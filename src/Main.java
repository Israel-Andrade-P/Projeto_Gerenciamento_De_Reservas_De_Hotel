import exception.*;
import menu.AppInterface;

void main() {
    AppInterface app = new AppInterface();
    while(!app.getAppState()) {
        try {
            app.menuUsuario();
        } catch (OpcaoInvalidaException | InvalidReservaException |
                 ReservaNotFoundException | HospedeAlreadyExistsException | HotelCapacityException exp) {
            System.out.println(exp.getMessage());
        }
    }
    app.setAppState();
    //Menu disponível apenas para admins
    while ((!app.getAppState())) {
        app.menuAdmin();
    }

}
