package hospitalveterinario;

public abstract class Paciente {

    public static final String INGRESADO = "INGRESADO";
    public static final String CUARENTENA = "CUARENTENA";
    public static final String EN_TRATAMIENTO = "EN_TRATAMIENTO";

    private String nombre;
    private double peso;
    private int edad;
    private String estado;
    private final double factorDosis;
    private double dosisAcumulada;
    private Jaula jaula;

    public Paciente(String nombre, double peso, int edad, double factorDosis) {
        setNombre(nombre);
        setPeso(peso);
        setEdad(edad);
        this.factorDosis = factorDosis;
        this.estado = INGRESADO;
        this.dosisAcumulada = 0;
        this.jaula = null;
    }

    public abstract String getTipo();
    public abstract String getZona();
    public abstract double getPesoMaximo();

    public String getTamanoNecesario() {
        if (peso < 5) {
            return "S";
        } else if (peso <= 25) {
            return "M";
        }
        return "L";
    }

    public double getDosisMaxima() {
        return peso * factorDosis;
    }

    public double getDosisMaximaAcumulada() {
        return 3 * getDosisMaxima();
    }

    public boolean puedeSerMedicado() {
        return true;
    }

    public void aplicarDosis(double mg) {
        dosisAcumulada += mg;
        estado = EN_TRATAMIENTO;
    }

    public boolean estaEnCuarentena() {
        return estado.equals(CUARENTENA);
    }

    public String getNombre() { return nombre; }
    public double getPeso() { return peso; }
    public int getEdad() { return edad; }
    public String getEstado() { return estado; }
    public double getFactorDosis() { return factorDosis; }
    public double getDosisAcumulada() { return dosisAcumulada; }
    public Jaula getJaula() { return jaula; }

    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) return false;
        this.nombre = nombre.trim();
        return true;
    }

    public boolean setPeso(double peso) {
        if (!(peso > 0) || peso > getPesoMaximo()) return false;
        this.peso = peso;
        return true;
    }

    public boolean setEdad(int edad) {
        if (edad < 0 || edad > 30) return false;
        this.edad = edad;
        return true;
    }

    public boolean setEstado(String estado) {
        if (estado == null) return false;
        if (estado.equals(INGRESADO) || estado.equals(CUARENTENA) || estado.equals(EN_TRATAMIENTO)) {
            this.estado = estado;
            return true;
        }
        return false;
    }

    public void setJaula(Jaula jaula) {
        this.jaula = jaula;
    }
}

