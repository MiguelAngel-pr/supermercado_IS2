package negocio;

public class TEmpleado extends TTrabajador {

    private static final long serialVersionUID = 1L;

    private double salario;
    private int idAdmin;

    public TEmpleado(int id, String nombre, String apellidos, String NIF, String jornada, boolean activo, double salario, int idAdmin) {
        super(id, nombre, apellidos, NIF, jornada, false, activo);
        this.salario = salario;
        this.idAdmin = idAdmin;
    }

    public double getSalario() { return salario; }

    @Override
    public String getIdsEmpleados() { return ""; }

    @Override
    public int getIdAdmin() { return idAdmin; }

    @Override
    public String toString() {
        return super.toString().replace("\n", "") +
               ", Salario: " + salario + ", IdAdmin: " + idAdmin + "\n";
    }
}
