package com.kxh.jiker.command;

import java.util.HashMap;
import java.util.Map;

public class ActionCommandFactory {

    private Map<String, Class<? extends ActionCommand>> actionCommandClasses = new HashMap<>();

    public ActionCommandFactory() {
        this.actionCommandClasses.put(ActionCommandKeys.FORWARD, ActionCommandForward.class);
        this.actionCommandClasses.put(ActionCommandKeys.BACKWARD, ActionCommandBackward.class);
        this.actionCommandClasses.put(ActionCommandKeys.TURNRIGHT, ActionCommandTurnright.class);
        this.actionCommandClasses.put(ActionCommandKeys.TURNLEFT, ActionCommandTurnleft.class);
    }

    public ActionCommand CreateActionCommand(String commandKey) {
        ActionCommand command = null;
        try {
            command = this.actionCommandClasses.get(commandKey).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return command;
    }
}
