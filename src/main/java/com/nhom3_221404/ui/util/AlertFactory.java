package com.nhom3_221404.ui.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertFactory {
    public static Alert createAlert(AlertType type, String content) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setTitle(type.toString());
        return alert;
    }

    public static Alert error(String content) {
        return createAlert(AlertType.ERROR, content);
    }

    public static Alert info(String content) {
        return createAlert(AlertType.INFORMATION, content);
    }

    public static Alert warning(String content) {
        return createAlert(AlertType.WARNING, content);
    }

    public static Alert confirmation(String content) {
        return createAlert(AlertType.CONFIRMATION, content);
    }
}
