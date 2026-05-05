package presentacion;

import integracion.DBSetup;

public class Main {
    public static void main(String[] args) {
        DBSetup.initializeDatabase();
        Controller controller = Controller.getInstance();
        controller.handleAction(Events.MOSTRAR_PANEL, null);
    }
}
