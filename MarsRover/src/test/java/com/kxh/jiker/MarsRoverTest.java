package com.kxh.jiker;

import com.kxh.jiker.command.ActionCommand;
import com.kxh.jiker.command.ActionCommandFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarsRoverTest {

    Mars mars;
    ActionCommandFactory actionCommandFactory;
    MarsRover marsRover;
    Site landingSite;

    @BeforeAll
    public void init_exploring_area() {
        this.mars = new Mars();
        this.mars.setupArea(new Area(10, 10));

        this.actionCommandFactory = new ActionCommandFactory();

        this.marsRover = new MarsRover();
        this.landingSite = new Site(5, 5);
    }

    @Test
    public void should_exe_forward_batch_commands_to_north() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(5, this.marsRover.getCurrentSite().getX());
        assertEquals(2, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_forward_batch_commands_to_south() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.South);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(5, this.marsRover.getCurrentSite().getX());
        assertEquals(8, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_forward_batch_commands_to_east() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.East);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(8, this.marsRover.getCurrentSite().getX());
        assertEquals(5, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_forward_batch_commands_to_west() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.West);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(2, this.marsRover.getCurrentSite().getX());
        assertEquals(5, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_backward_batch_commands_from_north() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "b");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(5, this.marsRover.getCurrentSite().getX());
        assertEquals(8, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_backward_batch_commands_from_south() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.South);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "b");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(5, this.marsRover.getCurrentSite().getX());
        assertEquals(2, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_backward_batch_commands_from_east() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.East);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "b");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(2, this.marsRover.getCurrentSite().getX());
        assertEquals(5, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_backward_batch_commands_from_west() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.West);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "b");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(8, this.marsRover.getCurrentSite().getX());
        assertEquals(5, this.marsRover.getCurrentSite().getY());
    }

    @Test
    public void should_exe_forward_batch_commands_to_north_then_turnright_then_forward() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        actionCommands = generateCommands(actionCommands, 1, "r");
        actionCommands = generateCommands(actionCommands, 1, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(6, this.marsRover.getCurrentSite().getX());
        assertEquals(2, this.marsRover.getCurrentSite().getY());
        assertEquals(Direction.East, this.marsRover.getActionDirection());
    }

    @Test
    public void should_exe_forward_batch_commands_to_north_then_turnleft_then_backward() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 3, "f");
        actionCommands = generateCommands(actionCommands, 1, "l");
        actionCommands = generateCommands(actionCommands, 1, "f");
        this.marsRover.exeCommands(actionCommands);
        assertEquals(4, this.marsRover.getCurrentSite().getX());
        assertEquals(2, this.marsRover.getCurrentSite().getY());
        assertEquals(Direction.West, this.marsRover.getActionDirection());
    }

    @Test
    public void should_prevention_given_out_of_area_when_forward_to_north() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 6, "f");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(5, this.marsRover.getCurrentSite().getX());
            assertEquals(0, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_forward_to_south() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 2, "r");
        actionCommands = generateCommands(actionCommands, 6, "f");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(5, this.marsRover.getCurrentSite().getX());
            assertEquals(10, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_forward_to_east() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 1, "r");
        actionCommands = generateCommands(actionCommands, 6, "f");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(10, this.marsRover.getCurrentSite().getX());
            assertEquals(5, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_forward_to_west() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 1, "l");
        actionCommands = generateCommands(actionCommands, 6, "f");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(0, this.marsRover.getCurrentSite().getX());
            assertEquals(5, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_backward_from_north() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.North);
        List<ActionCommand> actionCommands = generateCommands(null, 6, "b");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(5, this.marsRover.getCurrentSite().getX());
            assertEquals(10, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_backward_from_south() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.South);
        List<ActionCommand> actionCommands = generateCommands(null, 6, "b");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(5, this.marsRover.getCurrentSite().getX());
            assertEquals(0, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_backward_from_east() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.East);
        List<ActionCommand> actionCommands = generateCommands(null, 6, "b");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(0, this.marsRover.getCurrentSite().getX());
            assertEquals(5, this.marsRover.getCurrentSite().getY());
        }
    }

    @Test
    public void should_prevention_given_out_of_area_when_backward_from_west() {
        this.marsRover.landOn(this.mars, this.landingSite, Direction.West);
        List<ActionCommand> actionCommands = generateCommands(null, 6, "b");
        try {
            this.marsRover.exeCommands(actionCommands);
            fail("Exception: will out of area, should be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("will out of area"));
            assertEquals(10, this.marsRover.getCurrentSite().getX());
            assertEquals(5, this.marsRover.getCurrentSite().getY());
        }
    }

    private List<ActionCommand> generateCommands(List<ActionCommand> commands, int count, String commandKey) {
        List<ActionCommand> resultCommands = null;
        if (commands != null) {
            resultCommands = commands;
        } else {
            resultCommands = new ArrayList<>();
        }

        for (int i = 0; i < count; i++) {
            resultCommands.add(this.actionCommandFactory.CreateActionCommand(commandKey));
        }
        return resultCommands;
    }
}
