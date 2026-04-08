package gotham.asset.mgmt.multiple.choice.engine;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import gotham.asset.mgmt.multiple.choice.repo.SessionData;

import java.util.*;

/**
 * Builds immutable {@link SessionData} snapshots from a set of questions.
 * Centralises shuffling so the same logic can be exercised in tests.
 */
public final class SessionBuilder {
    private SessionBuilder() {}

    public static SessionData build(String mode,
                                    String focus,
                                    String subfocus,
                                    List<Question> questions,
                                    Random random) {
        Objects.requireNonNull(mode, "mode");
        Objects.requireNonNull(focus, "focus");
        Objects.requireNonNull(subfocus, "subfocus");
        Objects.requireNonNull(questions, "questions");
        Random rnd = (random == null) ? new Random() : random;

        // De-duplicate by class name to guarantee unique questions.
        LinkedHashMap<String, Question> unique = new LinkedHashMap<>();
        for (Question q : questions) {
            unique.putIfAbsent(q.getClass().getName(), q);
        }
        List<Question> ordered = new ArrayList<>(unique.values());
        Collections.shuffle(ordered, rnd);

        SessionData d = new SessionData();
        d.id = String.valueOf(System.currentTimeMillis());
        d.mode = mode;
        d.focus = focus;
        d.subfocus = subfocus;
        d.createdAt = System.currentTimeMillis();
        d.cursor = 0;
        d.completed = false;
        d.classNames = new ArrayList<>();
        d.questionTexts = new ArrayList<>();
        d.choices = new ArrayList<>();
        d.shuffledChoices = new ArrayList<>();
        d.correctIndices = new ArrayList<>();
        d.shuffledCorrect = new ArrayList<>();
        d.userAnswers = new ArrayList<>();

        for (Question q : ordered) {
            List<String> choices = new ArrayList<>(q.getChoices());
            ShuffleResult shuffled = shuffleChoices(q, rnd);

            d.classNames.add(q.getClass().getName());
            d.questionTexts.add(q.getQuestionText());
            d.choices.add(choices);
            d.correctIndices.add(q.getCorrectAnswerIndex());
            d.shuffledChoices.add(shuffled.displayChoices());
            d.shuffledCorrect.add(shuffled.shuffledCorrectIndex());
            d.userAnswers.add(-1);
        }
        return d;
    }

    public static ShuffleResult shuffleChoices(Question q, Random rnd) {
        List<String> choices = q.getChoices();
        List<Integer> order = new ArrayList<>();
        for (int i = 0; i < choices.size(); i++) {
            order.add(i);
        }
        Random random = (rnd == null) ? new Random() : rnd;
        if (q.hasFixedLastChoice() && order.size() > 1) {
            int last = order.remove(order.size() - 1);
            Collections.shuffle(order, random);
            order.add(last);
        } else {
            Collections.shuffle(order, random);
        }
        List<String> shuffled = new ArrayList<>();
        int correctIdx = -1;
        for (int i = 0; i < order.size(); i++) {
            int orig = order.get(i);
            shuffled.add(choices.get(orig));
            if (orig == q.getCorrectAnswerIndex()) {
                correctIdx = i;
            }
        }
        if (correctIdx < 0) {
            correctIdx = 0; // fallback to avoid index errors
        }
        return new ShuffleResult(shuffled, correctIdx);
    }

    public record ShuffleResult(List<String> displayChoices, int shuffledCorrectIndex) {}
}
