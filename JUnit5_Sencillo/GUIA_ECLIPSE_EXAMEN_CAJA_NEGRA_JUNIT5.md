# Guia Completa Eclipse: Examen de Caja Negra con JUnit 5

> Objetivo: que puedas entrar al examen, leer un PDF/enunciado y convertirlo en tests `.java` correctos, ejecutables y bien presentados.

---

## 1. Que te van a pedir en este tipo de examen

Normalmente te dan:
- Un enunciado (PDF o texto) con reglas de negocio.
- Una clase ya hecha (produccion) o descripcion de metodos.
- Instruccion clara: "haz tests de caja negra con JUnit 5".

Tu salida esperada:
- Uno o varios archivos de test (`...Test.java`).
- Casos de particiones + valores limite.
- Uso correcto de asserts y anotaciones JUnit 5.

---

## 2. Preparar Eclipse para JUnit 5

## Opcion A: Proyecto Java normal (sin Maven)

1. `File` -> `New` -> `Java Project`.
2. Crea paquete `tests` (y si hace falta `main` o similar para produccion).
3. Añade JUnit 5 al Build Path:
- Click derecho proyecto -> `Build Path` -> `Add Libraries...`
- Selecciona `JUnit`
- Elige `JUnit 5`
- `Finish`
4. Verifica que aparecen imports `org.junit.jupiter...`.

Comprobacion rapida:
- Crea clase `PruebaInicialTest.java`.
- Escribe un `@Test` con `assertTrue(true)`.
- Ejecuta `Run As -> JUnit Test`.

## Opcion B: Proyecto Maven (recomendado si te lo permiten)

1. `File` -> `New` -> `Maven Project`.
2. En `pom.xml` añade dependencia JUnit Jupiter:

```xml
<dependencies>
  <dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
  </dependency>
</dependencies>
```

3. Asegura plugin de tests:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <version>3.2.5</version>
    </plugin>
  </plugins>
</build>
```

4. Estructura estandar:
- `src/main/java` -> codigo produccion
- `src/test/java` -> tests

---

## 3. Estructura recomendada de carpetas en examen

Si NO es Maven:
- `src/tests/ClubNauticoTest.java`
- `src/tests/ClubNautico.java` (si te dejan incluirla)

Si es Maven:
- `src/main/java/.../ClubNautico.java`
- `src/test/java/.../ClubNauticoTest.java`

Regla:
- Test y clase objetivo deben estar en paquetes coherentes.
- Si la clase es package-private, el test debe estar en el mismo paquete.

---

## 4. Checklist de imports correctos (muy importante)

Usa siempre JUnit 5:

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
```

No mezclar con JUnit 4:
- Evita `org.junit.Test`
- Evita `org.junit.Assert.*`

---

## 5. Flujo de trabajo en examen: del PDF al .java

## Paso 1: leer el PDF y extraer reglas exactas

Por cada metodo, anota:
- Entradas.
- Salidas.
- Excepciones.
- Fronteras numericas.
- Restricciones de formato.

Plantilla rapida:

```text
Metodo:
Entradas:
Reglas:
Errores:
Fronteras:
```

## Paso 2: construir particiones de equivalencia

Para cada metodo:
- Particiones invalidas (error/excepcion)
- Particiones validas (resultado esperado)

Ejemplo:
- `edad < 0` -> invalida
- `0..17` -> valida
- `18..64` -> valida
- `>=65` -> valida

## Paso 3: construir valores limite

Si frontera es 18:
- 17, 18, 19

Si frontera es 6.0:
- 5.9, 6.0, 6.1

## Paso 4: diseñar tabla de casos

Modelo:

| ID | Entrada | Tipo | Esperado |
|---|---|---|---|
| P1 | ... | invalida | excepcion |
| P2 | ... | valida | valor |
| L1 | limite-1 | AVL | valor |
| L2 | limite exacto | AVL | valor |
| L3 | limite+1 | AVL | valor |

## Paso 5: pasar tabla a tests Java

Orden recomendado:
1. Invalidos (`assertThrows` / `false`).
2. Validos basicos (`assertEquals`).
3. Limites.
4. Parametrizados (`@CsvSource`).

---

## 6. Plantilla base de clase de test (copiar en examen)

```java
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class MiClaseTest {

    private MiClase obj;

    @BeforeAll
    static void inicio() {}

    @BeforeEach
    void setUp() {
        obj = new MiClase();
    }

    @AfterAll
    static void fin() {}

    @Test
    @DisplayName("[P1-INV] Caso invalido")
    void metodo_CasoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> obj.metodo(-1));
    }

    @Test
    @DisplayName("[P2] Caso valido")
    void metodo_CasoValido() {
        assertEquals(10.0, obj.metodo(1), 0.01);
    }

    @ParameterizedTest
    @CsvSource({
        "1,10.0",
        "2,20.0"
    })
    void metodo_VariosCasos(int entrada, double esperado) {
        assertEquals(esperado, obj.metodo(entrada), 0.01);
    }
}
```

---

## 7. Como ejecutar tests en Eclipse

Opcion 1:
- Click derecho sobre clase test -> `Run As` -> `JUnit Test`.

Opcion 2:
- Click derecho sobre proyecto -> `Run As` -> `JUnit Test` (ejecuta todos).

Resultado visual:
- Verde: todo OK.
- Rojo: hay fallos.

Panel importante:
- `JUnit` view muestra test, fallo y stack trace.

---

## 8. Como leer y corregir fallos rapidamente

Si falla un test:
1. Lee nombre del test (`DisplayName` ayuda mucho).
2. Mira mensaje de error:
- esperado vs real
- si no lanzó excepción
3. Revisa:
- dato de entrada
- assert usado
- valor esperado

Regla:
- Primero valida que el test esté bien diseñado.
- Luego sospecha de la implementación.

---

## 9. Conversión directa de un enunciado PDF a codigo

### Ejemplo de traduccion

PDF dice:
- "edad < 0 lanza excepcion"
- "edad < 18 => 30"
- "18 a 64 => 60"
- "65 o mas => 40"

En tests se traduce a:
- un `assertThrows` para edad negativa
- un test para cada tramo
- tests de limite: 17,18,19 y 64,65,66
- un parametrizado para varios valores representativos

---

## 10. Reglas de oro para sacar nota alta

1. Nombra bien cada test.
2. Cubre todas las particiones.
3. No olvides límites exactos.
4. Usa delta en `double`.
5. Incluye `null` cuando haya `String`/objeto.
6. Usa parametrizados cuando toque tabla de casos.
7. Mantén estructura AAA.
8. No metas lógica compleja dentro del test.

---

## 11. Errores que penalizan mucho

- Imports JUnit 4 en lugar de JUnit 5.
- Falta de casos inválidos.
- Sin pruebas de límites.
- `assertEquals` invertido o sin delta en decimales.
- Tests sin relación con el enunciado.
- No comprobar excepciones cuando el enunciado lo exige.

---

## 12. Guion de 45 minutos (examen real)

Min 0-8:
- leer enunciado y sacar reglas.

Min 8-15:
- diseñar particiones + límites (en papel o comentarios).

Min 15-35:
- codificar tests base + invalidos + límites.

Min 35-42:
- añadir parametrizados y `DisplayName`.

Min 42-45:
- ejecutar todo, corregir rojos, revisar cobertura.

---

## 13. Mini checklist final antes de entregar

- [ ] Compila sin errores.
- [ ] Ejecuta en verde.
- [ ] Hay tests para invalidos.
- [ ] Hay tests para todos los tramos validos.
- [ ] Hay tests de límites.
- [ ] Hay al menos un parametrizado si aplica.
- [ ] Nombres claros y profesionales.

---

## 14. Material de apoyo en esta carpeta

Para practicar junto a esta guía:
- `JUnit5_Sencillo/ClubNauticoTest.java`
- `JUnit5_Sencillo/ClubNautico(alumno).java`
- `JUnit5_Sencillo/BibliotecaTest.java`
- `JUnit5_Sencillo/CajaNegra_JUnit5_Teoria_Completa.md`

---

## 15. Plan recomendado de estudio (3 sesiones)

Sesion 1:
- Teoria + repetir ejemplos de cuota/clasificacion.

Sesion 2:
- Resolver `ClubNautico(alumno).java` sin mirar solución.

Sesion 3:
- Simulacro nuevo (gimnasio/videoclub) en tiempo limitado.

Resultado esperado:
- alumno capaz de pasar de enunciado PDF a test completo por sí solo.

