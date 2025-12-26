import exception.HotelCapacityException;
import exception.InvalidReservaException;
import exception.OpcaoInvalidaException;
import menu.AppInterface;

void main() {
    AppInterface app = new AppInterface();
    while(!app.getAppState()) {
        try {
            app.menuUsuario();
        }catch (OpcaoInvalidaException | InvalidReservaException | HotelCapacityException exp) {
            System.out.println(exp.getMessage());
        }
    }
}
