package negocio;

public class TAdministrador extends TTrabajador {

    private static final long serialVersionUID = 1L;

    private double salario;
    private String idsEmpleados; // comma-separated ids

    public TAdministrador(int id, String nombre, String apellidos, String NIF, String jornada, double salario, String idsEmpleados) {
        super(id, nombre, apellidos, NIF, jornada, true);
        this.salario = salario;
        this.idsEmpleados = idsEmpleados;
    }

    public double getSalario() { return salario; }

    @Override
    public String getIdsEmpleados() { return idsEmpleados; }

    @Override
    public int getIdAdmin() { return -1; }

    @Override
    public String toString() {
        return super.toString().replace("\n", "") +
               ", Salario: " + salario + ", IdsEmpleados: " + idsEmpleados + "\n";
    }
}
