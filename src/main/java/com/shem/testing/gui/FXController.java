package com.shem.testing.gui;

import com.shem.testing.Utils;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;

import java.net.SocketException;

public class FXController {

    private static FXController instance;

    @FXML
    ListView listView;

    ObservableList<String> results;

    @FXML
    @SuppressWarnings("unchecked")
    public void initialize() {
        System.out.println("GUI has been created");
        results = FXCollections.observableArrayList();
        listView.setItems(results);

        instance = this;
    }

    public static FXController getInstance() {
        return instance;
    }

    public void addResult(String result) {
        results.add(result);
    }

    @FXML
    public void getIp(){
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ваш IP");
            alert.setHeaderText("Информация");
            try {
                alert.setContentText(Utils.getServerUrl());
            } catch (SocketException e) {
                alert.setContentText("Проблемы с подключением");
                e.printStackTrace();
            }
            alert.show();
        });
    }

}
