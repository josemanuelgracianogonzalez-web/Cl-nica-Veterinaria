package hospitalveterinario;

public class Aveexotica extends Paciente{

    private double envergadura;

    public Aveexotica(String nombre, double peso, int edad, double envergadura) {
        super(nombre, peso, edad, 3);
        setEnvergadura(envergadura);
    }

    @Override public String getTipo() { return "Ave"; }
    @Override public String getZona() { return "B"; }
    @Override public double getPesoMaximo() { return 15; }

    public double getEnvergadura() { return envergadura; }

    public boolean setEnvergadura(double envergadura) {
        if (!(envergadura > 0)) return false;
        this.envergadura = envergadura;
        return true;
    }

    @Override
    public String getTamanoNecesario() {
        String tamano = super.getTamanoNecesario();
        if (envergadura > 60 && tamano.equals("S")) {
            return "M";
        }
        return tamano;
    }
}
