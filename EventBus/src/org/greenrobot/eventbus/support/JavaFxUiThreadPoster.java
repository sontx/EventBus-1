package org.greenrobot.eventbus.support;

import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Poster;
import org.greenrobot.eventbus.Subscription;

public class JavaFxUiThreadPoster implements Poster {
    private final EventBus eventBus;
    private final Object wait = new Object();

    JavaFxUiThreadPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void enqueue(final Subscription subscription, final Object event) {
        if (Platform.isFxApplicationThread())
            eventBus.invokeSubscriber(subscription, event);
        else
            invokeInJavaFxThread(subscription, event);
    }

    private synchronized void invokeInJavaFxThread(Subscription subscription, Object event) {
        Platform.runLater(() -> {
            eventBus.invokeSubscriber(subscription, event);
            synchronized (wait) {
                wait.notify();
            }
        });

        try {
            synchronized (wait) {
                wait.wait();
            }
        } catch (InterruptedException ignored) {
        }
    }
}
