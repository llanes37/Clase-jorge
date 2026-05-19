# Bloque 06 · Triggers

> Motor: **MySQL 8 obligatorio** · Archivo principal: `06_Triggers_Explicado.sql`

Un **trigger** (disparador) es código SQL que se ejecuta **automáticamente** cuando ocurre un evento (`INSERT`, `UPDATE` o `DELETE`) sobre una tabla. No se llama manualmente: la propia base de datos lo dispara.

---

## Objetivos

1. Crear triggers `BEFORE` y `AFTER` para los tres eventos DML
2. Usar `NEW` y `OLD` para acceder a los valores antes/después del cambio
3. Validar datos y lanzar errores con `SIGNAL`
4. Implementar auditoría automática de cambios en una tabla de log
5. Gestionar triggers: listar, ver código y eliminar

---

## Teoría esencial

### Tipos de trigger

| Tipo | Cuándo se ejecuta | Uso típico |
|---|---|---|
| `BEFORE INSERT` | Antes de insertar | Validar o modificar el valor entrante |
| `AFTER INSERT` | Después de insertar | Registrar en log, actualizar contadores |
| `BEFORE UPDATE` | Antes de actualizar | Validar el nuevo valor |
| `AFTER UPDATE` | Después de actualizar | Auditoría de cambios |
| `BEFORE DELETE` | Antes de borrar | Impedir borrados no autorizados |
| `AFTER DELETE` | Después de borrar | Archivar el registro eliminado |

### Variables NEW y OLD

```sql
-- NEW = valor que va a quedar después de la operación
-- OLD = valor que había antes

-- En BEFORE UPDATE puedes modificar NEW antes de que se guarde:
SET NEW.salario = 0;  -- fuerza el salario a 0 antes de guardar
```

### Estructura base

```sql
DELIMITER $$
CREATE TRIGGER nombre_trigger
    AFTER UPDATE ON empleados
    FOR EACH ROW
BEGIN
    INSERT INTO empleados_log (empleado_id, salario_old, salario_new)
    VALUES (OLD.id, OLD.salario, NEW.salario);
END $$
DELIMITER ;
```

### Impedir una operación

```sql
DELIMITER $$
CREATE TRIGGER trg_no_borrar_clientes
    BEFORE DELETE ON clientes
    FOR EACH ROW
BEGIN
    SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se permite borrar clientes directamente';
END $$
DELIMITER ;
```

---

## Comandos útiles

```sql
SHOW TRIGGERS FROM repaso_bd;             -- listar todos
SHOW CREATE TRIGGER nombre_trigger;       -- ver código fuente
DROP TRIGGER IF EXISTS nombre_trigger;    -- eliminar
```

---

## Ejercicios del archivo

| Nº | Trigger | Tipo | Qué hace |
|---|---|---|---|
| 1 | `trg_prevent_delete_cliente` | BEFORE DELETE | Impide borrar clientes |
| 2 | `trg_validate_salario` | BEFORE INSERT | Rechaza salarios negativos |
| 3 | `trg_audit_update_salario` | AFTER UPDATE | Registra cambios de salario en `empleados_log` |
| 4 | `trg_audit_delete_empleado` | AFTER DELETE | Archiva el registro borrado |