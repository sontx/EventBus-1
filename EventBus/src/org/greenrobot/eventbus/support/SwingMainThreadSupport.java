package org.greenrobot.eventbus.support;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.MainThreadSupport;
import org.greenrobot.eventbus.Poster;

import javax.swing.*;

public class SwingMainThreadSupport implements MainThreadSupport {
    @Override
    public boolean isMainThread() {
        return SwingUtilities.isEventDispatchThread();
    }

    @Override
    public Poster createPoster(EventBus eventBus) {
        return new SwingUiThreadPoster(eventBus);
    }
}
