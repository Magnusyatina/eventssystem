package engines;

import events.IEvent;

public class DumbEngine implements IEngine {

    @Override
    public void onEvent(IEvent event) {
        System.out.println("This is default method for this event");
    }
}
