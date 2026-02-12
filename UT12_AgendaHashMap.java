/******************************************************************************************
 *  📚 CURSO DE PROGRAMACIÓN EN JAVA - AUTOR: Joaquín Rodríguez Llanes
 *  📅 FECHA: 2025
 *  🔹 UNIDAD 12: COLECCIONES - HASHMAP
 *  📞 EJERCICIO: AGENDA TELEFÓNICA CON HASHMAP
 ******************************************************************************************/

import java.util.HashMap;
import java.util.Scanner;
import java.util.Objects;

// ═══════════════════════════════════════════════════════════════════════════════
// CLASE PERSONA - Funciona como CLAVE del HashMap (nombre + apellidos)
// ═══════════════════════════════════════════════════════════════════════════════
class Persona {
    private String nombre;
    private String apellidos;

    public Persona(String nombre, String apellidos) {
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public String toString() { return nombre + " " + apellidos; }

    // OBLIGATORIO para ser clave de HashMap
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona otra = (Persona) obj;
        return nombre.equals(otra.nombre) && apellidos.equals(otra.apellidos);
    }

    public int hashCode() {
        return Objects.hash(nombre, apellidos);
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// CLASE AGENDA - HashMap<Persona, Integer> (Persona=clave, Teléfono=valor)
// ═══════════════════════════════════════════════════════════════════════════════
class Agenda {
    private HashMap<Persona, Integer> contactos = new HashMap<>();

    // addContacto: devuelve true si sobrescribió (NO usa containsKey)
    public boolean addContacto(String nombre, String apellidos, int telefono) {
        Persona p = new Persona(nombre, apellidos);
        Integer anterior = contactos.put(p, telefono);
        return anterior != null;
    }

    // getTelefono: devuelve null si no existe (NO usa containsKey)
    public Integer getTelefono(String nombre, String apellidos) {
        return contactos.get(new Persona(nombre, apellidos));
    }

    // getPersona: busca por teléfono (recorre el HashMap)
    public Persona getPersona(int telefono) {
        for (HashMap.Entry<Persona, Integer> e : contactos.entrySet()) {
            if (e.getValue() == telefono) return e.getKey();
        }
        return null;
    }

    // updateTelefono: usa replace(), devuelve true si actualizó
    public boolean updateTelefono(String nombre, String apellidos, int nuevoTel) {
        Persona p = new Persona(nombre, apellidos);
        return contactos.replace(p, nuevoTel) != null;
    }

    public void mostrarTodos() {
        if (contactos.isEmpty()) { System.out.println("Agenda vacia."); return; }
        for (HashMap.Entry<Persona, Integer> e : contactos.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}

// ═══════════════════════════════════════════════════════════════════════════════
// CLASE PRINCIPAL - Menú
// ═══════════════════════════════════════════════════════════════════════════════
public class UT12_AgendaHashMap {

    static Agenda agenda = new Agenda();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int op = -1;
        while (op != 0) {
            System.out.println("\n=== AGENDA ===");
            System.out.println("1. Anadir contacto");
            System.out.println("2. Buscar telefono por nombre");
            System.out.println("3. Buscar persona por telefono");
            System.out.println("4. Actualizar telefono");
            System.out.println("5. Mostrar todos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            op = leerInt();

            if (op == 1) anadirContacto();
            else if (op == 2) buscarTelefono();
            else if (op == 3) buscarPersona();
            else if (op == 4) actualizarTelefono();
            else if (op == 5) agenda.mostrarTodos();
        }
        sc.close();
    }

    static int leerInt() {
        while (!sc.hasNextInt()) sc.next();
        int n = sc.nextInt(); sc.nextLine();
        return n;
    }

    static void anadirContacto() {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Apellidos: "); String ape = sc.nextLine();
        System.out.print("Telefono: "); int tel = leerInt();
        boolean sobrescrito = agenda.addContacto(nom, ape, tel);
        System.out.println(sobrescrito ? "Contacto actualizado" : "Contacto anadido");
    }

    static void buscarTelefono() {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Apellidos: "); String ape = sc.nextLine();
        Integer tel = agenda.getTelefono(nom, ape);
        System.out.println(tel != null ? "Telefono: " + tel : "No encontrado");
    }

    static void buscarPersona() {
        System.out.print("Telefono: "); int tel = leerInt();
        Persona p = agenda.getPersona(tel);
        System.out.println(p != null ? "Persona: " + p : "No encontrado");
    }

    static void actualizarTelefono() {
        System.out.print("Nombre: "); String nom = sc.nextLine();
        System.out.print("Apellidos: "); String ape = sc.nextLine();
        System.out.print("Nuevo telefono: "); int tel = leerInt();
        boolean ok = agenda.updateTelefono(nom, ape, tel);
        System.out.println(ok ? "Actualizado!" : "No existe ese contacto");
    }
}
