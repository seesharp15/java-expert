package gotham.asset.mgmt.multiple.choice.questions;

import java.util.*;

public abstract class Question {
    public String packageName = getPackageName();
    private String getPackageName() {
        var pkgName = getClass().getPackage().getName();
        var startPosition = pkgName.lastIndexOf('.') + 1;
        return pkgName.substring(startPosition);
    }

    public abstract String getQuestionText();
    public abstract List<String> getChoices();
    public abstract int getCorrectAnswerIndex();
    public abstract String getExplanation();

    /**
     * Languages (lowercase) this question applies to. Empty means all languages.
     * Used mainly by debugging questions that are language-specific.
     */
    public Set<String> getApplicableLanguages() {
        return Collections.emptySet();
    }

    /**
     * Best-effort path to the source file for this question.
     * Falls back to the compiled resource location if the source tree is unavailable.
     */
    public String getSourcePath() {
        try {
            String rel = getClass().getName().replace('.', '/') + ".java";
            // repo layout: modules/interview-prep/src/main/java/...
            var candidate = java.nio.file.Paths.get( "src", "main", "java", rel).toAbsolutePath();
            if (java.nio.file.Files.exists(candidate)) {
                return candidate.toString();
            }
            var url = getClass().getClassLoader().getResource(getClass().getName().replace('.', '/') + ".class");
            if (url != null) {
                return url.toString();
            }
        } catch (Exception ignored) {}
        return "";
    }

    /**
     * If true, the last choice (e.g. "All of the above") stays pinned at the end
     * during randomization.
     */
    public boolean hasFixedLastChoice() {
        return false;
    }
}
