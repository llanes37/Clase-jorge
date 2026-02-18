package com.entornos;

// ? =====================================================================
// ? CLASE VALIDADORUSUARIO — Clase para practicar casos más "reales"
// ? =====================================================================
// ? Esta clase simula la validación de datos de usuario en un sistema
// ? de registro. Contiene métodos que validan emails, contraseñas y
// ? el proceso de registro completo.
// ? 
// ? Es un ejemplo más cercano a la vida real que la Calculadora,
// ? para que veas cómo se testean validaciones de cadenas y lógica
// ? de negocio.
// ? =====================================================================

// ! RECUERDA: Esta clase va en src/main/java/com/entornos/
// ! Los tests van en src/test/java/com/entornos/ValidadorUsuarioTest.java

public class ValidadorUsuario {

    // ================================================================
    // * CONSTANTES — Valores fijos usados en las validaciones
    // ? Las definimos como constantes para no usar "números mágicos"
    // ? en el código. Si cambian los requisitos, se modifican aquí.
    // ================================================================
    
    // * Longitud mínima y máxima de la contraseña
    private static final int PASSWORD_MIN_LENGTH = 8;
    private static final int PASSWORD_MAX_LENGTH = 30;

    // * Longitud máxima del email
    private static final int EMAIL_MAX_LENGTH = 100;

    // ================================================================
    // * MÉTODO: validarEmail
    // ? Valida que un email tenga un formato básico correcto.
    // ? Reglas:
    // ?   - No puede ser null ni vacío
    // ?   - Debe contener exactamente un '@'
    // ?   - Debe tener texto antes y después del '@'
    // ?   - Debe tener al menos un '.' después del '@'
    // ?   - No puede superar los 100 caracteres
    // 
    // ! NOTA: Esta es una validación simplificada.
    // ! En la vida real se usaría una expresión regular más compleja
    // ! o una librería especializada.
    // ================================================================
    public boolean validarEmail(String email) {
        // * Paso 1: Comprobar null y vacío
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        // * Paso 2: Comprobar longitud máxima
        if (email.length() > EMAIL_MAX_LENGTH) {
            return false;
        }

        // * Paso 3: Debe contener exactamente un '@'
        // ? Contamos cuántas '@' hay
        long arrobas = email.chars().filter(c -> c == '@').count();
        if (arrobas != 1) {
            return false; // ? 0 arrobas o más de 1 → inválido
        }

        // * Paso 4: Separar por '@' y comprobar las partes
        int posArroba = email.indexOf('@');
        String parteLocal = email.substring(0, posArroba);     // ? Lo que hay antes del @
        String dominio = email.substring(posArroba + 1);       // ? Lo que hay después del @

        // ? La parte local no puede estar vacía
        if (parteLocal.isEmpty()) {
            return false;
        }

        // ? El dominio no puede estar vacío y debe tener al menos un punto
        if (dominio.isEmpty() || !dominio.contains(".")) {
            return false;
        }

        // ? El dominio no puede empezar ni terminar con punto
        if (dominio.startsWith(".") || dominio.endsWith(".")) {
            return false;
        }

        return true; // * Todas las validaciones pasaron
    }

    // ================================================================
    // * MÉTODO: validarPassword
    // ? Valida que una contraseña cumpla requisitos de seguridad.
    // ? Reglas:
    // ?   - No puede ser null ni vacía
    // ?   - Mínimo 8 caracteres, máximo 30
    // ?   - Debe contener al menos una letra mayúscula
    // ?   - Debe contener al menos una letra minúscula
    // ?   - Debe contener al menos un dígito (número)
    // ?   - Debe contener al menos un carácter especial (!@#$%^&*)
    // 
    // ! IMPORTANTE: Estos requisitos son habituales en exámenes
    // ! y en aplicaciones reales.
    // ================================================================
    public boolean validarPassword(String password) {
        // * Paso 1: Null y vacío
        if (password == null || password.isEmpty()) {
            return false;
        }

        // * Paso 2: Longitud
        if (password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH) {
            return false;
        }

        // * Paso 3: Comprobamos cada requisito con flags (banderas)
        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneDigito = false;
        boolean tieneEspecial = false;

        // ? Caracteres especiales permitidos
        String especiales = "!@#$%^&*()_+-=[]{}|;':\",./<>?";

        // * Recorremos cada carácter de la contraseña
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) {
                tieneDigito = true;
            } else if (especiales.indexOf(c) >= 0) {
                tieneEspecial = true;
            }
        }

        // * Todos los requisitos deben cumplirse
        // ! Si falta cualquiera, la contraseña es inválida
        return tieneMayuscula && tieneMinuscula && tieneDigito && tieneEspecial;
    }

    // ================================================================
    // * MÉTODO: registrarUsuario
    // ? Simula el registro de un usuario validando email y password.
    // ? Devuelve un mensaje de resultado.
    // ? 
    // ? Reglas:
    // ?   - Si email es null o password es null → IllegalArgumentException
    // ?   - Si email no es válido → devuelve "Email no válido"
    // ?   - Si password no es válida → devuelve "Contraseña no válida"
    // ?   - Si ambos son válidos → devuelve "Usuario registrado correctamente"
    // 
    // ! Este método combina validaciones, lo que permite practicar
    // ! tanto assertThrows como assertEquals en los tests.
    // ================================================================
    public String registrarUsuario(String email, String password) {
        // * Validación de nulls — lanza excepción (no valor de retorno)
        // ? Usamos excepciones para errores de programación (pasar null)
        // ? Usamos valores de retorno para errores de validación (email malo)
        if (email == null || password == null) {
            // ! Esta excepción la capturaremos con assertThrows en los tests
            throw new IllegalArgumentException("El email y la contraseña no pueden ser null");
        }

        // * Validar email
        if (!validarEmail(email)) {
            return "Email no válido";
        }

        // * Validar password
        if (!validarPassword(password)) {
            return "Contraseña no válida";
        }

        // * Todo correcto — usuario registrado
        return "Usuario registrado correctamente";
    }

    // ================================================================
    // * MÉTODO: calcularFortalezaPassword
    // ? Calcula la "fortaleza" de una contraseña en una escala de 0 a 5.
    // ? Criterios (1 punto por cada uno):
    // ?   - Longitud >= 8
    // ?   - Longitud >= 12
    // ?   - Tiene mayúsculas
    // ?   - Tiene dígitos
    // ?   - Tiene caracteres especiales
    // ? 
    // ? Devuelve:
    // ?   0-1 → "Muy débil"
    // ?   2   → "Débil"
    // ?   3   → "Media"
    // ?   4   → "Fuerte"
    // ?   5   → "Muy fuerte"
    // 
    // TODO El alumno puede crear tests parametrizados para este método
    // ================================================================
    public String calcularFortalezaPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Muy débil";
        }

        int puntos = 0;

        // * Criterio 1: Longitud mínima
        if (password.length() >= 8) puntos++;

        // * Criterio 2: Longitud extra
        if (password.length() >= 12) puntos++;

        // * Criterio 3: Mayúsculas
        if (password.chars().anyMatch(Character::isUpperCase)) puntos++;

        // * Criterio 4: Dígitos
        if (password.chars().anyMatch(Character::isDigit)) puntos++;

        // * Criterio 5: Caracteres especiales
        String especiales = "!@#$%^&*()_+-=[]{}|;':\",./<>?";
        if (password.chars().anyMatch(c -> especiales.indexOf(c) >= 0)) puntos++;

        // * Devolvemos la categoría según los puntos
        switch (puntos) {
            case 0: case 1: return "Muy débil";
            case 2: return "Débil";
            case 3: return "Media";
            case 4: return "Fuerte";
            case 5: return "Muy fuerte";
            default: return "Muy débil";
        }
    }

    // ================================================================
    // * MÉTODO: normalizarEmail
    // ? Limpia y normaliza un email:
    // ?   - Elimina espacios al inicio y final
    // ?   - Convierte todo a minúsculas
    // ?   - Si es null, devuelve cadena vacía
    // ? Ejemplo: "  Juan@EMAIL.COM  " → "juan@email.com"
    // ================================================================
    public String normalizarEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }
}
