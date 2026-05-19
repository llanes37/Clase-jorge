# ⚙️ Bloque 04: Procedimientos Simulados

En este bloque descubrirás dos enfoques para trabajar con **lógica repetible** en bases de datos:

1. **Simulación de procedimientos** usando solo consultas SQL y condicionales (`CASE`).
2. **Procedimientos almacenados** nativos de MySQL, que permiten encapsular lógica y reutilizarla con `CALL`.

---

## 🎯 Objetivos

- Entender la **diferencia** entre una consulta con lógica y un verdadero procedimiento almacenado.
- Aprender a **definir** procedimientos en MySQL con y sin **parámetros**.
- Practicar la **invocación** de procedimientos mediante `CALL`.
- Gestionar la creación y eliminación de procedimientos.

---

## 📘 Teoría

### Simulación de procedimientos
- Usar `SELECT ... CASE ... END` para aplicar lógicas complejas.
- No requiere privilegios especiales ni delimitadores.

### Procedimientos almacenados en MySQL
- Definidos con `CREATE PROCEDURE <nombre>(...)` y delimitadores `DELIMITER`.
- Permiten lógica imperativa (`IF`, `LOOP`, etc.) y múltiples sentencias.
- Invocados con `CALL nombre_procedimiento(param1, param2);`

---

## 🧪 Ejemplos Prácticos

1. **Consultar nivel salarial**  
   - Simulación: `SELECT ... CASE ... END`  
   - Procedimiento nativo: `CALL ObtenerNivelSalario();`
2. **Buscar empleados por nombre**  
   - Procedimiento con parámetro: `CALL BuscarEmpleado('texto');`
3. **Insertar clientes**  
   - Procedimiento `AgregarCliente('Nombre', 'email');`
4. **Eliminar según condición**  
   - Procedimiento `EliminarEmpleadoPorSalario(1000.00);`

---

## ✍️ Tareas Sugeridas

- Modifica `BuscarEmpleado` para que acepte dos parámetros (nombre y salario mínimo).
- Crea un nuevo procedimiento `ActualizarSalario` que reciba `id` y `nuevoSalario`.
- Prueba a eliminar un procedimiento con `DROP PROCEDURE`.
- Experimenta con bloques `IF ... ELSE ... END IF;` dentro de un procedimiento.

---

> **Tip:** Después de crear procedimientos, usa `SHOW PROCEDURE STATUS` para listarlos y `SHOW CREATE PROCEDURE <nombre>;` para ver su código.
