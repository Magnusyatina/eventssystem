package events;

import engines.IEngine;

public class BaseEvent implements IEvent {

    @Override
    public void start(IEngine engine) {
        engine.onEvent(this);
    }
}
