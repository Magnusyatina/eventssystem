package engines;

import events.IEvent;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class SmartEngine implements IEngine {
    private HashMap<Class<?>, Method> methodList = new HashMap<>();
    private IEngine specializedEngine = new DumbEngine();

    @Override
    public void onEvent(IEvent event) {
        Class<?> cl = event.getClass();
        try {
            Method method;

            if ((method = methodList.get(cl)) == null) {
                method = getClass().getMethod("onEvent", cl);
                methodList.put(cl, method);
            }

            method.invoke(this, event);
        } catch (NoSuchMethodException e) {
            System.out.println("Method did not find");
            try {
                Method method = SmartEngine.class.getDeclaredMethod("onEvent", Object.class);
                method.setAccessible(true);
                methodList.put(cl, method);
                method.invoke(this, event);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException noSuchMethodException) {

            }
        } catch (IllegalAccessException e) {

        } catch (InvocationTargetException e) {

        }
    }

    private void onEvent(Object event) {
        specializedEngine.onEvent((IEvent) event);
    }

    public void setSpecializedEngine(IEngine specializedEngine) {
        this.specializedEngine = specializedEngine;
    }

}
