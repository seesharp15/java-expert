package dev.expert.persistence;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Requires Postgres Testcontainer; implement when ready")
class JooqRepositoryTest {
    @Test
    void optimisticLockingPreventsLostUpdate() {
        // TODO: spin up Postgres Testcontainer, create table, assert version conflict
    }
}
