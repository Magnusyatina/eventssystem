package engines;

import events.IEvent;

public interface IEngine {

    public void onEvent(IEvent event);
}
