# P07 — Almacenando Datos: Ficheros

> **Archivo de práctica:** `P07_Ficheros.java`  
> **PDF de referencia:** `Almacenando datos. Ficheros.pdf`

---

## ¿Por qué ficheros?

Los datos en memoria (variables, objetos) **se pierden** al cerrar el programa. Los ficheros permiten guardarlos de forma **persistente** en disco.

---

## Clases principales (`java.io`)

| Clase | Para qué sirve |
|-------|---------------|
| `File` | Representa un archivo o directorio |
| `FileWriter` | Escribe texto (caracter a caracter) |
| `BufferedWriter` | Escribe texto con buffer (más eficiente) |
| `FileReader` | Lee texto (caracter a caracter) |
| `BufferedReader` | Lee texto línea a línea |

---

## 1. La clase `File`

```java
File f = new File("datos.txt");

f.createNewFile();     // crea el archivo si no existe
f.exists();            // ¿existe?
f.isFile();            // ¿es un archivo?
f.isDirectory();       // ¿es un directorio?
f.delete();            // borra el archivo
f.length();            // tamaño en bytes
f.getName();           // nombre del archivo
f.getAbsolutePath();   // ruta completa
```

---

## 2. Escribir en un fichero

```java
try (FileWriter fw = new FileWriter("notas.txt", false)) {
    // false = sobreescritura (borra lo anterior)
    // true  = append (añade al final)
    fw.write("Hola mundo\n");
} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
}
```

Con `BufferedWriter` para múltiples líneas:

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("notas.txt"))) {
    bw.write("Línea 1");
    bw.newLine();      // salto de línea portable (Windows/Linux)
    bw.write("Línea 2");
    bw.newLine();
}
```

---

## 3. Leer de un fichero

```java
try (BufferedReader br = new BufferedReader(new FileReader("notas.txt"))) {
    String linea;
    while ((linea = br.readLine()) != null) {
        System.out.println(linea);
    }
} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
}
```

> `readLine()` devuelve `null` al llegar al final del archivo.

---

## 4. `try-with-resources` — la forma correcta

```java
try (BufferedWriter bw = new BufferedWriter(new FileWriter("f.txt"))) {
    bw.write("Contenido");
}  // ← bw.close() se llama AUTOMÁTICAMENTE aquí
```

> Siempre usa `try-with-resources` para cerrar streams. Si no los cierras puedes perder datos o tener fugas de memoria.

---

## Modos de apertura

```java
new FileWriter("f.txt")        // sobreescritura (borra el contenido)
new FileWriter("f.txt", true)  // append (añade al final)
```

---

## Flujo típico

```
Crear File  →  Abrir FileWriter/BufferedWriter  →  Escribir  →  Cerrar
Crear File  →  Comprobar exists()               →  Abrir BufferedReader → Leer → Cerrar
```

---

## Reglas resumen

| Regla | Detalle |
|-------|---------|
| Siempre cerrar | Usar `try-with-resources` |
| Comprobar antes de leer | `file.exists()` antes de `FileReader` |
| `newLine()` | Más portable que `"\n"` |
| `append = true` | Para no borrar el contenido existente |
| IOException es checked | Obligatorio manejar con try-catch |

---

## Ejercicio propuesto

1. Método `escribirAgenda(String fichero)` que guarda contactos con formato `"Nombre;Telefono\n"`.
2. Método `leerAgenda(String fichero)` que lee y separa campos con `split(";")`.
3. Prueba ambos en el `main`.
