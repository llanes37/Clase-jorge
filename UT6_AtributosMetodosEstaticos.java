/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 6: ATRIBUTOS Y MÉTODOS ESTÁTICOS EN JAVA
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.Scanner;              // ? Para leer datos del usuario
import java.util.ArrayList;            // ? Para almacenar objetos en lista

public class UT6_AtributosMetodosEstaticos {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // 🛠️ Objeto para leer entradas del usuario
        int opcion;                          // 🎛️ Variable para controlar el menú

        // * MENÚ PRINCIPAL - Permite al usuario elegir qué concepto explorar
        do {
            System.out.println("\n⚡ MENÚ - ATRIBUTOS Y MÉTODOS ESTÁTICOS:");
            System.out.println("1. 📊 Ver diferencia entre STATIC y NO STATIC");
            System.out.println("2. 🔢 Contador de instancias (atributo estático)");
            System.out.println("3. 📐 Usar constantes estáticas (final static)");
            System.out.println("4. 🧮 Métodos estáticos de utilidad (calculadora)");
            System.out.println("5. 🏗️ Bloque estático de inicialización");
            System.out.println("6. 🎯 Patrón Singleton (solo una instancia)");
            System.out.println("7. 📋 Resumen teórico completo");
            System.out.println("0. Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();        // 📥 Lee la opción seleccionada
            sc.nextLine();                // 🧹 Limpia el buffer tras leer número

            switch (opcion) {
                case 1 -> ejemploDiferenciaStaticNoStatic();
                case 2 -> ejemploContadorInstancias();
                case 3 -> ejemploConstantesEstaticas();
                case 4 -> ejemploMetodosEstaticosUtilidad(sc);
                case 5 -> ejemploBloqueEstatico();
                case 6 -> ejemploPatronSingleton();
                case 7 -> mostrarResumenTeorico();
                case 0 -> System.out.println("👋 ¡Saliendo del programa!");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0); // 🔁 Repite mientras no se elija salir

        sc.close(); // 🔐 Cerramos el Scanner al terminar
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 1: DIFERENCIA ENTRE STATIC Y NO STATIC
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: ¿Qué significa STATIC?
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? STATIC = Pertenece a la CLASE, no a cada objeto individual
    // ? NO STATIC = Pertenece a cada OBJETO (instancia) de la clase
    // ?
    // ? Imagina una clase "Alumno":
    // ?   - nombreAlumno → NO STATIC (cada alumno tiene su propio nombre)
    // ?   - contadorAlumnos → STATIC (es compartido por TODOS los alumnos)
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploDiferenciaStaticNoStatic() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("📊 EJEMPLO 1: DIFERENCIA ENTRE STATIC Y NO STATIC");
        System.out.println("═".repeat(70));

        // ? Creamos varios objetos de la clase Coche
        Coche coche1 = new Coche("Toyota", "Rojo");
        Coche coche2 = new Coche("Ford", "Azul");
        Coche coche3 = new Coche("BMW", "Negro");

        System.out.println("\n🚗 Hemos creado 3 coches diferentes:");
        System.out.println("──────────────────────────────────────");

        // ? Cada coche tiene sus PROPIOS atributos (no static)
        System.out.println("Coche 1: " + coche1.marca + " - " + coche1.color);
        System.out.println("Coche 2: " + coche2.marca + " - " + coche2.color);
        System.out.println("Coche 3: " + coche3.marca + " - " + coche3.color);

        // ? Pero TODOS comparten el mismo contador (static)
        System.out.println("\n📊 Atributo ESTÁTICO (compartido por todos):");
        System.out.println("──────────────────────────────────────");
        System.out.println("Total de coches creados: " + Coche.totalCoches);

        // ? Se accede con NombreClase.atributo (no con objeto.atributo)
        System.out.println("\n💡 IMPORTANTE:");
        System.out.println("   - Los atributos NO STATIC son únicos para cada objeto");
        System.out.println("   - Los atributos STATIC son compartidos por TODOS los objetos");
        System.out.println("   - Se accede a STATIC con: NombreClase.atributo");

        // ! ✅ TAREA ALUMNO:
        // * Crea 2 coches más y comprueba cómo cambia el contador totalCoches
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 2: CONTADOR DE INSTANCIAS
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Contador de instancias
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? Un uso clásico de atributos estáticos es contar cuántos objetos se han creado.
    // ? Cada vez que se crea un objeto, el constructor incrementa el contador.
    // ? Como es STATIC, el valor se mantiene entre todas las instancias.
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploContadorInstancias() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("🔢 EJEMPLO 2: CONTADOR DE INSTANCIAS CON STATIC");
        System.out.println("═".repeat(70));

        // ? Reseteamos el contador para esta demo
        Alumno.resetearContador();

        System.out.println("\n📚 Creando alumnos para el curso...\n");

        // ? Cada vez que creamos un Alumno, el contador aumenta automáticamente
        Alumno a1 = new Alumno("María García", 20);
        System.out.println("✅ Creado: " + a1.getNombre() + " (ID: " + a1.getId() + ")");
        System.out.println("   Total alumnos hasta ahora: " + Alumno.getTotalAlumnos());

        Alumno a2 = new Alumno("Juan López", 22);
        System.out.println("✅ Creado: " + a2.getNombre() + " (ID: " + a2.getId() + ")");
        System.out.println("   Total alumnos hasta ahora: " + Alumno.getTotalAlumnos());

        Alumno a3 = new Alumno("Ana Martínez", 19);
        System.out.println("✅ Creado: " + a3.getNombre() + " (ID: " + a3.getId() + ")");
        System.out.println("   Total alumnos hasta ahora: " + Alumno.getTotalAlumnos());

        System.out.println("\n📊 RESUMEN:");
        System.out.println("──────────────────────────────────────");
        System.out.println("Total de alumnos matriculados: " + Alumno.getTotalAlumnos());
        System.out.println("Cada alumno tiene un ID único asignado automáticamente");

        // ! ✅ TAREA ALUMNO:
        // * Añade un método estático getPromedioEdad() que calcule la media de edad
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 3: CONSTANTES ESTÁTICAS (final static)
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Constantes con final static
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? final = El valor NO puede cambiar después de asignarse
    // ? static = Pertenece a la clase, no a cada objeto
    // ? final static = CONSTANTE de la clase (convención: MAYÚSCULAS_CON_GUIONES)
    // ?
    // ? Ejemplos conocidos: Math.PI, Integer.MAX_VALUE, Color.RED
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploConstantesEstaticas() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("📐 EJEMPLO 3: CONSTANTES ESTÁTICAS (final static)");
        System.out.println("═".repeat(70));

        System.out.println("\n🔢 Constantes matemáticas (clase Constantes):");
        System.out.println("──────────────────────────────────────");
        System.out.println("PI = " + Constantes.PI);
        System.out.println("E (número de Euler) = " + Constantes.E);
        System.out.println("Gravedad terrestre = " + Constantes.GRAVEDAD + " m/s²");

        System.out.println("\n🎨 Constantes de configuración:");
        System.out.println("──────────────────────────────────────");
        System.out.println("Nombre de la aplicación: " + Constantes.NOMBRE_APP);
        System.out.println("Versión: " + Constantes.VERSION);
        System.out.println("Máx. usuarios permitidos: " + Constantes.MAX_USUARIOS);

        // ? Ejemplo práctico: calcular área de círculo
        double radio = 5.0;
        double area = Constantes.PI * radio * radio;
        System.out.println("\n🔵 Cálculo usando constantes:");
        System.out.println("──────────────────────────────────────");
        System.out.println("Radio del círculo: " + radio);
        System.out.println("Área = PI * r² = " + area);

        System.out.println("\n💡 VENTAJAS de usar constantes:");
        System.out.println("   1. El valor está en UN solo lugar (fácil de cambiar)");
        System.out.println("   2. Código más legible (GRAVEDAD vs 9.81)");
        System.out.println("   3. El compilador avisa si intentas cambiar el valor");

        // ! ✅ TAREA ALUMNO:
        // * Añade una constante VELOCIDAD_LUZ = 299792458 y úsala en un cálculo
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 4: MÉTODOS ESTÁTICOS DE UTILIDAD
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Métodos estáticos
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? Un método STATIC se puede llamar SIN crear un objeto de la clase.
    // ? Se llama con: NombreClase.nombreMetodo()
    // ?
    // ? Son ideales para:
    // ?   - Funciones de utilidad (Math.sqrt(), Integer.parseInt())
    // ?   - Operaciones que no dependen del estado de un objeto
    // ?   - Métodos que trabajan solo con sus parámetros
    // ?
    // ? ⚠️ IMPORTANTE: Un método static NO puede usar atributos NO static de la clase
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploMetodosEstaticosUtilidad(Scanner sc) {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("🧮 EJEMPLO 4: MÉTODOS ESTÁTICOS DE UTILIDAD");
        System.out.println("═".repeat(70));

        System.out.println("\n📊 Nuestra clase Calculadora tiene métodos estáticos:");
        System.out.println("──────────────────────────────────────");
        System.out.println("Se llaman con Calculadora.metodo() - ¡SIN crear objeto!");

        // ? Usamos métodos estáticos SIN crear un objeto Calculadora
        System.out.println("\n🔢 Ejemplos de uso:");
        System.out.println("Calculadora.sumar(10, 5) = " + Calculadora.sumar(10, 5));
        System.out.println("Calculadora.restar(10, 5) = " + Calculadora.restar(10, 5));
        System.out.println("Calculadora.multiplicar(10, 5) = " + Calculadora.multiplicar(10, 5));
        System.out.println("Calculadora.dividir(10, 5) = " + Calculadora.dividir(10, 5));
        System.out.println("Calculadora.esParImpar(7) = " + Calculadora.esParImpar(7));
        System.out.println("Calculadora.esPrimo(17) = " + Calculadora.esPrimo(17));
        System.out.println("Calculadora.factorial(5) = " + Calculadora.factorial(5));

        // ? Mini calculadora interactiva
        System.out.println("\n🎮 PRUEBA TÚ - Introduce dos números:");
        System.out.print("   Primer número: ");
        double num1 = sc.nextDouble();
        System.out.print("   Segundo número: ");
        double num2 = sc.nextDouble();

        System.out.println("\n📊 Resultados:");
        System.out.println("   " + num1 + " + " + num2 + " = " + Calculadora.sumar(num1, num2));
        System.out.println("   " + num1 + " - " + num2 + " = " + Calculadora.restar(num1, num2));
        System.out.println("   " + num1 + " * " + num2 + " = " + Calculadora.multiplicar(num1, num2));
        if (num2 != 0) {
            System.out.println("   " + num1 + " / " + num2 + " = " + Calculadora.dividir(num1, num2));
        } else {
            System.out.println("   División por cero no permitida");
        }

        System.out.println("\n💡 Comparación con la clase Math de Java:");
        System.out.println("   Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("   Math.pow(2, 8) = " + Math.pow(2, 8));
        System.out.println("   Math.abs(-42) = " + Math.abs(-42));

        // ! ✅ TAREA ALUMNO:
        // * Añade un método estático potencia(base, exponente) a la clase Calculadora
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 5: BLOQUE ESTÁTICO DE INICIALIZACIÓN
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Bloque estático (static { })
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? El bloque static { } se ejecuta UNA SOLA VEZ cuando la clase se carga en memoria.
    // ? Se usa para:
    // ?   - Inicializar atributos estáticos complejos
    // ?   - Cargar configuraciones
    // ?   - Establecer conexiones a bases de datos
    // ?
    // ? El orden de ejecución es:
    // ?   1. Bloque static (al cargar la clase)
    // ?   2. Constructor (al crear cada objeto)
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploBloqueEstatico() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("🏗️ EJEMPLO 5: BLOQUE ESTÁTICO DE INICIALIZACIÓN");
        System.out.println("═".repeat(70));

        System.out.println("\n📋 La clase Configuracion tiene un bloque static { }");
        System.out.println("   que se ejecuta AUTOMÁTICAMENTE al cargar la clase.\n");

        // ? Al acceder a la clase por primera vez, se ejecuta su bloque static
        System.out.println("🔄 Accediendo a Configuracion por primera vez...\n");
        System.out.println("──────────────────────────────────────");
        Configuracion.mostrarConfiguracion();
        System.out.println("──────────────────────────────────────");

        System.out.println("\n🔄 Creando objetos de Configuracion...");
        System.out.println("   (El bloque static NO se vuelve a ejecutar)\n");

        // ? El bloque static solo se ejecuta una vez, aunque creemos varios objetos
        Configuracion c1 = new Configuracion();
        Configuracion c2 = new Configuracion();

        System.out.println("\n📊 Orden de ejecución:");
        System.out.println("   1. Bloque STATIC → Solo 1 vez (al cargar clase)");
        System.out.println("   2. Constructor → Cada vez que se crea un objeto");

        // ! ✅ TAREA ALUMNO:
        // * Añade un bloque static a la clase Alumno que imprima un mensaje
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    EJEMPLO 6: PATRÓN SINGLETON
    // ═══════════════════════════════════════════════════════════════════════════════════

    // * 📖 TEORÍA: Patrón Singleton
    // ──────────────────────────────────────────────────────────────────────────────────
    // ? El patrón Singleton garantiza que solo exista UNA instancia de una clase.
    // ? Se usa para:
    // ?   - Gestores de configuración
    // ?   - Conexiones a bases de datos
    // ?   - Logs del sistema
    // ?
    // ? Características:
    // ?   - Constructor PRIVADO (nadie puede hacer new)
    // ?   - Atributo static con la única instancia
    // ?   - Método static getInstancia() para obtenerla
    // ──────────────────────────────────────────────────────────────────────────────────

    public static void ejemploPatronSingleton() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("🎯 EJEMPLO 6: PATRÓN SINGLETON (una sola instancia)");
        System.out.println("═".repeat(70));

        System.out.println("\n📋 La clase Logger solo permite UNA instancia:");
        System.out.println("──────────────────────────────────────");

        // ? No podemos hacer: Logger log = new Logger(); // Error: constructor privado

        // ? Obtenemos la instancia única con getInstancia()
        Logger log1 = Logger.getInstancia();
        Logger log2 = Logger.getInstancia();

        System.out.println("\n🔍 Comprobando que son la MISMA instancia:");
        System.out.println("   log1 == log2 → " + (log1 == log2));
        System.out.println("   Ambas variables apuntan al MISMO objeto en memoria");

        System.out.println("\n📝 Usando el Logger:");
        log1.escribirLog("INFO", "Aplicación iniciada");
        log2.escribirLog("WARNING", "Memoria al 80%");
        log1.escribirLog("ERROR", "No se pudo conectar a la BD");

        System.out.println("\n📊 Mensajes registrados: " + Logger.getInstancia().getTotalMensajes());

        System.out.println("\n💡 ¿Por qué usar Singleton?");
        System.out.println("   - Asegura que siempre usamos el MISMO log");
        System.out.println("   - Evita problemas de concurrencia");
        System.out.println("   - Ahorra recursos (solo 1 objeto en memoria)");

        // ! ✅ TAREA ALUMNO:
        // * Crea una clase Singleton llamada GestorBaseDatos con métodos conectar() y desconectar()
    }

    // ═══════════════════════════════════════════════════════════════════════════════════
    // *                    RESUMEN TEÓRICO COMPLETO
    // ═══════════════════════════════════════════════════════════════════════════════════

    public static void mostrarResumenTeorico() {
        System.out.println("\n" + "═".repeat(70));
        System.out.println("📋 RESUMEN TEÓRICO: STATIC EN JAVA");
        System.out.println("═".repeat(70));

        System.out.println("""
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 ¿QUÉ SIGNIFICA STATIC?                                          │
        ├─────────────────────────────────────────────────────────────────────┤
        │  STATIC = Pertenece a la CLASE, no a cada objeto individual.        │
        │  Se comparte entre TODAS las instancias de esa clase.               │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 ATRIBUTOS ESTÁTICOS                                             │
        ├─────────────────────────────────────────────────────────────────────┤
        │  • Se declaran con: static tipo nombre;                             │
        │  • Se acceden con: NombreClase.atributo                             │
        │  • Son COMPARTIDOS por todos los objetos                            │
        │  • Existen aunque no haya ningún objeto creado                      │
        │                                                                     │
        │  Ejemplo:                                                           │
        │     static int contador = 0;                                        │
        │     System.out.println(MiClase.contador);                           │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 MÉTODOS ESTÁTICOS                                               │
        ├─────────────────────────────────────────────────────────────────────┤
        │  • Se declaran con: static tipoRetorno nombre(params)               │
        │  • Se llaman con: NombreClase.metodo()                              │
        │  • NO necesitan un objeto para ser llamados                         │
        │  • NO pueden usar atributos/métodos no estáticos                    │
        │  • NO pueden usar 'this'                                            │
        │                                                                     │
        │  Ejemplo:                                                           │
        │     public static int sumar(int a, int b) { return a + b; }         │
        │     int resultado = Calculadora.sumar(5, 3);                        │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 CONSTANTES (final static)                                       │
        ├─────────────────────────────────────────────────────────────────────┤
        │  • Se declaran con: final static TIPO NOMBRE = valor;               │
        │  • Por convención se escriben en MAYÚSCULAS_CON_GUIONES             │
        │  • Su valor NO puede cambiar después de asignarse                   │
        │                                                                     │
        │  Ejemplo:                                                           │
        │     public static final double PI = 3.14159;                        │
        │     public static final int MAX_INTENTOS = 3;                       │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 BLOQUE ESTÁTICO static { }                                      │
        ├─────────────────────────────────────────────────────────────────────┤
        │  • Se ejecuta UNA SOLA VEZ cuando la clase se carga                 │
        │  • Se usa para inicializar atributos estáticos complejos            │
        │  • Se ejecuta ANTES que cualquier constructor                       │
        │                                                                     │
        │  Ejemplo:                                                           │
        │     static {                                                        │
        │         System.out.println("Clase cargada");                        │
        │         configuracion = cargarConfig();                             │
        │     }                                                               │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 CUÁNDO USAR STATIC                                              │
        ├─────────────────────────────────────────────────────────────────────┤
        │  ✅ USAR STATIC cuando:                                             │
        │     • El valor/método es igual para TODOS los objetos               │
        │     • Es una función de utilidad (Math.sqrt, Integer.parseInt)      │
        │     • Quieres contar instancias de una clase                        │
        │     • Defines constantes (PI, GRAVEDAD, MAX_VALOR)                  │
        │     • Implementas el patrón Singleton                               │
        │                                                                     │
        │  ❌ NO USAR STATIC cuando:                                          │
        │     • El valor es diferente para cada objeto                        │
        │     • El método necesita acceder a atributos de instancia           │
        │     • Cada objeto debe tener su propio comportamiento               │
        └─────────────────────────────────────────────────────────────────────┘
        
        ┌─────────────────────────────────────────────────────────────────────┐
        │  🔹 ERRORES COMUNES                                                 │
        ├─────────────────────────────────────────────────────────────────────┤
        │  ❌ Error: acceder a atributo no static desde método static         │
        │     static void metodo() {                                          │
        │         System.out.println(this.nombre); // ERROR                   │
        │     }                                                               │
        │                                                                     │
        │  ❌ Error: llamar método static como si fuera de instancia          │
        │     Calculadora calc = new Calculadora();                           │
        │     calc.sumar(5, 3); // Funciona pero NO es correcto               │
        │     Calculadora.sumar(5, 3); // ✅ CORRECTO                         │
        └─────────────────────────────────────────────────────────────────────┘
        """);

        // ! ✅ TAREA ALUMNO:
        // * Lee este resumen y prepara 3 preguntas para la próxima clase
    }
}

// ═══════════════════════════════════════════════════════════════════════════════════════
// *                           CLASES AUXILIARES PARA LOS EJEMPLOS
// ═══════════════════════════════════════════════════════════════════════════════════════

// * 📖 CLASE COCHE - Demuestra la diferencia entre atributos static y no static
// ──────────────────────────────────────────────────────────────────────────────────────
class Coche {
    // ? Atributos NO STATIC - Cada coche tiene los suyos propios
    String marca;                      // 🚗 Marca del coche (diferente para cada uno)
    String color;                      // 🎨 Color del coche (diferente para cada uno)

    // ? Atributo STATIC - Compartido por TODOS los coches
    static int totalCoches = 0;        // 📊 Contador global de coches creados

    // ? Constructor: se ejecuta cada vez que creamos un coche
    public Coche(String marca, String color) {
        this.marca = marca;            // Asignamos la marca de ESTE coche
        this.color = color;            // Asignamos el color de ESTE coche
        totalCoches++;                 // 📈 Incrementamos el contador COMPARTIDO
    }
}

// * 📖 CLASE ALUMNO - Demuestra contador de instancias y IDs únicos
// ──────────────────────────────────────────────────────────────────────────────────────
class Alumno {
    // ? Atributos de instancia (cada alumno tiene los suyos)
    private String nombre;
    private int edad;
    private int id;                    // ID único de cada alumno

    // ? Atributos estáticos (compartidos por todos)
    private static int totalAlumnos = 0;      // Contador de alumnos
    private static int siguienteId = 1;       // Próximo ID a asignar

    // ? Constructor
    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = siguienteId;         // Asignamos el ID actual
        siguienteId++;                 // Incrementamos para el siguiente alumno
        totalAlumnos++;                // Incrementamos el contador total
    }

    // ? Getters
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public int getId() { return id; }

    // ? Métodos estáticos
    public static int getTotalAlumnos() { return totalAlumnos; }
    
    public static void resetearContador() {
        totalAlumnos = 0;
        siguienteId = 1;
    }
}

// * 📖 CLASE CONSTANTES - Demuestra el uso de final static
// ──────────────────────────────────────────────────────────────────────────────────────
class Constantes {
    // ? Constantes matemáticas
    public static final double PI = 3.14159265358979;
    public static final double E = 2.71828182845904;
    public static final double GRAVEDAD = 9.81;

    // ? Constantes de configuración de la aplicación
    public static final String NOMBRE_APP = "MiAplicación v1.0";
    public static final String VERSION = "1.0.0";
    public static final int MAX_USUARIOS = 100;

    // ? Constructor privado - No se pueden crear instancias
    private Constantes() {}
}

// * 📖 CLASE CALCULADORA - Demuestra métodos estáticos de utilidad
// ──────────────────────────────────────────────────────────────────────────────────────
class Calculadora {
    // ? Operaciones básicas
    public static double sumar(double a, double b) {
        return a + b;
    }

    public static double restar(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) {
        if (b == 0) {
            System.out.println("⚠️ Error: División por cero");
            return 0;
        }
        return a / b;
    }

    // ? Funciones de utilidad
    public static String esParImpar(int numero) {
        return (numero % 2 == 0) ? "PAR" : "IMPAR";
    }

    public static boolean esPrimo(int numero) {
        if (numero < 2) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) return false;
        }
        return true;
    }

    public static long factorial(int n) {
        if (n < 0) return -1;
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // ? Constructor privado - Esta clase solo tiene métodos estáticos
    private Calculadora() {}
}

// * 📖 CLASE CONFIGURACION - Demuestra bloque estático
// ──────────────────────────────────────────────────────────────────────────────────────
class Configuracion {
    // ? Atributos estáticos
    private static String idioma;
    private static String zonaHoraria;
    private static boolean modoDebug;
    private static int numInstancias = 0;

    // ? BLOQUE ESTÁTICO - Se ejecuta UNA vez al cargar la clase
    static {
        System.out.println("🔄 [BLOQUE STATIC] Inicializando configuración...");
        idioma = "Español";
        zonaHoraria = "Europe/Madrid";
        modoDebug = false;
        System.out.println("🔄 [BLOQUE STATIC] Configuración cargada correctamente.");
    }

    // ? Constructor - Se ejecuta cada vez que creamos un objeto
    public Configuracion() {
        numInstancias++;
        System.out.println("🏗️ [CONSTRUCTOR] Instancia #" + numInstancias + " creada.");
    }

    // ? Método estático para mostrar configuración
    public static void mostrarConfiguracion() {
        System.out.println("📋 Configuración actual:");
        System.out.println("   - Idioma: " + idioma);
        System.out.println("   - Zona horaria: " + zonaHoraria);
        System.out.println("   - Modo debug: " + (modoDebug ? "Activado" : "Desactivado"));
    }
}

// * 📖 CLASE LOGGER - Demuestra el patrón Singleton
// ──────────────────────────────────────────────────────────────────────────────────────
class Logger {
    // ? Atributo estático que guarda la ÚNICA instancia
    private static Logger instanciaUnica = null;

    // ? Atributos de la instancia
    private ArrayList<String> mensajes;
    private int totalMensajes;

    // ? Constructor PRIVADO - Nadie puede hacer new Logger()
    private Logger() {
        mensajes = new ArrayList<>();
        totalMensajes = 0;
        System.out.println("📝 Logger creado (solo se crea UNA vez)");
    }

    // ? Método estático para obtener la instancia única
    public static Logger getInstancia() {
        if (instanciaUnica == null) {
            // Si no existe, la creamos
            instanciaUnica = new Logger();
        }
        // Siempre devolvemos la misma instancia
        return instanciaUnica;
    }

    // ? Método para escribir en el log
    public void escribirLog(String nivel, String mensaje) {
        String entrada = "[" + nivel + "] " + mensaje;
        mensajes.add(entrada);
        totalMensajes++;
        System.out.println("📝 " + entrada);
    }

    // ? Getter para el total de mensajes
    public int getTotalMensajes() {
        return totalMensajes;
    }
}
