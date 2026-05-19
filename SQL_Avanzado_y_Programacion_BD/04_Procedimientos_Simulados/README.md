# Bloque 04 · Procedimientos Almacenados

> Motor: **MySQL 8 obligatorio** · Archivo principal: `procedimientos_simulados.sql`
> Arrancar Docker antes: `docker start repaso-bbdd`

Un **procedimiento almacenado** es un bloque de lógica SQL que vive dentro de la base de datos, tiene nombre propio y se invoca con `CALL`. Es el equivalente a una función en Java o Python, pero que corre directamente en el servidor.

---

## Objetivos

1. Entender la diferencia entre una consulta con lógica y un procedimiento almacenado real
2. Crear procedimientos con y sin parámetros (`IN`, `OUT`)
3. Invocar procedimientos con `CALL`
4. Usar `SIGNAL` para lanzar errores controlados desde dentro
5. Gestionar procedimientos: listar, ver código fuente y eliminar

---

## Teoría esencial

### Estructura base

```sql
DELIMITER $$
CREATE PROCEDURE NombreProcedimiento(IN param1 INT)
BEGIN
    SELECT * FROM empleados WHERE id = param1;
END $$
DELIMITER ;

CALL NombreProcedimiento(1);
```

> `DELIMITER $$` es necesario porque MySQL necesita distinguir el `;` del procedimiento del `;` que lo termina.

### Parámetro OUT

```sql
DELIMITER $$
CREATE PROCEDURE ContarEmpleados(OUT total INT)
BEGIN
    SELECT COUNT(*) INTO total FROM empleados;
END $$
DELIMITER ;

CALL ContarEmpleados(@resultado);
SELECT @resultado;
```

### Lanzar un error controlado

```sql
SIGNAL SQLSTATE '45000'
    SET MESSAGE_TEXT = 'El registro ya existe';
```

---

## Comandos útiles

```sql
SHOW PROCEDURE STATUS WHERE Db = 'repaso_bd';
SHOW CREATE PROCEDURE NombreProcedimiento;
DROP PROCEDURE IF EXISTS NombreProcedimiento;
```

---

## Ejercicios del archivo

| Nº | Procedimiento | Qué hace |
|---|---|---|
| 1 | `ObtenerNivelSalario` | Clasifica empleados por salario |
| 2 | `BuscarEmpleado` | Busca por nombre con parámetro IN |
| 3 | `AgregarCliente` | Inserta cliente validando duplicado |
| 4 | `EliminarEmpleadoPorSalario` | Borra con condición y SIGNAL si no existe |