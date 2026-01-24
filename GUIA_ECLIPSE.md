# Guía súper completa: abrir este repositorio en Eclipse y ejecutar `.java` individuales

> Contexto: en esta carpeta tienes varios archivos `.java` sueltos (por ejemplo `UT5_ClasesObjetos.java`) y no un proyecto Java “formal” (Maven/Gradle) con estructura típica. Eclipse funciona mejor cuando esos `.java` están dentro de un **Java Project** con un **JRE/JDK** configurado.

---

## 0) Requisitos (para que compile y ejecute)

1) **Instala un JDK (no solo JRE)**  
   - Recomendado: **JDK 17** o **JDK 21** (LTS).
   - Comprueba en PowerShell:
     - `java -version`
     - `javac -version`

2) **Instala Eclipse IDE for Java Developers**  
   - Cualquier Eclipse reciente sirve (2023/2024/2025).

3) (Opcional pero muy útil) **Git** instalado si vas a clonar/actualizar repos.

---

## 1) Conceptos clave de Eclipse (para entender “por qué no corre” a veces)

- **Workspace**: carpeta donde Eclipse guarda su configuración interna y metadatos de proyectos (no es necesariamente tu repo).  
  - Eclipse crea una carpeta oculta `.metadata` dentro del workspace.
- **Project**: unidad de trabajo dentro del workspace.  
  - En Java, un **Java Project** tiene al menos: `src/`, `bin/`, un JRE asignado y su `.classpath`.
- **Build path / classpath**: lista de carpetas y librerías que Eclipse usa para compilar y ejecutar.
- **Run Configuration**: “plantilla” de ejecución (qué clase tiene `main`, argumentos, VM options, working directory, etc.).

---

## 2) Opción A (recomendada): crear un Java Project e importar los `.java`

### Paso A1: crea un workspace (una vez)

1) Abre Eclipse.
2) Te pedirá **Workspace**:
   - Recomendación: usa algo como `C:\workspace-eclipse\`
   - Evita poner el workspace dentro de carpetas con sync agresivo (OneDrive) si te da problemas.

### Paso A2: crea un proyecto Java vacío

1) `File` → `New` → `Java Project`
2) **Project name**: por ejemplo `ClaseJorge`
3) En **JRE**:
   - Selecciona `Use an execution environment JRE:` y elige `JavaSE-17` o `JavaSE-21`
   - Si no aparece, primero añade el JDK (ver sección 5).
4) Finish.

### Paso A3: importa tus `.java` dentro de `src`

Tienes dos formas buenas:

**Forma 1 (copiar/importar archivos):**
1) Click derecho en `src` → `Import...`
2) `General` → `File System` → `Next`
3) En `From directory` elige:  
   `C:\Users\MediaMarktVillaverde\Desktop\Clases particulares\Clase jorge`
4) Marca los `.java` que quieres (`UT5_ClasesObjetos.java`, etc.)
5) En `Into folder` selecciona tu proyecto → `src`
6) Finish

**Forma 2 (link sin duplicar archivos):**
1) Click derecho en `src` → `New` → `File`
2) Marca `Link to file in the file system`
3) Selecciona el `.java` del disco
4) Repite con los demás `.java`

> Nota: si no usas `package ...;` (paquete) en tus archivos, Eclipse los pondrá en el **default package**. Para ejercicios sencillos está bien, pero para proyectos “serios” se recomienda usar paquetes.

---

## 3) Ejecutar un `.java` individual (si tiene `public static void main(String[] args)`)

1) En el `Package Explorer`, abre el archivo (ej. `UT5_ClasesObjetos.java`)
2) Asegúrate de que:
   - La clase pública coincide con el nombre del archivo:  
     `public class UT5_ClasesObjetos` ↔ `UT5_ClasesObjetos.java`
   - Existe el método:
     - `public static void main(String[] args)`
3) Click derecho dentro del editor o sobre el archivo/clase → `Run As` → `Java Application`

### Si Eclipse te lanza una clase distinta o “se queda con una vieja”

- `Run` → `Run Configurations...`
  - En `Java Application` verás entradas guardadas
  - Borra o edita la configuración y elige la clase correcta

### Debug (muy parecido a “Run”, pero con breakpoints)

1) Pon un breakpoint (doble click en el margen izquierdo)
2) Click derecho → `Debug As` → `Java Application`
3) Usa `Step Over (F6)`, `Step Into (F5)`, `Resume (F8)`

---

## 4) Problemas típicos y soluciones rápidas

### 4.1 “The selection cannot be launched and there are no recent launches”

Causas típicas:
- No estás en un Java Project
- No hay clase con `main`
- Hay errores de compilación

Solución:
- Asegúrate de haber creado el **Java Project** y que el archivo está dentro de `src`
- Corrige errores (ver `Problems` view)
- Ejecuta con `Run As → Java Application`

### 4.2 “Could not find or load main class …”

Suele ser:
- Nombre de clase/archivo no coincide
- Estás usando `package` pero la ruta no coincide con el paquete

Solución:
- Si usas `package mi.paquete;`, el archivo debe estar bajo `src/mi/paquete/`
- Verifica el `Run Configuration` apunta a la clase correcta

### 4.3 Caracteres raros (tildes/emojis salen “rotos”)

En tu repo hay comentarios con símbolos/emojis; si la codificación no coincide, se verán mal.

Soluciones:
- `Window` → `Preferences` → `General` → `Workspace` → `Text file encoding` → `UTF-8`
- Para un proyecto concreto:  
  click derecho proyecto → `Properties` → `Resource` → `Text file encoding` → `UTF-8`
- Si la consola muestra mal, prueba:  
  `Run Configurations...` → `Common` → `Encoding` → `UTF-8` (si aparece en tu versión)

### 4.4 Mezcla de `.class` sueltos en la carpeta (ej. `Persona.class`)

Eclipse compila a su carpeta `bin/` del proyecto. Los `.class` sueltos fuera del proyecto pueden confundir si ejecutas desde consola.

Recomendación:
- En Eclipse: `Project` → `Clean...` y deja que compile a `bin/`
- Si no los necesitas: borra los `.class` del directorio “a mano”

---

## 5) Configurar el JDK en Eclipse (si no te aparece JavaSE-17/21)

1) `Window` → `Preferences`
2) `Java` → `Installed JREs`
3) `Add...`
   - `Standard VM` → `Next`
   - `JRE home`: selecciona la carpeta del JDK, por ejemplo:
     - `C:\Program Files\Java\jdk-17\`
4) Marca ese JDK como **Default**
5) (Opcional) `Java` → `Execution Environments`  
   - Mapea `JavaSE-17` / `JavaSE-21` al JDK instalado

---

## 6) Diferencias importantes: Eclipse vs Visual Studio Code (VS Code)

### Filosofía de trabajo

- **Eclipse**
  - IDE “monolítico” para Java: proyecto + build path + refactor + debug muy integrados
  - Guarda configuraciones en el **workspace**
  - Compila incrementalmente mientras escribes (muy orientado a “proyecto”)

- **VS Code**
  - Editor ligero por defecto; Java depende de extensiones (Language Support for Java, Debugger, etc.)
  - Suele funcionar bien con proyectos Maven/Gradle o con configuración automática, pero puede ser más “manual” con `.java` sueltos
  - La ejecución suele gestionarse con configuraciones (`launch.json`) o botones “Run”

### Ejecutar un `.java` suelto

- En **Eclipse**, normalmente necesitas (o te conviene) un **Java Project** para que todo sea consistente.
- En **VS Code**, a veces puede ejecutar un archivo directamente, pero si hay varias clases/archivos con dependencias, el classpath puede volverse confuso si no es un proyecto formal.

### Refactor y navegación (muy típico en clases)

- Eclipse suele ganar en:
  - refactors seguros (Rename/Move con actualización total)
  - jerarquía de tipos, llamadas, implementaciones
  - organización de imports y “quick fixes” clásicos de Java

---

## 7) ¿Qué es Webclipse y en qué se diferencia de “Visual”?

“Webclipse” normalmente se refiere a un **conjunto de plugins/producto** para Eclipse orientado a **desarrollo web** (JavaScript/TypeScript, frameworks, etc.).

- Para **Java básico** (clases, objetos, herencia, etc.) **no lo necesitas**.
- Puede aportar herramientas web, pero no cambia la base: Eclipse sigue usando proyectos, workspace y build path.

Si con “Visual” te refieres a **Visual Studio Code**:
- VS Code es un editor extensible; Webclipse es “Eclipse con extras web”.
- Para Java de clase (POO), lo más estable suele ser: **Eclipse IDE for Java Developers** sin plugins extra.

---

## 8) Mini-checklist (cuando algo no ejecuta)

1) ¿El archivo está dentro de un **Java Project** en `src/`?
2) ¿La clase tiene `main`?
3) ¿Nombre de archivo = nombre de clase pública?
4) ¿No hay errores en `Problems`?
5) ¿Tienes un JDK instalado y seleccionado?
6) ¿Run Configuration apunta a la clase correcta?

---

## 9) Extra: ejecutar desde consola (por si quieres comparar)

Desde esta carpeta (si el `.java` está “plano” sin paquetes):

1) Compilar:
   - `javac UT5_ClasesObjetos.java`
2) Ejecutar:
   - `java UT5_ClasesObjetos`

En Eclipse, el equivalente es **Build** y **Run As → Java Application**, pero usando el classpath del proyecto.

---

## 10) Siguiente paso recomendado (si quieres dejarlo “como proyecto”)

Si vas a seguir ampliando ejercicios:
- Crea paquetes: por ejemplo `ut5.clasesobjetos`
- Mueve clases a `src/ut5/clasesobjetos/`
- Añade una estructura clara por unidades (UT1, UT2, UT5…)

