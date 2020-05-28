package com.kxh.jiker.command;

import com.kxh.jiker.Direction;
import com.kxh.jiker.MarsRover;
import com.kxh.jiker.Site;

import java.util.HashMap;
import java.util.Map;

public class ActionCommandTurnleft extends ActionCommand {
    private Map<Direction, Direction> directionAfterTurnleft = new HashMap<>();

    public ActionCommandTurnleft() {
        this.directionAfterTurnleft.put(Direction.North, Direction.West);
        this.directionAfterTurnleft.put(Direction.West, Direction.South);
        this.directionAfterTurnleft.put(Direction.South, Direction.East);
        this.directionAfterTurnleft.put(Direction.East, Direction.North);
    }

    @Override
    public String getKey() {
        return ActionCommandKeys.TURNLEFT;
    }

    @Override
    public Site exe(MarsRover marsRover) {
        marsRover.setActionDirection(getDirectionAfterTurnLeft(marsRover.getActionDirection()));
        return marsRover.getCurrentSite();
    }

    public Direction getDirectionAfterTurnLeft(Direction currentDirection) {
        return this.directionAfterTurnleft.get(currentDirection);
    }

    @Override
    protected Site calculateSiteAfterWalking(MarsRover marsRover) {
        return null;
    }
}
