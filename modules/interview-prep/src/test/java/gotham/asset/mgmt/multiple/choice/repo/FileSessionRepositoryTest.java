package gotham.asset.mgmt.multiple.choice.repo;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FileSessionRepositoryTest {

    @Test
    void computesCorrectAnswersIndexWise() throws Exception {
        Path dir = Files.createTempDirectory("session-repo-test");
        FileSessionRepository repo = new FileSessionRepository(dir);

        SessionData data = new SessionData();
        data.id = "test1";
        data.mode = "test";
        data.focus = "java";
        data.subfocus = "core";
        data.classNames = List.of("dummy.Q1", "dummy.Q2");
        data.choices = List.of(List.of("a"), List.of("b"));
        data.questionTexts = List.of("q1", "q2");
        data.shuffledChoices = List.of(List.of("a"), List.of("b"));
        data.shuffledCorrect = List.of(0, 0);
        data.correctIndices = List.of(0, 0);
        data.userAnswers = List.of(0, 1); // second answer wrong
        data.cursor = 2;
        data.createdAt = System.currentTimeMillis();
        data.completed = true;

        repo.save(data);

        List<SessionMeta> metas = repo.list();
        assertThat(metas).hasSize(1);
        SessionMeta meta = metas.get(0);
        assertThat(meta.correct()).isEqualTo(1);
        assertThat(meta.total()).isEqualTo(2);
    }
}
