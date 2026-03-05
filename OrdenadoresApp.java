import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class OrdenadoresApp {
    // !IMPORTANT: ENUNCIADO (programa principal):
    // !IMPORTANT: "Realizar secuencialmente: 1) anadir equipo, 2) mostrar equipos, 3) anadir perifericos,
    // !IMPORTANT: 4) eliminar averiados, 5) eliminar periferico por marca/tipo, 6) mostrar perifericos
    // !IMPORTANT: de ordenadores con mas de 2 nucleos, 7) sumar 2 nucleos a todos, 8) eliminar equipos
    // !IMPORTANT: con mas de 2 perifericos. No se pide menu de opciones."
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorOrdenadores gestor = new GestorOrdenadores();

        // !IMPORTANT: ENUNCIADO - Paso 1: "Anadir un equipo".
        // *INFO: Se crea un ordenador pidiendo exactamente los datos exigidos por el PDF.
        // *INFO: Evita el error tipico de crear equipos con campos incompletos.
        System.out.println("1) Anadir un equipo");
        Ordenador nuevoOrdenador = leerOrdenador(scanner);
        boolean anadido = gestor.addOrdenador(nuevoOrdenador);
        System.out.println(anadido ? "Equipo anadido correctamente." : "No se pudo anadir el equipo (posible duplicado).");

        // !IMPORTANT: ENUNCIADO - Paso 2: "Mostrar los datos de todos los equipos, una linea por ordenador".
        // *INFO: El formato muestra numero de serie + datos para identificar cada equipo rapidamente.
        // *INFO: Evita el error tipico de imprimir todo mezclado en un unico bloque.
        System.out.println();
        System.out.println("2) Mostrar los datos de todos los equipos");
        gestor.mostrarDatosEquipos();

        // !IMPORTANT: ENUNCIADO - Paso 3: "Anadir perifericos a un ordenador por numero de serie".
        // *INFO: Si el ordenador no existe, se informa; si existe, se piden N perifericos y se cuenta
        // *INFO: cuántos se pudieron anadir realmente (por limite maximo y no duplicados en HashSet).
        System.out.println();
        System.out.println("3) Anadir perifericos");
        System.out.print("Introduce el numero de serie del ordenador: ");
        String numeroSerie = scanner.nextLine();
        Ordenador ordenadorBuscado = gestor.buscarPorNumeroSerie(numeroSerie);
        if (ordenadorBuscado == null) {
            System.out.println("No existe un ordenador con ese numero de serie.");
        } else {
            System.out.print("Cuantos perifericos quieres anadir: ");
            int cantidadPerifericos = Integer.parseInt(scanner.nextLine());
            int anadidosCorrectamente = 0;
            for (int i = 0; i < cantidadPerifericos; i++) {
                // *INFO: El constructor de Periferico exige tipo y marca; averiado se genera aleatorio internamente.
                // *INFO: Contar solo los true de addPeriferico evita "falsos anadidos" por duplicados o exceso.
                System.out.print("Periferico " + (i + 1) + " - Tipo: ");
                String tipo = scanner.nextLine();
                System.out.print("Periferico " + (i + 1) + " - Marca: ");
                String marca = scanner.nextLine();
                Periferico nuevoPeriferico = new Periferico(tipo, marca);
                if (ordenadorBuscado.addPeriferico(nuevoPeriferico)) {
                    anadidosCorrectamente++;
                }
            }
            System.out.println("Se pudieron anadir correctamente " + anadidosCorrectamente + " perifericos.");
        }

        // !IMPORTANT: ENUNCIADO - Paso 4: "Eliminar perifericos estropeados de todos los ordenadores".
        // *INFO: Se devuelve el total eliminado global, que es justo lo solicitado por el PDF.
        // *INFO: Evita el error tipico de contar por ordenador pero no totalizar el acumulado final.
        System.out.println();
        System.out.println("4) Perifericos estropeados");
        int totalEliminadosAveriados = gestor.eliminarPerifericosAveriados();
        System.out.println("Total de perifericos averiados eliminados: " + totalEliminadosAveriados);

        // !IMPORTANT: ENUNCIADO - Paso 5: "Eliminar periferico por marca y tipo en cualquier ordenador".
        // *INFO: Se usa ArrayList<String> para guardar los numeros de serie afectados, como pide el enunciado.
        // *INFO: Evita el error tipico de perder la trazabilidad de en qué ordenadores se hizo la eliminacion.
        System.out.println();
        System.out.println("5) Eliminar periferico por marca y tipo");
        System.out.print("Marca del periferico a eliminar: ");
        String marcaEliminar = scanner.nextLine();
        System.out.print("Tipo del periferico a eliminar: ");
        String tipoEliminar = scanner.nextLine();
        Periferico perifericoABuscar = new Periferico(tipoEliminar, marcaEliminar);
        ArrayList<String> seriesAfectadas = gestor.eliminarPerifericoEnTodos(perifericoABuscar);
        if (seriesAfectadas.isEmpty()) {
            System.out.println("No se elimino ese periferico en ningun ordenador.");
        } else {
            System.out.println("Ordenadores afectados: " + seriesAfectadas);
        }

        // !IMPORTANT: ENUNCIADO - Paso 6: "Mostrar perifericos de ordenadores con mas de dos nucleos".
        // *INFO: El filtro se hace sobre el valor de nucleos del array procesador[0].
        // *INFO: Evita el error tipico de filtrar por RAM o por cantidad de perifericos.
        System.out.println();
        System.out.println("6) Mostrar perifericos de ordenadores con mas de dos nucleos");
        gestor.mostrarPerifericosOrdenadoresConMasDeDosNucleos();

        // !IMPORTANT: ENUNCIADO - Paso 7: "Anadir 2 nucleos a todos los ordenadores".
        // *INFO: Se actualiza a todos, sin excepciones, para cumplir literalmente el enunciado.
        // *INFO: Evita el error tipico de actualizar solo al ultimo o al primero del conjunto.
        System.out.println();
        System.out.println("7) Actualizar nucleos (+2 a todos)");
        gestor.actualizarNucleosSumandoDos();
        System.out.println("Actualizacion de nucleos completada.");

        // !IMPORTANT: ENUNCIADO - Paso 8: "Eliminar todos los equipos con mas de dos perifericos".
        // *INFO: Se informa del total de equipos eliminados para poder verificar la operacion.
        // *INFO: Evita el error tipico de eliminar pero no reportar resultado.
        System.out.println();
        System.out.println("8) Eliminar equipos con mas de dos perifericos");
        int totalEquiposEliminados = gestor.eliminarEquiposConMasDeDosPerifericos();
        System.out.println("Equipos eliminados: " + totalEquiposEliminados);

        // TODO: Como mejora futura de clase, se podria persistir la coleccion en fichero para no perder datos al cerrar.
        scanner.close();
    }

    private static Ordenador leerOrdenador(Scanner scanner) {
        // !IMPORTANT: ENUNCIADO (constructor de Ordenador):
        // !IMPORTANT: Se necesita numero de serie, marca, nucleos, velocidad, RAM y maximo de perifericos.
        // *INFO: Se centraliza la lectura aqui para no duplicar codigo en main.
        // *INFO: Evita el error tipico de pedir unos campos en un sitio y otros en otro.
        System.out.print("Numero de serie: ");
        String numeroSerie = scanner.nextLine();

        System.out.print("Marca: ");
        String marca = scanner.nextLine();

        System.out.print("Nucleos del procesador: ");
        int nucleos = Integer.parseInt(scanner.nextLine());

        System.out.print("Velocidad del procesador: ");
        double velocidad = Double.parseDouble(scanner.nextLine());

        System.out.print("Memoria RAM (GB, sin decimales): ");
        int memoriaRam = Integer.parseInt(scanner.nextLine());

        System.out.print("Numero maximo de perifericos: ");
        int numeroMaximoPerifericos = Integer.parseInt(scanner.nextLine());

        // *INFO: El estado inicial real (sin perifericos y apagado) lo garantiza el constructor de Ordenador.
        // *INFO: Evita el error tipico de intentar forzar ese estado desde fuera con setters (no permitidos).
        return new Ordenador(numeroSerie, marca, nucleos, velocidad, memoriaRam, numeroMaximoPerifericos);
    }
}

class GestorOrdenadores {
    // !IMPORTANT: ENUNCIADO (parte 2 del PDF):
    // !IMPORTANT: "Crear una clase gestora de objetos Ordenador a traves de un HashSet".
    private final HashSet<Ordenador> ordenadores;

    public GestorOrdenadores() {
        // !IMPORTANT: El enunciado exige una clase gestora basada en HashSet de Ordenador.
        // *INFO: HashSet impide duplicados segun equals/hashCode y evita errores tipicos de insertar repetidos.
        this.ordenadores = new HashSet<>();
    }

    public boolean addOrdenador(Ordenador ordenador) {
        return ordenadores.add(ordenador);
    }

    public Ordenador buscarPorNumeroSerie(String numeroSerie) {
        // !IMPORTANT: ENUNCIADO - Paso 3: buscar por numero de serie antes de anadir perifericos.
        // *INFO: La busqueda de la tarea 3 se realiza por numero de serie, tal como pide el enunciado.
        // *INFO: Iterar el HashSet evita depender de ordenes concretos que HashSet no garantiza.
        for (Ordenador ordenador : ordenadores) {
            if (Objects.equals(ordenador.getNumeroSerie(), numeroSerie)) {
                return ordenador;
            }
        }
        return null;
    }

    public void mostrarDatosEquipos() {
        // !IMPORTANT: ENUNCIADO - Paso 2: mostrar cada ordenador en una linea diferente.
        if (ordenadores.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        for (Ordenador ordenador : ordenadores) {
            // !IMPORTANT: Formato en una linea por ordenador para cumplir la salida del enunciado.
            // *INFO: Mostrar numero de serie separado ayuda a localizar equipos y evita confundir datos largos.
            System.out.println("Numero de Serie: " + ordenador.getNumeroSerie() + " Datos: " + ordenador);
        }
    }

    public int eliminarPerifericosAveriados() {
        // !IMPORTANT: ENUNCIADO - Paso 4: eliminar perifericos averiados de TODOS los ordenadores.
        int eliminados = 0;
        for (Ordenador ordenador : ordenadores) {
            // *INFO: Se hace copia para recorrer mientras se elimina con removePeriferico sin ConcurrentModificationException.
            // *INFO: Este patron evita un error tipico al borrar elementos durante la iteracion directa del Set.
            ArrayList<Periferico> copia = new ArrayList<>(ordenador.getPerifericos());
            for (Periferico periferico : copia) {
                if (periferico.isAveriado() && ordenador.removePeriferico(periferico)) {
                    eliminados++;
                }
            }
        }
        return eliminados;
    }

    public ArrayList<String> eliminarPerifericoEnTodos(Periferico periferico) {
        // !IMPORTANT: ENUNCIADO - Paso 5: eliminar ese periferico en cualquier ordenador donde este instalado.
        // !IMPORTANT: Guardar los numeros de serie afectados en un ArrayList.
        ArrayList<String> seriesAfectadas = new ArrayList<>();
        for (Ordenador ordenador : ordenadores) {
            // *INFO: Se usa removePeriferico de Ordenador, como exige expresamente el punto 5 del enunciado.
            // *INFO: Guardar series en ArrayList cumple la estructura pedida y evita perder repeticiones de recorrido.
            if (ordenador.removePeriferico(periferico)) {
                seriesAfectadas.add(ordenador.getNumeroSerie());
            }
        }
        return seriesAfectadas;
    }

    public void mostrarPerifericosOrdenadoresConMasDeDosNucleos() {
        // !IMPORTANT: ENUNCIADO - Paso 6: listar perifericos solo de ordenadores con mas de dos nucleos.
        for (Ordenador ordenador : ordenadores) {
            if (ordenador.getProcesador()[0] > 2) {
                System.out.println(
                        "Perifericos del ordenador con numero de serie " + ordenador.getNumeroSerie() + " : "
                                + ordenador.getPerifericos());
            }
        }
    }

    public void actualizarNucleosSumandoDos() {
        // !IMPORTANT: ENUNCIADO - Paso 7: sumar 2 nucleos a TODOS los ordenadores.
        for (Ordenador ordenador : ordenadores) {
            // !IMPORTANT: El punto 7 pide sumar 2 nucleos a todos los ordenadores.
            // ?QUESTION: El PDF no define setter especifico; se actualiza el array de procesador recibido por getProcesador().
            ordenador.getProcesador()[0] = ordenador.getProcesador()[0] + 2;
        }
    }

    public int eliminarEquiposConMasDeDosPerifericos() {
        // !IMPORTANT: ENUNCIADO - Paso 8: eliminar equipos con mas de dos perifericos.
        int eliminados = 0;
        Iterator<Ordenador> iterator = ordenadores.iterator();
        while (iterator.hasNext()) {
            Ordenador ordenador = iterator.next();
            if (ordenador.getPerifericos().size() > 2) {
                // *INFO: Se elimina con Iterator para no romper la iteracion del HashSet.
                // *INFO: Esto evita el error tipico ConcurrentModificationException.
                iterator.remove();
                eliminados++;
            }
        }
        return eliminados;
    }
}

class Ordenador {
    // !IMPORTANT: ENUNCIADO (clase Ordenador) - atributos obligatorios:
    // !IMPORTANT: numero de serie, marca, procesador[2] (nucleos, velocidad), RAM (sin decimales),
    // !IMPORTANT: perifericos en HashSet por agregacion, numero maximo de perifericos (constante/final), encendido.
    private final String numeroSerie;
    private final String marca;
    private final double[] procesador;
    private final int memoriaRam;
    private final HashSet<Periferico> perifericos;
    private final int numeroMaximoPerifericos;
    private boolean encendido;

    public Ordenador(
            String numeroSerie,
            String marca,
            int nucleosProcesador,
            double velocidadProcesador,
            int memoriaRam,
            int numeroMaximoPerifericos) {
        // !IMPORTANT: ENUNCIADO - constructor:
        // !IMPORTANT: Para crear un ordenador se piden estos campos y SIEMPRE nace sin perifericos y apagado.
        // !IMPORTANT: Se respeta el modelo del PDF: procesador como array [nucleos, velocidad].
        // *INFO: Este formato evita errores tipicos de intercambiar campos al calcular consumo.
        this.numeroSerie = numeroSerie;
        this.marca = marca;
        this.procesador = new double[2];
        this.procesador[0] = nucleosProcesador;
        this.procesador[1] = velocidadProcesador;
        this.memoriaRam = memoriaRam;
        this.numeroMaximoPerifericos = numeroMaximoPerifericos;

        // !IMPORTANT: El ordenador se crea siempre sin perifericos y apagado.
        // *INFO: Forzar estado inicial uniforme evita inconsistencias entre instancias.
        this.perifericos = new HashSet<>();
        this.encendido = false;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public String getMarca() {
        return marca;
    }

    public double[] getProcesador() {
        // !IMPORTANT: ENUNCIADO - hay getters para todos los atributos y no hay setters.
        // !IMPORTANT: Para el paso 7 necesitamos poder modificar nucleos sin inventar un setter nuevo.
        // !IMPORTANT: Se devuelve la referencia para permitir el paso 7 sin setters, segun la restriccion del enunciado.
        // *INFO: Esto evita inventar metodos no pedidos, aunque expone mutabilidad del array.
        return procesador;
    }

    public int getMemoriaRam() {
        return memoriaRam;
    }

    public Set<Periferico> getPerifericos() {
        // !IMPORTANT: ENUNCIADO - "NO se permite que getPerifericos devuelva un HashSet de Perifericos".
        // !IMPORTANT: No se devuelve HashSet, cumpliendo literalmente la restriccion del enunciado.
        // *INFO: Se devuelve vista inmodificable para evitar cambios externos saltandose reglas de negocio.
        return Collections.unmodifiableSet(perifericos);
    }

    public int getNumeroMaximoPerifericos() {
        return numeroMaximoPerifericos;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public boolean addPeriferico(Periferico periferico) {
        // !IMPORTANT: ENUNCIADO - addPeriferico devuelve boolean e impide superar maximo y duplicados.
        // !IMPORTANT: No se puede superar el maximo, y HashSet ya impide duplicados.
        // *INFO: Comprobar null evita el error tipico de insertar referencia nula en operaciones posteriores.
        if (periferico == null) {
            return false;
        }
        if (perifericos.size() >= numeroMaximoPerifericos) {
            return false;
        }
        return perifericos.add(periferico);
    }

    public boolean removePeriferico(Periferico periferico) {
        // !IMPORTANT: ENUNCIADO - removePeriferico recibe periferico y devuelve si se pudo eliminar o no.
        // *INFO: Se delega en HashSet.remove, que usa equals/hashCode para encontrar el elemento.
        // *INFO: Evita el error tipico de eliminar por referencia exacta en lugar de igualdad logica.
        return perifericos.remove(periferico);
    }

    public double calcularConsumo() {
        // !IMPORTANT: ENUNCIADO - formula exacta: (nucleos del procesador * velocidad del procesador) / 10.
        // !IMPORTANT: Formula exacta del enunciado: (nucleos * velocidad) / 10.
        // *INFO: Usar double evita truncado entero y errores tipicos de perder decimales.
        return (procesador[0] * procesador[1]) / 10.0;
    }

    @Override
    public boolean equals(Object o) {
        // !IMPORTANT: ENUNCIADO - dos Ordenador son iguales por numero de serie y marca.
        // !IMPORTANT: Dos ordenadores son iguales por numero de serie y marca.
        // *INFO: Ignorar el resto de campos evita duplicados logicos en HashSet por cambios de hardware.
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ordenador)) {
            return false;
        }
        Ordenador ordenador = (Ordenador) o;
        return Objects.equals(numeroSerie, ordenador.numeroSerie) && Objects.equals(marca, ordenador.marca);
    }

    @Override
    public int hashCode() {
        // !IMPORTANT: ENUNCIADO - hashCode coherente con equals para colecciones hash.
        // *INFO: hashCode y equals usan los mismos campos para comportamiento correcto en HashSet.
        // *INFO: Esto evita el error tipico de no poder localizar o eliminar objetos que si son "iguales".
        return Objects.hash(numeroSerie, marca);
    }

    @Override
    public String toString() {
        // !IMPORTANT: ENUNCIADO - toString debe incluir todos los atributos, consumo y datos de perifericos.
        // *INFO: Mostrar consumo aqui permite verificar facilmente la formula en cada impresion de ordenador.
        // *INFO: Evita el error tipico de olvidar campos clave al depurar o mostrar por pantalla.
        return "Ordenador{" +
                "numeroSerie='" + numeroSerie + '\'' +
                ", marca='" + marca + '\'' +
                ", procesador=[nucleos=" + procesador[0] + ", velocidad=" + procesador[1] + "]" +
                ", memoriaRam=" + memoriaRam +
                ", perifericos=" + perifericos +
                ", numeroMaximoPerifericos=" + numeroMaximoPerifericos +
                ", encendido=" + encendido +
                ", consumo=" + calcularConsumo() +
                '}';
    }
}

class Periferico {
    // !IMPORTANT: ENUNCIADO (clase Periferico) - atributos obligatorios:
    // !IMPORTANT: tipo, marca, averiado; constructor(tipo, marca); getters; equals/hashCode por tipo+marca; toString.
    private final String tipo;
    private final String marca;
    private final boolean averiado;

    public Periferico(String tipo, String marca) {
        // !IMPORTANT: ENUNCIADO - al crear Periferico, averiado se genera aleatoriamente con Random.nextBoolean().
        // !IMPORTANT: Se implementa exactamente el requisito: Random random = new Random(); random.nextBoolean();
        // *INFO: Esto evita errores tipicos de dejar averiado fijo o depender de entrada manual del usuario.
        Random random = new Random();
        this.averiado = random.nextBoolean();
        this.tipo = tipo;
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMarca() {
        return marca;
    }

    public boolean isAveriado() {
        return averiado;
    }

    @Override
    public boolean equals(Object o) {
        // !IMPORTANT: ENUNCIADO - equals de Periferico por tipo y marca.
        // !IMPORTANT: Igualdad de Periferico por tipo y marca, exactamente como exige el PDF.
        // *INFO: Excluir averiado evita considerar distintos dos objetos que representan el mismo periferico fisico.
        if (this == o) {
            return true;
        }
        if (!(o instanceof Periferico)) {
            return false;
        }
        Periferico that = (Periferico) o;
        return Objects.equals(tipo, that.tipo) && Objects.equals(marca, that.marca);
    }

    @Override
    public int hashCode() {
        // !IMPORTANT: ENUNCIADO - hashCode de Periferico coherente con equals (tipo y marca).
        // *INFO: Coherencia con equals para funcionamiento correcto en HashSet de perifericos.
        // *INFO: Evita errores tipicos de add/remove incoherentes por hash distinto.
        return Objects.hash(tipo, marca);
    }

    @Override
    public String toString() {
        // *INFO: toString muestra todos los atributos para facilitar trazas y comprobacion de resultados.
        // *INFO: Evita el error tipico de depurar "a ciegas" sin ver tipo, marca y estado averiado.
        return "Periferico{" +
                "tipo='" + tipo + '\'' +
                ", marca='" + marca + '\'' +
                ", averiado=" + averiado +
                '}';
    }
}
