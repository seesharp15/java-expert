package gotham.asset.mgmt.multiple.choice.report;

import gotham.asset.mgmt.multiple.choice.repo.SessionData;
import gotham.asset.mgmt.multiple.choice.repo.SessionMeta;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DiskReportExporter implements ReportExporter {
    private final Path reportDir;

    public DiskReportExporter(Path reportDir) {
        this.reportDir = reportDir;
        try {
            Files.createDirectories(reportDir);
        } catch (IOException ignored) {}
    }

    @Override
    public void exportAll(List<SessionMeta> metas, List<SessionData> sessions) {
        try {
            Files.createDirectories(reportDir);
            try (var ds = Files.newDirectoryStream(reportDir, "session-*.json")) {
                for (Path p : ds) {
                    Files.deleteIfExists(p);
                }
            } catch (IOException ignored) {}
            for (SessionData s : sessions) {
                Files.writeString(reportDir.resolve("session-" + s.id + ".json"), toSessionJson(s));
            }
            Files.writeString(reportDir.resolve("sessions-index.json"), toIndexJson(metas));
            Files.writeString(reportDir.resolve("index.html"), portalHtml());
        } catch (IOException e) {
            System.err.println("Warning: could not write report files: " + e.getMessage());
        }
    }

    private String toSessionJson(SessionData s) {
        StringBuilder sb = new StringBuilder();
        int total = s.classNames.size();
        int correct = 0;
        for (int i = 0; i < s.userAnswers.size() && i < s.shuffledCorrect.size(); i++) {
            int ua = s.userAnswers.get(i);
            if (ua >= 0 && ua == s.shuffledCorrect.get(i)) correct++;
        }
        sb.append("{");
        field(sb, "id", s.id); sb.append(",");
        field(sb, "mode", s.mode); sb.append(",");
        field(sb, "focus", s.focus); sb.append(",");
        field(sb, "subfocus", s.subfocus); sb.append(",");
        field(sb, "createdAt", s.createdAt); sb.append(",");
        field(sb, "completed", s.completed); sb.append(",");
        field(sb, "cursor", s.cursor); sb.append(",");
        field(sb, "total", total); sb.append(",");
        field(sb, "correct", correct); sb.append(",");
        sb.append("\"questions\":[");
        for (int i = 0; i < total; i++) {
            if (i > 0) sb.append(",");
            sb.append("{");
            field(sb, "idx", i + 1); sb.append(",");
            String category = categoryFromClassName(s.classNames.get(i));
            field(sb, "category", category); sb.append(",");
            field(sb, "className", s.classNames.get(i)); sb.append(",");
            field(sb, "text", s.questionTexts.get(i)); sb.append(",");
            sb.append("\"choices\":[");
            List<String> disp = (s.shuffledChoices != null && i < s.shuffledChoices.size()) ? s.shuffledChoices.get(i) : s.choices.get(i);
            for (int c = 0; c < disp.size(); c++) {
                if (c > 0) sb.append(",");
                quote(sb, disp.get(c));
            }
            sb.append("],");
            int correctIdx = (s.shuffledCorrect != null && i < s.shuffledCorrect.size()) ? s.shuffledCorrect.get(i) : s.correctIndices.get(i);
            field(sb, "correctIndex", correctIdx); sb.append(",");
            int ans = (i < s.userAnswers.size()) ? s.userAnswers.get(i) : -1;
            field(sb, "userAnswer", ans);
            sb.append("}");
        }
        sb.append("]}");
        return sb.toString();
    }

    private String toIndexJson(List<SessionMeta> metas) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < metas.size(); i++) {
            SessionMeta m = metas.get(i);
            if (i > 0) sb.append(",");
            sb.append("{");
            field(sb, "id", m.id()); sb.append(",");
            field(sb, "mode", m.mode()); sb.append(",");
            field(sb, "focus", m.focus()); sb.append(",");
            field(sb, "subfocus", m.subfocus()); sb.append(",");
            field(sb, "createdAt", m.createdAt()); sb.append(",");
            field(sb, "completed", m.completed()); sb.append(",");
            field(sb, "cursor", m.cursor()); sb.append(",");
            field(sb, "total", m.total()); sb.append(",");
            field(sb, "correct", m.correct()); sb.append(",");
            field(sb, "file", "session-" + m.id() + ".json");
            sb.append("}");
        }
        sb.append("]");
        return sb.toString();
    }

    private String portalHtml() {
        return "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>Quiz Sessions</title>"
                + "<style>body{font-family:Arial, sans-serif;margin:0;background:#f7f7f7;color:#222;}header{background:#1f2937;color:#fff;padding:12px 16px;}main{display:flex;height:calc(100vh - 52px);}aside{width:320px;overflow:auto;border-right:1px solid #ccc;background:#fff;}section{flex:1;overflow:auto;padding:16px;}ul{list-style:none;padding:0;margin:0;}li{padding:10px 12px;border-bottom:1px solid #eee;cursor:pointer;}li:hover{background:#f0f0f0;}li.active{background:#e0f2ff;}table{border-collapse:collapse;width:100%;margin-top:12px;}th,td{border:1px solid #ccc;padding:6px 8px;vertical-align:top;}th{background:#ececec;}tr:nth-child(even){background:#fcfcfc;}code{white-space:pre-wrap;display:block;} .ok{color:#0a0;} .bad{color:#c00;} .miss{color:#999;}</style>"
                + "</head><body><header><h2>Quiz Sessions</h2></header><main>"
                + "<aside><ul id='sessionList'></ul></aside>"
                + "<section><div id='details'>Loading sessions…</div></section>"
                + "<script>"
                + "async function loadIndex(){const res=await fetch('sessions-index.json');if(!res.ok){details.innerHTML='No sessions.';return;}const sessions=await res.json();renderList(sessions);} "
                + "const ul=document.getElementById('sessionList');const details=document.getElementById('details');"
                + "async function renderList(sessions){ul.innerHTML='';sessions.forEach((s,i)=>{const li=document.createElement('li');const pct=s.total?((s.correct*100)/s.total).toFixed(1):'0.0';li.textContent=`${s.mode.toUpperCase()} | ${s.focus}/${s.subfocus} | ${s.completed?'done':'in-progress'} | ${pct}% | ${new Date(s.createdAt).toLocaleString()}`;li.onclick=()=>select(s,i);li.id='sess-'+i;ul.appendChild(li);}); if(sessions.length>0) select(sessions[0],0);} "
                + "async function select(sess,i){document.querySelectorAll('#sessionList li').forEach(e=>e.classList.remove('active'));const li=document.getElementById('sess-'+i);if(li)li.classList.add('active');const res=await fetch(sess.file);if(!res.ok){details.innerHTML='Could not load session file.';return;}const s=await res.json();let html=`<h3>${s.mode.toUpperCase()} – ${s.focus}/${s.subfocus}</h3>`;const pct=s.total?((s.correct*100)/s.total).toFixed(1):'0.0';html+=`<p><b>ID:</b> ${s.id} &nbsp; <b>Created:</b> ${new Date(s.createdAt).toLocaleString()} &nbsp; <b>Status:</b> ${s.completed?'Completed':'In-progress'}<br><b>Score:</b> ${s.correct}/${s.total} (${pct}%)</p>`;html+=\"<table><tr><th>#</th><th>Category</th><th>Question</th><th>Choices</th><th>Correct</th><th>Your Answer</th><th>Status</th></tr>\";s.questions.forEach(q=>{const correct=q.correctIndex;const ua=q.userAnswer;const status=ua<0?'<span class=miss>Unanswered</span>':(ua===correct?'<span class=ok>Correct</span>':'<span class=bad>Incorrect</span>');html+=`<tr><td>${q.idx}</td><td>${q.category}</td><td><code>${escapeHtml(q.text)}</code></td><td>`;q.choices.forEach((c,j)=>{html+=`<div>${String.fromCharCode(65+j)}) ${escapeHtml(c)}</div>`;});html+=`</td><td>${String.fromCharCode(65+correct)}) ${escapeHtml(q.choices[correct])}</td>`;if(ua>=0&&ua<q.choices.length){html+=`<td>${String.fromCharCode(65+ua)}) ${escapeHtml(q.choices[ua])}</td>`;}else{html+='<td>Unanswered</td>'; } html+=`<td>${status}</td></tr>`;});html+='</table>';details.innerHTML=html;} "
                + "function escapeHtml(s){return s.replace(/[&<>]/g,ch=>({'&':'&amp;','<':'&lt;','>':'&gt;'}[ch]));}"
                + "loadIndex();"
                + "</script></main></body></html>";
    }

    private void field(StringBuilder sb, String key, String val) {
        quote(sb, key); sb.append(":"); quote(sb, val == null ? "" : val);
    }
    private void field(StringBuilder sb, String key, long val) { quote(sb, key); sb.append(":").append(val); }
    private void field(StringBuilder sb, String key, boolean val) { quote(sb, key); sb.append(":").append(val); }
    private void field(StringBuilder sb, String key, int val) { quote(sb, key); sb.append(":").append(val); }
    private void quote(StringBuilder sb, String s) {
        sb.append("\"");
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '\\' -> sb.append("\\\\");
                case '"' -> sb.append("\\\"");
                case '\n' -> sb.append("\\n");
                case '\r' -> sb.append("\\r");
                case '\t' -> sb.append("\\t");
                case '\f' -> sb.append("\\f");
                default -> sb.append(c);
            }
        }
        sb.append("\"");
    }

    private String categoryFromClassName(String className) {
        if (className == null || !className.startsWith("gotham.asset.mgmt.multiple.choice.questions")) return "OTHER";
        String remainder = className.substring("gotham.asset.mgmt.multiple.choice.questions".length());
        if (remainder.startsWith(".")) remainder = remainder.substring(1);
        if (remainder.isEmpty()) return "CORE";
        String[] parts = remainder.split("\\.");
        if (parts.length >= 2) return (parts[0] + "/" + parts[1]).toUpperCase();
        return parts[0].toUpperCase();
    }
}
