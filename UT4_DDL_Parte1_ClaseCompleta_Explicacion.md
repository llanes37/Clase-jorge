# 🎓 UT4 - SQL DDL Parte 1
> 📘 Guía visual de clase basada en `UT4_DDL_Parte1_ClaseCompleta.sql`

---

## 🧭 Mapa rápido de la sesión
- 🎯 Objetivos
- 🧠 Teoría base de SQL y DDL
- 🧱 Construcción de tablas y constraints
- 🧪 Pruebas de errores reales
- 🔍 Consulta de metadatos
- 📝 Ejercicio final

---

## 🎯 1) Objetivo de la sesión
Al terminar esta clase, el alumno podrá:

- ✅ Crear tablas con `CREATE TABLE`
- ✅ Definir restricciones con `CONSTRAINT`
- ✅ Aplicar `PK`, `FK`, `NOT NULL`, `UNIQUE`, `CHECK`, `DEFAULT`
- ✅ Entender `ON DELETE SET NULL` y `ON DELETE CASCADE`
- ✅ Consultar estructura con `USER_TABLES`, `USER_CONSTRAINTS`, `USER_CONS_COLUMNS`

---

## 🧠 2) Contexto teórico
SQL se organiza en sublenguajes:

| Sub-lenguaje | Uso principal | Ejemplos |
|---|---|---|
| `DDL` | Definir estructura | `CREATE`, `ALTER`, `DROP` |
| `DML` | Manipular datos | `SELECT`, `INSERT`, `UPDATE`, `DELETE` |
| `DCL/TCL` | Permisos y transacciones | `GRANT`, `REVOKE`, `COMMIT`, `ROLLBACK` |

⚠️ **Clave Oracle:** muchas sentencias DDL hacen `COMMIT` implícito.

---

## 🎨 3) Convención visual de comentarios del `.sql`
- `-- *` explicación suave / teoría
- `-- !` advertencia importante
- `-- ?` aclaración útil
- `-- TODO` ejercicio guiado
- `-- OK` solución o pista

---

## 🧱 4) Estructura del script por bloques

### 🧹 Bloque 0 - Limpieza
- Borra tablas previas para repetir la práctica sin errores.
- Usa bloques `BEGIN ... EXCEPTION ... END;` para no romper si no existen.

Tablas limpiadas:
- `templeados`
- `tdepartamentos`
- `tpersonas`
- `tprovincias`

### 🏙️ Bloque 1 - Modelo `TPROVINCIAS` + `TPERSONAS`
Relación maestro-detalle:
- `TPROVINCIAS.cd_prov` → `PRIMARY KEY`
- `TPROVINCIAS.nom_prov` → `NOT NULL` + `UNIQUE`
- `TPERSONAS.cd_pers` → `PRIMARY KEY`
- `TPERSONAS.dni` → `NOT NULL` + `UNIQUE`
- `TPERSONAS.cd_prov` → `FOREIGN KEY` a `TPROVINCIAS(cd_prov)` con `ON DELETE SET NULL`

💡 Si borras una provincia, la persona se conserva pero su `cd_prov` pasa a `NULL`.

### 🏢 Bloque 2 - Modelo `TDEPARTAMENTOS` + `TEMPLEADOS`
Reglas de negocio incluidas:
- `cd_dpto` y `emp_no` como PK
- `ciudad` limitada por `CHECK` (`ALMERIA`, `SORIA`, `VALLADOLID`)
- `f_nac` obligatorio (`NOT NULL`)
- `casado` solo `'S'` o `'N'` (`CHECK`)
- `sueldo` con `DEFAULT 3000.50` y validación `>= 0`
- `dept_no` como FK hacia `tdepartamentos`

### 📥 Bloque 3 - Datos válidos
- Inserciones correctas para verificar que el modelo funciona.
- Cierre con `COMMIT`.

### 🚨 Bloque 4 - Pruebas de error guiadas
Errores preparados para descomentar en clase:
- PK duplicada
- UNIQUE duplicada
- CHECK inválido
- NOT NULL violado

👨‍🏫 Dinámica recomendada:
1. Descomentar 1 sentencia
2. Ejecutar
3. Leer error
4. Identificar constraint
5. Corregir

### 🔄 Bloque 5 - Demo `ON DELETE SET NULL`
- Consulta antes
- Borrado en maestra
- Consulta después

Resultado esperado: filas hijas siguen existiendo con FK a `NULL`.

### 🔍 Bloque 6 - Metadatos
Consultas clave:
- `USER_TABLES`
- `USER_CONSTRAINTS`
- `USER_CONS_COLUMNS`

🎯 Objetivo: que el alumno vea que Oracle guarda toda la estructura en diccionario.

### 🌊 Bloque 7 - Demo `ON DELETE CASCADE`
Modelo `TPEDIDOS` / `TLINEAS_PEDIDO`.

Resultado esperado:
- Borras pedido maestro
- Se borran automáticamente sus líneas detalle

---

## 🧩 5) Constraints explicadas en 1 minuto

| Constraint | Qué garantiza | Ejemplo |
|---|---|---|
| `PRIMARY KEY` | Identidad única de fila | `emp_no` |
| `FOREIGN KEY` | Integridad entre tablas | `dept_no -> cd_dpto` |
| `NOT NULL` | Campo obligatorio | `f_nac` |
| `UNIQUE` | No repetir valores | `telefono` |
| `CHECK` | Regla lógica de dominio | `casado IN ('S','N')` |
| `DEFAULT` | Valor automático por defecto | `sueldo 3000.50` |

---

## 🏷️ 6) Nombres de constraints (muy recomendado)
Ejemplos del script:
- `emp_dni_uk`
- `per_prv_fk`
- `dpt_ciu_ck`

✅ Ventaja: cuando Oracle lanza error, se identifica rápido la regla que falla.

---

## 🧪 7) Orden ideal para impartir la clase
1. `Bloque 0` y `Bloque 1`
2. Explicar diagrama maestro-detalle
3. `Bloque 2` (reglas de negocio)
4. `Bloque 3` (datos válidos)
5. `Bloque 4` (laboratorio de errores)
6. `Bloque 5` vs `Bloque 7` (`SET NULL` vs `CASCADE`)
7. `Bloque 6` (metadatos)

---

## ❌ 8) Errores frecuentes del alumno
- Olvidar comas entre columnas
- Crear tabla hija antes que la maestra
- Definir FK sobre columna no PK/UK
- Confundir `NOT NULL` con `UNIQUE`
- Pensar que `DEFAULT` sustituye validaciones

---

## ✅ 9) Checklist de corrección rápida
- [ ] PK definidas
- [ ] FK correctas
- [ ] CHECK alineado con reglas de negocio
- [ ] NOT NULL en campos obligatorios
- [ ] Casos válidos e inválidos probados
- [ ] Metadatos consultados

---

## 📝 10) Ejercicio final propuesto
Crear `TPROYECTOS` y relacionarla con `TEMPLEADOS`.

Requisitos:
- PK de proyecto
- Nombre obligatorio
- Estado con `CHECK ('A','C')`
- FK al empleado responsable
- Verificación en `USER_CONSTRAINTS`

---

## 🧾 11) Cierre
Este material cubre la base sólida de modelado relacional con DDL en Oracle.
Con esto, el alumno ya puede diseñar tablas reales con integridad y trazabilidad.

📌 Archivo práctico asociado:
- `UT4_DDL_Parte1_ClaseCompleta.sql`