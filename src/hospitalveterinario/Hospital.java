package hospitalveterinario;

import java.util.Locale;

public class Hospital {

    public static final String ERROR_DATOS = "ERROR: datos invalidos";

    private Paciente[] pacientes = new Paciente[10];
    private int contador = 0;
    private Jaula[] camasVT = new Jaula[6];

    public Hospital() {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        camasVT[0] = new Jaula(1, "S", "A");
        camasVT[1] = new Jaula(2, "M", "A");
        camasVT[2] = new Jaula(3, "M", "A");
        camasVT[3] = new Jaula(4, "L", "A");
        camasVT[4] = new Jaula(5, "S", "B");
        camasVT[5] = new Jaula(6, "M", "B");
        pacientes[contador++] = new Perro("Rocky", 30.0, 4, "labrador", true);
        pacientes[contador++] = new Gato("Misifu", 4.0, 3, 0);
        pacientes[contador++] = new Aveexotica("Kiwi", 0.8, 2, 70);
    }

    private int buscarIndice(String nombre) {
        for (int i = 0; i < contador; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }

    private String verificarIngreso(String nombre, double peso, int edad, double pesoMax) {
        if (nombre == null || nombre.trim().isEmpty() || !(peso > 0) || peso > pesoMax
                || edad < 0 || edad > 30) {
            return ERROR_DATOS;
        }
        if (buscarIndice(nombre) != -1) return "ERROR: nombre duplicado";
        if (contador == pacientes.length) return "ERROR: hospital lleno";
        return null;
    }

    private String guardar(Paciente p) {
        pacientes[contador++] = p;
        if (p.estaEnCuarentena()) {
            return "OK: " + p.getNombre() + " ingresado en CUARENTENA";
        }
        return "OK: " + p.getNombre() + " ingresado";
    }

    private String formatoMg(double mg) {
        if (mg == Math.rint(mg)) return String.valueOf((long) mg);
        return String.valueOf(mg);
    }

    public String ingresarPerro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        if (raza == null || raza.trim().isEmpty()) return ERROR_DATOS;
        String error = verificarIngreso(nombre, peso, edad, 80);
        if (error != null) return error;
        return guardar(new Perro(nombre, peso, edad, raza, vacunado));
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        String error = verificarIngreso(nombre, peso, edad, 15);
        if (error != null) return error;
        return guardar(new Gato(nombre, peso, edad, 0));
    }

    public String ingresarAve(String nombre, double peso, int edad, double envergadura) {
        if (!(envergadura > 0)) return ERROR_DATOS;
        String error = verificarIngreso(nombre, peso, edad, 15);
        if (error != null) return error;
        return guardar(new Aveexotica(nombre, peso, edad, envergadura));
    }

    public String vacunar(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) return "ERROR: paciente no encontrado";
        Paciente p = pacientes[i];
        if (!(p instanceof Perro)) return "ERROR: solo aplica a perros";
        Perro perro = (Perro) p;
        if (perro.isVacunado()) return "ERROR: ya vacunado";
        perro.vacunar();
        return "OK: " + perro.getNombre() + " vacunado (pasa a INGRESADO)";
    }

    public String asignar(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) return "ERROR: paciente no encontrado";
        Paciente p = pacientes[i];
        if (p.estaEnCuarentena()) return "ERROR: paciente en cuarentena";
        if (p.getJaula() != null) return "ERROR: paciente ya tiene jaula";
        for (Jaula j : camasVT) {
            if (j.isLibre()
                    && j.getTamano().equals(p.getTamanoNecesario())
                    && j.getZona().equals(p.getZona())) {
                j.setOcupante(p);
                p.setJaula(j);
                return "OK: " + p.getNombre() + " asignado a jaula " + j.getNumero();
            }
        }
        return "ERROR: no hay jaula compatible";
    }

    public String medicar(String nombre, double mg) {
        int i = buscarIndice(nombre);
        if (i == -1) return "ERROR: paciente no encontrado";
        if (!(mg > 0)) return ERROR_DATOS;
        Paciente p = pacientes[i];
        if (p.estaEnCuarentena()) return "ERROR: paciente en cuarentena";
        if (p.getJaula() == null) return "ERROR: paciente sin jaula";
        if (!p.puedeSerMedicado()) return "ERROR: gato con estres alto";
        if (mg > p.getDosisMaxima()) return "ERROR: dosis excede el maximo";
        if (p.getDosisAcumulada() + mg > p.getDosisMaximaAcumulada()) return "ERROR: tope acumulado superado";
        p.aplicarDosis(mg);
        return "OK: " + p.getNombre() + " medicado con " + formatoMg(mg) + " mg";
    }

    public String alta(String nombre) {
        int i = buscarIndice(nombre);
        if (i == -1) return "ERROR: paciente no encontrado";
        Paciente p = pacientes[i];
        if (!p.getEstado().equals(Paciente.EN_TRATAMIENTO)) return "ERROR: paciente no apto para alta";
        if (p.getJaula() != null) {
            p.getJaula().setOcupante(null);
            p.setJaula(null);
        }
        for (int k = i; k < contador - 1; k++) {
            pacientes[k] = pacientes[k + 1];
        }
        pacientes[contador - 1] = null;
        contador--;
        return "OK: " + p.getNombre() + " dado de alta\nHasta pronto " + p.getNombre();
    }

    public String reporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- REPORTE ---\n");
        sb.append("Pacientes: ").append(contador).append("/10\n");
        for (int i = 0; i < contador; i++) {
            Paciente p = pacientes[i];
            String numJaula = (p.getJaula() == null) ? "-" : String.valueOf(p.getJaula().getNumero());
            sb.append(p.getNombre()).append(" | ").append(p.getTipo()).append(" | ")
                    .append(String.format(Locale.US, "%.1f", p.getPeso())).append(" kg | ")
                    .append(p.getEstado()).append(" | jaula: ").append(numJaula).append("\n");
        }
        int libres = 0;
        for (Jaula j : camasVT) {
            if (j.isLibre()) libres++;
        }
        sb.append("Jaulas libres: ").append(libres);
        return sb.toString();
    }

}
