package gotham.asset.mgmt.multiple.choice;

import gotham.asset.mgmt.multiple.choice.questions.Question;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QuizAppTest {

    @Test
    void placeholderTest_scaffolding() {
        assertThat(true).isTrue();
    }

    // ---------- Helpers ----------

    static Method privateMethod(Class<?> clazz, String name, Class<?>... params) throws Exception {
        var m = clazz.getDeclaredMethod(name, params);
        m.setAccessible(true);
        return m;
    }

    @SuppressWarnings("unchecked")
    static List<Question> loadQuestions(QuizApp app) throws Exception {
        var discover = privateMethod(QuizApp.class, "discoverQuestions");
        return (List<Question>) discover.invoke(null);
    }

    static long countByCategory(List<Question> qs, String category) {
        return qs.stream()
                .filter(q -> q.getClass().getPackageName().contains("." + category))
                .count();
    }

    // ---------- Example tests ----------

    @Test
    void shouldDiscoverQuestionsAcrossCategories() throws Exception {
        var app = new QuizApp(new java.util.Scanner(System.in));
        var qs = loadQuestions(app);
        assertThat(qs).isNotEmpty();
        assertThat(countByCategory(qs, "java")).isGreaterThan(0);
        assertThat(countByCategory(qs, "SQL")).isGreaterThan(0);
    }

    @Test
    void shuffleChoicesKeepsSize() throws Exception {
        var app = new QuizApp(new java.util.Scanner(System.in));
        var qs = loadQuestions(app);
        var first = qs.get(0);
        var shuffle = privateMethod(QuizApp.class, "shuffleChoices", Question.class);
        var shuffled = shuffle.invoke(app, first);
        var displayChoices = shuffled.getClass().getMethod("displayChoices");
        @SuppressWarnings("unchecked")
        var display = (List<String>) displayChoices.invoke(shuffled);
        assertThat(display).hasSize(first.getChoices().size());
    }
}
