import java.util.Objects;

public class P04_EqualsHerencia_Alumno {

    /*
     * =============================================================
     * PLANTILLA ALUMNO - EQUALS EN HERENCIA
     * =============================================================
     * * Instrucciones:
     *   1. Implementa los TODO en orden.
     *   2. Ejecuta y observa los resultados de == vs equals().
     *   3. Compara con P04_EqualsHerencia.java al terminar.
     *
     * * Pasos para sobreescribir equals() correctamente:
     *   1. if (this == obj) return true;
     *   2. if (obj == null) return false;
     *   3. if (getClass() != obj.getClass()) return false;
     *   4. casting
     *   5. comparar campos con Objects.equals()
     * =============================================================
     */

    // ! Si sobreescribes equals(), SIEMPRE sobreescribe tambien hashCode().
    // ! Usa Objects.equals(a, b) para comparar Strings (maneja null seguro).
    // ! Usa Objects.hash(campo) para calcular hashCode.

    public static void main(String[] args) {

        System.out.println("=== == VS equals() ===");
        Producto p1 = new Producto("P001", "Teclado", 29.99);
        Producto p2 = new Producto("P001", "Teclado", 29.99);
        Producto p3 = p1;

        // TODO 1: imprime el resultado de p1 == p2    (esperado: false)
        // TODO 2: imprime el resultado de p1 == p3    (esperado: true)
        // TODO 3: imprime el resultado de p1.equals(p2) (esperado: true si bien implementado)
        // TODO 4: imprime el resultado de p1.equals(null) (esperado: false, nunca NPE)

        System.out.println("\n=== HASHCODE ===");
        // TODO 5: imprime hashCode de p1 y p2.
        //         Si equals devuelve true, los hashCode DEBEN ser iguales.

        System.out.println("\n=== EQUALS EN HERENCIA ===");
        ProductoDigital pd1 = new ProductoDigital("D001", "IDE", 0.0, "https://a.com");
        ProductoDigital pd2 = new ProductoDigital("D001", "IDE", 0.0, "https://a.com");
        ProductoDigital pd3 = new ProductoDigital("D001", "IDE", 0.0, "https://b.com");

        // TODO 6: imprime pd1.equals(pd2)  (esperado: true,  misma url)
        // TODO 7: imprime pd1.equals(pd3)  (esperado: false, url diferente)
        // TODO 8: imprime p1.equals(pd1)   (esperado: false, tipos distintos)

        System.out.println("\n=== EN COLECCIONES ===");
        java.util.HashSet<Producto> set = new java.util.HashSet<>();
        set.add(p1);
        set.add(p2);  // igual que p1 -> no se anade si equals y hashCode estan bien
        // TODO 9: imprime set.size() -> deberia ser 1 si equals/hashCode estan bien
    }

    // =====================================================================
    // TODO Alumno A: Implementa la clase Producto.
    // =====================================================================
    static class Producto {
        private String codigo;
        private String nombre;
        private double precio;

        public Producto(String codigo, String nombre, double precio) {
            // TODO A1: asigna los campos. precio no puede ser negativo (usa Math.max).
        }

        // TODO A2: sobreescribe equals() siguiendo los 5 pasos del enunciado.
        //          Compara SOLO por codigo (identificador unico del producto).
        @Override
        public boolean equals(Object obj) {
            // Paso 1: if (this == obj) return true;
            // Paso 2: if (obj == null) return false;
            // Paso 3: if (getClass() != obj.getClass()) return false;
            // Paso 4: Producto otro = (Producto) obj;
            // Paso 5: return Objects.equals(codigo, otro.codigo);
            return false; // reemplaza esto
        }

        // TODO A3: sobreescribe hashCode() usando Objects.hash(codigo).
        //          ! Usa los MISMOS campos que en equals().
        @Override
        public int hashCode() {
            return 0; // reemplaza esto
        }

        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }

        @Override
        public String toString() {
            return "Producto{codigo='" + codigo + "', nombre='" + nombre + "'}";
        }
    }

    // =====================================================================
    // TODO Alumno B: Implementa ProductoDigital extends Producto.
    //   equals() en subclase:
    //   1. Llama a super.equals(obj) -> si devuelve false, devuelve false.
    //   2. Casting al tipo de la subclase.
    //   3. Compara el campo propio urlDescarga.
    // =====================================================================
    static class ProductoDigital extends Producto {
        private String urlDescarga;

        public ProductoDigital(String codigo, String nombre, double precio, String urlDescarga) {
            // TODO B1: super(...) y asignar urlDescarga
        }

        // TODO B2: sobreescribe equals() llamando a super.equals() primero.
        @Override
        public boolean equals(Object obj) {
            // Paso 1: if (!super.equals(obj)) return false;
            // Paso 2: ProductoDigital otro = (ProductoDigital) obj;
            // Paso 3: return Objects.equals(urlDescarga, otro.urlDescarga);
            return false; // reemplaza esto
        }

        // TODO B3: sobreescribe hashCode() combinando super.hashCode() y urlDescarga.
        //          Pista: return Objects.hash(super.hashCode(), urlDescarga);
        @Override
        public int hashCode() {
            return 0; // reemplaza esto
        }

        @Override
        public String toString() {
            return "ProductoDigital{codigo='" + getCodigo() + "', url='" + urlDescarga + "'}";
        }
    }
}
