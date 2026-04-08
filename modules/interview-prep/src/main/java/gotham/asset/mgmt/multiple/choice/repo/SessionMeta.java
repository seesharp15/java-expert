package gotham.asset.mgmt.multiple.choice.repo;

public record SessionMeta(String id, String mode, String focus, String subfocus, long createdAt, boolean completed, int cursor, int total, int correct) {}
