# Caja Negra Completa - Biblioteca Municipal "Lectura Viva"

Este material replica la idea, estructura y nivel de detalle del ejercicio de `ClubNauticoTest.java`, pero con un tema nuevo para seguir practicando.

Archivo principal:
- `JUnit5_Sencillo/BibliotecaTest.java`

## 1) Contexto del ejercicio

La biblioteca necesita validar reglas de negocio en 4 metodos.
El objetivo academico es practicar testing de caja negra con JUnit 5:
- Particiones de equivalencia.
- Analisis de valores limite (AVL).
- Casos validos, invalidos y excepciones.
- Tests parametrizados para reducir repeticion.

## 2) Enunciado funcional (resumen)

### Metodo 1: `calcularCuotaCarnet(int edad, boolean familiaNumerosa)`
- `edad < 0` -> `IllegalArgumentException`
- `edad < 14` -> cuota `8.0`
- `14 <= edad <= 64` -> cuota `25.0`
- `edad >= 65` -> cuota `12.0`
- Si `familiaNumerosa=true` -> descuento del 20%

### Metodo 2: `clasificarLecturaMensual(int librosLeidos)`
- `librosLeidos < 0` -> `IllegalArgumentException`
- `librosLeidos < 2` -> `"Ocasional"`
- `2 <= librosLeidos < 5` -> `"Frecuente"`
- `librosLeidos >= 5` -> `"Intensiva"`

### Metodo 3: `calcularPenalizacionRetraso(String tipoMaterial, int diasRetraso)`
- `diasRetraso < 0` -> `IllegalArgumentException`
- `tipoMaterial` desconocido o `null` -> `IllegalArgumentException`
- Tarifas por dia:
- `libro` -> `0.50`
- `revista` -> `0.25`
- `dvd` -> `1.50`

### Metodo 4: `validarCodigoPrestamo(String codigo)`
- `null` -> `false`
- longitud distinta de `10` -> `false`
- Formato valido: `LL-DDDDDDL`
- Ejemplo valido: `AB-123456Z`

## 3) Diseno de pruebas (como se construyo)

## Metodo 1 - Cuota de carnet
- Particiones:
- P1 invalida: edad negativa.
- P2 valida: menores de 14.
- P3 valida: 14 a 64.
- P4 valida: 65 o mas.
- P5 valida: familia numerosa (descuento).
- Limites:
- frontera 14 -> `13`, `14`, `15`
- frontera 65 -> `64`, `65`, `66`

## Metodo 2 - Clasificacion de lectura
- Particiones:
- P1 invalida: libros negativos.
- P2 valida: menos de 2.
- P3 valida: entre 2 y 4.
- P4 valida: 5 o mas.
- Limites:
- frontera 2 -> `1`, `2`, `3`
- frontera 5 -> `4`, `5`, `6`

## Metodo 3 - Penalizacion por retraso
- Particiones:
- P1 invalida: dias negativos.
- P2 invalida: tipo desconocido o `null`.
- P3 valida: libro.
- P4 valida: revista.
- P5 valida: dvd.
- Limites:
- frontera de dias en 0 -> `-1`, `0`, `1`

## Metodo 4 - Validacion de codigo
- Particiones:
- P1 invalida: `null`.
- P2 invalida: longitud distinta de 10.
- P3 invalida: posiciones 0-1 no letras.
- P4 invalida: posicion 2 sin `-`.
- P5 invalida: posiciones 3-8 no digitos.
- P6 invalida: posicion 9 no letra.
- P7 valida: formato correcto.
- Limites:
- longitud `9`, `10`, `11`

## 4) Estructura del archivo Java

`BibliotecaTest.java` mantiene el esquema de estudio completo:
- Clase de produccion (`Biblioteca`) + clase de tests (`BibliotecaTest`).
- `@BeforeAll`, `@BeforeEach`, `@AfterAll`.
- Bloques de comentarios por metodo con particiones y AVL.
- Tests unitarios clasicos (`@Test`) para casos clave.
- Tests parametrizados (`@ParameterizedTest` + `@CsvSource` / `@ValueSource`).
- Test global con `assertAll`.
- Mini resumen final para repaso de examen.

## 5) Que aprende el alumno aqui

- Traducir un enunciado funcional a casos de prueba concretos.
- Detectar fronteras donde suelen aparecer bugs (`<`, `<=`, `>=`).
- Separar comportamiento esperado de implementacion interna.
- Crear una bateria de tests mantenible y legible.

## 6) Checklist rapido para estudiar

- Identificar entradas invalidas primero.
- Definir particiones validas sin solaparlas.
- Probar limites exactos y vecinos.
- Incluir al menos un test por particion.
- Usar tests parametrizados cuando cambia solo la entrada/salida.
- Verificar excepciones con `assertThrows`.

## 7) Siguiente practica sugerida

Crear una tercera version con otro dominio (por ejemplo, gimnasio o videoclub) reutilizando la misma plantilla:
- 4 metodos.
- reglas con tramos y limites.
- al menos 1 validacion de formato.
- test global `assertAll`.
