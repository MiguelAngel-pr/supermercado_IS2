package presentacion;

public abstract class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if (instance == null) instance = new ControllerImp();
        return instance;
    }

    public abstract void handleAction(Events event, Object data);
}
