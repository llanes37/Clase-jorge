-- 🔧 CREACIÓN DE TABLAS PARA REPASO BBDD
-- ========================================

-- Activar uso de claves foráneas en SQLite
PRAGMA foreign_keys = ON;

-- 🧍 Tabla EMPLEADOS
-- -----------------------------
-- Esta tabla guarda los empleados con su salario y fecha de alta.
-- Tiene relación con la tabla DEPARTAMENTOS.
DROP TABLE IF EXISTS empleados;

CREATE TABLE empleados (
  id INTEGER PRIMARY KEY AUTOINCREMENT,  -- ID único por empleado
  nombre TEXT NOT NULL,                  -- Nombre obligatorio
  salario REAL CHECK(salario >= 0),      -- Salario positivo
  fecha_alta TEXT NOT NULL,              -- Fecha de incorporación (formato YYYY-MM-DD)
  departamento_id INTEGER,               -- Relación con DEPARTAMENTOS
  FOREIGN KEY (departamento_id) REFERENCES departamentos(id)
);

-- 👥 Tabla CLIENTES
-- -----------------------------
-- Almacena nombre e email de los clientes. El email debe ser único.
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
  id INTEGER PRIMARY KEY AUTOINCREMENT,  -- ID único
  nombre TEXT NOT NULL,                  -- Nombre obligatorio
  email TEXT UNIQUE                      -- Email único (clave alternativa)
);

-- 🏢 Tabla DEPARTAMENTOS
-- -----------------------------
-- Lista los departamentos disponibles para los empleados.
DROP TABLE IF EXISTS departamentos;

CREATE TABLE departamentos (
  id INTEGER PRIMARY KEY AUTOINCREMENT,  -- ID único
  nombre TEXT NOT NULL                   -- Nombre del departamento
);

-- ✅ Confirmación
-- Puedes ejecutar este script completo en SQLite o Visual Studio Code
-- para dejar la base de datos lista para trabajar.
