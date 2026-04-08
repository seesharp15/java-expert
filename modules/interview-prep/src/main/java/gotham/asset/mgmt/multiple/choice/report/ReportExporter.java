package gotham.asset.mgmt.multiple.choice.report;

import gotham.asset.mgmt.multiple.choice.repo.SessionData;
import gotham.asset.mgmt.multiple.choice.repo.SessionMeta;

import java.util.List;

public interface ReportExporter {
    void exportAll(List<SessionMeta> metas, List<SessionData> sessions);
}
