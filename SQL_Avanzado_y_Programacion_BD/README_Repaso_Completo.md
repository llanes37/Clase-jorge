# Repaso Completo · Base de Datos con Programación (SQL + Lógica)

> **Nivel:** 2º DAM / 2º DAW · Asignaturas: *Bases de Datos*, *Acceso a Datos*, *PSP*
> **Motor principal:** MySQL 8 vía Docker · Bloques 01-03 también ejecutables en SQLite

Este material cubre todos los conceptos de **programación sobre bases de datos**: desde consultas avanzadas hasta procedimientos almacenados, cursores y triggers. Es el paso natural después del Curso-SQL introductorio.

---

## Puesta en marcha del entorno (5 minutos)

### Requisito: Docker Desktop
Descarga desde [docker.com](https://www.docker.com/products/docker-desktop/) e instálalo. Solo se hace una vez.

### Levantar MySQL con Docker

```bash
# Opción A: con docker-compose (recomendado)
docker compose up -d

# Opción B: comando directo
docker run --name repaso-bbdd -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=repaso_bd -p 3306:3306 -d mysql:8
```

### Gestión del contenedor

```bash
docker start repaso-bbdd    # arrancar (cada vez que abras VS Code)
docker stop  repaso-bbdd    # parar al acabar
docker logs  repaso-bbdd    # ver logs si algo falla
```

### Conectar VS Code al MySQL

1. Instala la extensión **Database Client** (`cweijan.vscode-database-client2`)
2. Panel izquierdo → icono de base de datos → `+` Nueva conexión → MySQL
3. Datos de conexión:

| Campo | Valor |
|---|---|
| Host | `localhost` |
| Puerto | `3306` |
| Usuario | `root` |
| Contraseña | `root` |
| Base de datos | `repaso_bd` |

4. Conectar → ya puedes ver y explorar las tablas
5. Abre cualquier `.sql` → clic derecho → **Run on active connection**

### Inicializar la base de datos

Ejecuta `00_reset_completo.sql` para crear todas las tablas y datos de práctica. **Úsalo también cuando quieras volver al estado inicial.**

---

## Qué bloques necesitan qué motor

| Bloque | Contenido | Motor |
|---|---|---|
| 01 Tablas | CREATE TABLE, tipos, PK, FK | SQLite o MySQL |
| 02 Insertar Datos | INSERT, transacciones | SQLite o MySQL |
| 03 SELECT y Lógica | WHERE, CASE, JOIN, GROUP BY | SQLite o MySQL |
| **04 Procedimientos** | DELIMITER, CREATE PROCEDURE, CALL | **MySQL obligatorio** |
| **05 Cursores** | DECLARE CURSOR, FETCH, LOOP | **MySQL obligatorio** |
| **06 Triggers** | BEFORE/AFTER, SIGNAL, auditoría | **MySQL obligatorio** |
| 99 Ejercicio Final | Todo lo anterior combinado | **MySQL obligatorio** |

> Los bloques 01-03 son compatibles con SQLite si el alumno ya tiene VS Code configurado del Curso-SQL introductorio. A partir del bloque 04 es imprescindible MySQL.

---

---

## 🧠 ANTES DE EMPEZAR: 3 CONCEPTOS CLAVE GENERALES

### 1. ¿Qué es una base de datos?

Una base de datos es un conjunto estructurado de datos organizados en **tablas**, como si fueran hojas de Excel, que permiten guardar, consultar y modificar información.

Ejemplo:

```sql
CREATE TABLE empleados (
  id INTEGER PRIMARY KEY,
  nombre TEXT,
  salario REAL
);
```

---

### 2. ¿Qué es SQL?

SQL es un lenguaje de programación diseñado para **consultar y manipular bases de datos**. Se compone de comandos como:

* `CREATE TABLE`: crea nuevas tablas
* `INSERT INTO`: añade datos
* `SELECT`: consulta registros
* `UPDATE`: actualiza información
* `DELETE`: elimina datos

Ejemplo:

```sql
INSERT INTO empleados (nombre, salario) VALUES ('Ana', 1500);
```

---

### 3. ¿Qué es programar sobre una base de datos?

Significa aplicar **lógica de programación** (como condiciones, bucles, decisiones) dentro del entorno SQL, o desde lenguajes como **Java o Python** que se conectan a la base. Por ejemplo:

```sql
SELECT nombre,
  CASE
    WHEN salario < 1000 THEN 'BAJO'
    WHEN salario BETWEEN 1000 AND 2000 THEN 'MEDIO'
    ELSE 'ALTO'
  END AS nivel
FROM empleados;
```

---

## 📊 CONCEPTOS CLAVE DEL TEMARIO

### 🔧 Procedimientos (simulados en SQLite)

Un **procedimiento almacenado** es una serie de instrucciones SQL que se pueden ejecutar con un solo comando. En SQLite los simulamos con consultas preparadas.

Ejemplo:

```sql
-- Simulamos un procedimiento para mostrar el salario con una etiqueta
SELECT nombre, salario,
  CASE
    WHEN salario < 1000 THEN 'Bajo'
    WHEN salario BETWEEN 1000 AND 2000 THEN 'Medio'
    ELSE 'Alto'
  END AS nivel_salario
FROM empleados;
```

Objetivo: que el alumno entienda cómo aplicar condicionales, calcular valores y estructurar la lógica desde SQL.

---

### 🔄 Cursores (simulados con SELECT y ResultSet)

Los **cursores** permiten recorrer fila a fila los resultados de una consulta. Aunque SQLite no tiene cursores como tal, podemos simular su funcionamiento recorriendo un `SELECT` desde Java o mentalmente.

Ejemplo:

```sql
SELECT nombre, salario FROM empleados ORDER BY salario DESC;
```

Desde Java:

```java
while (rs.next()) {
  System.out.println(rs.getString("nombre") + " - " + rs.getDouble("salario"));
}
```

Objetivo: comprender cómo recorrer registros y aplicar operaciones por cada fila.

---

### ⚠️ Triggers

Un **trigger** o disparador es una acción automática que se ejecuta cuando ocurre un evento (como `INSERT`, `UPDATE` o `DELETE`). Son muy útiles para validar o registrar acciones sin necesidad de programar desde fuera.

Ejemplo:

```sql
CREATE TRIGGER evitar_borrado
BEFORE DELETE ON clientes
BEGIN
  SELECT RAISE(ABORT, 'No puedes borrar clientes directamente.');
END;
```

Objetivo: automatizar lógica y proteger la base de datos con reglas internas.

---

## 📦 ESTRUCTURA DEL REPASO

### 🔹 01\_Tablas

* Crear tablas
* Tipos de datos
* Claves primarias y foráneas
* Validaciones con `NOT NULL`, `CHECK`, `UNIQUE`

### 🔹 02\_Insertar\_Datos

* Uso de `INSERT INTO`
* Inserciones múltiples
* Relación entre datos (empleado con departamento)

### 🔹 03\_Select\_Logica

* `WHERE`, `LIKE`, `BETWEEN`
* `CASE`, `IF / ELSE`
* Alias de columnas y condiciones anidadas

### 🔹 04\_Procedimientos\_Simulados

* Comparaciones entre valores
* Cálculos y validaciones
* Simulaciones tipo `if-else`, `switch` con `CASE`

### 🔹 05\_Cursores\_Simulados

* Recorrer listas ordenadas
* Aplicar condiciones dentro del recorrido
* Preparar la transición hacia Java o PL/SQL

### 🔹 06\_Triggers

* Disparadores de tipo `BEFORE` y `AFTER`
* Validaciones automáticas
* Auditoría de cambios

### 🔹 99\_Ejercicio\_Final

* Repaso integral
* Simulación de una situación real de empresa
* Consulta avanzada, triggers, validación, y recorrido

---

## 🧑‍🏫 METODOLOGÍA

Cada carpeta contiene:

* `.sql` con ejercicios listos para ejecutar
* `README.md` explicando la teoría y los objetivos
* Datos de ejemplo
* Ejercicios guiados y opcionales

---

## 👨‍🎓 RECOMENDACIÓN DE ESTUDIO

1. Empieza por `01_Tablas` y asegúrate de que entiendes y ejecutas todo correctamente.
2. Ejecuta los `.sql` en DB Browser o Visual Studio Code.
3. Lee primero el `README.md` de cada carpeta: explica el "por qué" y el "para qué".
4. Modifica los ejercicios para experimentar.
5. Apunta dudas y consulta en clase o en grupo.

---

## 💡 CONSEJO FINAL

**No copies. Piensa, prueba, equivócate, rompe y vuelve a construir.**

La mejor forma de aprender a programar bases de datos es equivocándote y corrigiendo.

🌟 ¡A por ello!
