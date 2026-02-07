/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES
 *  🎮 CLASE: Principal - Menú interactivo para gestionar radios
 *  🔐 REPOSITORIO PRIVADO EN GITHUB (USO EDUCATIVO EXCLUSIVO)
 ******************************************************************************************/

import java.util.HashSet;       // ? Colección que NO permite duplicados
import java.util.Iterator;      // ? Para eliminar elementos mientras iteramos
import java.util.Scanner;       // ? Para leer entrada del usuario
import java.util.ArrayList;     // ? Para el ejercicio EXTRA comparativo

/**
 * 📖 TEORÍA: ¿Por qué HashSet?
 * ──────────────────────────────────────────────
 * ? HashSet NO permite duplicados (usa equals() y hashCode()).
 * ? Búsqueda muy rápida: O(1) en promedio.
 * ? NO mantiene orden de inserción.
 * ? Ideal cuando necesitamos evitar elementos repetidos.
 */
public class Principal {

    // * ═══════════════════════════════════════════
    // * 📦 ATRIBUTOS ESTÁTICOS (compartidos)
    // * ═══════════════════════════════════════════
    
    private static HashSet<Radio> conjuntoRadios = new HashSet<>();  // 📻 Colección de radios
    private static Scanner sc = new Scanner(System.in);               // 🎤 Lector de entrada

    // * ═══════════════════════════════════════════
    // * 🚀 MÉTODO MAIN - PUNTO DE ENTRADA
    // * ═══════════════════════════════════════════

    public static void main(String[] args) {
        int opcion;  // 🎛️ Variable para controlar el menú

        System.out.println("\n🎵 BIENVENIDO AL SISTEMA DE GESTIÓN DE RADIOS 🎵");

        // * MENÚ PRINCIPAL - Bucle hasta que el usuario elija salir
        do {
            mostrarMenu();
            opcion = leerEntero("👉 Elige una opción: ");

            switch (opcion) {
                case 1 -> crearRadio();
                case 2 -> mostrarTodasLasRadios();
                case 3 -> encenderApagarRadio();
                case 4 -> mostrarRadiosEncendidas();
                case 5 -> subirBajarFrecuenciaTodas();
                case 6 -> establecerFrecuenciaRadio();
                case 7 -> eliminarRadiosBateriaBaja();
                case 8 -> consultarBaterias();
                case 9 -> System.out.println("\n👋 ¡Hasta pronto! Gracias por usar el sistema.");
                default -> System.out.println("\n⚠️ Opción no válida. Introduce un número del 1 al 9.");
            }
        } while (opcion != 9);

        sc.close();  // 🔐 Cerramos el Scanner
    }

    // * ═══════════════════════════════════════════
    // * 📋 MOSTRAR MENÚ
    // * ═══════════════════════════════════════════

    private static void mostrarMenu() {
        System.out.println("\n═══════════════════════════════════════════════════════");
        System.out.println("          📻 MENÚ - GESTIÓN DE RADIOS                   ");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  1. 🆕 Crear radio y añadir al conjunto");
        System.out.println("  2. 📋 Mostrar todas las radios");
        System.out.println("  3. 🔌 Encender/Apagar radio por número de serie");
        System.out.println("  4. 🟢 Mostrar radios encendidas (datos técnicos)");
        System.out.println("  5. 📶 Subir/Bajar frecuencia de TODAS las radios");
        System.out.println("  6. 🎚️ Establecer frecuencia en una radio");
        System.out.println("  7. 🗑️ Eliminar radios con batería baja");
        System.out.println("  8. 🔋 Consultar batería de todas las radios");
        System.out.println("  9. 🚪 Salir");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    // * ═══════════════════════════════════════════
    // * 🛠️ MÉTODOS AUXILIARES - LECTURA ROBUSTA
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Entrada robusta de enteros
     * ──────────────────────────────────────────────
     * ? Si el usuario mete texto donde esperamos número, el programa no debe fallar.
     * ? Usamos un bucle que repite hasta que la entrada sea válida.
     * ? hasNextInt() comprueba si lo siguiente es un entero.
     * 
     * @param mensaje 📝 Mensaje a mostrar al usuario
     * @return int número válido introducido
     */
    private static int leerEntero(String mensaje) {
        int numero;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextInt()) {
                numero = sc.nextInt();
                sc.nextLine();  // 🧹 Limpiamos el buffer
                return numero;
            } else {
                System.out.println("❌ Error: Introduce un número entero válido.");
                sc.nextLine();  // 🧹 Descartamos la entrada inválida
            }
        }
    }

    /**
     * 📖 TEORÍA: Entrada robusta de decimales (double)
     * ──────────────────────────────────────────────
     * ? Similar al método anterior, pero para números decimales.
     * ? hasNextDouble() comprueba si lo siguiente es un double.
     * 
     * @param mensaje 📝 Mensaje a mostrar al usuario
     * @return double número decimal válido
     */
    private static double leerDouble(String mensaje) {
        double numero;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextDouble()) {
                numero = sc.nextDouble();
                sc.nextLine();  // 🧹 Limpiamos el buffer
                return numero;
            } else {
                System.out.println("❌ Error: Introduce un número válido (usa coma o punto decimal).");
                sc.nextLine();  // 🧹 Descartamos la entrada inválida
            }
        }
    }

    /**
     * 📖 TEORÍA: Entrada robusta de long
     * ──────────────────────────────────────────────
     * ? Para números de serie que pueden ser muy grandes.
     * 
     * @param mensaje 📝 Mensaje a mostrar
     * @return long número válido
     */
    private static long leerLong(String mensaje) {
        long numero;
        while (true) {
            System.out.print(mensaje);
            if (sc.hasNextLong()) {
                numero = sc.nextLong();
                sc.nextLine();
                return numero;
            } else {
                System.out.println("❌ Error: Introduce un número de serie válido.");
                sc.nextLine();
            }
        }
    }

    // * ═══════════════════════════════════════════
    // * 1️⃣ CREAR RADIO Y AÑADIR AL CONJUNTO
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Añadir a HashSet
     * ──────────────────────────────────────────────
     * ? HashSet.add() devuelve true si el elemento se añadió.
     * ? Devuelve false si el elemento ya existía (duplicado).
     * ? Usa equals() y hashCode() para determinar si es duplicado.
     */
    private static void crearRadio() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🆕 CREAR NUEVA RADIO               ║");
        System.out.println("╚═══════════════════════════════════════╝");

        // 📋 Pedimos datos de especificaciones
        System.out.println("\n📋 DATOS DE ESPECIFICACIONES:");
        System.out.print("   🏭 Marca de la radio: ");
        String marcaRadio = sc.nextLine();
        System.out.print("   📱 Modelo: ");
        String modelo = sc.nextLine();
        long numeroSerie = leerLong("   🔢 Número de serie: ");

        // 🔋 Pedimos datos de batería
        System.out.println("\n🔋 DATOS DE BATERÍA:");
        System.out.print("   🏭 Marca de la batería: ");
        String marcaBateria = sc.nextLine();
        int porcentaje = leerEntero("   ⚡ Porcentaje de carga (0-100): ");

        // 📻 Pedimos frecuencias
        System.out.println("\n📻 CONFIGURACIÓN DE FRECUENCIAS:");
        double freqMin = leerDouble("   📉 Frecuencia mínima (ej: 87.5): ");
        double freqMax = leerDouble("   📈 Frecuencia máxima (ej: 108.0): ");

        // 🔨 Creamos los objetos (AGREGACIÓN)
        Especificaciones especificaciones = new Especificaciones(marcaRadio, modelo, numeroSerie);
        Bateria bateria = new Bateria(marcaBateria, porcentaje);
        Radio nuevaRadio = new Radio(especificaciones, bateria, freqMin, freqMax);

        // ➕ Intentamos añadir al HashSet
        if (conjuntoRadios.add(nuevaRadio)) {
            System.out.println("\n✅ Radio añadida correctamente al conjunto.");
            System.out.println("📊 Total de radios en el sistema: " + conjuntoRadios.size());
        } else {
            System.out.println("\n⚠️ Ya existe una radio con el número de serie " + numeroSerie);
            System.out.println("ℹ️ No se ha añadido para evitar duplicados.");
        }
    }

    // * ═══════════════════════════════════════════
    // * 2️⃣ MOSTRAR TODAS LAS RADIOS
    // * ═══════════════════════════════════════════

    private static void mostrarTodasLasRadios() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     📋 LISTADO DE TODAS LAS RADIOS     ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas en el sistema.");
            return;
        }

        System.out.println("\n📊 Total de radios: " + conjuntoRadios.size());
        
        // 🔁 Recorremos el HashSet con for-each
        for (Radio radio : conjuntoRadios) {
            System.out.println(radio.toString());  // 📝 Usa el toString() de Radio
        }
    }

    // * ═══════════════════════════════════════════
    // * 3️⃣ ENCENDER/APAGAR RADIO POR NÚMERO DE SERIE
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Buscar en HashSet
     * ──────────────────────────────────────────────
     * ? HashSet no tiene método get() por índice.
     * ? Debemos recorrer y buscar manualmente.
     * ? La búsqueda es rápida gracias al hashCode().
     */
    private static void encenderApagarRadio() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🔌 ENCENDER/APAGAR RADIO           ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        long numeroSerie = leerLong("🔢 Introduce el número de serie: ");

        // 🔍 Buscamos la radio por número de serie
        Radio radioEncontrada = buscarRadioPorSerie(numeroSerie);

        if (radioEncontrada != null) {
            String resultado = radioEncontrada.botonEncendido();
            System.out.println("\n" + resultado);
        } else {
            System.out.println("\n❌ No se encontró ninguna radio con el número de serie " + numeroSerie);
        }
    }

    /**
     * 🔍 Método auxiliar para buscar radio por número de serie
     * @param numeroSerie Número de serie a buscar
     * @return Radio encontrada o null si no existe
     */
    private static Radio buscarRadioPorSerie(long numeroSerie) {
        for (Radio radio : conjuntoRadios) {
            if (radio.getEspecificaciones().getNumeroSerie() == numeroSerie) {
                return radio;
            }
        }
        return null;  // ❌ No encontrada
    }

    // * ═══════════════════════════════════════════
    // * 4️⃣ MOSTRAR RADIOS ENCENDIDAS
    // * ═══════════════════════════════════════════

    private static void mostrarRadiosEncendidas() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🟢 RADIOS ENCENDIDAS                ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        boolean hayEncendidas = false;

        for (Radio radio : conjuntoRadios) {
            if (radio.isEncendida()) {
                hayEncendidas = true;
                System.out.println("\n────────────────────────────────────");
                System.out.println("📋 DATOS TÉCNICOS:");
                System.out.println("   " + radio.getEspecificaciones().toString());
                System.out.println("📻 Frecuencia actual: " + radio.getFrecuenciaActual() + " MHz");
                System.out.println("   " + radio.getBateria().toString());
            }
        }

        if (!hayEncendidas) {
            System.out.println("\nℹ️ No hay ninguna radio encendida actualmente.");
        }
    }

    // * ═══════════════════════════════════════════
    // * 5️⃣ SUBIR/BAJAR FRECUENCIA DE TODAS
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Operación masiva con consumo
     * ──────────────────────────────────────────────
     * ? Modificamos TODAS las radios del conjunto.
     * ? Solo las encendidas consumen batería.
     */
    private static void subirBajarFrecuenciaTodas() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     📶 AJUSTAR FRECUENCIA (TODAS)       ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        System.out.print("\n¿Subir (S) o Bajar (B) frecuencia? ");
        String respuesta = sc.nextLine().toUpperCase();

        if (!respuesta.equals("S") && !respuesta.equals("B")) {
            System.out.println("❌ Opción no válida. Usa 'S' para subir o 'B' para bajar.");
            return;
        }

        System.out.println("\n📻 RESULTADO DE LA OPERACIÓN:");
        System.out.println("────────────────────────────────────");

        for (Radio radio : conjuntoRadios) {
            long serie = radio.getEspecificaciones().getNumeroSerie();
            double frecuencia;

            // 📶 Ajustamos frecuencia
            if (respuesta.equals("S")) {
                frecuencia = radio.subirFrecuencia();
            } else {
                frecuencia = radio.bajarFrecuencia();
            }

            // ⚡ Solo las encendidas consumen batería
            if (radio.isEncendida()) {
                radio.getBateria().consumir(10);  // 🔋 Consume 10%
                System.out.println("📻 Radio " + serie + ": " + frecuencia + " MHz | " +
                        "🔋 Batería restante: " + radio.getBateria().getPorcentajeCarga() + "%");
            } else {
                System.out.println("📻 Radio " + serie + ": " + frecuencia + " MHz | " +
                        "🔴 (Apagada - sin consumo)");
            }
        }
    }

    // * ═══════════════════════════════════════════
    // * 6️⃣ ESTABLECER FRECUENCIA EN UNA RADIO
    // * ═══════════════════════════════════════════

    private static void establecerFrecuenciaRadio() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🎚️ ESTABLECER FRECUENCIA            ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        long numeroSerie = leerLong("🔢 Introduce el número de serie: ");
        Radio radio = buscarRadioPorSerie(numeroSerie);

        if (radio == null) {
            System.out.println("\n❌ No se encontró ninguna radio con ese número de serie.");
            return;
        }

        System.out.println("\nℹ️ Rango permitido: " + radio.getFrecuenciaMin() + 
                          " - " + radio.getFrecuenciaMax() + " MHz");
        double nuevaFrecuencia = leerDouble("📻 Nueva frecuencia: ");

        if (radio.establecerFrecuencia(nuevaFrecuencia)) {
            System.out.println("\n✅ Frecuencia establecida en " + nuevaFrecuencia + " MHz");
        } else {
            System.out.println("\n❌ La frecuencia " + nuevaFrecuencia + 
                              " está fuera del rango permitido.");
        }
    }

    // * ═══════════════════════════════════════════
    // * 7️⃣ ELIMINAR RADIOS CON BATERÍA BAJA
    // * ═══════════════════════════════════════════

    /**
     * 📖 TEORÍA: Eliminar mientras iteramos
     * ──────────────────────────────────────────────
     * ? NO podemos usar for-each y remove() al mismo tiempo (ConcurrentModificationException).
     * ? Usamos Iterator que permite eliminar de forma segura con iterator.remove().
     */
    private static void eliminarRadiosBateriaBaja() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🗑️ ELIMINAR RADIOS BATERÍA BAJA     ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        int umbral = leerEntero("⚡ Introduce el umbral de batería (eliminar si < umbral): ");

        // 🔄 Usamos Iterator para eliminar de forma segura
        Iterator<Radio> iterador = conjuntoRadios.iterator();
        int eliminadas = 0;

        System.out.println("\n🗑️ RADIOS ELIMINADAS:");
        System.out.println("────────────────────────────────────");

        while (iterador.hasNext()) {
            Radio radio = iterador.next();
            int carga = radio.getBateria().getPorcentajeCarga();
            
            if (carga < umbral) {
                System.out.println("   ❌ Radio " + 
                        radio.getEspecificaciones().getNumeroSerie() + 
                        " (Batería: " + carga + "%)");
                iterador.remove();  // 🗑️ Eliminación segura con Iterator
                eliminadas++;
            }
        }

        if (eliminadas == 0) {
            System.out.println("   ℹ️ No se eliminó ninguna radio.");
        } else {
            System.out.println("\n📊 Total eliminadas: " + eliminadas);
            System.out.println("📊 Radios restantes: " + conjuntoRadios.size());
        }
    }

    // * ═══════════════════════════════════════════
    // * 8️⃣ CONSULTAR BATERÍA DE TODAS
    // * ═══════════════════════════════════════════

    private static void consultarBaterias() {
        System.out.println("\n╔═══════════════════════════════════════╗");
        System.out.println("║     🔋 ESTADO DE BATERÍAS               ║");
        System.out.println("╚═══════════════════════════════════════╝");

        if (conjuntoRadios.isEmpty()) {
            System.out.println("\nℹ️ No hay radios registradas.");
            return;
        }

        System.out.println("\n─────────────────────────────────────────────");
        System.out.printf("│ %-15s │ %-10s │ %-10s │%n", "N° Serie", "Carga", "Estado");
        System.out.println("─────────────────────────────────────────────");

        for (Radio radio : conjuntoRadios) {
            long serie = radio.getEspecificaciones().getNumeroSerie();
            int carga = radio.getBateria().getPorcentajeCarga();
            String indicador = obtenerIndicadorBateria(carga);
            
            System.out.printf("│ %-15d │ %7d%% │ %-10s │%n", serie, carga, indicador);
        }
        System.out.println("─────────────────────────────────────────────");
    }

    /**
     * 🎨 Genera un indicador visual del nivel de batería
     */
    private static String obtenerIndicadorBateria(int porcentaje) {
        if (porcentaje > 70) return "🟢 Alta";
        if (porcentaje > 30) return "🟡 Media";
        if (porcentaje > 0) return "🔴 Baja";
        return "⚫ Agotada";
    }

    // ! ═══════════════════════════════════════════════════════════════════════════════
    // ! 🎁 EXTRA: VERSIÓN CON ArrayList (ver diferencias)
    // ! ═══════════════════════════════════════════════════════════════════════════════

    /**
     * 📖 TEORÍA: HashSet vs ArrayList
     * ──────────────────────────────────────────────
     * 
     * ? HASHSET:
     *   - NO permite duplicados (usa equals/hashCode)
     *   - Búsqueda muy rápida: O(1) promedio
     *   - NO mantiene orden de inserción
     *   - NO tiene índices (no hay get(i))
     * 
     * ? ARRAYLIST:
     *   - SÍ permite duplicados
     *   - Búsqueda más lenta: O(n)
     *   - SÍ mantiene orden de inserción
     *   - SÍ tiene índices (get(i), set(i, elem))
     * 
     * ? ¿Cuándo usar cada uno?
     *   - HashSet: cuando necesitas evitar duplicados y búsqueda rápida
     *   - ArrayList: cuando necesitas orden, índices o permitir duplicados
     * 
     * EJEMPLO DE CÓDIGO CON ARRAYLIST:
     * ──────────────────────────────────────────────
     * 
     * private static ArrayList<Radio> listaRadios = new ArrayList<>();
     * 
     * // Para EVITAR duplicados manualmente con ArrayList:
     * private static boolean existeRadio(long numeroSerie) {
     *     for (Radio r : listaRadios) {
     *         if (r.getEspecificaciones().getNumeroSerie() == numeroSerie) {
     *             return true;
     *         }
     *     }
     *     return false;
     * }
     * 
     * // Añadir con verificación manual:
     * if (!existeRadio(numeroSerie)) {
     *     listaRadios.add(nuevaRadio);
     *     System.out.println("✅ Radio añadida");
     * } else {
     *     System.out.println("⚠️ Ya existe esa radio");
     * }
     * 
     * // Acceso por índice (ventaja de ArrayList):
     * Radio primera = listaRadios.get(0);  // ✅ Posible con ArrayList
     * // Radio primera = conjuntoRadios.get(0);  // ❌ ERROR: HashSet no tiene get(i)
     * 
     * // Ordenar (más fácil con ArrayList):
     * Collections.sort(listaRadios, (r1, r2) -> 
     *     Long.compare(r1.getEspecificaciones().getNumeroSerie(),
     *                  r2.getEspecificaciones().getNumeroSerie()));
     */

    // ! ✅ TAREAS ALUMNO:
    // * 1. Ejecuta el programa y prueba todas las opciones del menú
    // * 2. Intenta añadir dos radios con el mismo número de serie. ¿Qué ocurre?
    // * 3. Crea una radio, enciéndela, sube la frecuencia. ¿Cuánta batería consume?
    // * 4. Crea varias radios con baterías bajas y usa la opción 7 para eliminarlas
    // * 5. RETO: Modifica el código para usar ArrayList en lugar de HashSet
    //    y comprueba las diferencias con duplicados
}
