# ➕ Bloque 02: Inserción de Datos — Guía Completa

En este bloque aprenderás a **poblar las tablas** creadas en **01\_Tablas** con registros de prueba, gestionando transacciones y validaciones para garantizar la **integridad referencial** y evitar errores comunes.

---

## 🎯 Objetivos

1. **Sintaxis básica de `INSERT`**
   Comprender cómo añadir un único registro a una tabla.
2. **Inserción múltiple**
   Aprender a agrupar varias filas en una sola sentencia para mayor eficiencia.
3. **Relaciones con claves foráneas**
   Asegurar que, al insertar en tablas hijas (p.ej. `empleados.departamento_id`), los valores referencien correctamente a la tabla padre (`departamentos.id`).
4. **Transacciones**
   Usar `START TRANSACTION` / `COMMIT` (o `BEGIN` / `COMMIT`) para agrupar múltiples operaciones y poder deshacerlas con `ROLLBACK`.
5. **Validaciones al insertar**
   Prevenir duplicados (`UNIQUE`), valores nulos (`NOT NULL`) y comprobar errores de inserción.

---

## 📘 Teoría Detallada

### 1. Insertar un solo registro

```sql
INSERT INTO departamentos (nombre)
VALUES ('Marketing');
```

* **`INSERT INTO <tabla>`**: especifica la tabla destino.
* **`(col1, col2…)`**: columnas que vas a rellenar.
* **`VALUES (v1, v2…)`**: valores en el mismo orden.

---

### 2. Insertar múltiples registros

```sql
INSERT INTO departamentos (nombre)
VALUES
  ('Ventas'),
  ('IT'),
  ('RRHH');
```

* Un solo `INSERT` → varias filas.
* Más eficiente que ejecutar tres `INSERT` separados.

---

### 3. Manejo de claves foráneas

Al insertar en la tabla **empleados**, debes conocer los IDs escritos en **departamentos**:

```sql
-- Primero aseguramos que exista el departamento con id=2
INSERT INTO departamentos (nombre) VALUES ('Soporte');  -- genera id=4, por ejemplo

-- Luego, lo referenciamos
INSERT INTO empleados (nombre, salario, fecha_alta, departamento_id)
VALUES ('Eva Gómez', 1300.00, '2024-05-10', 4);
```

* Si `departamento_id` no existe en `departamentos`, MySQL lanza un **error de integridad referencial**.

---

### 4. Transacciones

```sql
START TRANSACTION;

INSERT INTO clientes (nombre, email)
VALUES ('Juan Pérez', 'juan.perez@mail.com'),
       ('Ana Torres', 'ana.torres@mail.com');

-- Si ocurre un error antes de COMMIT, podemos revertir:
ROLLBACK;

-- Si todo está bien:
COMMIT;
```

* **`START TRANSACTION`** o **`BEGIN`**: inicia el bloque.
* **`ROLLBACK`**: deshace todas las operaciones desde el inicio de la transacción.
* **`COMMIT`**: confirma todos los cambios.

---

### 5. Validaciones y errores comunes

* **Clave única** (`UNIQUE`)
  Si intentas insertar un email duplicado:

  ```sql
  INSERT INTO clientes (nombre, email)
  VALUES ('Prueba', 'ana.torres@mail.com');
  -- ERROR 1062: Duplicate entry 'ana.torres@mail.com' for key 'clientes.email'
  ```
* **Valores nulos** (`NOT NULL`)
  Si omites una columna obligatoria:

  ```sql
  INSERT INTO empleados (nombre, salario)
  VALUES ('Luis Díaz', 1400);
  -- ERROR: Field 'fecha_alta' doesn't have a default value
  ```

---

## 🧪 Ejercicios Prácticos

1. Inserta **3 departamentos**:

   * ‘Logística’, ‘Finanzas’, ‘Calidad’.
2. Inserta **5 empleados**, asignando cada uno a un departamento válido.
3. Inserta **4 clientes** con emails únicos y prueba a duplicar uno para ver el error.
4. Agrupa todas las inserciones anteriores en una **transacción**, luego usa `ROLLBACK` para deshacer y vuelve a intentar con `COMMIT`.

---

## ✍️ Tareas Sugeridas

* Modifica los nombres y emails para practicar distintos formatos.
* Añade una columna `telefono` a `clientes` y actualiza el script.
* Combina `INSERT` y `SELECT`:

  ```sql
  INSERT INTO empleados (nombre, salario, fecha_alta, departamento_id)
  SELECT nombre, salario*1.1, fecha_alta, departamento_id
  FROM empleados
  WHERE salario < 1000;
  ```

  (Duplica empleados con aumento de salario si ganaban menos de 1000.)

---

> **Tip:** Ejecuta cada bloque en MySQL Workbench, observa los mensajes de “Query OK” y comprueba los cambios con `SELECT * FROM <tabla>;`.

```
```
