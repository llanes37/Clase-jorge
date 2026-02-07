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
            System.out.println("1. Ver diferencia entre STATIC y NO STATIC");
            System.out.println("2. Contador de instancias (atributo estático)");
            System.out.println("3. Usar constantes estáticas (final static)");
            System.out.println("4. Métodos estáticos de utilidad (calculadora)");
            System.out.println("5. Bloque estático de inicialización");
            System.out.println("6. Patrón Singleton (solo una instancia)");
            System.out.println("7. Resumen teórico completo");
            System.out.println("0. Salir");
            System.out.print("👉 Elige una opción: ");
            opcion = sc.nextInt();        // 📥 Lee la opción seleccionada
            sc.nextLine();                // 🧹 Limpia el buffer tras leer número

            switch (opcion) {
                case 1 -> ejemploStaticVsNoStatic();
                case 2 -> ejemploContadorInstancias();
                case 3 -> ejemploConstantes();
                case 4 -> ejemploMetodosEstaticos(sc);
                case 5 -> ejemploBloqueStatic();
                case 6 -> ejemploSingleton();
                case 7 -> mostrarResumen();
                case 0 -> System.out.println("👋 ¡Saliendo del programa!");
                default -> System.out.println("⚠️ Opción no válida.");
            }
        } while (opcion != 0); // 🔁 Repite mientras no se elija salir

        sc.close(); // 🔐 Cerramos el Scanner al terminar
    }

    // * 📖 TEORÍA: ¿Qué significa STATIC?
    // ──────────────────────────────────────────────
    // ? STATIC = Pertenece a la CLASE, no a cada objeto.
    // ? NO STATIC = Pertenece a cada OBJETO individual.
    public static void ejemploStaticVsNoStatic() {
        System.out.println("\n📊 EJEMPLO 1: STATIC vs NO STATIC");
        System.out.println("──────────────────────────────────────");

        Coche coche1 = new Coche("Toyota", "Rojo");   // 🚗 Creamos coche 1
        Coche coche2 = new Coche("Ford", "Azul");     // 🚗 Creamos coche 2
        Coche coche3 = new Coche("BMW", "Negro");     // 🚗 Creamos coche 3

        System.out.println("\n🚗 Atributos NO STATIC (diferentes para cada coche):");
        System.out.println("Coche 1: " + coche1.marca + " - " + coche1.color);
        System.out.println("Coche 2: " + coche2.marca + " - " + coche2.color);
        System.out.println("Coche 3: " + coche3.marca + " - " + coche3.color);

        System.out.println("\n📊 Atributo STATIC (compartido por todos):");
        System.out.println("Total coches creados: " + Coche.totalCoches);

        System.out.println("\n💡 RECUERDA:");
        System.out.println("   - NO STATIC: cada objeto tiene su copia");
        System.out.println("   - STATIC: todos comparten el mismo valor");
        System.out.println("   - Se accede con: NombreClase.atributo");

        // ! ✅ TAREA ALUMNO:
        // * Crea 2 coches más y mira cómo cambia totalCoches
    }

    // * 📖 TEORÍA: Contador de instancias
    // ──────────────────────────────────────────────
    // ? Usamos atributos STATIC para contar objetos creados.
    // ? El contador se incrementa en el constructor.
    public static void ejemploContadorInstancias() {
        System.out.println("\n🔢 EJEMPLO 2: CONTADOR DE INSTANCIAS");
        System.out.println("──────────────────────────────────────");

        Alumno.resetearContador(); // 🔄 Reseteamos para la demo

        Alumno a1 = new Alumno("María García", 20);  // 📚 Creamos alumno 1
        System.out.println("✅ Creado: " + a1.getNombre() + " (ID: " + a1.getId() + ")");
        System.out.println("   Total alumnos: " + Alumno.getTotalAlumnos());

        Alumno a2 = new Alumno("Juan López", 22);    // 📚 Creamos alumno 2
        System.out.println("✅ Creado: " + a2.getNombre() + " (ID: " + a2.getId() + ")");
        System.out.println("   Total alumnos: " + Alumno.getTotalAlumnos());

        Alumno a3 = new Alumno("Ana Martínez", 19);  // 📚 Creamos alumno 3
        System.out.println("✅ Creado: " + a3.getNombre() + " (ID: " + a3.getId() + ")");
        System.out.println("   Total alumnos: " + Alumno.getTotalAlumnos());

        System.out.println("\n📊 RESUMEN:");
        System.out.println("Total matriculados: " + Alumno.getTotalAlumnos());
        System.out.println("Cada alumno tiene ID único asignado automáticamente");

        // ! ✅ TAREA ALUMNO:
        // * Crea un método estático getPromedioEdad()
    }

    // * 📖 TEORÍA: Constantes (final static)
    // ──────────────────────────────────────────────
    // ? final = El valor no puede cambiar.
    // ? static = Pertenece a la clase.
    // ? Convención: MAYUSCULAS_CON_GUIONES
    public static void ejemploConstantes() {
        System.out.println("\n📐 EJEMPLO 3: CONSTANTES (final static)");
        System.out.println("──────────────────────────────────────");

        System.out.println("\n🔢 Constantes matemáticas:");
        System.out.println("PI = " + Constantes.PI);
        System.out.println("E = " + Constantes.E);
        System.out.println("GRAVEDAD = " + Constantes.GRAVEDAD + " m/s²");

        System.out.println("\n🎨 Constantes de la aplicación:");
        System.out.println("Nombre: " + Constantes.NOMBRE_APP);
        System.out.println("Versión: " + Constantes.VERSION);
        System.out.println("Máx usuarios: " + Constantes.MAX_USUARIOS);

        double radio = 5.0;                                  // 🔵 Radio del círculo
        double area = Constantes.PI * radio * radio;         // 📐 Calculamos área
        System.out.println("\n🔵 Cálculo con constantes:");
        System.out.println("Radio = " + radio);
        System.out.println("Área = PI * r² = " + area);

        System.out.println("\n💡 VENTAJAS:");
        System.out.println("   1. Valor en UN solo lugar");
        System.out.println("   2. Código más legible");
        System.out.println("   3. El compilador protege el valor");

        // ! ✅ TAREA ALUMNO:
        // * Añade VELOCIDAD_LUZ = 299792458 y úsala
    }

    // * 📖 TEORÍA: Métodos estáticos
    // ──────────────────────────────────────────────
    // ? Se llaman con: NombreClase.metodo()
    // ? NO necesitan crear un objeto.
    // ? NO pueden usar atributos de instancia.
    public static void ejemploMetodosEstaticos(Scanner sc) {
        System.out.println("\n🧮 EJEMPLO 4: MÉTODOS ESTÁTICOS");
        System.out.println("──────────────────────────────────────");

        System.out.println("\n📊 Ejemplos de uso:");
        System.out.println("sumar(10, 5) = " + Calculadora.sumar(10, 5));
        System.out.println("restar(10, 5) = " + Calculadora.restar(10, 5));
        System.out.println("multiplicar(10, 5) = " + Calculadora.multiplicar(10, 5));
        System.out.println("dividir(10, 5) = " + Calculadora.dividir(10, 5));
        System.out.println("esParImpar(7) = " + Calculadora.esParImpar(7));
        System.out.println("esPrimo(17) = " + Calculadora.esPrimo(17));
        System.out.println("factorial(5) = " + Calculadora.factorial(5));

        System.out.println("\n🎮 PRUEBA TÚ:");
        System.out.print("Primer número: ");
        double num1 = sc.nextDouble();              // 📥 Leemos primer número
        System.out.print("Segundo número: ");
        double num2 = sc.nextDouble();              // 📥 Leemos segundo número

        System.out.println("\n📊 Resultados:");
        System.out.println(num1 + " + " + num2 + " = " + Calculadora.sumar(num1, num2));
        System.out.println(num1 + " - " + num2 + " = " + Calculadora.restar(num1, num2));
        System.out.println(num1 + " * " + num2 + " = " + Calculadora.multiplicar(num1, num2));
        if (num2 != 0) {
            System.out.println(num1 + " / " + num2 + " = " + Calculadora.dividir(num1, num2));
        }

        System.out.println("\n💡 Comparación con Math de Java:");
        System.out.println("Math.sqrt(16) = " + Math.sqrt(16));
        System.out.println("Math.pow(2, 8) = " + Math.pow(2, 8));
        System.out.println("Math.abs(-42) = " + Math.abs(-42));

        // ! ✅ TAREA ALUMNO:
        // * Añade un método potencia(base, exponente)
    }

    // * 📖 TEORÍA: Bloque estático
    // ──────────────────────────────────────────────
    // ? Se ejecuta UNA sola vez al cargar la clase.
    // ? Se usa para inicializar cosas complejas.
    public static void ejemploBloqueStatic() {
        System.out.println("\n🏗️ EJEMPLO 5: BLOQUE ESTÁTICO");
        System.out.println("──────────────────────────────────────");

        System.out.println("\n📋 La clase Configuracion tiene un bloque static { }");
        System.out.println("Se ejecuta AUTOMÁTICAMENTE al usar la clase.\n");

        System.out.println("🔄 Accediendo a Configuracion...\n");
        Configuracion.mostrarConfiguracion();       // 🔄 Primera vez que usamos la clase

        System.out.println("\n🔄 Creando objetos (el bloque NO se repite):\n");
        Configuracion c1 = new Configuracion();     // 🏗️ Creamos objeto 1
        Configuracion c2 = new Configuracion();     // 🏗️ Creamos objeto 2

        System.out.println("\n📊 Orden de ejecución:");
        System.out.println("   1. Bloque STATIC --> Solo 1 vez");
        System.out.println("   2. Constructor --> Cada new");

        // ! ✅ TAREA ALUMNO:
        // * Añade un bloque static a la clase Alumno
    }

    // * 📖 TEORÍA: Patrón Singleton
    // ──────────────────────────────────────────────
    // ? Solo existe UNA instancia de la clase.
    // ? Constructor privado + método getInstancia()
    public static void ejemploSingleton() {
        System.out.println("\n🎯 EJEMPLO 6: PATRÓN SINGLETON");
        System.out.println("──────────────────────────────────────");

        System.out.println("\n📋 Logger solo permite UNA instancia:");

        Logger log1 = Logger.getInstancia();        // 🎯 Obtenemos instancia 1
        Logger log2 = Logger.getInstancia();        // 🎯 Obtenemos instancia 2

        System.out.println("\n🔍 ¿Son el mismo objeto?");
        System.out.println("log1 == log2 --> " + (log1 == log2));

        System.out.println("\n📝 Usando el Logger:");
        log1.escribirLog("INFO", "Aplicación iniciada");
        log2.escribirLog("WARNING", "Memoria al 80%");
        log1.escribirLog("ERROR", "Conexión fallida");

        System.out.println("\n📊 Mensajes totales: " + Logger.getInstancia().getTotalMensajes());

        System.out.println("\n💡 ¿Por qué Singleton?");
        System.out.println("   - Siempre el MISMO objeto");
        System.out.println("   - Ahorra memoria");
        System.out.println("   - Ideal para logs, configs, BD");

        // ! ✅ TAREA ALUMNO:
        // * Crea GestorBaseDatos con conectar() y desconectar()
    }

    // * 📖 RESUMEN TEÓRICO
    // ──────────────────────────────────────────────
    public static void mostrarResumen() {
        System.out.println("\n📋 RESUMEN: STATIC EN JAVA");
        System.out.println("══════════════════════════════════════════════════════════════════");
        
        System.out.println("\n🔹 ¿QUÉ ES STATIC?");
        System.out.println("   Pertenece a la CLASE, no a cada objeto.");
        System.out.println("   Se comparte entre TODAS las instancias.");
        
        System.out.println("\n🔹 ATRIBUTOS ESTÁTICOS");
        System.out.println("   static int contador = 0;");
        System.out.println("   Se accede: NombreClase.contador");
        System.out.println("   Son compartidos por todos los objetos.");
        
        System.out.println("\n🔹 MÉTODOS ESTÁTICOS");
        System.out.println("   public static int sumar(int a, int b) { }");
        System.out.println("   Se llama: Calculadora.sumar(5, 3)");
        System.out.println("   NO necesitan crear un objeto.");
        System.out.println("   NO pueden usar this ni atributos no estáticos.");
        
        System.out.println("\n🔹 CONSTANTES (final static)");
        System.out.println("   public static final double PI = 3.14159;");
        System.out.println("   Se escriben en MAYUSCULAS_CON_GUIONES.");
        System.out.println("   No pueden cambiar de valor.");
        
        System.out.println("\n🔹 BLOQUE ESTÁTICO");
        System.out.println("   static { // código }");
        System.out.println("   Se ejecuta UNA vez al cargar la clase.");
        System.out.println("   Va ANTES que cualquier constructor.");
        
        System.out.println("\n🔹 CUÁNDO USAR STATIC");
        System.out.println("   ✅ Contadores compartidos");
        System.out.println("   ✅ Funciones de utilidad (Math.sqrt)");
        System.out.println("   ✅ Constantes (PI, GRAVEDAD)");
        System.out.println("   ✅ Patrón Singleton");
        System.out.println("   ❌ NO usar si cada objeto necesita su valor");
        
        System.out.println("\n══════════════════════════════════════════════════════════════════");

        // ! ✅ TAREA ALUMNO:
        // * Prepara 3 preguntas sobre STATIC para la próxima clase
    }
}


// ════════════════════════════════════════════════════════════════════════════════════════
// *                              CLASES AUXILIARES
// ════════════════════════════════════════════════════════════════════════════════════════


// * 📖 CLASE COCHE - Diferencia entre static y no static
// ──────────────────────────────────────────────
class Coche {
    String marca;                      // 🚗 NO STATIC - cada coche tiene su marca
    String color;                      // 🎨 NO STATIC - cada coche tiene su color
    static int totalCoches = 0;        // 📊 STATIC - contador compartido

    public Coche(String marca, String color) {
        this.marca = marca;
        this.color = color;
        totalCoches++;                 // 📈 Incrementa el contador compartido
    }
}


// * 📖 CLASE ALUMNO - Contador de instancias con ID único
// ──────────────────────────────────────────────
class Alumno {
    private String nombre;             // 📝 Nombre del alumno
    private int edad;                  // 🎂 Edad del alumno
    private int id;                    // 🔑 ID único

    private static int totalAlumnos = 0;   // 📊 Contador de alumnos
    private static int siguienteId = 1;    // 🔢 Próximo ID a asignar

    public Alumno(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
        this.id = siguienteId;         // 🔑 Asigna ID único
        siguienteId++;                 // 🔢 Prepara el siguiente
        totalAlumnos++;                // 📈 Incrementa contador
    }

    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public int getId() { return id; }
    public static int getTotalAlumnos() { return totalAlumnos; }

    public static void resetearContador() {
        totalAlumnos = 0;
        siguienteId = 1;
    }
}


// * 📖 CLASE CONSTANTES - Uso de final static
// ──────────────────────────────────────────────
class Constantes {
    public static final double PI = 3.14159265358979;      // 🔢 Número PI
    public static final double E = 2.71828182845904;       // 🔢 Número de Euler
    public static final double GRAVEDAD = 9.81;            // 🌍 Gravedad terrestre
    public static final String NOMBRE_APP = "MiApp v1.0";  // 📱 Nombre de la app
    public static final String VERSION = "1.0.0";          // 📋 Versión
    public static final int MAX_USUARIOS = 100;            // 👥 Límite usuarios

    private Constantes() { }           // 🔒 Constructor privado
}


// * 📖 CLASE CALCULADORA - Métodos estáticos de utilidad
// ──────────────────────────────────────────────
class Calculadora {

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

    private Calculadora() { }          // 🔒 Constructor privado
}


// * 📖 CLASE CONFIGURACION - Bloque estático
// ──────────────────────────────────────────────
class Configuracion {
    private static String idioma;
    private static String zonaHoraria;
    private static boolean modoDebug;
    private static int numInstancias = 0;

    // 🏗️ BLOQUE ESTÁTICO - Se ejecuta UNA vez al cargar la clase
    static {
        System.out.println("🔄 [BLOQUE STATIC] Cargando configuración...");
        idioma = "Español";
        zonaHoraria = "Europe/Madrid";
        modoDebug = false;
        System.out.println("🔄 [BLOQUE STATIC] Configuración lista.");
    }

    // 🏗️ CONSTRUCTOR - Se ejecuta cada vez que creamos un objeto
    public Configuracion() {
        numInstancias++;
        System.out.println("🏗️ [CONSTRUCTOR] Instancia #" + numInstancias + " creada.");
    }

    public static void mostrarConfiguracion() {
        System.out.println("📋 Configuración:");
        System.out.println("   Idioma: " + idioma);
        System.out.println("   Zona: " + zonaHoraria);
        System.out.println("   Debug: " + (modoDebug ? "Sí" : "No"));
    }
}


// * 📖 CLASE LOGGER - Patrón Singleton
// ──────────────────────────────────────────────
class Logger {
    private static Logger instanciaUnica = null;  // 🎯 La única instancia
    private ArrayList<String> mensajes;           // 📋 Lista de mensajes
    private int totalMensajes;                    // 🔢 Contador

    // 🔒 CONSTRUCTOR PRIVADO - No se puede hacer new Logger()
    private Logger() {
        mensajes = new ArrayList<>();
        totalMensajes = 0;
        System.out.println("📝 Logger creado (solo UNA vez)");
    }

    // 🎯 Método para obtener la instancia única
    public static Logger getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Logger();
        }
        return instanciaUnica;
    }

    public void escribirLog(String nivel, String mensaje) {
        String entrada = "[" + nivel + "] " + mensaje;
        mensajes.add(entrada);
        totalMensajes++;
        System.out.println("📝 " + entrada);
    }

    public int getTotalMensajes() {
        return totalMensajes;
    }
}
