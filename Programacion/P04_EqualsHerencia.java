import java.util.Objects;

public class P04_EqualsHerencia {

    /*
     * =============================================================
     * TEORIA: EQUALS EN HERENCIA
     * Basado en "Equals en herencia.pdf"
     * =============================================================
     * * El problema:
     *   Por defecto, el operador == compara REFERENCIAS (si apuntan
     *   al mismo objeto en memoria). Para comparar el CONTENIDO
     *   de dos objetos hay que sobreescribir equals().
     *
     * * Contrato de equals() (obligatorio cumplirlo):
     *   1. Reflexivo:    a.equals(a) -> true
     *   2. Simetrico:    a.equals(b) == b.equals(a)
     *   3. Transitivo:   si a.equals(b) y b.equals(c), entonces a.equals(c)
     *   4. Consistente:  siempre devuelve el mismo resultado si no cambia el estado
     *   5. No nulo:      a.equals(null) -> false (nunca NullPointerException)
     *
     * * hashCode() - relacion con equals():
     *   Si a.equals(b) == true, entonces a.hashCode() == b.hashCode().
     *   Es decir: siempre que sobreescribas equals(), DEBES sobreescribir hashCode().
     *   (De lo contrario HashMap, HashSet... no funcionan bien.)
     *
     * * Equals en herencia:
     *   Al sobreescribir equals() en una subclase hay que:
     *   1. Llamar a super.equals(obj) para comparar atributos del padre.
     *   2. Hacer el casting para acceder a los atributos propios.
     * =============================================================
     */

    // * Better Comments:
    // * Teoria.    ! Critico.    ? Aclaracion.    TODO Alumno.    ✅ Solucion.

    public static void main(String[] args) {

        // ── PARTE 1: Comparacion con == vs equals() ───────────────────────
        System.out.println("=== == VS equals() ===");
        Producto p1 = new Producto("P001", "Teclado", 29.99);
        Producto p2 = new Producto("P001", "Teclado", 29.99);
        Producto p3 = p1;   // misma referencia

        // ! == compara referencias: p1 y p2 son objetos distintos en memoria.
        System.out.println("p1 == p2       : " + (p1 == p2));       // false
        System.out.println("p1 == p3       : " + (p1 == p3));       // true (misma ref)

        // * equals() sobreescrito compara contenido.
        System.out.println("p1.equals(p2)  : " + p1.equals(p2));    // true (mismo codigo)
        System.out.println("p1.equals(null): " + p1.equals(null));   // false (no NPE)
        // TODO mini 1: crea un p4 con codigo diferente "P002" y comprueba p1.equals(p4).
        //              ¿Devuelve false aunque nombre y precio sean iguales?

        // ── PARTE 2: hashCode coherente con equals ────────────────────────
        System.out.println("\n=== HASHCODE ===");
        System.out.println("hashCode p1: " + p1.hashCode());
        System.out.println("hashCode p2: " + p2.hashCode());
        // ! Si equals devuelve true, hashCode DEBE ser igual.
        System.out.println("Son iguales segun equals: " + p1.equals(p2));
        System.out.println("Mismo hashCode:           " + (p1.hashCode() == p2.hashCode()));

        // ── PARTE 3: Equals en herencia ───────────────────────────────────
        System.out.println("\n=== EQUALS EN HERENCIA ===");
        ProductoDigital pd1 = new ProductoDigital("D001", "IDE Java", 0.0, "https://ejemplo.com");
        ProductoDigital pd2 = new ProductoDigital("D001", "IDE Java", 0.0, "https://ejemplo.com");
        ProductoDigital pd3 = new ProductoDigital("D001", "IDE Java", 0.0, "https://otro.com");

        System.out.println("pd1.equals(pd2): " + pd1.equals(pd2));   // true (mismo codigo Y url)
        System.out.println("pd1.equals(pd3): " + pd1.equals(pd3));   // false (url diferente)

        // ? Comparar un Producto con un ProductoDigital
        System.out.println("p1.equals(pd1) : " + p1.equals(pd1));   // false (tipos distintos)
        System.out.println("pd1.equals(p1) : " + pd1.equals(p1));   // false

        // ── PARTE 4: Uso en colecciones ───────────────────────────────────
        System.out.println("\n=== EN COLECCIONES ===");
        java.util.HashSet<Producto> set = new java.util.HashSet<>();
        set.add(p1);
        set.add(p2);  // igual que p1 segun equals -> no se aniade
        System.out.println("Elementos en set (deberian ser 1): " + set.size());
        // TODO mini 2: comenta el @Override de hashCode() en Producto y vuelve a ejecutar.
        //              ¿Cuantos elementos hay ahora en el set? ¿Por que?
        //              (Recuerda volver a descomentar despues).

        // TODO Alumno:
        // 1) Crea una clase "Cliente" con atributos: id (int), nombre, email.
        //    Sobreescribe equals() comparando SOLO por id.
        //    Sobreescribe hashCode() usando Objects.hash(id).
        // 2) Crea dos clientes con mismo id pero distinto nombre. Comprueba equals().
        // 3) Crea una subclase "ClienteVIP" con atributo nivel (int).
        //    Sobreescribe equals() llamando a super.equals() y comparando nivel.
        // 4) Prueba que no ocurre NullPointerException al llamar equals(null).

        // ✅ Solucion orientativa (comentada)
        // Cliente c1 = new Cliente(1, "Ana", "ana@mail.com");
        // Cliente c2 = new Cliente(1, "Ana Lopez", "otro@mail.com");
        // System.out.println(c1.equals(c2));  // true (mismo id)
    }

    // =====================================================================
    // * CLASE BASE: Producto
    // =====================================================================
    static class Producto {
        private String codigo;
        private String nombre;
        private double precio;

        public Producto(String codigo, String nombre, double precio) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = Math.max(precio, 0);
        }

        // * Paso a paso para sobreescribir equals() correctamente:
        @Override
        public boolean equals(Object obj) {
            // 1. Reflexividad: si es el mismo objeto, true.
            if (this == obj) return true;
            // 2. Nulidad: si obj es null, false.
            if (obj == null) return false;
            // ! 3. Tipo: si no es la misma clase (o subclase), false.
            //    Usamos getClass() en lugar de instanceof para evitar
            //    que Producto.equals(ProductoDigital) devuelva true.
            if (getClass() != obj.getClass()) return false;
            // 4. Casting: convertimos para acceder a los atributos.
            Producto otro = (Producto) obj;
            // 5. Comparar los campos relevantes.
            return Objects.equals(codigo, otro.codigo);
            // ? Aqui comparamos solo por codigo (identificador unico).
            // TODO mini 3: prueba a cambiar la comparacion para que use tambien nombre.
            //              ¿Que pasa con p1 y p2 si cambian de codigo pero tienen mismo nombre?
        }

        @Override
        public int hashCode() {
            // ! Usar los mismos campos que en equals().
            return Objects.hash(codigo);
        }

        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }

        @Override
        public String toString() {
            return "Producto{codigo='" + codigo + "', nombre='" + nombre + "', precio=" + precio + "}";
        }
    }

    // =====================================================================
    // * SUBCLASE: ProductoDigital extends Producto
    // * Equals en herencia: usa super.equals() + compara campos propios.
    // =====================================================================
    static class ProductoDigital extends Producto {
        private String urlDescarga;

        public ProductoDigital(String codigo, String nombre, double precio, String urlDescarga) {
            super(codigo, nombre, precio);
            this.urlDescarga = urlDescarga;
        }

        @Override
        public boolean equals(Object obj) {
            // 1. Llamamos al equals() del padre para comparar codigo, etc.
            if (!super.equals(obj)) return false;
            // 2. El padre ya comprueba null y getClass(), asi que aqui obj es seguro.
            ProductoDigital otro = (ProductoDigital) obj;
            // 3. Comparamos los campos propios de la subclase.
            return Objects.equals(urlDescarga, otro.urlDescarga);
        }

        @Override
        public int hashCode() {
            // ! Combinar hashCode del padre con los campos propios.
            return Objects.hash(super.hashCode(), urlDescarga);
        }

        @Override
        public String toString() {
            return "ProductoDigital{codigo='" + getCodigo() + "', url='" + urlDescarga + "'}";
        }
    }
}
