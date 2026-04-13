/*
 * ******************************************************************************************
 *  CURSO DE PROGRAMACION EN JAVA - AUTOR: Joaquin Rodriguez Llanes
 *  BLOQUE DE EJERCICIOS POR NIVELES - 1 DAM
 *  NIVEL 1 - SENCILLO
 * ******************************************************************************************
 */
/*
 * ==========================================================================================
 *  EJERCICIO NIVEL 1 | CLASES Y OBJETOS - PRODUCTO BASICO
 * ==========================================================================================
 *  En este ejercicio vas a practicar el patron mas basico de examen con objetos:
 *
 *  1. Crear una clase sencilla.
 *  2. Declarar atributos privados.
 *  3. Hacer un constructor.
 *  4. Crear getters.
 *  5. Crear algun metodo simple de negocio.
 *  6. Crear varios objetos en main y mostrarlos.
 *
 *  IDEAS CLAVE:
 *  - La clase se diseña arriba.
 *  - Los objetos se crean en main.
 *  - Primero construyes la clase, despues la usas.
 *  - Si toString() esta bien, visualizar el objeto es mucho mas facil.
 *
 *  RECOMENDACION:
 *  - Completa los TODO en orden.
 *  - Ejecuta varias veces.
 *  - No intentes hacerlo todo de golpe.
 * ==========================================================================================
 */

public class N1_Producto_Basico {

    public static void main(String[] args) {

        // * PASO 1: CREAR OBJETOS
        // ? Cuando la clase este terminada, aqui podremos instanciar productos.

        // TODO 9: Crea un producto llamado "Teclado", con precio 25.99 y stock 8
        // Producto p1 = ...

        // TODO 10: Crea un producto llamado "Raton", con precio 14.50 y stock 0
        // Producto p2 = ...

        // TODO 11: Crea un producto llamado "Monitor", con precio 179.95 y stock 3
        // Producto p3 = ...

        // * PASO 2: MOSTRAR OBJETOS
        // ? Si toString() esta bien implementado, esto dara mucha informacion.

        // TODO 12: Muestra p1, p2 y p3 por pantalla

        // * PASO 3: PROBAR METODOS DE NEGOCIO
        // ? Ahora comprobaremos si hay stock y actualizaremos cantidades.

        // TODO 13: Muestra si p1 tiene stock
        // TODO 14: Muestra si p2 tiene stock

        // TODO 15: Suma 5 unidades al stock de p2
        // TODO 16: Resta 2 unidades al stock de p3

        // TODO 17: Vuelve a mostrar p2 y p3 tras los cambios

        // * PASO 4: MENSAJE FINAL
        // TODO 18: Escribe una frase final indicando que el ejercicio esta probado
    }
}

// * CLASE AUXILIAR DEL EJERCICIO
// ? En muchos examenes aparece una clase sencilla como esta.
class Producto {

    // TODO 1: Declara los atributos privados:
    // - nombre (String)
    // - precio (double)
    // - stock (int)

    // TODO 2: Implementa el constructor con los 3 parametros
    public Producto(String nombre, double precio, int stock) {
        // TODO
    }

    // * GETTERS
    // TODO 3: Crea getNombre()
    // TODO 4: Crea getPrecio()
    // TODO 5: Crea getStock()

    // * METODOS SENCILLOS DE NEGOCIO
    // ? Estos metodos son cortos y muy tipicos en ejercicios iniciales.

    // TODO 6: Crea un metodo hayStock() que devuelva true si stock > 0

    // TODO 7: Crea un metodo anadirStock(int cantidad)
    // ! Suma la cantidad recibida al stock actual

    // TODO 8: Crea un metodo quitarStock(int cantidad)
    // ! Resta la cantidad recibida al stock actual

    // * VISUALIZACION DEL OBJETO
    // ? Muy recomendable en examen porque ayuda a comprobar si todo va bien.
    @Override
    public String toString() {
        return "Producto{}";
    }
}
