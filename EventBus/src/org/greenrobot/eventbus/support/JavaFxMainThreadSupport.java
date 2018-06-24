package org.greenrobot.eventbus.support;

import javafx.application.Platform;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.MainThreadSupport;
import org.greenrobot.eventbus.Poster;

public class JavaFxMainThreadSupport implements MainThreadSupport {
    @Override
    public boolean isMainThread() {
        return Platform.isFxApplicationThread();
    }

    @Override
    public Poster createPoster(EventBus eventBus) {
        return new JavaFxUiThreadPoster(eventBus);
    }
}
