package gotham.asset.mgmt.multiple.choice.repo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class FileSessionRepository implements SessionRepository {
    private final Path root;

    public FileSessionRepository(Path root) {
        this.root = root;
        try {
            Files.createDirectories(root);
        } catch (IOException ignored) {}
    }

    private Path path(String id) {
        return root.resolve("session-" + id + ".ser");
    }

    @Override
    public void save(SessionData data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path(data.id)))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Warning: could not save session: " + e.getMessage());
        }
    }

    @Override
    public Optional<SessionData> load(String id) {
        Path p = path(id);
        if (!Files.exists(p)) return Optional.empty();
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(p))) {
            Object o = ois.readObject();
            if (o instanceof SessionData sd) return Optional.of(sd);
        } catch (Exception e) {
            System.err.println("Warning: could not load session " + id + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<SessionMeta> list() {
        List<SessionMeta> metas = new ArrayList<>();
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(root, "session-*.ser")) {
            for (Path p : ds) {
                try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(p))) {
                    Object o = ois.readObject();
                    if (o instanceof SessionData sd) {
                        int total = sd.classNames.size();
                        int correct = 0;
                        int max = Math.min(sd.userAnswers.size(), sd.shuffledCorrect.size());
                        for (int i = 0; i < max; i++) {
                            int ans = sd.userAnswers.get(i);
                            if (ans >= 0 && ans == sd.shuffledCorrect.get(i)) {
                                correct++;
                            }
                        }
                        metas.add(new SessionMeta(sd.id, sd.mode, sd.focus, sd.subfocus, sd.createdAt, sd.completed, sd.cursor, total, correct));
                    }
                } catch (Exception ignored) {}
            }
        } catch (IOException ignored) {}
        metas.sort(Comparator.comparingLong(SessionMeta::createdAt).reversed());
        return metas;
    }

    @Override
    public void delete(String id) {
        try {
            Files.deleteIfExists(path(id));
        } catch (IOException ignored) {}
    }
}
