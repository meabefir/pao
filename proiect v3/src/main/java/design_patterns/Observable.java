package design_patterns;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    List<Observer> obs = new ArrayList<>();

    public void addObserver(Observer o) {
        if (o == null)
            throw new NullPointerException();
        if (!obs.contains(o)) {
            obs.add(o);
        }
    }

    public void notifyObservers(Object ob) {
        for (Observer o: obs) {
            o.notify(this, ob);
        }
    }
}
