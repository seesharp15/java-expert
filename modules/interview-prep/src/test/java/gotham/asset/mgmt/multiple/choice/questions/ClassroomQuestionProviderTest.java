package gotham.asset.mgmt.multiple.choice.questions;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class ClassroomQuestionProviderTest {

    @Test
    void loadsQuestionsWithoutDuplicates() {
        ClassroomQuestionProvider provider = new ClassroomQuestionProvider();
        List<Question> questions = provider.loadAll();
        assertThat(questions).isNotEmpty();
        Set<String> names = new HashSet<>();
        for (Question q : questions) {
            names.add(q.getClass().getName());
        }
        assertThat(names).hasSize(questions.size());
    }
}
