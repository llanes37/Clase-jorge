const STORAGE_KEY = "laboratorio_bases_de_datos_layout_v1";
const DEMOS = [
  {
    id: "biblioteca_demo",
    title: "Biblioteca",
    description: "Socios, autores, categorias, libros y prestamos para practicar lo basico del SQL academico.",
    sqlPath: "muestras/biblioteca_demo.sql",
    practicesPath: "muestras/biblioteca_demo_practicas.json",
    tags: ["SELECT", "WHERE", "JOIN", "GROUP BY"]
  },
  {
    id: "tienda_demo",
    title: "Tienda online",
    description: "Clientes, productos, pedidos y lineas de pedido para practicar filtros, joins y totales.",
    sqlPath: "muestras/tienda_demo.sql",
    practicesPath: "muestras/tienda_demo_practicas.json",
    tags: ["JOIN", "ORDER BY", "COUNT", "SUM"]
  }
];
const appState = {
  sqlJs: null,
  db: null,
  baseline: null,
  appliedScripts: [],
  tableNames: [],
  activeTable: "",
  history: [],
  currentMaterial: null,
  practiceSet: null,
  lastExecution: {
    sql: "",
    error: "",
    resultCount: 0,
    rowCount: 0,
    durationMs: 0,
    mode: "editor"
  },
  layout: {
    sidebarHidden: false,
    infoHidden: false,
    statusHidden: false,
    collapsedPanels: {
      editor: false,
      results: false,
      inspector: false,
      history: false
    }
  }
};

const refs = {
  appShell: document.getElementById("app-shell"),
  workspace: document.getElementById("workspace"),
  mainGrid: document.getElementById("main-grid"),
  tableFilter: document.getElementById("table-filter"),
  tableList: document.getElementById("table-list"),
  tablesCount: document.getElementById("tables-count"),
  topbarSubtitle: document.getElementById("topbar-subtitle"),
  engineStatus: document.getElementById("engine-status"),
  engineStatusDetail: document.getElementById("engine-status-detail"),
  sourceName: document.getElementById("source-name"),
  sourceKind: document.getElementById("source-kind"),
  appliedCount: document.getElementById("applied-count"),
  appliedDetail: document.getElementById("applied-detail"),
  lastRunStatus: document.getElementById("last-run-status"),
  lastRunDetail: document.getElementById("last-run-detail"),
  editorStatus: document.getElementById("editor-status"),
  sqlEditor: document.getElementById("sql-editor"),
  resultsBody: document.getElementById("results-body"),
  tableInspector: document.getElementById("table-inspector"),
  historyList: document.getElementById("history-list"),
  materialView: document.getElementById("material-view"),
  inputSqlLoad: document.getElementById("input-sql-load"),
  inputSqlApply: document.getElementById("input-sql-apply"),
  inputDbLoad: document.getElementById("input-db-load"),
  inputMaterialLoad: document.getElementById("input-material-load"),
  schemaModal: document.getElementById("schema-modal"),
  helpModal: document.getElementById("help-modal"),
  practicesModal: document.getElementById("practices-modal"),
  demoModal: document.getElementById("demo-modal"),
  schemaBody: document.getElementById("mermaid-root"),
  pasteModal: document.getElementById("paste-modal"),
  practicesTitle: document.getElementById("practices-title"),
  practicesSubtitle: document.getElementById("practices-subtitle"),
  practicesList: document.getElementById("practices-list"),
  demoList: document.getElementById("demo-list"),
  pastedScript: document.getElementById("pasted-script"),
  btnShowSidebar: document.getElementById("btn-show-sidebar"),
  btnShowInfo: document.getElementById("btn-show-info"),
  btnToggleStatus: document.getElementById("btn-toggle-status"),
  btnToggleInfo: document.getElementById("btn-toggle-info"),
  btnOpenPractices: document.getElementById("btn-open-practices"),
  btnCloseDemoModal: document.getElementById("btn-close-demo-modal"),
  panels: {
    editor: document.getElementById("editor-panel"),
    results: document.getElementById("results-panel"),
    inspector: document.getElementById("inspector-panel"),
    history: document.getElementById("history-panel")
  },
  sideTabs: Array.from(document.querySelectorAll(".side-tab")),
  sideTabBodies: {
    history: document.getElementById("history-list"),
    material: document.getElementById("material-view")
  }
};

function escapeHtml(value) {
  return String(value).replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
}

function quoteIdentifier(value) {
  return `"${String(value).replaceAll('"', '""')}"`;
}

function summarizeSql(sqlText) {
  const cleaned = String(sqlText || "").trim().replace(/\s+/g, " ");
  return cleaned.length > 140 ? `${cleaned.slice(0, 140)}...` : cleaned || "Consulta vacia";
}

function saveLayoutState() {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(appState.layout));
}

function loadLayoutState() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) return;
    const parsed = JSON.parse(raw);
    appState.layout = {
      ...appState.layout,
      ...parsed,
      collapsedPanels: {
        ...appState.layout.collapsedPanels,
        ...(parsed.collapsedPanels || {})
      }
    };
  } catch {}
}

function applyLayoutState() {
  refs.appShell.classList.toggle("sidebar-hidden", appState.layout.sidebarHidden);
  refs.mainGrid.classList.toggle("info-hidden", appState.layout.infoHidden);
  refs.workspace.classList.toggle("status-hidden", appState.layout.statusHidden);
  refs.btnShowSidebar.classList.toggle("show", appState.layout.sidebarHidden);
  refs.btnShowInfo.classList.toggle("show", appState.layout.infoHidden && window.innerWidth > 1160);
  refs.btnToggleStatus.textContent = appState.layout.statusHidden ? "Mostrar estado" : "Ocultar estado";
  refs.btnToggleInfo.textContent = appState.layout.infoHidden ? "Mostrar lateral derecho" : "Ocultar lateral derecho";

  Object.entries(refs.panels).forEach(([key, panel]) => {
    const collapsed = !!appState.layout.collapsedPanels[key];
    panel.classList.toggle("collapsed", collapsed);
    const toggle = document.querySelector(`[data-panel="${panel.id}"]`);
    if (toggle) {
      toggle.innerHTML = collapsed ? "&#9660;" : "&#9650;";
      toggle.title = collapsed ? "Mostrar panel" : "Plegar panel";
    }
  });
}

function toggleSidebar(hidden) {
  appState.layout.sidebarHidden = hidden;
  applyLayoutState();
  saveLayoutState();
}

function toggleInfoColumn(hidden) {
  appState.layout.infoHidden = hidden;
  applyLayoutState();
  saveLayoutState();
}

function toggleStatus(hidden) {
  appState.layout.statusHidden = hidden;
  applyLayoutState();
  saveLayoutState();
}

function togglePanel(panelKey) {
  appState.layout.collapsedPanels[panelKey] = !appState.layout.collapsedPanels[panelKey];
  applyLayoutState();
  saveLayoutState();
}

function setEngineStatus(title, detail) {
  refs.engineStatus.textContent = title;
  refs.engineStatusDetail.textContent = detail;
}

function setTopbarSubtitle(text) {
  refs.topbarSubtitle.textContent = text;
}

function setEditorStatus(text) {
  refs.editorStatus.textContent = text;
}

function getSuggestedTableName() {
  return appState.activeTable || appState.tableNames[0] || "tabla_ejemplo";
}

function injectHelpSql(sqlTemplate) {
  const tableName = getSuggestedTableName();
  refs.sqlEditor.value = String(sqlTemplate || "").replaceAll("tabla_ejemplo", quoteIdentifier(tableName));
  refs.sqlEditor.focus();
  setEditorStatus(`Ejemplo cargado en el editor. Ajusta la consulta y ejecutala sobre ${tableName}.`);
}

function updatePracticesButton() {
  const count = appState.practiceSet?.items?.length || 0;
  refs.btnOpenPractices.disabled = !count;
  refs.btnOpenPractices.textContent = count ? `Practicar muestra (${count})` : "Practicar muestra";
}

function resetPracticeSet() {
  appState.practiceSet = null;
  refs.practicesTitle.textContent = "Practicas de la muestra";
  refs.practicesSubtitle.textContent = "Ejercicios guiados para trabajar sobre la muestra cargada.";
  refs.practicesList.innerHTML = `<div class="empty-block">No hay practicas disponibles para la base actual.</div>`;
  updatePracticesButton();
}

function normalizePracticeSet(data) {
  if (!data || !Array.isArray(data.items)) return null;
  return {
    id: data.id || "practice_set",
    title: data.title || "Practicas de la muestra",
    description: data.description || "Ejercicios guiados para trabajar sobre la base cargada.",
    items: data.items
      .filter((item) => item && item.title && item.statement)
      .map((item, index) => ({
        id: item.id || `practice_${index + 1}`,
        title: item.title,
        statement: item.statement,
        level: item.level || "Basico",
        focus: Array.isArray(item.focus) ? item.focus : [],
        template: item.template || "",
        solution: item.solution || ""
      }))
  };
}

function derivePracticeManifestPath(fileName) {
  const baseName = String(fileName || "").replace(/\.[^.]+$/u, "");
  return baseName ? `muestras/${baseName}_practicas.json` : null;
}

async function loadTextAsset(path) {
  const response = await fetch(path, { cache: "no-store" });
  if (!response.ok) throw new Error(`No se pudo cargar ${path}`);
  return response.text();
}

async function loadJsonAsset(path) {
  const response = await fetch(path, { cache: "no-store" });
  if (!response.ok) throw new Error(`No se pudo cargar ${path}`);
  return response.json();
}

async function loadPracticeSetForBaseline() {
  resetPracticeSet();
  if (!appState.baseline?.practiceManifest) return;

  try {
    const data = await loadJsonAsset(appState.baseline.practiceManifest);
    const normalized = normalizePracticeSet(data);
    if (!normalized) return;
    appState.practiceSet = normalized;
    refs.practicesTitle.textContent = normalized.title;
    refs.practicesSubtitle.textContent = normalized.description;
    renderPracticeSet();
  } catch {}
}

function loadPracticeIntoEditor(sqlText, title) {
  if (!sqlText) return;
  refs.sqlEditor.value = sqlText;
  refs.sqlEditor.focus();
  setEditorStatus(`Practica cargada en el editor: ${title}.`);
  closePracticesModal();
}

function renderPracticeSet() {
  const practiceSet = appState.practiceSet;
  if (!practiceSet?.items?.length) {
    resetPracticeSet();
    return;
  }

  refs.practicesList.innerHTML = "";
  const wrapper = document.createElement("div");
  wrapper.className = "practice-grid";

  practiceSet.items.forEach((item, index) => {
    const card = document.createElement("section");
    card.className = "practice-card";
    card.innerHTML = `
      <div class="practice-card-head">
        <div>
          <strong>${index + 1}. ${escapeHtml(item.title)}</strong>
          <p>${escapeHtml(item.statement)}</p>
        </div>
        <span class="level-badge">${escapeHtml(item.level)}</span>
      </div>
      <div class="practice-card-body">
        ${item.focus.length ? `<div class="practice-focus">${item.focus.map((focusItem) => `<span class="chip">${escapeHtml(focusItem)}</span>`).join("")}</div>` : ""}
        ${item.template ? `<p><strong>Plantilla orientativa</strong></p><pre>${escapeHtml(item.template)}</pre>` : ""}
        <div class="practice-actions">
          ${item.template ? `<button class="btn secondary" data-practice-template="${escapeHtml(item.id)}">Cargar plantilla</button>` : ""}
          ${item.solution ? `<button class="btn" data-practice-solution="${escapeHtml(item.id)}">Cargar solucion</button>` : ""}
        </div>
      </div>
    `;
    wrapper.appendChild(card);
  });

  refs.practicesList.appendChild(wrapper);

  practiceSet.items.forEach((item) => {
    const templateButton = refs.practicesList.querySelector(`[data-practice-template="${item.id}"]`);
    const solutionButton = refs.practicesList.querySelector(`[data-practice-solution="${item.id}"]`);
    if (templateButton) templateButton.addEventListener("click", () => loadPracticeIntoEditor(item.template, item.title));
    if (solutionButton) solutionButton.addEventListener("click", () => loadPracticeIntoEditor(item.solution, `${item.title} (solucion)`));
  });

  updatePracticesButton();
}

function replaceDatabase(nextDb) {
  if (appState.db) {
    try { appState.db.close(); } catch {}
  }
  appState.db = nextDb;
}

function getCurrentSourceLabel() {
  if (!appState.baseline) return { name: "Base vacia", kind: "Sin origen guardado" };
  const kindMap = {
    empty: "Base vacia generada en el laboratorio",
    sql: "Script SQL cargado y ejecutado",
    demo: "Demo SQL interna del laboratorio",
    binary: "Base SQLite abierta desde archivo"
  };
  return {
    name: appState.baseline.name,
    kind: kindMap[appState.baseline.kind] || "Origen desconocido"
  };
}

function updateStatusCards() {
  const source = getCurrentSourceLabel();
  refs.sourceName.textContent = source.name;
  refs.sourceKind.textContent = source.kind;
  refs.appliedCount.textContent = String(appState.appliedScripts.length);
  refs.appliedDetail.textContent = appState.appliedScripts.length
    ? `Scripts aplicados desde la carga inicial: ${appState.appliedScripts.join(", ")}`
    : "Ningun script aplicado encima de la base origen.";

  if (appState.lastExecution.sql) {
    refs.lastRunStatus.textContent = appState.lastExecution.error ? "Error SQL" : "Consulta correcta";
    refs.lastRunDetail.textContent = appState.lastExecution.error
      ? appState.lastExecution.error
      : `${appState.lastExecution.rowCount} fila(s) visibles en ${appState.lastExecution.durationMs} ms.`;
  } else {
    refs.lastRunStatus.textContent = "Sin consultas";
    refs.lastRunDetail.textContent = "El editor esta listo para ejecutar SQL.";
  }
}

function renderInfoMessage(message, type = "info") {
  refs.resultsBody.innerHTML = `<div class="message ${type}">${escapeHtml(message).replaceAll("\n", "<br>")}</div>`;
}

function getTableNames() {
  if (!appState.db) return [];
  try {
    const result = appState.db.exec("SELECT name FROM sqlite_master WHERE type='table' AND name NOT LIKE 'sqlite_%' ORDER BY name;");
    return result[0]?.values?.map((row) => row[0]) || [];
  } catch {
    return [];
  }
}

function getScalar(query) {
  try {
    const result = appState.db.exec(query);
    return result[0]?.values?.[0]?.[0] ?? 0;
  } catch {
    return 0;
  }
}

function getTableColumns(tableName) {
  try {
    return appState.db.exec(`PRAGMA table_info(${quoteIdentifier(tableName)});`)[0]?.values || [];
  } catch {
    return [];
  }
}

function getAllColumnNames() {
  return [...new Set(appState.tableNames.flatMap((tableName) => getTableColumns(tableName).map((col) => col[1])))];
}

function refreshSchemaState() {
  appState.tableNames = getTableNames();
  refs.tablesCount.textContent = String(appState.tableNames.length);
}

function renderTableList() {
  const filter = refs.tableFilter.value.trim().toLowerCase();
  const visibleTables = appState.tableNames.filter((tableName) => tableName.toLowerCase().includes(filter));

  if (!visibleTables.length) {
    refs.tableList.innerHTML = `<div class="empty-block">${appState.tableNames.length ? "No hay tablas que coincidan con ese filtro." : "Todavia no hay tablas. Carga un script o una base SQLite."}</div>`;
    return;
  }

  refs.tableList.innerHTML = "";
  visibleTables.forEach((tableName) => {
    const rowCount = getScalar(`SELECT COUNT(*) FROM ${quoteIdentifier(tableName)};`);
    const item = document.createElement("button");
    item.type = "button";
    item.className = `table-item${appState.activeTable === tableName ? " active" : ""}`;
    item.innerHTML = `<strong>${escapeHtml(tableName)}</strong><span>${rowCount} fila(s) detectadas</span>`;
    item.addEventListener("click", () => focusTable(tableName));
    refs.tableList.appendChild(item);
  });
}

function renderTableInspector() {
  if (!appState.activeTable || !appState.tableNames.includes(appState.activeTable)) {
    refs.tableInspector.innerHTML = `<div class="empty-block">Selecciona una tabla del lateral para ver columnas, recuentos y consultas rapidas.</div>`;
    return;
  }

  const tableName = appState.activeTable;
  const rowCount = getScalar(`SELECT COUNT(*) FROM ${quoteIdentifier(tableName)};`);
  const columns = getTableColumns(tableName);

  refs.tableInspector.innerHTML = `
    <section class="inspector-card">
      <h4>${escapeHtml(tableName)}</h4>
      <div class="inspector-body">
        <p>Filas detectadas: <strong>${rowCount}</strong></p>
        <p style="margin-top:8px;">Columnas: <strong>${columns.length}</strong></p>
        <div style="margin-top:14px;">
          ${columns.map((col) => `<span class="chip">${escapeHtml(col[1])} : ${escapeHtml(col[2] || "TEXT")}</span>`).join("")}
        </div>
      </div>
    </section>
  `;
}

function recordHistory(entry) {
  appState.history.unshift(entry);
  appState.history = appState.history.slice(0, 8);
  renderHistory();
}

function renderHistory() {
  if (!appState.history.length) {
    refs.historyList.innerHTML = `<div class="empty-block">Todavia no hay consultas en el historial.</div>`;
    return;
  }

  refs.historyList.innerHTML = "";
  appState.history.forEach((entry) => {
    const card = document.createElement("button");
    card.type = "button";
    card.className = `history-item ${entry.status}`;
    card.innerHTML = `
      <div class="history-head">
        <span class="history-status">${entry.status === "ok" ? "Correcta" : "Con error"}</span>
        <span class="section-meta">${entry.time}</span>
      </div>
      <pre class="history-sql">${escapeHtml(summarizeSql(entry.sql))}</pre>
    `;
    card.addEventListener("click", () => {
      refs.sqlEditor.value = entry.sql;
      refs.sqlEditor.focus();
      setEditorStatus("Consulta recuperada desde el historial.");
    });
    refs.historyList.appendChild(card);
  });
}

function setSideTab(tabId) {
  refs.sideTabs.forEach((button) => {
    button.classList.toggle("active", button.dataset.sideTab === tabId);
  });
  Object.entries(refs.sideTabBodies).forEach(([key, body]) => {
    body.classList.toggle("active", key === tabId);
  });
}

function clearCurrentMaterial() {
  if (appState.currentMaterial?.objectUrl) {
    URL.revokeObjectURL(appState.currentMaterial.objectUrl);
  }
  appState.currentMaterial = null;
}

function renderMaterialView() {
  if (!appState.currentMaterial) {
    refs.materialView.innerHTML = `<div class="empty-block">Carga un PDF, Markdown, HTML o TXT para tener material de apoyo dentro del laboratorio.</div>`;
    return;
  }

  const material = appState.currentMaterial;
  let bodyMarkup = "";

  if (material.kind === "pdf") {
    bodyMarkup = `<iframe class="material-pdf-frame" src="${material.objectUrl}" title="${escapeHtml(material.name)}"></iframe>`;
  } else if (material.kind === "html") {
    bodyMarkup = `<iframe class="material-html-frame" src="${material.objectUrl}" title="${escapeHtml(material.name)}"></iframe>`;
  } else if (material.kind === "markdown") {
    bodyMarkup = `<div class="material-markdown">${marked.parse(material.content)}</div>`;
  } else {
    bodyMarkup = `<pre>${escapeHtml(material.content)}</pre>`;
  }

  refs.materialView.innerHTML = `
    <section class="material-card">
      <div class="material-head">
        <strong>${escapeHtml(material.name)}</strong>
        <span class="section-meta">${escapeHtml(material.label)}</span>
      </div>
      <div class="material-body">
        ${bodyMarkup}
      </div>
    </section>
  `;
}

function inferMaterialKind(fileName, mimeType) {
  const lowerName = fileName.toLowerCase();
  if (mimeType === "application/pdf" || lowerName.endsWith(".pdf")) return "pdf";
  if (mimeType === "text/html" || lowerName.endsWith(".html") || lowerName.endsWith(".htm")) return "html";
  if (mimeType === "text/markdown" || lowerName.endsWith(".md") || lowerName.endsWith(".markdown")) return "markdown";
  return "text";
}

async function handleMaterialLoad(event) {
  const file = event.target.files?.[0];
  event.target.value = "";
  if (!file) return;

  clearCurrentMaterial();
  const kind = inferMaterialKind(file.name, file.type);

  try {
    if (kind === "pdf") {
      appState.currentMaterial = {
        name: file.name,
        kind,
        label: "PDF",
        objectUrl: URL.createObjectURL(file)
      };
    } else if (kind === "html") {
      const content = await file.text();
      const blob = new Blob([content], { type: "text/html" });
      appState.currentMaterial = {
        name: file.name,
        kind,
        label: "HTML",
        content,
        objectUrl: URL.createObjectURL(blob)
      };
    } else {
      const content = await file.text();
      appState.currentMaterial = {
        name: file.name,
        kind,
        label: kind === "markdown" ? "Markdown" : "Texto",
        content
      };
    }
    renderMaterialView();
    setSideTab("material");
  } catch (error) {
    refs.materialView.innerHTML = `<div class="message error">No se pudo cargar el material.<br>${escapeHtml(error.message)}</div>`;
    setSideTab("material");
  }
}

function updateAllViews() {
  refreshSchemaState();
  renderTableList();
  renderTableInspector();
  renderHistory();
  updateStatusCards();
}

async function createEmptyDatabase({ setBaseline = true, name = "Base vacia" } = {}) {
  const freshDb = new appState.sqlJs.Database();
  replaceDatabase(freshDb);
  appState.activeTable = "";
  appState.appliedScripts = [];
  appState.history = [];
  resetPracticeSet();
  appState.lastExecution = { sql: "", error: "", resultCount: 0, rowCount: 0, durationMs: 0, mode: "editor" };
  if (setBaseline) appState.baseline = { kind: "empty", name };
  setTopbarSubtitle("Base vacia preparada. Puedes cargar un script SQL, pegarlo manualmente o abrir una base SQLite existente.");
  setEditorStatus("Base vacia lista para trabajar.");
  renderInfoMessage("Sesion reiniciada a base vacia. Usa el boton Ayuda si quieres ver como empezar paso a paso.", "success");
  updateAllViews();
}

async function executeScriptAsNewBase(scriptText, name, kind = "sql", options = {}) {
  const tempDb = new appState.sqlJs.Database();
  try {
    tempDb.exec(scriptText);
  } catch (error) {
    tempDb.close();
    throw error;
  }

  replaceDatabase(tempDb);
  appState.baseline = {
    kind,
    name,
    scriptText,
    practiceManifest: options.practiceManifest ?? derivePracticeManifestPath(name)
  };
  appState.appliedScripts = [];
  appState.activeTable = "";
  appState.history = [];
  appState.lastExecution = { sql: "", error: "", resultCount: 0, rowCount: 0, durationMs: 0, mode: "editor" };
  setTopbarSubtitle(`Base cargada desde ${name}. Puedes explorar tablas o ejecutar consultas libres.`);
  setEditorStatus(`Base creada desde ${name}.`);
  renderInfoMessage(`Base cargada correctamente desde ${name}.\nAhora revisa las tablas del lateral, pulsa una para generar una consulta base o abre la ayuda para ver ejemplos listos.`, "success");
  updateAllViews();
  await loadPracticeSetForBaseline();
}

async function executeScriptOnCurrent(scriptText, name) {
  const snapshot = appState.db.export();
  const tempDb = new appState.sqlJs.Database(snapshot);
  try {
    tempDb.exec(scriptText);
  } catch (error) {
    tempDb.close();
    throw error;
  }

  replaceDatabase(tempDb);
  appState.appliedScripts.push(name);
  setTopbarSubtitle(`Script aplicado sobre la base actual: ${name}.`);
  setEditorStatus(`Script aplicado correctamente: ${name}.`);
  renderInfoMessage(`Script ejecutado sobre la base actual: ${name}.`, "success");
  updateAllViews();
}

async function openDatabaseFile(file) {
  const bytes = new Uint8Array(await file.arrayBuffer());
  const tempDb = new appState.sqlJs.Database(bytes);
  replaceDatabase(tempDb);
  appState.baseline = { kind: "binary", name: file.name, bytes: Array.from(bytes), practiceManifest: null };
  appState.appliedScripts = [];
  appState.activeTable = "";
  appState.history = [];
  resetPracticeSet();
  appState.lastExecution = { sql: "", error: "", resultCount: 0, rowCount: 0, durationMs: 0, mode: "editor" };
  setTopbarSubtitle(`Base SQLite abierta desde ${file.name}. Puedes inspeccionar tablas o lanzar consultas sobre ella.`);
  setEditorStatus(`Base abierta desde archivo: ${file.name}.`);
  renderInfoMessage(`Base SQLite abierta correctamente: ${file.name}.\nBusca las tablas a la izquierda y pulsa una para empezar con un SELECT real.`, "success");
  updateAllViews();
}

async function resetToBaseline() {
  if (!appState.baseline) {
    await createEmptyDatabase({ setBaseline: true, name: "Base vacia" });
    return;
  }

  if (appState.baseline.kind === "empty") {
    await createEmptyDatabase({ setBaseline: true, name: appState.baseline.name });
    return;
  }

  if (appState.baseline.kind === "binary") {
    const bytes = Uint8Array.from(appState.baseline.bytes);
    const tempDb = new appState.sqlJs.Database(bytes);
    replaceDatabase(tempDb);
    appState.appliedScripts = [];
    appState.activeTable = "";
    appState.history = [];
    resetPracticeSet();
    appState.lastExecution = { sql: "", error: "", resultCount: 0, rowCount: 0, durationMs: 0, mode: "editor" };
    setTopbarSubtitle(`Base restaurada desde ${appState.baseline.name}.`);
    setEditorStatus("La base ha vuelto a su estado original.");
    renderInfoMessage(`Base restaurada correctamente desde ${appState.baseline.name}.`, "success");
    updateAllViews();
    return;
  }

  await executeScriptAsNewBase(
    appState.baseline.scriptText,
    appState.baseline.name,
    appState.baseline.kind,
    { practiceManifest: appState.baseline.practiceManifest || null }
  );
}

function downloadCurrentDatabase() {
  if (!appState.db) return;
  const binaryArray = appState.db.export();
  const blob = new Blob([binaryArray], { type: "application/octet-stream" });
  const link = document.createElement("a");
  link.href = URL.createObjectURL(blob);
  link.download = "laboratorio_bases_de_datos.sqlite";
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
  URL.revokeObjectURL(link.href);
  setEditorStatus("Base exportada correctamente.");
}

function buildSchemaDiagram() {
  const tables = appState.tableNames;
  if (!tables.length) return "flowchart TD\n    A[No hay tablas cargadas]";

  const safeNames = new Map(tables.map((tableName) => [tableName, tableName.replace(/[^a-zA-Z0-9_]/g, "_") || "tabla"]));
  let diagram = "erDiagram\n";

  tables.forEach((tableName) => {
    const foreignKeys = appState.db.exec(`PRAGMA foreign_key_list(${quoteIdentifier(tableName)});`)[0]?.values || [];
    foreignKeys.forEach((fk) => {
      const source = safeNames.get(fk[2]);
      const target = safeNames.get(tableName);
      if (source && target) diagram += `    ${source} ||--o{ ${target} : "${fk[3]}"\n`;
    });
  });

  tables.forEach((tableName) => {
    const safeName = safeNames.get(tableName);
    const columns = getTableColumns(tableName);
    const foreignKeyColumns = new Set((appState.db.exec(`PRAGMA foreign_key_list(${quoteIdentifier(tableName)});`)[0]?.values || []).map((row) => row[3]));
    diagram += `    ${safeName} {\n`;
    columns.forEach((col) => {
      const type = (col[2] || "TEXT").split(" ")[0];
      const modifiers = [];
      if (col[5] === 1) modifiers.push("PK");
      if (foreignKeyColumns.has(col[1])) modifiers.push("FK");
      diagram += `        ${type} ${col[1]} ${modifiers.length ? `"${modifiers.join(",")}"` : ""}\n`;
    });
    diagram += "    }\n";
  });

  return diagram;
}

function openSchemaModal() {
  refs.schemaBody.innerHTML = `<div class="mermaid">${buildSchemaDiagram()}</div>`;
  mermaid.init(undefined, refs.schemaBody.querySelectorAll(".mermaid"));
  refs.schemaModal.classList.add("show");
}

function closeSchemaModal() {
  refs.schemaModal.classList.remove("show");
}

function openHelpModal() {
  refs.helpModal.classList.add("show");
}

function closeHelpModal() {
  refs.helpModal.classList.remove("show");
}

function renderDemoList() {
  refs.demoList.innerHTML = "";
  DEMOS.forEach((demo) => {
    const card = document.createElement("section");
    card.className = "demo-card";
    card.innerHTML = `
      <div class="demo-card-head">
        <strong>${escapeHtml(demo.title)}</strong>
        <p>${escapeHtml(demo.description)}</p>
      </div>
      <div class="demo-card-body">
        <div class="demo-meta">
          ${demo.tags.map((tag) => `<span class="chip">${escapeHtml(tag)}</span>`).join("")}
        </div>
        <p>Archivo base: <strong>${escapeHtml(demo.sqlPath.split("/").pop())}</strong></p>
        <button class="btn primary" data-demo-id="${escapeHtml(demo.id)}">Cargar esta demo</button>
      </div>
    `;
    refs.demoList.appendChild(card);
  });

  DEMOS.forEach((demo) => {
    const button = refs.demoList.querySelector(`[data-demo-id="${demo.id}"]`);
    if (button) {
      button.addEventListener("click", () => loadSelectedDemo(demo.id));
    }
  });
}

function openDemoModal() {
  renderDemoList();
  refs.demoModal.classList.add("show");
}

function closeDemoModal() {
  refs.demoModal.classList.remove("show");
}

async function loadSelectedDemo(demoId) {
  const demo = DEMOS.find((item) => item.id === demoId);
  if (!demo) return;

  try {
    const scriptText = await loadTextAsset(demo.sqlPath);
    await executeScriptAsNewBase(scriptText, demo.sqlPath.split("/").pop(), "demo", {
      practiceManifest: demo.practicesPath
    });
    closeDemoModal();
  } catch (error) {
    renderInfoMessage(`No se pudo cargar la demo ${demo.title}.\n${error.message}`, "error");
  }
}

function openPracticesModal() {
  if (!appState.practiceSet?.items?.length) return;
  refs.practicesModal.classList.add("show");
}

function closePracticesModal() {
  refs.practicesModal.classList.remove("show");
}

function openPasteModal() {
  refs.pasteModal.classList.add("show");
}

function closePasteModal() {
  refs.pasteModal.classList.remove("show");
}

function getEditorSelectionOrWhole() {
  const start = refs.sqlEditor.selectionStart;
  const end = refs.sqlEditor.selectionEnd;
  const selected = refs.sqlEditor.value.slice(start, end).trim();
  return selected || refs.sqlEditor.value.trim();
}

function inferSqlTips(sqlText, errorMessage = "") {
  const sql = String(sqlText || "").trim();
  const normalized = sql.toUpperCase();
  const tips = [];

  if (!sql) tips.push("No hay SQL para ejecutar.");
  if (normalized.includes("SELECT *") && !normalized.includes("LIMIT")) tips.push("Si solo quieres inspeccionar datos, anade LIMIT para no abrir una tabla demasiado grande.");
  if (normalized.includes("JOIN") && !normalized.includes(" ON ")) tips.push("Hay un JOIN sin ON visible. Revisa la condicion de union.");
  if (normalized.includes("GROUP BY") && !/(COUNT|SUM|AVG|MIN|MAX)\s*\(/.test(normalized)) tips.push("Hay GROUP BY pero no aparece una agregacion clara.");
  if (/syntax error/i.test(errorMessage)) tips.push("SQLite detecta un error de sintaxis. Revisa comas, parentesis, alias y orden de clausulas.");
  if (/no such table/i.test(errorMessage)) tips.push("La tabla no existe en la base cargada. Comprueba nombres en el lateral o en el esquema.");
  if (/no such column/i.test(errorMessage)) tips.push("La columna no existe o no esta disponible en ese contexto.");
  if (/ambiguous column name/i.test(errorMessage)) tips.push("La columna es ambigua. Usa alias o el nombre de la tabla delante.");
  if (!tips.length) tips.push("No se detectan patrones problematicos adicionales en la consulta.");
  return tips;
}

function levenshteinDistance(left, right) {
  const a = left.toLowerCase();
  const b = right.toLowerCase();
  const matrix = Array.from({ length: a.length + 1 }, (_, row) =>
    Array.from({ length: b.length + 1 }, (_, col) => (row === 0 ? col : col === 0 ? row : 0))
  );

  for (let row = 1; row <= a.length; row++) {
    for (let col = 1; col <= b.length; col++) {
      const cost = a[row - 1] === b[col - 1] ? 0 : 1;
      matrix[row][col] = Math.min(
        matrix[row - 1][col] + 1,
        matrix[row][col - 1] + 1,
        matrix[row - 1][col - 1] + cost
      );
    }
  }

  return matrix[a.length][b.length];
}

function suggestClosestItems(input, candidates, maxSuggestions = 3) {
  const value = String(input || "").trim().toLowerCase();
  if (!value) return [];

  return candidates
    .map((candidate) => {
      const lower = candidate.toLowerCase();
      const score = lower.includes(value) || value.includes(lower) ? 0 : levenshteinDistance(value, lower);
      return { candidate, score };
    })
    .sort((left, right) => left.score - right.score)
    .filter((item, index) => index < maxSuggestions && item.score <= Math.max(3, Math.floor(item.candidate.length / 2)))
    .map((item) => item.candidate);
}

function buildErrorDetails(errorMessage, sqlText) {
  const details = {
    summary: "SQLite no ha podido ejecutar la consulta.",
    hints: inferSqlTips(sqlText, errorMessage),
    matches: []
  };

  const tableMatch = errorMessage.match(/no such table:\s*([^\s;]+)/i);
  if (tableMatch) {
    const missingTable = tableMatch[1].replace(/["'`]/g, "");
    const similarTables = suggestClosestItems(missingTable, appState.tableNames);
    details.summary = `La tabla \`${missingTable}\` no existe en la base cargada.`;
    if (similarTables.length) details.matches.push(`Quiza querias escribir: ${similarTables.map((item) => `\`${item}\``).join(", ")}.`);
  }

  const columnMatch = errorMessage.match(/no such column:\s*([^\s;]+)/i);
  if (columnMatch) {
    const missingColumn = columnMatch[1].replace(/["'`]/g, "");
    const similarColumns = suggestClosestItems(missingColumn, getAllColumnNames());
    details.summary = `La columna \`${missingColumn}\` no existe o no esta accesible en esa consulta.`;
    if (similarColumns.length) details.matches.push(`Columnas parecidas: ${similarColumns.map((item) => `\`${item}\``).join(", ")}.`);
  }

  if (/syntax error/i.test(errorMessage)) details.summary = "Hay un error de sintaxis en la consulta.";
  if (/ambiguous column name/i.test(errorMessage)) details.summary = "Hay una columna ambigua en una consulta con varias tablas.";
  return details;
}

function renderExecutionSummary(sourceSql, meta = {}) {
  const modeLabel = meta.mode === "selection" ? "Seleccion" : "Editor completo";
  const wrapper = document.createElement("section");
  wrapper.className = "execution-card";
  wrapper.innerHTML = `
    <div class="execution-card-header">
      <strong>Consulta ejecutada</strong>
      <div class="execution-meta">
        <span class="meta-badge">${modeLabel}</span>
        <span class="meta-badge">${meta.durationMs || 0} ms</span>
        <span class="meta-badge">${meta.resultCount || 0} bloque(s)</span>
        <span class="meta-badge">${meta.rowCount || 0} fila(s)</span>
      </div>
    </div>
    <pre class="execution-sql">${escapeHtml(sourceSql)}</pre>
  `;
  refs.resultsBody.appendChild(wrapper);
}

function renderDiagnosticPanel(kind, title, summary, items = [], extraLines = []) {
  const section = document.createElement("section");
  section.className = `diagnostic-card${kind === "success" ? " successish" : ""}`;
  const listMarkup = items.length ? `<ul class="diagnostic-list">${items.map((item) => `<li>${escapeHtml(item)}</li>`).join("")}</ul>` : "";
  const extraMarkup = extraLines.length ? `<ul class="diagnostic-list">${extraLines.map((item) => `<li>${escapeHtml(item)}</li>`).join("")}</ul>` : "";
  section.innerHTML = `
    <div class="diagnostic-header">
      <strong>${kind === "success" ? "Estado de ejecucion" : "Diagnostico"}</strong>
    </div>
    <div class="diagnostic-body">
      <h4 class="diagnostic-title">${escapeHtml(title)}</h4>
      <p class="diagnostic-text">${escapeHtml(summary)}</p>
      ${listMarkup}
      ${extraMarkup}
    </div>
  `;
  refs.resultsBody.appendChild(section);
}

function renderErrorResults(errorMessage, sourceSql, meta) {
  refs.resultsBody.innerHTML = "";
  renderExecutionSummary(sourceSql, meta);
  const details = buildErrorDetails(errorMessage, sourceSql);
  renderDiagnosticPanel("error", `Error SQL: ${errorMessage}`, details.summary, details.hints, details.matches);
}

function renderResults(results, sourceSql, meta) {
  refs.resultsBody.innerHTML = "";
  renderExecutionSummary(sourceSql, meta);

  if (!results || !results.length) {
    renderDiagnosticPanel("success", "Ejecucion correcta", "La sentencia se ha ejecutado bien, pero no ha devuelto filas para mostrar.", [
      "Esto es normal en CREATE, INSERT, UPDATE, DELETE, ALTER o DROP.",
      "Si esperabas una tabla de salida, revisa si falta un SELECT al final."
    ]);
    return;
  }

  renderDiagnosticPanel("success", "Consulta completada", "La consulta se ha ejecutado correctamente y las tablas devueltas aparecen debajo.", [
    `Bloques devueltos: ${results.length}.`,
    `Filas visibles: ${meta.rowCount}.`
  ]);

  results.forEach((result, index) => {
    const wrapper = document.createElement("section");
    wrapper.className = "result-card";
    wrapper.innerHTML = `<h4>Resultado ${index + 1} - ${result.values.length} fila(s) - ${result.columns.length} columna(s)</h4>`;
    const table = document.createElement("table");
    const thead = document.createElement("thead");
    const headerRow = document.createElement("tr");
    result.columns.forEach((column) => {
      const th = document.createElement("th");
      th.textContent = column;
      headerRow.appendChild(th);
    });
    thead.appendChild(headerRow);
    table.appendChild(thead);
    const tbody = document.createElement("tbody");
    result.values.forEach((row) => {
      const tr = document.createElement("tr");
      row.forEach((value) => {
        const td = document.createElement("td");
        td.textContent = value === null ? "NULL" : String(value);
        tr.appendChild(td);
      });
      tbody.appendChild(tr);
    });
    table.appendChild(tbody);
    const scroll = document.createElement("div");
    scroll.className = "table-scroll";
    scroll.appendChild(table);
    wrapper.appendChild(scroll);
    refs.resultsBody.appendChild(wrapper);
  });
}

function executeEditor(runSelectionOnly = false) {
  if (!appState.db) return;
  const sqlText = runSelectionOnly ? getEditorSelectionOrWhole() : refs.sqlEditor.value.trim();
  if (!sqlText) {
    renderInfoMessage("No hay SQL para ejecutar.\nSi no sabes por donde empezar, abre Ayuda o pulsa una tabla del lateral para generar una consulta base.", "info");
    return;
  }

  const startedAt = performance.now();
  try {
    const results = appState.db.exec(sqlText);
    const durationMs = Math.max(1, Math.round(performance.now() - startedAt));
    const rowCount = results.reduce((total, result) => total + result.values.length, 0);
    appState.lastExecution = { sql: sqlText, error: "", resultCount: results.length, rowCount, durationMs, mode: runSelectionOnly ? "selection" : "editor" };
    renderResults(results, sqlText, appState.lastExecution);
    setEditorStatus(runSelectionOnly ? "Se ha ejecutado la seleccion actual." : "Se ha ejecutado todo el editor.");
    recordHistory({ sql: sqlText, status: "ok", time: new Date().toLocaleTimeString("es-ES") });
    updateStatusCards();
    refreshSchemaState();
    renderTableList();
    renderTableInspector();
  } catch (error) {
    const durationMs = Math.max(1, Math.round(performance.now() - startedAt));
    appState.lastExecution = { sql: sqlText, error: error.message, resultCount: 0, rowCount: 0, durationMs, mode: runSelectionOnly ? "selection" : "editor" };
    renderErrorResults(error.message, sqlText, appState.lastExecution);
    setEditorStatus("La consulta ha fallado. Revisa el diagnostico.");
    recordHistory({ sql: sqlText, status: "error", time: new Date().toLocaleTimeString("es-ES") });
    updateStatusCards();
  }
}

function focusTable(tableName) {
  appState.activeTable = tableName;
  renderTableList();
  renderTableInspector();
  refs.sqlEditor.value = `SELECT *\nFROM ${quoteIdentifier(tableName)}\nLIMIT 25;`;
  executeEditor(false);
  setEditorStatus(`Vista rapida cargada para la tabla ${tableName}.`);
}

async function handleSqlFileLoad(event, mode) {
  const file = event.target.files?.[0];
  event.target.value = "";
  if (!file) return;
  try {
    const scriptText = await file.text();
    if (mode === "replace") await executeScriptAsNewBase(scriptText, file.name, "sql");
    else await executeScriptOnCurrent(scriptText, file.name);
  } catch (error) {
    renderInfoMessage(`No se pudo procesar ${file.name}.\n${error.message}`, "error");
  }
}

async function handleDbFileLoad(event) {
  const file = event.target.files?.[0];
  event.target.value = "";
  if (!file) return;
  try {
    await openDatabaseFile(file);
  } catch (error) {
    renderInfoMessage(`No se pudo abrir ${file.name} como base SQLite.\n${error.message}`, "error");
  }
}

async function loadDemo() {
  openDemoModal();
}

async function runPastedScript(mode) {
  const scriptText = refs.pastedScript.value.trim();
  if (!scriptText) {
    renderInfoMessage("El script pegado esta vacio.", "info");
    return;
  }

  try {
    if (mode === "replace") await executeScriptAsNewBase(scriptText, "script_pegado.sql", "sql");
    else await executeScriptOnCurrent(scriptText, "script_pegado.sql");
    closePasteModal();
  } catch (error) {
    renderInfoMessage(`No se pudo ejecutar el script pegado.\n${error.message}`, "error");
  }
}

function bindEvents() {
  refs.tableFilter.addEventListener("input", renderTableList);

  document.querySelectorAll("[data-help-sql]").forEach((button) => {
    button.addEventListener("click", () => injectHelpSql(button.dataset.helpSql));
  });

  refs.sqlEditor.addEventListener("keydown", (event) => {
    if ((event.ctrlKey || event.metaKey) && event.shiftKey && event.key === "Enter") {
      event.preventDefault();
      executeEditor(false);
      return;
    }
    if ((event.ctrlKey || event.metaKey) && event.key === "Enter") {
      event.preventDefault();
      executeEditor(true);
    }
  });

  document.getElementById("btn-new-empty").addEventListener("click", () => createEmptyDatabase({ setBaseline: true, name: "Base vacia" }));
  document.getElementById("btn-open-help").addEventListener("click", openHelpModal);
  refs.btnOpenPractices.addEventListener("click", openPracticesModal);
  document.getElementById("btn-load-sql").addEventListener("click", () => refs.inputSqlLoad.click());
  document.getElementById("btn-apply-sql").addEventListener("click", () => refs.inputSqlApply.click());
  document.getElementById("btn-paste-sql").addEventListener("click", openPasteModal);
  document.getElementById("btn-load-db").addEventListener("click", () => refs.inputDbLoad.click());
  document.getElementById("btn-load-demo").addEventListener("click", loadDemo);
  document.getElementById("btn-load-material").addEventListener("click", () => refs.inputMaterialLoad.click());
  document.getElementById("btn-clear-material").addEventListener("click", () => {
    clearCurrentMaterial();
    renderMaterialView();
  });
  document.getElementById("btn-open-schema").addEventListener("click", openSchemaModal);
  document.getElementById("btn-reset-db").addEventListener("click", resetToBaseline);
  document.getElementById("btn-clear-all").addEventListener("click", () => createEmptyDatabase({ setBaseline: true, name: "Base vacia" }));
  document.getElementById("btn-export-db").addEventListener("click", downloadCurrentDatabase);
  document.getElementById("btn-run-selection").addEventListener("click", () => executeEditor(true));
  document.getElementById("btn-run-editor").addEventListener("click", () => executeEditor(false));
  document.getElementById("btn-clear-editor").addEventListener("click", () => {
    refs.sqlEditor.value = "";
    setEditorStatus("Editor limpiado.");
  });

  refs.inputSqlLoad.addEventListener("change", (event) => handleSqlFileLoad(event, "replace"));
  refs.inputSqlApply.addEventListener("change", (event) => handleSqlFileLoad(event, "apply"));
  refs.inputDbLoad.addEventListener("change", handleDbFileLoad);
  refs.inputMaterialLoad.addEventListener("change", handleMaterialLoad);

  document.getElementById("btn-close-schema").addEventListener("click", closeSchemaModal);
  document.getElementById("btn-close-help").addEventListener("click", closeHelpModal);
  document.getElementById("btn-close-practices").addEventListener("click", closePracticesModal);
  refs.btnCloseDemoModal.addEventListener("click", closeDemoModal);
  document.getElementById("btn-close-paste").addEventListener("click", closePasteModal);
  document.getElementById("btn-run-pasted-replace").addEventListener("click", () => runPastedScript("replace"));
  document.getElementById("btn-run-pasted-apply").addEventListener("click", () => runPastedScript("apply"));

  refs.schemaModal.addEventListener("click", (event) => {
    if (event.target === refs.schemaModal) closeSchemaModal();
  });
  refs.helpModal.addEventListener("click", (event) => {
    if (event.target === refs.helpModal) closeHelpModal();
  });
  refs.practicesModal.addEventListener("click", (event) => {
    if (event.target === refs.practicesModal) closePracticesModal();
  });
  refs.demoModal.addEventListener("click", (event) => {
    if (event.target === refs.demoModal) closeDemoModal();
  });
  refs.pasteModal.addEventListener("click", (event) => {
    if (event.target === refs.pasteModal) closePasteModal();
  });

  document.getElementById("btn-hide-sidebar").addEventListener("click", () => toggleSidebar(true));
  refs.btnShowSidebar.addEventListener("click", () => toggleSidebar(false));
  document.getElementById("btn-hide-status").addEventListener("click", () => toggleStatus(true));
  refs.btnToggleStatus.addEventListener("click", () => toggleStatus(!appState.layout.statusHidden));
  refs.btnToggleInfo.addEventListener("click", () => toggleInfoColumn(!appState.layout.infoHidden));
  refs.btnShowInfo.addEventListener("click", () => toggleInfoColumn(false));

  document.querySelectorAll(".panel-toggle").forEach((button) => {
    button.addEventListener("click", () => {
      const map = {
        "editor-panel": "editor",
        "results-panel": "results",
        "inspector-panel": "inspector",
        "history-panel": "history"
      };
      togglePanel(map[button.dataset.panel]);
    });
  });

  refs.sideTabs.forEach((button) => {
    button.addEventListener("click", () => setSideTab(button.dataset.sideTab));
  });

  window.addEventListener("resize", applyLayoutState);
  window.addEventListener("beforeunload", clearCurrentMaterial);
}

async function boot() {
  try {
    mermaid.initialize({ startOnLoad: false, theme: "default" });
    marked.setOptions({ breaks: true, gfm: true });
    loadLayoutState();
    bindEvents();
    applyLayoutState();
    renderMaterialView();
    setSideTab("history");
    setEngineStatus("Cargando motor...", "Descargando SQLite en WebAssembly.");
    appState.sqlJs = await initSqlJs({ locateFile: (file) => `https://cdnjs.cloudflare.com/ajax/libs/sql.js/1.8.0/${file}` });
    setEngineStatus("Motor listo", "SQLite cargado en memoria.");
    await createEmptyDatabase({ setBaseline: true, name: "Base vacia" });
  } catch (error) {
    setEngineStatus("Error de motor", error.message);
    renderInfoMessage(`No se pudo iniciar el laboratorio.\n${error.message}`, "error");
  }
}

boot();
