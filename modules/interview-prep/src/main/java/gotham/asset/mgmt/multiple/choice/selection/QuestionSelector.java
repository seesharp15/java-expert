package gotham.asset.mgmt.multiple.choice.selection;

import gotham.asset.mgmt.multiple.choice.io.QuizIO;
import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.ui.Menu;

import java.util.*;

public class QuestionSelector {
    private static final String BASE = "gotham.asset.mgmt.multiple.choice.questions";

    public record Selection(List<Question> questions, String focus, String subfocus) {}

    public Selection select(List<Question> all, QuizIO io, Menu menu) {
        Map<String, List<Question>> byTop = new HashMap<>();
        Map<String, Map<String, List<Question>>> bySub = new HashMap<>();
        for (var q : all) {
            var parts = packageSegments(q);
            var top = parts.length > 0 ? parts[0] : "core";
            var sub = parts.length > 1 ? parts[1] : "all";
            byTop.computeIfAbsent(top, k -> new ArrayList<>()).add(q);
            bySub.computeIfAbsent(top, k -> new HashMap<>())
                    .computeIfAbsent(sub, k -> new ArrayList<>()).add(q);
        }
        List<String> topOptions = new ArrayList<>(byTop.keySet());
        Collections.sort(topOptions);
        topOptions.addFirst("all");
        var topIdx = menu.select("Focus area", topOptions, 0);
        var focus = topOptions.get(topIdx);
        if (focus.equals("all")) {
            var available = byTop.values().stream().mapToInt(List::size).sum();
            var count = promptCount(io, available);
            var distributed = distributeEvenly(byTop, count);
            return new Selection(distributed, "all", "all");
        }

        var subs = bySub.getOrDefault(focus, Map.of());
        List<String> subOptions = new ArrayList<>(subs.keySet());
        Collections.sort(subOptions);
        subOptions.add(0, "all");
        var subIdx = menu.select("Sub-area for " + focus, subOptions, 0);
        var subfocus = subOptions.get(subIdx);
        List<Question> pool = new ArrayList<>();
        if (subfocus.equals("all")) {
            subs.values().forEach(pool::addAll);
        } else {
            pool.addAll(subs.getOrDefault(subfocus, List.of()));
            if ("debugging".equalsIgnoreCase(focus)) {
                for (var entry : subs.entrySet()) {
                    if (entry.getKey().equals(subfocus)) continue;
                    for (Question q : entry.getValue()) {
                        if (appliesTo(subfocus, q)) {
                            pool.add(q);
                        }
                    }
                }
            }
        }
        pool = dedup(pool, subfocus.equals("all") ? null : subfocus);
        Collections.shuffle(pool);
        var count = promptCount(io, pool.size());
        return new Selection(pool.subList(0, count), focus, subfocus);
    }

    private List<Question> distributeEvenly(Map<String, List<Question>> byTop, int desired) {
        List<List<Question>> pools = new ArrayList<>();
        for (var list : byTop.values()) {
            List<Question> copy = new ArrayList<>(new LinkedHashSet<>(list));
            Collections.shuffle(copy);
            pools.add(copy);
        }
        Set<String> seen = new HashSet<>();
        List<Question> out = new ArrayList<>();
        var idx = 0;
        while (out.size() < desired && !pools.isEmpty()) {
            var pool = pools.get(idx % pools.size());
            if (pool.isEmpty()) {
                pools.remove(idx % pools.size());
                continue;
            }
            var q = pool.removeLast();
            if (seen.add(q.getClass().getName())) {
                out.add(q);
            }
            idx++;
        }
        return dedup(out, null);
    }

    private int promptCount(QuizIO io, int max) {
        while (true) {
            io.print("How many questions? (1-" + max + ", Enter=all): ");
            var line = io.readLine();
            if (line == null || line.trim().isEmpty()) return max;
            try {
                var n = Integer.parseInt(line.trim());
                if (n >= 1 && n <= max) return n;
            } catch (NumberFormatException ignored) {}
            io.println("Invalid number, try again.");
        }
    }

    private String[] packageSegments(Question q) {
        var pkg = q.getClass().getPackageName();
        if (!pkg.startsWith(BASE)) {
            return new String[]{"other"};
        }
        var remainder = pkg.substring(BASE.length());
        if (remainder.startsWith(".")) remainder = remainder.substring(1);
        if (remainder.isEmpty()) return new String[]{"core"};
        return remainder.split("\\.");
    }

    private List<Question> dedup(List<Question> pool, String langFilter) {
        LinkedHashMap<String, Question> unique = new LinkedHashMap<>();
        for (Question q : pool) {
            if (langFilter != null && !appliesTo(langFilter, q)) continue;
            String key = normalize(q.getQuestionText());
            unique.putIfAbsent(key, q);
        }
        return new ArrayList<>(unique.values());
    }

    private boolean appliesTo(String language, Question q) {
        if (language == null || language.isBlank()) return true;
        var langs = q.getApplicableLanguages();
        if (langs == null || langs.isEmpty()) {
            String pkgLang = packageLanguage(q);
            if (pkgLang.isEmpty()) return true; // non-debugging question
            return pkgLang.equalsIgnoreCase(language);
        }
        return langs.stream().anyMatch(l -> l.equalsIgnoreCase(language));
    }

    private String normalize(String text) {
        return text == null ? "" : text.replaceAll("\\s+", " ").trim().toLowerCase();
    }

    private String packageLanguage(Question q) {
        String[] parts = packageSegments(q);
        if (parts.length >= 2 && parts[0].equalsIgnoreCase("debugging")) {
            return parts[1];
        }
        return "";
    }
}
