Qué se hizo:

Sistema de consola en Java para gestionar los pacientes de una veterinaria. Permite ingresar perros, gatos y aves exóticas, vacunar, asignar jaula, medicar, dar de alta y mostrar un reporte. Todos los datos se guardan en vectores de capacidad fija, sin usar ArrayList.

Cómo se hizo:

El proyecto está en el package hospitalveterinario con 7 clases:

Paciente: clase padre abstracta. Perro, Gato y Aveexotica: clases hijas que heredan de Paciente. Jaula: número, tamaño, zona y ocupante. Hospital: vectores, datos iniciales y reglas del negocio. Main: único lugar con Scanner y System.out.

Los atributos son private y los setters validan, rechazando valores inválidos sin modificar nada. La lógica común está solo en Paciente, y las hijas la reutilizan con super(...). Hospital devuelve los mensajes y Main los imprime, manteniendo separados el dominio y la consola. Los vectores usan un contador para controlar las posiciones ocupadas, y el alta compacta el vector. Al iniciar, cargarDatosIniciales() crea las 6 jaulas y los 3 pacientes.

Temas desconocidos usados

Polimorfismo: el vector Paciente[] guarda perros, gatos y aves, y Java ejecuta el método del objeto real. @Override y super.metodo(): @Override marca los métodos que las hijas redefinen, y super.metodo() reutiliza la versión del padre (por ejemplo, el Gato sube su estrés después de aplicar la dosis común). instanceof y casting: comprueban que un paciente sea Perro para usar sus métodos propios, solo en vacunar. static final: define constantes, como los estados y el mensaje de datos inválidos. split("\s+"): separa cada comando escrito en palabras. try/catch: evita que el programa se caiga si se escribe texto donde va un número. equalsIgnoreCase: compara los nombres sin distinguir mayúsculas. String.format(Locale.US, ...) y StringBuilder: muestran el peso con un decimal y arman el reporte por partes.

subido a la rama master.

Se usó asistencia de IA (Claude, de Anthropic) para el diseño del código y la explicación de los conceptos nuevos. El código fue revisado, probado y subido a GitHub por mí.
