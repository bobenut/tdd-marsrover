package com.kxh.jiker;

import com.kxh.jiker.command.ActionCommand;

import java.util.List;

public class MarsRover {
    private Mars mars;
    private Direction actionDirection;
    private Site currentSite = new Site(0, 0);

    public Mars getMars() {
        return mars;
    }

    public Direction getActionDirection() {
        return this.actionDirection;
    }

    public void setActionDirection(Direction actionDirection) {
        this.actionDirection = actionDirection;
    }

    public Site getCurrentSite() {
        return this.currentSite;
    }

    public void landOn(Mars mars, Site site, Direction direction) {
        this.mars = mars;
        this.currentSite = site;
        this.actionDirection = direction;
    }

    public void exeCommands(List<ActionCommand> actionCommands) {
        actionCommands.stream().forEach(actionCommand -> {
            this.currentSite = actionCommand.exe(this);
        });
    }
}
