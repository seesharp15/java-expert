package gotham.asset.mgmt.multiple.choice.repo;

import java.util.List;
import java.util.Optional;

public interface SessionRepository {
    void save(SessionData data);
    Optional<SessionData> load(String id);
    List<SessionMeta> list();
    void delete(String id);
}
