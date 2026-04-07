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
     * If true, the last choice (e.g. "All of the above") stays pinned at the end
     * during randomization.
     */
    public boolean hasFixedLastChoice() {
        return false;
    }
}
