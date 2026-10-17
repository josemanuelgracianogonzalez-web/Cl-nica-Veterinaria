package hospitalveterinario;

public class Perro extends Paciente{

    private String raza;
    private boolean vacunado;

    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        super(nombre, peso, edad, 10);
        setRaza(raza);
        this.vacunado = vacunado;
        if (!vacunado) {
            setEstado(CUARENTENA);
        }
    }

    @Override public String getTipo() { return "Perro"; }
    @Override public String getZona() { return "A"; }
    @Override public double getPesoMaximo() { return 80; }

    public String getRaza() { return raza; }
    public boolean isVacunado() { return vacunado; }

    public boolean setRaza(String raza) {
        if (raza == null || raza.trim().isEmpty()) return false;
        this.raza = raza.trim();
        return true;
    }

    public void vacunar() {
        vacunado = true;
        setEstado(INGRESADO);
    }
}
