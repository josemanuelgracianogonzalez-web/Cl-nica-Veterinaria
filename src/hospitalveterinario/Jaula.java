package hospitalveterinario;

public class Jaula {

    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona) {
        setNumero(numero);
        setTamano(tamano);
        setZona(zona);
        this.ocupante = null;
    }

    public int getNumero() { return numero; }
    public String getTamano() { return tamano; }
    public String getZona() { return zona; }
    public Paciente getOcupante() { return ocupante; }

    public boolean isLibre() {
        return ocupante == null;
    }

    public boolean setNumero(int numero) {
        if (numero <= 0) return false;
        this.numero = numero;
        return true;
    }

    public boolean setTamano(String tamano) {
        if (tamano == null || !(tamano.equals("S") || tamano.equals("M") || tamano.equals("L"))) return false;
        this.tamano = tamano;
        return true;
    }

    public boolean setZona(String zona) {
        if (zona == null || !(zona.equals("A") || zona.equals("B"))) return false;
        this.zona = zona;
        return true;
    }

    public void setOcupante(Paciente ocupante) {
        this.ocupante = ocupante;
    }
}
