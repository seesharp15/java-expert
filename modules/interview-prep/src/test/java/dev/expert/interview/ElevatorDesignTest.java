package dev.expert.interview;

import dev.expert.interview.ElevatorDesign.ElevatorDesign3;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ElevatorDesign3Test {

    @Test
    void noRequests() {
        var requests = List.<ElevatorDesign3.Request>of();
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        assertThat(order).isEmpty();
    }

    @Test
    void onlyUpAbove_currentFloor_ascending() {
        var requests = List.of(
                new ElevatorDesign3.Request(10, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(4,  ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(7,  ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        // Up above current floor in ascending order: 4,7,10
        assertThat(order).containsExactly(4, 7, 10);
    }

    @Test
    void onlyDownAbove_currentFloor_thenDownDescending() {
        var requests = List.of(
                new ElevatorDesign3.Request(9, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(4, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(7, ElevatorDesign3.Direction.DOWN)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        // No UP pickups above; swing down and clear DOWN pickups top->bottom: 9,7,4
        assertThat(order).containsExactly(9, 7, 4);
    }

    @Test
    void mixedAbove_upFirstAscending_thenDownDescending() {
        var requests = List.of(
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(5, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(9, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        // UP above: 5,8 then DOWN above on the way back: 9,6
        assertThat(order).containsExactly(5, 8, 9, 6);
    }

    @Test
    void includesUpBelow_onlyAfterSwingBackUp_ascendingFromBelow() {
        var requests = List.of(
                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(1, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(7, ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(5, requests);

        // First clear UP above (7), then swing down (no DOWN requests), then pick UP below ascending: 1,2
        assertThat(order).containsExactly(7, 1, 2);
    }

    @Test
    void fullCycle_upAbove_thenDownAll_thenUpBelow() {
        var requests = List.of(
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(9, ElevatorDesign3.Direction.UP),

                new ElevatorDesign3.Request(10, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(7,  ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(2,  ElevatorDesign3.Direction.DOWN),

                new ElevatorDesign3.Request(1, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(4, ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(5, requests);

        // UP above: 6,9
        // then DOWN (global swing down): 10,7,2
        // then UP below: 1,4
        assertThat(order).containsExactly(6, 9, 10, 7, 2, 1, 4);
    }

    @Test
    void requestAtCurrentFloor_upTreatedAsInFirstUpRun() {
        var requests = List.of(
                new ElevatorDesign3.Request(3, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(5, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.DOWN)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        // Treat current-floor UP as part of the first upward clearing: 3,5 then down: 6
        assertThat(order).containsExactly(3, 5, 6);
    }

    @Test
    void requestAtCurrentFloor_downTreatedAsInDownSwing() {
        var requests = List.of(
                new ElevatorDesign3.Request(3, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(5, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.DOWN)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(3, requests);

        // UP above first: 5
        // then down swing should include current-floor DOWN (3) and below DOWN (2): 3,2
        assertThat(order).containsExactly(5, 3, 2);
    }

    @Test
    void duplicatesSameFloor_differentDirections_bothAppear_inCorrectPhases() {
        var requests = List.of(
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(9, ElevatorDesign3.Direction.DOWN)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(5, requests);

        // UP above: 6
        // DOWN swing: 9,6
        // UP below: 2
        assertThat(order).containsExactly(6, 9, 6, 2);
    }

    @Test
    void stableOrdering_withTies_sameDirection_sameFloor_keepsBoth() {
        var requests = List.of(
                new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(5, requests);

        // UP above ascending: 6,8,8
        assertThat(order).containsExactly(6, 8, 8);
    }

    @Test
    void downBelowOnly_afterUpRun_shouldBeClearedOnDownSwing_descending() {
        var requests = List.of(
                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(1, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(7, ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(5, requests);

        // UP above: 7
        // then down swing clears DOWN below descending (2,1)
        assertThat(order).containsExactly(7, 2, 1);
    }

    @Test
    void mixedEverything_regression() {
        var requests = List.of(
                new ElevatorDesign3.Request(12, ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(11, ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(9,  ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(8,  ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(6,  ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(4,  ElevatorDesign3.Direction.DOWN),
                new ElevatorDesign3.Request(2,  ElevatorDesign3.Direction.UP),
                new ElevatorDesign3.Request(1,  ElevatorDesign3.Direction.UP)
        );
        var design = new ElevatorDesign3();

        var order = design.schedule(6, requests);

        // UP above: 9,12
        // DOWN swing: 11,8,4
        // UP below: 1,2
        assertThat(order).containsExactly(6, 9, 12, 11, 8, 4, 1, 2);
    }

//    @Test
//    void simpleRequests1() {
//        var requests = List.of(
//            new ElevatorDesign3.Request(5, ElevatorDesign3.Direction.UP),
//            new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.DOWN),
//            new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP)
//        );
//        var design = new ElevatorDesign3();
//        var order = design.schedule(3, requests);
//        // Expect to clear upward then downward, for example: 5,8,2
//        assertThat(order).containsExactly(5,8,2);
//    }
//
//    @Test
//    void complexScanSchedulesRequests() {
//
//        var request = List.of(
//                new ElevatorDesign3.Request(5, ElevatorDesign3.Direction.UP),
//                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.DOWN),
//                new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP),
//                new ElevatorDesign3.Request(7, ElevatorDesign3.Direction.UP),
//                new ElevatorDesign3.Request(2, ElevatorDesign3.Direction.DOWN),
//                new ElevatorDesign3.Request(1, ElevatorDesign3.Direction.UP),
//                new ElevatorDesign3.Request(8, ElevatorDesign3.Direction.UP),
//                new ElevatorDesign3.Request(6, ElevatorDesign3.Direction.DOWN),
//                new ElevatorDesign3.Request(10, ElevatorDesign3.Direction.UP)
//        );
//
//        var design = new ElevatorDesign3();
//        var order = design.schedule(5, request);
//
//        assertThat(order).containsExactly(7, 8, 10,
//    }
}
