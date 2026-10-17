package hospitalveterinario;

public class Gato extends Paciente{

    private int nivelEstres;

    public Gato(String nombre, double peso, int edad, int nivelEstres) {
        super(nombre, peso, edad, 6);
        setNivelEstres(nivelEstres);
    }

    @Override public String getTipo() { return "Gato"; }
    @Override public String getZona() { return "A"; }
    @Override public double getPesoMaximo() { return 15; }

    public int getNivelEstres() { return nivelEstres; }

    public boolean setNivelEstres(int nivel) {
        if (nivel < 0 || nivel > 10) return false;
        this.nivelEstres = nivel;
        return true;
    }

    @Override
    public boolean puedeSerMedicado() {
        return nivelEstres < 8;
    }

    @Override
    public void aplicarDosis(double mg) {
        super.aplicarDosis(mg);
        nivelEstres = Math.min(10, nivelEstres + 2);
    }
}
