package dev.expert.interview;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElevatorDesignTest {

    @Test
    void simpleScanSchedulesRequests() {
        var requests = List.of(
            new ElevatorDesign.Request(5, ElevatorDesign.Direction.UP),
            new ElevatorDesign.Request(2, ElevatorDesign.Direction.DOWN),
            new ElevatorDesign.Request(8, ElevatorDesign.Direction.UP)
        );
        var design = new ElevatorDesign();
        var order = design.schedule(3, requests);
        // Expect to clear upward then downward, for example: 5,8,2
        assertThat(order).containsExactly(5,8,2);
    }
}
