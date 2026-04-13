/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 2 - INTERMEDIO - SOLUCION
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  SOLUCION | ALUMNOS + ARRAY DE OBJETOS + METODOS
 * ==========================================================================================
 *  Esta solucion no solo enseña "que escribir", sino tambien "como pensar":
 *
 *  1. Primero construyo la clase del objeto.
 *  2. Despues creo varios objetos concretos.
 *  3. Luego los guardo en un array.
 *  4. Finalmente proceso ese array con metodos.
 *
 *  IDEA CLAVE:
 *  - La clase AlumnoCursoSolucion representa UN alumno.
 *  - Los metodos estaticos representan operaciones sobre MUCHOS alumnos.
 *
 *  Ese cambio de mentalidad es muy importante en 1 DAM.
 * ==========================================================================================
 */

public class N2_Alumno_Array_Metodos_Solucion {

    public static void main(String[] args) {

        // * PASO 1: CREAR EL ARRAY
        // ? El array sera el contenedor donde guardaremos varios objetos alumno.
        // ? No guarda texto ni numeros sueltos: guarda referencias a objetos.
        AlumnoCursoSolucion[] alumnos = new AlumnoCursoSolucion[5];

        // * PASO 2: CREAR Y GUARDAR OBJETOS
        // ? Cada posicion del array almacenara un alumno distinto.
        // ! Aqui es donde el alumno debe ver claramente la diferencia entre:
        // ! - la clase (molde)
        // ! - el objeto concreto
        alumnos[0] = new AlumnoCursoSolucion("Ana", 8.5, false);
        alumnos[1] = new AlumnoCursoSolucion("Luis", 4.0, true);
        alumnos[2] = new AlumnoCursoSolucion("Marta", 6.75, false);
        alumnos[3] = new AlumnoCursoSolucion("Pablo", 9.25, false);

        // * PASO 3: MOSTRAR ALUMNOS
        // ? Este es el primer metodo recomendable porque sirve para comprobar:
        // ? - que el array esta bien creado
        // ? - que los objetos se han guardado bien
        // ? - que toString() funciona
        System.out.println("=== LISTADO DE ALUMNOS ===");
        mostrarAlumnos(alumnos);

        // * PASO 4: CONTAR APROBADOS
        // ? Este metodo entrena un patron muy tipico:
        // ? contador + recorrido + condicion + devolucion
        int aprobados = contarAprobados(alumnos);
        System.out.println("\nAprobados: " + aprobados);

        // * PASO 5: CALCULAR MEDIA
        // ? Aqui aparece otro patron importante:
        // ? suma + contador + division final
        double media = calcularMediaNotas(alumnos);
        System.out.println("Media de notas: " + media);

        // * PASO 6: BUSCAR POR NOMBRE
        // ? Buscar significa recorrer hasta encontrar el elemento correcto.
        AlumnoCursoSolucion encontrado = buscarPorNombre(alumnos, "Marta");
        System.out.println("\nAlumno buscado:");
        System.out.println(encontrado);

        // * PASO 7: OBTENER EL DE MAYOR NOTA
        // ? Este metodo obliga a comparar objetos entre si usando uno como referencia.
        AlumnoCursoSolucion mejor = obtenerMayorNota(alumnos);
        System.out.println("\nAlumno con mayor nota:");
        System.out.println(mejor);

        System.out.println("\nEjercicio intermedio probado correctamente.");
    }

    // * METODO 1: MOSTRAR TODOS LOS ALUMNOS
    // ? Este metodo recorre el array completo y muestra cada alumno existente.
    // ! No se puede imprimir directamente cada posicion sin comprobar null.
    public static void mostrarAlumnos(AlumnoCursoSolucion[] alumnos) {
        for (int i = 0; i < alumnos.length; i++) {

            // ? Si la posicion esta vacia, no intentamos usarla.
            if (alumnos[i] != null) {
                System.out.println(alumnos[i]);
            }
        }
    }

    // * METODO 2: CONTAR APROBADOS
    // ? Patron mental:
    // ? 1. Empiezo un contador en 0
    // ? 2. Recorro el array
    // ? 3. Si un alumno cumple la condicion, sumo 1
    // ? 4. Devuelvo el total
    public static int contarAprobados(AlumnoCursoSolucion[] alumnos) {
        int contador = 0;

        for (int i = 0; i < alumnos.length; i++) {

            // ? Solo trabajo con posiciones ocupadas.
            if (alumnos[i] != null && alumnos[i].estaAprobado()) {
                contador++;
            }
        }

        return contador;
    }

    // * METODO 3: CALCULAR MEDIA DE NOTAS
    // ? Aqui no basta con contar.
    // ? Tenemos que acumular las notas y, al final, dividir.
    public static double calcularMediaNotas(AlumnoCursoSolucion[] alumnos) {
        double suma = 0;
        int contador = 0;

        for (int i = 0; i < alumnos.length; i++) {

            // ? Si existe un alumno en esa posicion, usamos su nota.
            if (alumnos[i] != null) {
                suma += alumnos[i].getNotaFinal();
                contador++;
            }
        }

        // ! Si no hay alumnos cargados, evitamos dividir entre 0.
        if (contador == 0) {
            return 0;
        }

        return suma / contador;
    }

    // * METODO 4: BUSCAR POR NOMBRE
    // ? Buscar es recorrer uno a uno hasta encontrar coincidencia.
    // ? En cuanto encontramos el alumno correcto, devolvemos la referencia.
    public static AlumnoCursoSolucion buscarPorNombre(AlumnoCursoSolucion[] alumnos, String nombreBuscado) {
        for (int i = 0; i < alumnos.length; i++) {

            // ? equalsIgnoreCase() permite comparar ignorando mayusculas/minusculas.
            if (alumnos[i] != null && alumnos[i].getNombre().equalsIgnoreCase(nombreBuscado)) {
                return alumnos[i];
            }
        }

        // ? Si terminamos el recorrido sin encontrar nada, devolvemos null.
        return null;
    }

    // * METODO 5: OBTENER EL ALUMNO CON MAYOR NOTA
    // ? Este metodo cuesta mas porque no busca una coincidencia exacta.
    // ? Tiene que ir comparando cada alumno con el mejor encontrado hasta ese momento.
    public static AlumnoCursoSolucion obtenerMayorNota(AlumnoCursoSolucion[] alumnos) {
        AlumnoCursoSolucion mejorAlumno = null;

        for (int i = 0; i < alumnos.length; i++) {
            if (alumnos[i] != null) {

                // ? Si aun no hay mejor alumno, el primero valido pasa a serlo.
                // ? Si ya hay uno, comparamos las notas.
                if (mejorAlumno == null || alumnos[i].getNotaFinal() > mejorAlumno.getNotaFinal()) {
                    mejorAlumno = alumnos[i];
                }
            }
        }

        return mejorAlumno;
    }
}

// * CLASE AUXILIAR DE LA SOLUCION
// ? Representa a un solo alumno del curso.
class AlumnoCursoSolucion {

    // * ATRIBUTOS PRIVADOS
    // ? Guardan el estado interno del objeto.
    private String nombre;
    private double notaFinal;
    private boolean repetidor;

    // * CONSTRUCTOR
    // ? Recibe los datos iniciales del alumno y los guarda en el objeto.
    // ! this sirve para distinguir atributo y parametro.
    public AlumnoCursoSolucion(String nombre, double notaFinal, boolean repetidor) {
        this.nombre = nombre;
        this.notaFinal = notaFinal;
        this.repetidor = repetidor;
    }

    // * GETTERS
    // ? Devuelven informacion del objeto sin modificarlo.
    public String getNombre() {
        return nombre;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public boolean isRepetidor() {
        return repetidor;
    }

    // * METODO DE NEGOCIO 1
    // ? Responde a una pregunta: ¿esta aprobado este alumno?
    public boolean estaAprobado() {
        return notaFinal >= 5;
    }

    // * METODO DE NEGOCIO 2
    // ? Convierte el boolean repetidor en un texto mas legible.
    // ? Esto ayuda mucho a la hora de mostrar el objeto por pantalla.
    public String tipoAlumno() {
        if (repetidor) {
            return "Repetidor";
        }
        return "No repetidor";
    }

    // * TO STRING
    // ? Muy util para imprimir el alumno completo de forma clara.
    @Override
    public String toString() {
        return "AlumnoCurso{nombre='" + nombre + "', notaFinal=" + notaFinal
                + ", tipo='" + tipoAlumno() + "'}";
    }
}
