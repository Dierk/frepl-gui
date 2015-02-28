package org.frege;

import frege.runtime.Applicable;
import frege.runtime.Delayed;
import frege.runtime.Lambda;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;

public class FregeChangeListener implements ChangeListener {
    protected Lambda lambda;

    public FregeChangeListener(Lambda lambda) {
        this.lambda = lambda;
    }

    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        try {
            Applicable inter = lambda.apply((String)oldValue).apply((String) newValue); // we only work with Strings for the moment
            Delayed.forced(inter.apply(null).result().forced()); // the second argument is the IO context
        } catch(RuntimeException re) {
            re.printStackTrace();
            throw re;
        }
    }
}
