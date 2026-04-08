package gotham.asset.mgmt.multiple.choice.repo;

import java.io.Serializable;
import java.util.List;

public class SessionData implements Serializable {
    private static final long serialVersionUID = 1L;
    public String id;
    public String mode;
    public String focus;
    public String subfocus;
    public List<String> classNames;
    public List<List<String>> choices;
    public List<String> questionTexts;
    public List<List<String>> shuffledChoices;
    public List<Integer> shuffledCorrect;
    public List<Integer> correctIndices;
    public List<Integer> userAnswers; // -1 unanswered
    public int cursor;
    public long createdAt;
    public boolean completed;
}
