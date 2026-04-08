package gotham.asset.mgmt.multiple.choice.questions;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class ClasspathQuestionProviderTest {

    @Test
    void loadsQuestionsWithoutDuplicates() {
        ClasspathQuestionProvider provider = new ClasspathQuestionProvider();
        List<Question> questions = provider.loadAll();
        assertThat(questions).isNotEmpty();
        Set<String> names = new HashSet<>();
        for (Question q : questions) {
            names.add(q.getClass().getName());
        }
        assertThat(names).hasSize(questions.size());
    }


    @Test
    void doesNotContainDuplicateTestsAcrossLanguages() throws IOException, URISyntaxException {
        var provider = new ClasspathQuestionProvider();
        var questions = provider.loadAll();

        var texts = questions.stream().map(Question::getQuestionText).collect(Collectors.toSet());

        var duplicateGroups = questions.stream()
                .collect(Collectors.groupingBy(Question::getQuestionText))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));




        for(var dupe: duplicateGroups.entrySet()) {
            var builder = new StringBuilder();
            var qs = dupe.getValue();
            var parent = qs.getFirst();
            System.out.printf("### Parent: %s%n", parent.getSourcePath());

            for (int i = 1; i < qs.size(); i++) {
                var sourcePath = qs.get(i).getSourcePath();
                var pathUri = new URI(sourcePath);
                var path = Paths.get(pathUri);
                var parentDir = path.getParent();
                var dupeDir = parentDir.resolve("dupes");
                Files.createDirectories(dupeDir);

                var command = "mv " + path + "  " + dupeDir;

                builder.append(command);
                builder.append("\n");

            }


            System.out.println(builder);

//            builder.deleteCharAt(builder.length()-1);
//            System.out.printf("%s%n%s%n%n", builder, qs.getFirst().getQuestionText());
        }



    }
}
