/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 2 - INTERMEDIO
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  EJERCICIO NIVEL 2 | ALUMNOS + ARRAY DE OBJETOS + METODOS
 * ==========================================================================================
 *  Este ejercicio representa uno de los patrones mas tipicos de examen en 1 DAM:
 *
 *  - una clase sencilla
 *  - varios objetos
 *  - un array de objetos
 *  - metodos estaticos que recorren ese array
 *
 *  AQUI YA NO SOLO CREAS OBJETOS.
 *  Ahora tambien tienes que procesarlos.
 *
 *  VAS A PRACTICAR:
 *  1. clase con atributos, constructor, getters y toString()
 *  2. objeto con una regla simple: saber si esta aprobado
 *  3. creacion de varios objetos en main
 *  4. guardado en array
 *  5. metodos para mostrar, contar, calcular y buscar
 *
 *  RECOMENDACION DIDACTICA:
 *  - Completa primero la clase.
 *  - Despues crea el array y los objetos.
 *  - Haz primero mostrarAlumnos().
 *  - Luego contarAprobados().
 *  - Luego calcularMediaNotas().
 *  - Despues buscarPorNombre() y obtenerMayorNota().
 * ==========================================================================================
 */

public class N2_Alumno_Array_Metodos {

    public static void main(String[] args) {

        // * PASO 1: CREAR EL ARRAY DE OBJETOS
        // ? Reservamos espacio para varios alumnos.

        // TODO 9: Crea un array de AlumnoCurso con capacidad para 5 posiciones

        // * PASO 2: CREAR OBJETOS Y GUARDARLOS EN EL ARRAY
        // ? Cada objeto representa un alumno con nombre, nota y si es repetidor.

        // TODO 10: Crea un alumno llamado "Ana", nota 8.5, no repetidora
        // TODO 11: Guarda ese alumno en la posicion 0 del array

        // TODO 12: Crea un alumno llamado "Luis", nota 4.0, repetidor
        // TODO 13: Guarda ese alumno en la posicion 1 del array

        // TODO 14: Crea un alumno llamado "Marta", nota 6.75, no repetidora
        // TODO 15: Guarda ese alumno en la posicion 2 del array

        // TODO 16: Crea un alumno llamado "Pablo", nota 9.25, no repetidor
        // TODO 17: Guarda ese alumno en la posicion 3 del array

        // * PASO 3: MOSTRAR DATOS
        // ? Este suele ser el primer metodo bueno para probar si todo va correcto.

        // TODO 18: Llama al metodo mostrarAlumnos(alumnos)

        // * PASO 4: CONTAR APROBADOS
        // TODO 19: Guarda en una variable cuantos alumnos estan aprobados
        // TODO 20: Muestra ese resultado por pantalla

        // * PASO 5: CALCULAR MEDIA
        // TODO 21: Guarda en una variable la media de notas
        // TODO 22: Muestra esa media por pantalla

        // * PASO 6: BUSQUEDA POR NOMBRE
        // TODO 23: Busca a "Marta" usando buscarPorNombre(alumnos, "Marta")
        // TODO 24: Muestra el alumno encontrado

        // * PASO 7: OBTENER EL ALUMNO CON MAYOR NOTA
        // TODO 25: Guarda en una variable el alumno con mayor nota
        // TODO 26: Muestra ese alumno por pantalla

        // * PASO 8: MENSAJE FINAL
        // TODO 27: Escribe una frase final indicando que el nivel intermedio ha sido probado
    }

    // * METODO 1: MOSTRAR TODOS LOS ALUMNOS DEL ARRAY
    // ? Recorre el array y muestra los elementos que no sean null.
    public static void mostrarAlumnos(AlumnoCurso[] alumnos) {
        // TODO 13:
        // 1. Recorre el array con un for
        // 2. Comprueba si la posicion actual no es null
        // 3. Muestra el alumno de esa posicion
    }

    // * METODO 2: CONTAR APROBADOS
    // ? Debe devolver cuantos alumnos tienen nota >= 5.
    public static int contarAprobados(AlumnoCurso[] alumnos) {
        // TODO 14:
        // 1. Crea un contador a 0
        // 2. Recorre el array
        // 3. Si el alumno no es null y esta aprobado, suma 1
        // 4. Devuelve el contador
        return 0;
    }

    // * METODO 3: CALCULAR MEDIA DE NOTAS
    // ? Debe devolver la media de las notas de los alumnos que existan en el array.
    public static double calcularMediaNotas(AlumnoCurso[] alumnos) {
        // TODO 15:
        // 1. Crea una variable suma y otra contador
        // 2. Recorre el array
        // 3. Si el alumno no es null, suma su nota y aumenta el contador
        // 4. Si contador es 0, devuelve 0
        // 5. Si no, devuelve suma / contador
        return 0;
    }

    // * METODO 4: BUSCAR UN ALUMNO POR NOMBRE
    // ? Si encuentra el alumno, lo devuelve. Si no, devuelve null.
    public static AlumnoCurso buscarPorNombre(AlumnoCurso[] alumnos, String nombreBuscado) {
        // TODO 16:
        // 1. Recorre el array
        // 2. Comprueba si la posicion no es null
        // 3. Compara el nombre del alumno con nombreBuscado
        // 4. Si coincide, devuelve ese alumno
        // 5. Si termina el bucle sin encontrarlo, devuelve null
        return null;
    }

    // * METODO 5: OBTENER EL ALUMNO CON MAYOR NOTA
    // ? Debe devolver la referencia al alumno con nota mas alta.
    public static AlumnoCurso obtenerMayorNota(AlumnoCurso[] alumnos) {
        // TODO 17:
        // 1. Crea una variable mejorAlumno inicialmente a null
        // 2. Recorre el array
        // 3. Si la posicion no es null:
        //    - si mejorAlumno es null, actualizalo
        //    - si la nota actual es mayor que la de mejorAlumno, actualizalo
        // 4. Devuelve mejorAlumno
        return null;
    }
}

// * CLASE DEL EJERCICIO
// ? Representa a un alumno con nombre, nota final y si es repetidor.
class AlumnoCurso {

    // TODO 1: Declara los atributos privados:
    // - nombre (String)
    // - notaFinal (double)
    // - repetidor (boolean)

    // TODO 2: Implementa el constructor con esos 3 parametros
    public AlumnoCurso(String nombre, double notaFinal, boolean repetidor) {
        // TODO
    }

    // TODO 3: Crea getNombre()
    // TODO 4: Crea getNotaFinal()
    // TODO 5: Crea isRepetidor()

    // * METODO DE NEGOCIO
    // TODO 6: Crea estaAprobado() que devuelva true si notaFinal >= 5

    // * METODO AUXILIAR
    // TODO 7: Crea tipoAlumno() que devuelva:
    // - "Repetidor" si repetidor es true
    // - "No repetidor" si repetidor es false

    // * VISUALIZACION
    // TODO 8: Mejora toString() para que muestre nombre, nota y tipo de alumno
    @Override
    public String toString() {
        return "AlumnoCurso{}";
    }
}
