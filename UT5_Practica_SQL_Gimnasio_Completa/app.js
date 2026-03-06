const scriptCatalog = [
  { id: "creacion", label: "01 Creacion", path: "./01_creacion_tablas_gimnasio.sql" },
  { id: "carga", label: "02 Carga", path: "./02_carga_datos_gimnasio.sql" },
  { id: "alumno", label: "03 Consultas alumno", path: "./03_consultas_gimnasio_alumno.sql" },
  { id: "soluciones", label: "04 Soluciones", path: "./04_consultas_gimnasio_soluciones.sql" },
  { id: "verificaciones", label: "05 Verificaciones", path: "./05_verificaciones_gimnasio.sql" },
];

const quickQueries = [
  {
    title: "Actividades con profesor",
    sql: `SELECT a.descripcion, p.nombre || ' ' || p.apellidos AS profesor
FROM tactividades_g a
JOIN tprofesores_g p ON p.idprofesor = a.idprofesor
ORDER BY a.descripcion;`,
  },
  {
    title: "Alumnos sin clases",
    sql: `SELECT al.idalumno, al.nombre, al.apellidos
FROM talumnos_g al
LEFT JOIN talu_activ ta ON ta.idalumno = al.idalumno
WHERE ta.idalumno IS NULL
ORDER BY al.apellidos;`,
  },
  {
    title: "Demandas de actividades inactivas",
    sql: `SELECT al.nombre, al.apellidos, ac.descripcion
FROM talumnos_g al
JOIN talu_activ_demand td ON td.idalumno = al.idalumno
JOIN tactividades_g ac ON ac.idactividad = td.idactividad
WHERE ac.activa = 'n'
ORDER BY al.apellidos, ac.descripcion;`,
  },
  {
    title: "Numero de alumnos por actividad",
    sql: `SELECT a.descripcion, COUNT(*) AS total_alumnos
FROM tactividades_g a
JOIN talu_activ ta ON ta.idactividad = a.idactividad
GROUP BY a.descripcion
ORDER BY total_alumnos DESC, a.descripcion;`,
  },
];

const state = {
  SQL: null,
  db: null,
  scripts: new Map(),
  selectedScriptId: null,
};

const scriptList = document.querySelector("#script-list");
const scriptViewer = document.querySelector("#script-viewer");
const editor = document.querySelector("#editor");
const results = document.querySelector("#results");
const statusBox = document.querySelector("#status");
const quickList = document.querySelector("#quick-list");
const metricTables = document.querySelector("#metric-tables");
const metricRows = document.querySelector("#metric-rows");
const metricQueries = document.querySelector("#metric-queries");

// *INFO: Cargamos primero los textos de los ficheros para que el alumno vea
// *INFO: exactamente los mismos scripts que va a ejecutar en Oracle.
async function loadScripts() {
  for (const script of scriptCatalog) {
    const response = await fetch(script.path);
    if (!response.ok) {
      throw new Error(`No se pudo cargar ${script.path}`);
    }
    state.scripts.set(script.id, await response.text());
  }
}

// !IMPORTANT: El motor del navegador usa SQLite via sql.js. Esto permite que
// !IMPORTANT: el alumno practique SELECT y JOIN sin tocar la BD real.
async function initSqlJsEngine() {
  const initSqlJs = window.initSqlJs;
  state.SQL = await initSqlJs({
    locateFile: (file) => `./vendor/${file}`,
  });
}

// *INFO: La base del navegador se reinicia desde cero para evitar que el alumno
// *INFO: deje datos alterados de una prueba anterior y luego no entienda nada.
async function resetBrowserDatabase() {
  const response = await fetch("./assets/browser_setup.sql");
  if (!response.ok) {
    throw new Error("No se pudo cargar assets/browser_setup.sql");
  }

  const sqlSetup = await response.text();
  if (state.db) {
    state.db.close();
  }

  state.db = new state.SQL.Database();
  state.db.exec(sqlSetup);
  renderMetrics();
  setStatus("Base del navegador reiniciada con los datos del gimnasio.");
}

function buildScriptButtons() {
  // *INFO: Cada boton representa un archivo fisico de la carpeta. Asi el
  // *INFO: alumno relaciona interfaz visual con material real del repositorio.
  for (const script of scriptCatalog) {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "script-button";
    button.textContent = script.label;
    button.dataset.scriptId = script.id;
    button.addEventListener("click", () => showScript(script.id));
    scriptList.append(button);
  }
}

function showScript(scriptId) {
  const sqlText = state.scripts.get(scriptId) || "";
  scriptViewer.textContent = sqlText;
  state.selectedScriptId = scriptId;
  highlightActiveScriptButton();

  // *INFO: Solo volcamos automaticamente en el editor los archivos de consultas
  // *INFO: porque son los que el alumno tiene mas sentido ejecutar en el HTML.
  if (scriptId === "alumno" || scriptId === "soluciones" || scriptId === "verificaciones") {
    editor.value = sqlText;
  }
}

function buildQuickQueries() {
  // *INFO: Estas consultas sirven como demostraciones rapidas en clase y evitan
  // *INFO: que el profesor tenga que escribir siempre el mismo ejemplo desde cero.
  for (const item of quickQueries) {
    const button = document.createElement("button");
    button.type = "button";
    button.className = "quick-button";
    button.textContent = item.title;
    button.addEventListener("click", () => {
      highlightActiveQuickButton(button);
      editor.value = item.sql;
      runCurrentSql();
    });
    quickList.append(button);
  }
}

function highlightActiveScriptButton() {
  document.querySelectorAll(".script-button").forEach((button) => {
    button.classList.toggle("active", button.dataset.scriptId === state.selectedScriptId);
  });
}

function highlightActiveQuickButton(activeButton) {
  document.querySelectorAll(".quick-button").forEach((button) => {
    button.classList.toggle("active", button === activeButton);
  });
}

function renderMetrics() {
  // *INFO: Las metricas no son decorativas. Sirven para comprobar en un vistazo
  // *INFO: si la base local esta cargada antes de empezar a practicar consultas.
  const tableCount = state.db.exec("SELECT COUNT(*) AS total FROM sqlite_master WHERE type = 'table' AND name NOT LIKE 'sqlite_%';");
  const rowsCount = state.db.exec(`
    SELECT
      (SELECT COUNT(*) FROM talumnos_g) +
      (SELECT COUNT(*) FROM tprofesores_g) +
      (SELECT COUNT(*) FROM tactividades_g) +
      (SELECT COUNT(*) FROM talu_activ) +
      (SELECT COUNT(*) FROM talu_activ_demand) +
      (SELECT COUNT(*) FROM trecibos_g) AS total;
  `);

  metricTables.textContent = tableCount[0].values[0][0];
  metricRows.textContent = rowsCount[0].values[0][0];
  metricQueries.textContent = "14";
}

function setStatus(message, isError = false) {
  statusBox.textContent = message;
  statusBox.dataset.variant = isError ? "error" : "ok";
}

function renderResults(resultSets) {
  results.innerHTML = "";

  if (!resultSets.length) {
    const empty = document.createElement("p");
    empty.className = "empty-message";
    empty.textContent = "La sentencia se ejecuto. No ha devuelto filas.";
    results.append(empty);
    return;
  }

  resultSets.forEach((resultSet, index) => {
    // *INFO: Cada bloque se pinta por separado porque sql.js puede devolver
    // *INFO: varios conjuntos de resultados si el alumno pega varias SELECT.
    const block = document.createElement("section");
    block.className = "result-block";

    const heading = document.createElement("h3");
    heading.textContent = `Resultado ${index + 1}`;
    block.append(heading);

    const wrapper = document.createElement("div");
    wrapper.className = "table-wrapper";
    const table = document.createElement("table");

    const thead = document.createElement("thead");
    const headRow = document.createElement("tr");
    resultSet.columns.forEach((column) => {
      const th = document.createElement("th");
      th.textContent = column;
      headRow.append(th);
    });
    thead.append(headRow);
    table.append(thead);

    const tbody = document.createElement("tbody");
    resultSet.values.forEach((row) => {
      const tr = document.createElement("tr");
      row.forEach((value) => {
        const td = document.createElement("td");
        td.textContent = value === null ? "NULL" : String(value);
        tr.append(td);
      });
      tbody.append(tr);
    });
    table.append(tbody);
    wrapper.append(table);
    block.append(wrapper);
    results.append(block);
  });
}

function sanitizeSqlForBrowser(rawSql) {
  // *INFO: Quitamos comentarios de linea y barras sueltas para que el alumno
  // *INFO: pueda pegar archivos de clase sin limpiar manualmente el contenido.
  return rawSql
    .replace(/^\s*--.*$/gm, "")
    .replace(/^\s*\/\s*$/gm, "")
    .trim();
}

// !IMPORTANT: Filtramos comentarios y bloques vacios para que el alumno pueda
// !IMPORTANT: pegar un archivo grande sin que el simulador falle al primer "--".
function runCurrentSql() {
  try {
    const cleanSql = sanitizeSqlForBrowser(editor.value);

    if (!cleanSql) {
      setStatus("No hay ninguna consulta util para ejecutar.", true);
      results.innerHTML = "";
      return;
    }

    const resultSets = state.db.exec(cleanSql);
    renderResults(resultSets);
    setStatus("Consulta ejecutada correctamente en el simulador.");
  } catch (error) {
    results.innerHTML = "";
    setStatus(error.message, true);
  }
}

function wireEvents() {
  // !IMPORTANT: Centralizar eventos evita duplicar listeners si mas adelante
  // !IMPORTANT: se reinicia la interfaz o se amplian botones del simulador.
  document.querySelector("#run-sql").addEventListener("click", runCurrentSql);
  document.querySelector("#reset-db").addEventListener("click", resetBrowserDatabase);
  document.querySelector("#copy-viewer").addEventListener("click", async () => {
    try {
      await navigator.clipboard.writeText(scriptViewer.textContent);
      setStatus("Script copiado al portapapeles.");
    } catch (error) {
      setStatus("No se pudo copiar automaticamente. Copialo manualmente desde el visor.", true);
    }
  });
  document.querySelector("#load-viewer-into-editor").addEventListener("click", () => {
    editor.value = scriptViewer.textContent;
    setStatus("Contenido cargado en el editor.");
  });
}

async function start() {
  try {
    buildScriptButtons();
    buildQuickQueries();
    await loadScripts();
    await initSqlJsEngine();
    await resetBrowserDatabase();
    showScript("creacion");
    wireEvents();
  } catch (error) {
    setStatus(error.message, true);
  }
}

start();
