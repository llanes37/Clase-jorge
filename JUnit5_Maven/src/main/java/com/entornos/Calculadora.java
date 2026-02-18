package com.entornos;

// ? =====================================================================
// ? CLASE CALCULADORA — Clase "de producción" (la que vamos a testear)
// ? =====================================================================
// ? Esta clase contiene métodos matemáticos sencillos.
// ? Su objetivo es servir como ejemplo para aprender a escribir tests
// ? con JUnit 5. Cada método tiene un comportamiento claro y predecible,
// ? lo que facilita escribir pruebas unitarias.
// ? =====================================================================

// ! RECUERDA: Esta clase va en src/main/java/com/entornos/
// ! Los tests de esta clase van en src/test/java/com/entornos/CalculadoraTest.java

public class Calculadora {

    // ================================================================
    // * MÉTODO: sumar
    // ? Suma dos números enteros y devuelve el resultado.
    // ? Es el método más básico — perfecto para tu primer test.
    // ================================================================
    public int sumar(int a, int b) {
        return a + b;
    }

    // ================================================================
    // * MÉTODO: restar
    // ? Resta el segundo número al primero.
    // ================================================================
    public int restar(int a, int b) {
        return a - b;
    }

    // ================================================================
    // * MÉTODO: multiplicar
    // ? Multiplica dos números enteros.
    // ================================================================
    public int multiplicar(int a, int b) {
        return a * b;
    }

    // ================================================================
    // * MÉTODO: dividir
    // ? Divide el primer número entre el segundo.
    // ! ATENCIÓN: Si el divisor es 0, lanza ArithmeticException.
    // ! Esto es INTENCIONADO para practicar assertThrows en los tests.
    // ================================================================
    public double dividir(int dividendo, int divisor) {
        // * Validación: no se puede dividir entre cero
        if (divisor == 0) {
            // ! Esta excepción es la que capturaremos con assertThrows en el test
            throw new ArithmeticException("No se puede dividir entre cero");
        }
        // ? Usamos (double) para que la división sea decimal, no entera
        // ? Ejemplo: dividir(7, 2) → 3.5 (no 3)
        return (double) dividendo / divisor;
    }

    // ================================================================
    // * MÉTODO: esNumeroPrimo
    // ? Determina si un número es primo.
    // ? Un número primo es aquel que solo es divisible por 1 y por sí mismo.
    // ? Ejemplos: 2, 3, 5, 7, 11, 13... son primos
    // ?           1, 4, 6, 8, 9, 10... NO son primos
    // 
    // ! OJO: El 1 NO es primo (por convención matemática)
    // ! OJO: Los números negativos y el 0 NO son primos
    // ================================================================
    public boolean esNumeroPrimo(int numero) {
        // * Caso base: números menores o iguales a 1 no son primos
        if (numero <= 1) {
            return false;
        }
        // * Caso especial: el 2 es el único primo par
        if (numero == 2) {
            return true;
        }
        // * Si es par y no es 2, no es primo (optimización)
        if (numero % 2 == 0) {
            return false;
        }
        // * Solo necesitamos comprobar hasta la raíz cuadrada del número
        // ? ¿Por qué? Si n = a × b, entonces a o b (o ambos) son ≤ √n
        // ? Así evitamos iteraciones innecesarias
        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
            if (numero % i == 0) {
                return false; // ? Encontramos un divisor → no es primo
            }
        }
        return true; // * Si no encontramos divisores, es primo
    }

    // ================================================================
    // * MÉTODO: calcularModulo (valor absoluto)
    // ? Devuelve el valor absoluto de un número.
    // ? Ejemplo: calcularModulo(-5) → 5, calcularModulo(3) → 3
    // ================================================================
    public int calcularModulo(int numero) {
        // ? Operador ternario: si es negativo, lo multiplica por -1
        return numero < 0 ? -numero : numero;
    }

    // ================================================================
    // * MÉTODO: esPar
    // ? Devuelve true si el número es par, false si es impar.
    // ? Ejemplo: esPar(4) → true, esPar(7) → false
    // ================================================================
    public boolean esPar(int numero) {
        // ? El operador % devuelve el resto de la división
        // ? Si el resto de dividir entre 2 es 0, es par
        return numero % 2 == 0;
    }

    // ================================================================
    // * MÉTODO: validarDNI (simplificado)
    // ? Valida el formato de un DNI español: 8 dígitos + 1 letra
    // ? Ejemplo válido: "12345678A"
    // ? Ejemplo inválido: "1234A", "ABCDEFGHI", null, ""
    // 
    // ! NOTA: Esta es una validación SIMPLIFICADA.
    // ! No comprueba que la letra sea la correcta para ese número,
    // ! solo que el formato sea correcto (para mantener la clase sencilla).
    // 
    // TODO Ejercicio 9 del alumno: puedes ampliar este método
    // TODO para que también compruebe la letra correcta del DNI
    // ================================================================
    public boolean validarDNI(String dni) {
        // * Primero comprobamos que no sea null ni vacío
        if (dni == null || dni.isEmpty()) {
            return false;
        }
        // * Debe tener exactamente 9 caracteres (8 dígitos + 1 letra)
        if (dni.length() != 9) {
            return false;
        }
        // * Los 8 primeros deben ser dígitos
        String numeros = dni.substring(0, 8);
        for (char c : numeros.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // ? Encontramos algo que no es dígito
            }
        }
        // * El último carácter debe ser una letra
        char letra = dni.charAt(8);
        if (!Character.isLetter(letra)) {
            return false; // ? El último carácter no es una letra
        }
        return true; // * Formato correcto
    }

    // ================================================================
    // * MÉTODO: calcularMaximo
    // ? Devuelve el mayor de dos números.
    // ? Ejemplo: calcularMaximo(5, 3) → 5
    // TODO El alumno puede crear tests para este método (Ejercicio 1)
    // ================================================================
    public int calcularMaximo(int a, int b) {
        return a >= b ? a : b;
    }
}
