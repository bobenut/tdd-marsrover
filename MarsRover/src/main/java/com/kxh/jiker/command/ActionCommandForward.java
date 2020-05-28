package com.kxh.jiker.command;

import com.kxh.jiker.Direction;
import com.kxh.jiker.MarsRover;
import com.kxh.jiker.Site;

public class ActionCommandForward extends ActionCommand {
    @Override
    public String getKey() {
        return ActionCommandKeys.FORWARD;
    }

    @Override
    public Site exe(MarsRover marsRover) {
        return doWalking(marsRover);
    }

    @Override
    protected Site calculateSiteAfterWalking(MarsRover marsRover) {
        int x = marsRover.getCurrentSite().getX();
        int y = marsRover.getCurrentSite().getY();
        if (marsRover.getActionDirection() == Direction.North) {
            y -= 1;
        }

        if (marsRover.getActionDirection() == Direction.South) {
            y += 1;
        }

        if (marsRover.getActionDirection() == Direction.East) {
            x += 1;

        }

        if (marsRover.getActionDirection() == Direction.West) {
            x -= 1;
        }

        return new Site(x, y);
    }
}
