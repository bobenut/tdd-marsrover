package com.kxh.jiker.command;

import com.kxh.jiker.Direction;
import com.kxh.jiker.MarsRover;
import com.kxh.jiker.Site;

import java.util.HashMap;
import java.util.Map;

public class ActionCommandTurnright extends ActionCommand {
    Map<Direction, Direction> directionAfterTurnright = new HashMap<>();

    public ActionCommandTurnright() {
        this.directionAfterTurnright.put(Direction.North, Direction.East);
        this.directionAfterTurnright.put(Direction.East, Direction.South);
        this.directionAfterTurnright.put(Direction.South, Direction.West);
        this.directionAfterTurnright.put(Direction.West, Direction.North);
    }

    @Override
    public String getKey() {
        return ActionCommandKeys.TURNRIGHT;
    }

    @Override
    public Site exe(MarsRover marsRover) {
        marsRover.setActionDirection(getDirectionAfterTurnRight(marsRover.getActionDirection()));
        return marsRover.getCurrentSite();
    }

    public Direction getDirectionAfterTurnRight(Direction currentDirection) {
        return this.directionAfterTurnright.get(currentDirection);
    }

    @Override
    protected Site calculateSiteAfterWalking(MarsRover marsRover) {
        return null;
    }
}
