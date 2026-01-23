/*
 * ******************************************************************************************
 *              📚 **GUÍA COMPLETA: CLASES Y OBJETOS EN JAVA - NIVEL AVANZADO**
 * ──────────────────────────────────────────────────────────────────────────────────────
 * 
 * 🎯 **OBJETIVOS DE APRENDIZAJE:**
 * 
 * ✅ Programación Orientada a Objetos (POO) en Java
 * ✅ Clases, Objetos e Instancias
 * ✅ Atributos y Métodos (Estáticos e Instancia)
 * ✅ Constructores y Sobrecarga de Constructores
 * ✅ Encapsulación (private, public, protected, default)
 * ✅ Getters y Setters con Validación
 * ✅ La palabra clave 'this'
 * ✅ Métodos toString(), equals() y hashCode()
 * ✅ Sobrecarga de Métodos (Method Overloading)
 * ✅ Variables y Métodos Estáticos (static)
 * ✅ Constantes en Java (final)
 * ✅ Clases Anidadas (Inner Classes)
 * ✅ Referencias de Objetos y Paso por Referencia
 * ✅ Composición de Objetos
 * ✅ Comparación de Objetos
 * 
 * 🚀 **¡La guía más completa para dominar la POO en Java!**
 ******************************************************************************************/

/*
 * ═══════════════════════════════════════════════════════════════════════════════════════
 *                         🧠 **TEORÍA: FUNDAMENTOS DE POO EN JAVA**
 * ═══════════════════════════════════════════════════════════════════════════════════════
 * 
 * 🟢 **1. ¿QUÉ ES UNA CLASE?**
 * ──────────────────────────
 * Una clase es un PLANO o PLANTILLA que define:
 *   • ATRIBUTOS (características/datos)
 *   • MÉTODOS (comportamientos/acciones)
 * 
 * 🔹 Analogía: La clase es como el plano de una casa
 * 
 * 
 * 🟠 **2. ¿QUÉ ES UN OBJETO?**
 * ──────────────────────────
 * Un objeto es una INSTANCIA específica de una clase
 *   • Cada objeto tiene sus propios valores de atributos
 *   • Se crea usando la palabra clave 'new'
 * 
 * 🔹 Analogía: El objeto es la casa construida según el plano
 * 
 * 
 * 🔵 **3. LOS 4 PILARES DE LA POO**
 * ──────────────────────────────────
 * 1️⃣ ENCAPSULACIÓN: Ocultar datos internos y exponer solo lo necesario
 * 2️⃣ HERENCIA: Crear clases basadas en otras clases existentes
 * 3️⃣ POLIMORFISMO: Un mismo método puede tener diferentes comportamientos
 * 4️⃣ ABSTRACCIÓN: Modelar solo las características esenciales
 * 
 * 
 * 🟣 **4. MODIFICADORES DE ACCESO**
 * ──────────────────────────────────
 * • public:    Accesible desde cualquier lugar
 * • private:   Solo accesible dentro de la misma clase
 * • protected: Accesible en la misma clase, subclases y mismo paquete
 * • (default): Accesible solo en el mismo paquete
 * 
 * ═══════════════════════════════════════════════════════════════════════════════════════
 */

public class UT5_ClasesObjetosCompleto {
    public static void main(String[] args) {
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 1: CREACIÓN DE OBJETOS Y USO DE CONSTRUCTORES
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 1: Creación de Objetos y Constructores          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        // ! Constructor con parámetros
        Estudiante estudiante1 = new Estudiante("Jorge", 20, "DAW");
        estudiante1.mostrarInformacion();
        
        // ! Constructor sin parámetros
        Estudiante estudiante2 = new Estudiante();
        estudiante2.mostrarInformacion();
        
        // ! Constructor con algunos parámetros
        Estudiante estudiante3 = new Estudiante("María", 22);
        estudiante3.mostrarInformacion();
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 2: ENCAPSULACIÓN Y GETTERS/SETTERS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 2: Encapsulación y Getters/Setters              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        // * Modificar atributos usando setters (con validación)
        estudiante2.setNombre("Carlos");
        estudiante2.setEdad(25);
        estudiante2.setCurso("ASIR");
        estudiante2.mostrarInformacion();
        
        // ! Intentar establecer valores inválidos
        estudiante2.setEdad(-5);  // No se permite edad negativa
        estudiante2.setEdad(150); // No se permite edad mayor a 120
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 3: MÉTODOS ESTÁTICOS VS MÉTODOS DE INSTANCIA
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 3: Métodos Estáticos vs Métodos de Instancia    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        // ! Método estático: se llama desde la clase, no desde el objeto
        System.out.println("Total de estudiantes creados: " + Estudiante.getTotalEstudiantes());
        
        // ! Método de instancia: se llama desde el objeto
        estudiante1.estudiar(3);
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 4: SOBRECARGA DE MÉTODOS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 4: Sobrecarga de Métodos (Method Overloading)   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        estudiante1.estudiar();           // Sin parámetros
        estudiante1.estudiar(2);          // Con horas
        estudiante1.estudiar("Java", 4);  // Con asignatura y horas
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 5: MÉTODO toString() Y COMPARACIÓN DE OBJETOS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 5: toString() y Comparación de Objetos          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        // * El método toString() se llama automáticamente al imprimir
        System.out.println("Estudiante 1: " + estudiante1);
        System.out.println("Estudiante 3: " + estudiante3);
        
        // ! Comparación de objetos con equals()
        Estudiante estudiante4 = new Estudiante("Jorge", 20, "DAW");
        System.out.println("\n¿Son iguales estudiante1 y estudiante4? " + estudiante1.equals(estudiante4));
        System.out.println("¿Son iguales estudiante1 y estudiante2? " + estudiante1.equals(estudiante2));
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 6: COMPOSICIÓN DE OBJETOS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 6: Composición de Objetos                       ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        Direccion direccion1 = new Direccion("Calle Mayor", 123, "Madrid", "28013");
        Profesor profesor1 = new Profesor("Dr. García", "Programación", direccion1);
        profesor1.mostrarInformacion();
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 7: REFERENCIAS DE OBJETOS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 7: Referencias de Objetos y Paso por Referencia ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        Estudiante refEstudiante = estudiante1;  // Ambas variables apuntan al mismo objeto
        System.out.println("Estudiante original: " + estudiante1.getNombre());
        
        refEstudiante.setNombre("Jorge Modificado");
        System.out.println("Después de modificar la referencia:");
        System.out.println("Estudiante original: " + estudiante1.getNombre());
        System.out.println("Referencia: " + refEstudiante.getNombre());
        
        // ! Modificar objeto a través de método
        modificarEstudiante(estudiante1);
        System.out.println("Después de pasar por método: " + estudiante1.getNombre());
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 8: CLASE ANIDADA (INNER CLASS)
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 8: Clases Anidadas (Inner Classes)              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        Universidad universidad = new Universidad("Universidad Politécnica");
        Universidad.Facultad facultad = universidad.new Facultad("Informática", 500);
        facultad.mostrarInfo();
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 9: CONSTANTES Y VARIABLES ESTÁTICAS
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 9: Constantes y Variables Estáticas             ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Edad mínima para ser estudiante: " + Estudiante.EDAD_MINIMA);
        System.out.println("Edad máxima para ser estudiante: " + Estudiante.EDAD_MAXIMA);
        System.out.println("Total de estudiantes en el sistema: " + Estudiante.getTotalEstudiantes());
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 📌 EJEMPLO 10: BIBLIOTECA - EJEMPLO COMPLETO
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║  EJEMPLO 10: Sistema de Biblioteca (Ejemplo Completo)    ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");
        
        Libro libro1 = new Libro("Clean Code", "Robert C. Martin", "978-0132350884", 464);
        Libro libro2 = new Libro("Effective Java", "Joshua Bloch", "978-0134685991", 416);
        
        System.out.println(libro1);
        System.out.println(libro2);
        
        libro1.prestar();
        libro1.prestar(); // Ya está prestado
        libro1.devolver();
        
        System.out.println("\nTotal de libros en el sistema: " + Libro.getTotalLibros());
        
        
        // ═══════════════════════════════════════════════════════════════════════════════
        // * 🎯 EJERCICIOS PROPUESTOS PARA EL ALUMNO
        // ═══════════════════════════════════════════════════════════════════════════════
        
        System.out.println("\n╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║              🎯 EJERCICIOS PARA PRACTICAR                 ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
        
        // TODO: EJERCICIO 1 - Crear clase CuentaBancaria
        // * Atributos: numeroCuenta, titular, saldo, fechaApertura
        // * Métodos: depositar, retirar, consultarSaldo, transferir
        // * Implementar validaciones apropiadas
        
        // TODO: EJERCICIO 2 - Crear clase Vehiculo
        // * Atributos: marca, modelo, año, kilometraje, precio
        // * Métodos: acelerar, frenar, mostrarInfo, calcularDepreciacion
        // * Usar sobrecarga de constructores
        
        // TODO: EJERCICIO 3 - Crear clase Producto con Categoria (composición)
        // * Clase Producto: codigo, nombre, precio, stock, categoria
        // * Clase Categoria: id, nombre, descripcion
        // * Implementar métodos para gestionar stock
        
        // TODO: EJERCICIO 4 - Crear clase Empleado con validaciones complejas
        // * Validar DNI/NIE español
        // * Validar email
        // * Calcular salario con bonus
        // * Implementar equals() y hashCode()
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🔧 MÉTODO AUXILIAR: Demostración de paso por referencia
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * * Este método demuestra que en Java los objetos se pasan por referencia.
     * * Al modificar el objeto dentro del método, el objeto original también cambia.
     * 
     * @param estudiante El estudiante a modificar
     */
    private static void modificarEstudiante(Estudiante estudiante) {
        estudiante.setNombre("Nombre Cambiado en Método");
    }
}


// ═══════════════════════════════════════════════════════════════════════════════════════
// ║                                                                                       ║
// ║                         📚 CLASE: ESTUDIANTE (COMPLETA)                              ║
// ║                                                                                       ║
// ═══════════════════════════════════════════════════════════════════════════════════════

/**
 * * Clase Estudiante que representa a un estudiante con todas las características
 * * y comportamientos necesarios para un sistema educativo.
 * 
 * ? Esta clase demuestra:
 * ? - Encapsulación de atributos
 * ? - Múltiples constructores (sobrecarga)
 * ? - Getters y Setters con validación
 * ? - Métodos estáticos y de instancia
 * ? - Variables estáticas y constantes
 * ? - Sobrecarga de métodos
 * ? - Métodos toString() y equals()
 */
class Estudiante {
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🔒 ATRIBUTOS PRIVADOS (ENCAPSULACIÓN)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    // ! Atributos de instancia (cada objeto tiene sus propios valores)
    private String nombre;
    private int edad;
    private String curso;
    private double notaMedia;
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🌍 ATRIBUTOS ESTÁTICOS Y CONSTANTES
    // ═══════════════════════════════════════════════════════════════════════════════
    
    // ! Variable estática: compartida por todas las instancias de la clase
    private static int totalEstudiantes = 0;
    
    // ! Constantes: valores que no cambian (final)
    public static final int EDAD_MINIMA = 16;
    public static final int EDAD_MAXIMA = 120;
    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 10.0;
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🏗️ CONSTRUCTORES (SOBRECARGA DE CONSTRUCTORES)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * ! Constructor completo con todos los parámetros
     * 
     * @param nombre Nombre del estudiante
     * @param edad Edad del estudiante
     * @param curso Curso en el que está matriculado
     */
    public Estudiante(String nombre, int edad, String curso) {
        this.nombre = nombre;
        setEdad(edad);  // * Usar setter para validación
        this.curso = curso;
        this.notaMedia = 0.0;
        totalEstudiantes++;  // * Incrementar contador estático
        System.out.println("✅ Estudiante creado (Constructor completo)");
    }
    
    /**
     * ! Constructor con nombre y edad (sin curso)
     * 
     * @param nombre Nombre del estudiante
     * @param edad Edad del estudiante
     */
    public Estudiante(String nombre, int edad) {
        this(nombre, edad, "Sin asignar");  // * Llamar a otro constructor
        System.out.println("✅ Estudiante creado (Constructor parcial)");
    }
    
    /**
     * ! Constructor sin parámetros (valores por defecto)
     */
    public Estudiante() {
        this("Sin nombre", 18, "Sin asignar");  // * Llamar a constructor completo
        System.out.println("✅ Estudiante creado (Constructor vacío)");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 📖 GETTERS (Métodos de acceso a los atributos)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * ? Obtener el nombre del estudiante
     * @return El nombre del estudiante
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * ? Obtener la edad del estudiante
     * @return La edad del estudiante
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * ? Obtener el curso del estudiante
     * @return El curso del estudiante
     */
    public String getCurso() {
        return curso;
    }
    
    /**
     * ? Obtener la nota media del estudiante
     * @return La nota media del estudiante
     */
    public double getNotaMedia() {
        return notaMedia;
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * ✏️ SETTERS (Métodos para modificar los atributos con validación)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * * Establecer el nombre del estudiante
     * @param nombre El nuevo nombre (no puede estar vacío)
     */
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("⚠️ El nombre no puede estar vacío");
        }
    }
    
    /**
     * * Establecer la edad del estudiante con validación
     * @param edad La nueva edad (debe estar entre EDAD_MINIMA y EDAD_MAXIMA)
     */
    public void setEdad(int edad) {
        if (edad >= EDAD_MINIMA && edad <= EDAD_MAXIMA) {
            this.edad = edad;
        } else {
            System.out.println("⚠️ Edad inválida. Debe estar entre " + EDAD_MINIMA + " y " + EDAD_MAXIMA);
            this.edad = EDAD_MINIMA;  // * Valor por defecto
        }
    }
    
    /**
     * * Establecer el curso del estudiante
     * @param curso El nuevo curso
     */
    public void setCurso(String curso) {
        if (curso != null && !curso.trim().isEmpty()) {
            this.curso = curso;
        } else {
            this.curso = "Sin asignar";
        }
    }
    
    /**
     * * Establecer la nota media del estudiante con validación
     * @param notaMedia La nueva nota media (debe estar entre 0 y 10)
     */
    public void setNotaMedia(double notaMedia) {
        if (notaMedia >= NOTA_MINIMA && notaMedia <= NOTA_MAXIMA) {
            this.notaMedia = notaMedia;
        } else {
            System.out.println("⚠️ Nota inválida. Debe estar entre " + NOTA_MINIMA + " y " + NOTA_MAXIMA);
        }
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🔧 MÉTODOS DE INSTANCIA (comportamiento del objeto)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * * Mostrar toda la información del estudiante
     */
    public void mostrarInformacion() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│  📋 INFORMACIÓN DEL ESTUDIANTE          │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  Nombre:     " + nombre);
        System.out.println("│  Edad:       " + edad + " años");
        System.out.println("│  Curso:      " + curso);
        System.out.println("│  Nota Media: " + notaMedia);
        System.out.println("└─────────────────────────────────────────┘");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🔄 SOBRECARGA DE MÉTODOS (Method Overloading)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * * Método estudiar sin parámetros
     */
    public void estudiar() {
        System.out.println("📚 " + nombre + " está estudiando...");
    }
    
    /**
     * * Método estudiar con horas
     * @param horas Número de horas de estudio
     */
    public void estudiar(int horas) {
        System.out.println("📚 " + nombre + " estudió durante " + horas + " hora(s)");
    }
    
    /**
     * * Método estudiar con asignatura y horas
     * @param asignatura La asignatura que estudia
     * @param horas Número de horas de estudio
     */
    public void estudiar(String asignatura, int horas) {
        System.out.println("📚 " + nombre + " estudió " + asignatura + " durante " + horas + " hora(s)");
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🌍 MÉTODOS ESTÁTICOS (pertenecen a la clase, no al objeto)
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * ? Obtener el total de estudiantes creados
     * @return El número total de estudiantes
     */
    public static int getTotalEstudiantes() {
        return totalEstudiantes;
    }
    
    // ═══════════════════════════════════════════════════════════════════════════════
    // * 🔍 MÉTODOS ESPECIALES: toString() y equals()
    // ═══════════════════════════════════════════════════════════════════════════════
    
    /**
     * * Representación en texto del objeto
     * * Este método se llama automáticamente cuando se imprime el objeto
     * @return Cadena de texto con la información del estudiante
     */
    @Override
    public String toString() {
        return "Estudiante{" +
               "nombre='" + nombre + '\'' +
               ", edad=" + edad +
               ", curso='" + curso + '\'' +
               ", notaMedia=" + notaMedia +
               '}';
    }
    
    /**
     * * Comparar si dos estudiantes son iguales
     * * Dos estudiantes son iguales si tienen el mismo nombre, edad y curso
     * @param obj El objeto a comparar
     * @return true si son iguales, false si no
     */
    @Override
    public boolean equals(Object obj) {
        // ! Verificar si es el mismo objeto
        if (this == obj) return true;
        
        // ! Verificar si es null o de diferente clase
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // ! Convertir a Estudiante y comparar atributos
        Estudiante otro = (Estudiante) obj;
        return edad == otro.edad &&
               nombre.equals(otro.nombre) &&
               curso.equals(otro.curso);
    }
}


// ═══════════════════════════════════════════════════════════════════════════════════════
// ║                                                                                       ║
// ║                        📍 CLASE: DIRECCION (COMPOSICIÓN)                             ║
// ║                                                                                       ║
// ═══════════════════════════════════════════════════════════════════════════════════════

/**
 * * Clase Direccion que se usará como parte de otras clases (composición)
 */
class Direccion {
    private String calle;
    private int numero;
    private String ciudad;
    private String codigoPostal;
    
    /**
     * ! Constructor de Direccion
     */
    public Direccion(String calle, int numero, String ciudad, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }
    
    /**
     * * Obtener la dirección completa como String
     * @return La dirección completa
     */
    public String getDireccionCompleta() {
        return calle + " " + numero + ", " + ciudad + " (" + codigoPostal + ")";
    }
    
    // * Getters básicos
    public String getCalle() { return calle; }
    public int getNumero() { return numero; }
    public String getCiudad() { return ciudad; }
    public String getCodigoPostal() { return codigoPostal; }
    
    @Override
    public String toString() {
        return getDireccionCompleta();
    }
}


// ═══════════════════════════════════════════════════════════════════════════════════════
// ║                                                                                       ║
// ║                       👨‍🏫 CLASE: PROFESOR (USA COMPOSICIÓN)                           ║
// ║                                                                                       ║
// ═══════════════════════════════════════════════════════════════════════════════════════

/**
 * * Clase Profesor que demuestra la COMPOSICIÓN de objetos
 * * Un profesor "tiene una" dirección (relación HAS-A)
 */
class Profesor {
    private String nombre;
    private String especialidad;
    private Direccion direccion;  // ! Composición: Profesor tiene una Direccion
    
    /**
     * ! Constructor del Profesor
     */
    public Profesor(String nombre, String especialidad, Direccion direccion) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.direccion = direccion;
    }
    
    /**
     * * Mostrar información del profesor incluyendo su dirección
     */
    public void mostrarInformacion() {
        System.out.println("┌─────────────────────────────────────────┐");
        System.out.println("│  👨‍🏫 INFORMACIÓN DEL PROFESOR           │");
        System.out.println("├─────────────────────────────────────────┤");
        System.out.println("│  Nombre:       " + nombre);
        System.out.println("│  Especialidad: " + especialidad);
        System.out.println("│  Dirección:    " + direccion.getDireccionCompleta());
        System.out.println("└─────────────────────────────────────────┘");
    }
    
    // * Getters
    public String getNombre() { return nombre; }
    public String getEspecialidad() { return especialidad; }
    public Direccion getDireccion() { return direccion; }
}


// ═══════════════════════════════════════════════════════════════════════════════════════
// ║                                                                                       ║
// ║                    🏫 CLASE: UNIVERSIDAD (CON CLASE ANIDADA)                         ║
// ║                                                                                       ║
// ═══════════════════════════════════════════════════════════════════════════════════════

/**
 * * Clase Universidad que contiene una clase anidada (Inner Class)
 */
class Universidad {
    private String nombre;
    
    public Universidad(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * ! CLASE ANIDADA (INNER CLASS)
     * * Una clase dentro de otra clase
     * * Tiene acceso a los miembros de la clase externa
     */
    class Facultad {
        private String nombreFacultad;
        private int numeroEstudiantes;
        
        public Facultad(String nombreFacultad, int numeroEstudiantes) {
            this.nombreFacultad = nombreFacultad;
            this.numeroEstudiantes = numeroEstudiantes;
        }
        
        public void mostrarInfo() {
            // * Puede acceder a los atributos de la clase externa (Universidad)
            System.out.println("📍 Facultad: " + nombreFacultad);
            System.out.println("📍 Universidad: " + nombre);  // ! Accede a 'nombre' de Universidad
            System.out.println("📍 Estudiantes: " + numeroEstudiantes);
        }
    }
}


// ═══════════════════════════════════════════════════════════════════════════════════════
// ║                                                                                       ║
// ║                         📚 CLASE: LIBRO (EJEMPLO COMPLETO)                           ║
// ║                                                                                       ║
// ═══════════════════════════════════════════════════════════════════════════════════════

/**
 * * Clase Libro que representa un libro en una biblioteca
 * * Demuestra todos los conceptos de POO en un ejemplo práctico
 */
class Libro {
    // * Atributos privados
    private String titulo;
    private String autor;
    private String isbn;
    private int numeroPaginas;
    private boolean prestado;
    
    // * Variable estática - contador de libros
    private static int totalLibros = 0;
    
    // * Constantes
    public static final int PAGINAS_MINIMAS = 10;
    
    /**
     * ! Constructor completo
     */
    public Libro(String titulo, String autor, String isbn, int numeroPaginas) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.numeroPaginas = numeroPaginas;
        this.prestado = false;
        totalLibros++;
    }
    
    /**
     * * Prestar el libro
     */
    public void prestar() {
        if (!prestado) {
            prestado = true;
            System.out.println("✅ Libro '" + titulo + "' prestado correctamente");
        } else {
            System.out.println("⚠️ El libro '" + titulo + "' ya está prestado");
        }
    }
    
    /**
     * * Devolver el libro
     */
    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("✅ Libro '" + titulo + "' devuelto correctamente");
        } else {
            System.out.println("⚠️ El libro '" + titulo + "' no estaba prestado");
        }
    }
    
    /**
     * ? Método estático para obtener el total de libros
     */
    public static int getTotalLibros() {
        return totalLibros;
    }
    
    // * Getters
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getNumeroPaginas() { return numeroPaginas; }
    public boolean isPrestado() { return prestado; }
    
    @Override
    public String toString() {
        return "📖 Libro: '" + titulo + "' por " + autor + 
               " | ISBN: " + isbn + 
               " | Páginas: " + numeroPaginas +
               " | " + (prestado ? "🔴 PRESTADO" : "🟢 DISPONIBLE");
    }
}


/*
 * ═══════════════════════════════════════════════════════════════════════════════════════
 *                           📝 RESUMEN DE CONCEPTOS APRENDIDOS
 * ═══════════════════════════════════════════════════════════════════════════════════════
 * 
 * ✅ **CLASES Y OBJETOS**
 *    • Una clase es una plantilla, un objeto es una instancia
 *    • Los objetos se crean con 'new'
 * 
 * ✅ **ATRIBUTOS**
 *    • Variables de instancia (cada objeto tiene sus propios valores)
 *    • Variables estáticas (compartidas por todas las instancias)
 *    • Constantes (final - valores inmutables)
 * 
 * ✅ **MÉTODOS**
 *    • Métodos de instancia (requieren un objeto)
 *    • Métodos estáticos (se llaman desde la clase)
 *    • Sobrecarga de métodos (mismo nombre, diferentes parámetros)
 * 
 * ✅ **CONSTRUCTORES**
 *    • Inicializan los objetos
 *    • Pueden estar sobrecargados
 *    • Se puede llamar a un constructor desde otro con 'this()'
 * 
 * ✅ **ENCAPSULACIÓN**
 *    • Atributos privados (private)
 *    • Acceso mediante getters y setters
 *    • Validación en los setters
 * 
 * ✅ **LA PALABRA CLAVE 'this'**
 *    • Referencia al objeto actual
 *    • Distingue entre parámetros y atributos
 * 
 * ✅ **MÉTODOS ESPECIALES**
 *    • toString(): representación en texto del objeto
 *    • equals(): comparar objetos
 * 
 * ✅ **COMPOSICIÓN**
 *    • Un objeto puede contener otros objetos
 *    • Relación "HAS-A" (tiene un)
 * 
 * ✅ **CLASES ANIDADAS**
 *    • Clases dentro de otras clases
 *    • Tienen acceso a los miembros de la clase externa
 * 
 * ✅ **REFERENCIAS**
 *    • En Java, los objetos se pasan por referencia
 *    • Múltiples variables pueden apuntar al mismo objeto
 * 
 * ═══════════════════════════════════════════════════════════════════════════════════════
 * 
 * 🎯 **EJERCICIOS ADICIONALES RECOMENDADOS:**
 * 
 * 1. Implementar una clase `CuentaBancaria` con métodos de transferencia
 * 2. Crear un sistema de gestión de `Productos` con `Categorias`
 * 3. Diseñar clases para un sistema de reservas de hotel
 * 4. Implementar un juego simple con clases `Jugador`, `Enemigo`, `Arma`
 * 5. Crear un sistema de gestión de empleados con diferentes tipos
 * 
 * ═══════════════════════════════════════════════════════════════════════════════════════
 * 
 * 🚀 **¡Felicidades! Has completado la guía más completa de Clases y Objetos en Java!**
 * 
 * ═══════════════════════════════════════════════════════════════════════════════════════
 */
