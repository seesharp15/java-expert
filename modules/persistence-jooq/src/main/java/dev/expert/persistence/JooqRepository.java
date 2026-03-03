package dev.expert.persistence;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.impl.DSL;

import java.util.UUID;

public class JooqRepository {
    private final DSLContext dsl;

    public JooqRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    public <R extends Record> R insertWithVersion(R record, TableField<R, Integer> versionField) {
        throw new UnsupportedOperationException("TODO: implement insert with optimistic locking");
    }

    public <R extends Record> boolean updateWithVersion(R record, TableField<R, Integer> versionField) {
        throw new UnsupportedOperationException("TODO: implement update with optimistic locking");
    }
}

























































/*
ANSWER KEY (conceptual, assumes updatable records):

public <R extends Record> R insertWithVersion(R record, TableField<R, Integer> versionField) {
    record.set(versionField, 0);
    return dsl.insertInto(record.getTable())
        .set(record)
        .returning()
        .fetchOne();
}

public <R extends Record> boolean updateWithVersion(R record, TableField<R, Integer> versionField) {
    Integer current = record.get(versionField);
    int next = current + 1;
    int updated = dsl.update(record.getTable())
        .set(record)
        .set(versionField, next)
        .where(record.getTable().getPrimaryKey().getFields().stream()
            .map(f -> f.eq(record.get(f)))
            .reduce(DSL.noCondition(), DSL::and))
        .and(versionField.eq(current))
        .execute();
    return updated == 1;
}
*/
