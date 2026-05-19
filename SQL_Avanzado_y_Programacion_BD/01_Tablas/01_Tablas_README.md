# 🧱 Creación de Tablas

Este bloque contiene las sentencias SQL para crear las tablas básicas que usaremos durante todo el repaso:

- `empleados`: con sueldos, fecha de alta, etc.
- `clientes`: con nombre y email
- `departamentos`: opcional si usamos relación con empleados

---

## 🎯 Objetivos

- Comprender la estructura de una tabla relacional
- Definir claves primarias (`PRIMARY KEY`)
- Entender los tipos de datos básicos: `TEXT`, `INTEGER`, `REAL`, `DATE`
- Crear relaciones entre tablas (`FOREIGN KEY`)

---

## 📘 Teoría

### 🔹 ¿Qué es una tabla?
Una tabla en SQL es como una hoja de cálculo: tiene columnas (campos) y filas (registros).

### 🔹 Clave primaria (PRIMARY KEY)
Es el identificador único de cada fila. Por ejemplo, `id`.

### 🔹 Tipos de datos más usados
- `INTEGER`: números enteros
- `REAL`: números con decimales
- `TEXT`: cadenas de texto
- `DATE` o `TEXT`: para fechas (en SQLite usamos TEXT con formato 'YYYY-MM-DD')

---

## 🧪 Tablas del proyecto

```sql
-- 🧍 Tabla de empleados
CREATE TABLE IF NOT EXISTS empleados (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT NOT NULL,
  salario REAL,
  fecha_alta TEXT
);

-- 👥 Tabla de clientes
CREATE TABLE IF NOT EXISTS clientes (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT NOT NULL,
  email TEXT UNIQUE
);

-- 🏢 Tabla de departamentos
CREATE TABLE IF NOT EXISTS departamentos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  nombre TEXT
);

-- 📎 Relación empleados-departamentos
ALTER TABLE empleados ADD COLUMN departamento_id INTEGER REFERENCES departamentos(id);
```

---

## 🧠 Recomendaciones

- Usa `AUTOINCREMENT` solo en la clave primaria si necesitas que se genere automáticamente.
- SQLite no tiene `DATE`, pero puedes guardar fechas como `TEXT` en formato `'YYYY-MM-DD'`.
- Evita nombres genéricos como `data` o `value` para columnas.
