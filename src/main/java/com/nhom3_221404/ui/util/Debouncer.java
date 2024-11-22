package com.nhom3_221404.ui.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Debouncer {
    private Timeline timeline;

    public void debounce(Runnable action, int delay) {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(delay), event -> action.run()));
        timeline.play();
    }
}
