# 📚 UNIDAD 12: POO + AGREGACIÓN + COLECCIONES EN JAVA
## 📅 Autor: Joaquín Rodríguez Llanes | Curso: 1º DAM/DAW | Año: 2025

---

# 📋 ENUNCIADO DEL EJERCICIO: GESTIÓN DE RADIOS

## 🎯 Objetivo
Desarrollar un sistema de gestión de radios portátiles usando **Programación Orientada a Objetos**, 
**agregación de clases**, **equals/hashCode** y **colecciones (HashSet)** con un menú interactivo.

---

## 📝 PARTE 1: CLASES A IMPLEMENTAR

### 1.1 Clase `Especificaciones`
Representa los datos técnicos de una radio.

| Atributo | Tipo | Descripción |
|----------|------|-------------|
| marca | String | Marca del fabricante |
| modelo | String | Modelo de la radio |
| numeroSerie | long | Número de serie único |

**Métodos requeridos:**
- Constructor completo con los 3 parámetros
- `getNumeroSerie()`: devuelve el número de serie
- `toString()`: devuelve una cadena con todos los datos formateados

---

### 1.2 Clase `Bateria`
Representa la batería de una radio.

| Atributo | Tipo | Descripción |
|----------|------|-------------|
| marca | String | Marca de la batería |
| porcentajeCarga | int | Carga actual (0-100) |

**Métodos requeridos:**
- Constructor completo
- `getMarca()`: devuelve la marca
- `getPorcentajeCarga()`: devuelve el porcentaje de carga
- `toString()`: muestra los datos de la batería
- `consumir(int cantidad)`: resta cantidad al porcentaje, **nunca baja de 0**

---

### 1.3 Clase `Radio` (usa agregación)
Representa una radio portátil que **contiene** especificaciones y batería.

| Atributo | Tipo | Descripción |
|----------|------|-------------|
| especificaciones | Especificaciones | Datos técnicos (agregación) |
| bateria | Bateria | Batería de la radio (agregación) |
| frecuenciaMin | double | Frecuencia mínima FM |
| frecuenciaMax | double | Frecuencia máxima FM |
| frecuenciaActual | double | Frecuencia sintonizada |
| encendida | boolean | Estado de encendido |

**Constructores:**
1. Constructor completo: requiere especificaciones, bateria, frecuenciaMin, frecuenciaMax
   - Por defecto: apagada y frecuenciaActual = frecuenciaMin
2. Constructor simplificado: especificaciones, bateria (frecuencias por defecto: 87.5 - 108.0)

**Métodos requeridos:**
- Getters para todos los atributos
- `toString()`: muestra toda la información
- `equals(Object o)` y `hashCode()`: **dos radios son iguales si tienen el mismo numeroSerie**
- `botonEncendido()`: alterna encendido/apagado. Solo enciende si batería > 0. Devuelve String con estado.
- `subirFrecuencia()`: +0.5 sin pasar el máximo. Devuelve frecuenciaActual.
- `bajarFrecuencia()`: -0.5 sin bajar del mínimo. Devuelve frecuenciaActual.
- `establecerFrecuencia(double f)`: fija frecuencia si está en rango. Devuelve true/false.

---

## 📝 PARTE 2: CLASE PRINCIPAL CON MENÚ

Crea la clase `Principal` con un `HashSet<Radio>` y el siguiente menú:

```
═══════════════════════════════════════════
📻 MENÚ - GESTIÓN DE RADIOS
═══════════════════════════════════════════
1. Crear radio y añadir al conjunto
2. Mostrar todas las radios
3. Encender/Apagar radio por número de serie
4. Mostrar radios encendidas (datos técnicos)
5. Subir/Bajar frecuencia de TODAS las radios
6. Establecer frecuencia en una radio
7. Eliminar radios con batería baja
8. Consultar batería de todas las radios
9. Salir
═══════════════════════════════════════════
```

### Detalles de cada opción:

| Opción | Descripción |
|--------|-------------|
| 1 | Pide datos al usuario. Indica si se añadió o si ya existía (duplicado). |
| 2 | Lista todas las radios con toString(). |
| 3 | Pide numeroSerie y alterna estado. Indica si existe o no. |
| 4 | Solo radios encendidas: muestra especificaciones y frecuencia actual. |
| 5 | Pregunta subir/bajar. Para encendidas: consume 10% de batería. |
| 6 | Pide numeroSerie y nueva frecuencia. Valida límites. |
| 7 | Pide umbral. Elimina radios con batería < umbral. |
| 8 | Lista numeroSerie y porcentaje de batería de todas. |
| 9 | Finaliza el programa. |

---

## 📝 PARTE 3: VALIDACIONES

- **Entrada robusta**: si el usuario introduce texto donde va número, re-pedir sin que el programa falle.
- Mostrar mensajes claros: éxito ✅, error ❌, información ℹ️
- El programa debe continuar en bucle hasta elegir "Salir".

---

## 🎁 EXTRA (Opcional)
Al final del código, incluye una versión alternativa usando `ArrayList<Radio>` en lugar de `HashSet<Radio>`.
Explica en comentarios qué diferencias hay respecto a duplicados y búsqueda.

---
---

# 🔑 SOLUCIÓN GUIADA - PASOS A SEGUIR

## Paso 1: Crear la clase `Especificaciones`
```
1. Declara los 3 atributos privados
2. Crea el constructor que reciba los 3 parámetros
3. Implementa getNumeroSerie()
4. Implementa toString() con formato legible
```

## Paso 2: Crear la clase `Bateria`
```
1. Declara marca y porcentajeCarga (private)
2. Constructor con validación: si porcentaje < 0 → 0, si > 100 → 100
3. Getters para ambos atributos
4. toString() formateado
5. consumir(int cantidad): usa Math.max(0, porcentajeCarga - cantidad)
```

## Paso 3: Crear la clase `Radio` con agregación
```
1. Declara especificaciones y bateria como atributos (agregación)
2. Declara frecuenciaMin, frecuenciaMax, frecuenciaActual, encendida
3. Constructor completo: inicializa todo, apagada por defecto
4. Constructor simplificado: llama al completo con 87.5 y 108.0
5. Implementa getters
6. equals(): compara numeroSerie de especificaciones
7. hashCode(): usa numeroSerie
8. botonEncendido(): alterna solo si batería > 0 para encender
9. subirFrecuencia(): incrementa 0.5 si no supera max
10. bajarFrecuencia(): decrementa 0.5 si no baja de min
11. establecerFrecuencia(): valida rango antes de asignar
```

## Paso 4: Crear la clase `Principal`
```
1. Declara HashSet<Radio> y Scanner
2. Crea método para leer enteros de forma robusta
3. Crea método para leer doubles de forma robusta
4. Implementa el menú con switch
5. Para cada opción, crea un método separado
6. Usa iterator para eliminar elementos del HashSet mientras iteras
```

## Paso 5: Conceptos clave a recordar
- **Agregación**: Radio "tiene" Especificaciones y Bateria, pero pueden existir independientemente
- **HashSet**: no permite duplicados, usa equals() y hashCode() para comparar
- **equals/hashCode**: siempre sobrescribir ambos juntos

---
---

# 💡 EJEMPLO DE EJECUCIÓN

```
═══════════════════════════════════════════
📻 MENÚ - GESTIÓN DE RADIOS
═══════════════════════════════════════════
1. Crear radio y añadir al conjunto
...
9. Salir
═══════════════════════════════════════════
👉 Elige una opción: 1

📻 CREAR NUEVA RADIO
────────────────────
Marca de la radio: Sony
Modelo: XR-500
Número de serie: 12345
Marca de la batería: Duracell
Porcentaje de carga (0-100): 80
Frecuencia mínima (ej: 87.5): 87.5
Frecuencia máxima (ej: 108.0): 108.0

✅ Radio añadida correctamente al conjunto.

👉 Elige una opción: 1
...
Número de serie: 12345
...
⚠️ Ya existe una radio con ese número de serie. No se añadió.

👉 Elige una opción: 3
Introduce el número de serie: 12345
✅ Radio ENCENDIDA correctamente.

👉 Elige una opción: 5
¿Subir (S) o Bajar (B) frecuencia? S
📻 Radio 12345: Frecuencia actual 88.0 MHz (Batería consumida: 70%)

👉 Elige una opción: 9
👋 ¡Hasta pronto!
```

---

# 📖 TEORÍA ADICIONAL

## ¿Qué es la Agregación?
La **agregación** es una relación "tiene-un" donde un objeto contiene referencias a otros objetos,
pero estos pueden existir de forma independiente. En nuestro caso:
- Una `Radio` **tiene** `Especificaciones` y `Bateria`
- Si destruimos la Radio, las Especificaciones y Bateria podrían seguir existiendo

## ¿Por qué equals() y hashCode() juntos?
El contrato de Java establece que:
- Si `a.equals(b)` es `true`, entonces `a.hashCode() == b.hashCode()`
- HashSet usa `hashCode()` para ubicar elementos y `equals()` para confirmar igualdad
- Si no los sobrescribimos correctamente, el HashSet podría permitir "duplicados"

## HashSet vs ArrayList
| Característica | HashSet | ArrayList |
|----------------|---------|-----------|
| Duplicados | NO permite | SÍ permite |
| Orden | No garantizado | Orden de inserción |
| Búsqueda | O(1) promedio | O(n) |
| Índices | No tiene | Sí tiene |

---
