package org.greenrobot.eventbus.support;

import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Poster;
import org.greenrobot.eventbus.Subscription;

public class JavaFxUiThreadPoster implements Poster {
    private final EventBus eventBus;

    JavaFxUiThreadPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void enqueue(final Subscription subscription, final Object event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                eventBus.invokeSubscriber(subscription, event);
            }
        });
    }
}
