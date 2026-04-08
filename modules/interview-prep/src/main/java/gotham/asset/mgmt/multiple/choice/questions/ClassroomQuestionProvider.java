package gotham.asset.mgmt.multiple.choice.questions;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class ClassroomQuestionProvider implements QuestionProvider {
    private static final String QUESTION_BASE_PACKAGE = "gotham.asset.mgmt.multiple.choice.questions";

    @Override
    public List<Question> loadAll() {
        return discoverQuestions();
    }

    private static List<Question> discoverQuestions() {
        List<Question> found = new ArrayList<>();
        String packagePath = QUESTION_BASE_PACKAGE.replace('.', '/');
        try {
            var classLoader = Thread.currentThread().getContextClassLoader();
            var resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                var resource = resources.nextElement();
                if (resource.getProtocol().equals("file")) {
                    File dir = new File(resource.toURI());
                    if (dir.isDirectory()) {
                        scanDirectory(dir, QUESTION_BASE_PACKAGE, found);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Warning: could not scan for questions: " + e.getMessage());
        }
        found.sort(Comparator.comparing(q -> q.getClass().getSimpleName()));
        return found;
    }

    private static void scanDirectory(File dir, String packageName, List<Question> found) {
        File[] children = dir.listFiles();
        if (children == null) {
            return;
        }

        for (File file : children) {
            if (file.isDirectory()) {
                scanDirectory(file, packageName + "." + file.getName(), found);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + "." + file.getName().replace(".class", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    if (Question.class.isAssignableFrom(clazz)
                            && !Modifier.isAbstract(clazz.getModifiers())
                            && !clazz.equals(Question.class)) {
                        found.add((Question) clazz.getDeclaredConstructor().newInstance());
                    }
                } catch (Exception ignored) {
                    // skip classes that can't be instantiated
                }
            }
        }
    }
}
