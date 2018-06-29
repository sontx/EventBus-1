package org.greenrobot.eventbus.support;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Poster;
import org.greenrobot.eventbus.Subscription;

import javax.swing.*;

public class SwingUiThreadPoster implements Poster {
    private final EventBus eventBus;

    SwingUiThreadPoster(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void enqueue(final Subscription subscription, final Object event) {
        SwingUtilities.invokeLater(() -> eventBus.invokeSubscriber(subscription, event));
    }
}
