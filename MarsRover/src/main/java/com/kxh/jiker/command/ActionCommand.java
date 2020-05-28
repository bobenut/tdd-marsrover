package com.kxh.jiker.command;

import com.kxh.jiker.Area;
import com.kxh.jiker.MarsRover;
import com.kxh.jiker.Site;

public abstract class ActionCommand {
    public abstract String getKey();

    public abstract Site exe(MarsRover marsRover);

    protected abstract Site calculateSiteAfterWalking(MarsRover marsRover);

    protected Site doWalking(MarsRover marsRover) {
        Site siteAfterWalking = calculateSiteAfterWalking(marsRover);
        isWillOutOfArea(marsRover, siteAfterWalking);
        return siteAfterWalking;
    }

    protected void isWillOutOfArea(MarsRover marsRover, Site siteAfterWalking) {
        Area areaExplored = marsRover.getMars().getAreaExplored();

        if (isOutOfAreaWestBoundary(areaExplored, siteAfterWalking) ||
                isOutOfAreaNorthBoundary(areaExplored, siteAfterWalking) ||
                isOutOfAreaEastBoundary(areaExplored, siteAfterWalking) ||
                isOutOfAreaSouthBoundary(areaExplored, siteAfterWalking)
        ) {
            throw new IllegalArgumentException("will out of area");
        }
    }

    protected boolean isOutOfAreaEastBoundary(Area areaExplored, Site siteAfterWalking) {
        return siteAfterWalking.getX() > areaExplored.getOriginalx() + areaExplored.getX();
    }

    protected boolean isOutOfAreaSouthBoundary(Area areaExplored, Site siteAfterWalking) {
        return siteAfterWalking.getY() > areaExplored.getOriginaly() + areaExplored.getY();
    }

    protected boolean isOutOfAreaWestBoundary(Area areaExplored, Site siteAfterWalking) {
        return siteAfterWalking.getX() < areaExplored.getOriginalx();
    }

    protected boolean isOutOfAreaNorthBoundary(Area areaExplored, Site siteAfterWalking) {
        return siteAfterWalking.getY() < areaExplored.getOriginaly();
    }
}
