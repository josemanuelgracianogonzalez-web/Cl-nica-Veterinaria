
package hospitalveterinario;

import java.util.Scanner;

public class Main {

    private static double leerDouble(String texto) {
        try {
            double v = Double.parseDouble(texto);
            return Double.isInfinite(v) ? Double.NaN : v;
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private static int leerEntero(String texto) {
        try {
            return Integer.parseInt(texto);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String linea = sc.nextLine().trim();
            if (linea.isEmpty()) continue;
            String[] p = linea.split("\\s+");
            String cmd = p[0].toLowerCase();

            if (cmd.equals("salir")) break;

            switch (cmd) {
                case "ingresar":
                    System.out.println(procesarIngreso(hospital, p));
                    break;
                case "vacunar":
                    System.out.println(p.length == 2 ? hospital.vacunar(p[1]) : "ERROR: comando desconocido");
                    break;
                case "asignar":
                    System.out.println(p.length == 2 ? hospital.asignar(p[1]) : "ERROR: comando desconocido");
                    break;
                case "medicar":
                    if (p.length == 3) {
                        System.out.println(hospital.medicar(p[1], leerDouble(p[2])));
                    } else {
                        System.out.println(Hospital.ERROR_DATOS);
                    }
                    break;
                case "alta":
                    System.out.println(p.length == 2 ? hospital.alta(p[1]) : "ERROR: comando desconocido");
                    break;
                case "reporte":
                    System.out.println(hospital.reporte());
                    break;
                default:
                    System.out.println("ERROR: comando desconocido");
            }
        }
        sc.close();
    }

    private static String procesarIngreso(Hospital h, String[] p) {
        if (p.length < 2) return Hospital.ERROR_DATOS;
        String tipo = p[1].toLowerCase();
        if (tipo.equals("perro") && p.length == 7) {
            String vac = p[6].toLowerCase();
            if (!vac.equals("si") && !vac.equals("no")) return Hospital.ERROR_DATOS;
            return h.ingresarPerro(p[2], leerDouble(p[3]), leerEntero(p[4]), p[5], vac.equals("si"));
        } else if (tipo.equals("gato") && p.length == 5) {
            return h.ingresarGato(p[2], leerDouble(p[3]), leerEntero(p[4]));
        } else if (tipo.equals("ave") && p.length == 6) {
            return h.ingresarAve(p[2], leerDouble(p[3]), leerEntero(p[4]), leerDouble(p[5]));
        }
        return Hospital.ERROR_DATOS;
    }
}
