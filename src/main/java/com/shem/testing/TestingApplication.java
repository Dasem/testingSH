package com.shem.testing;

import com.shem.testing.gui.FXController;
import com.shem.testing.gui.Starter;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.net.SocketException;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) throws SocketException {
        new Thread(Starter::guiLaunch).start();
        SpringApplication.run(TestingApplication.class, args);
        FXController.SERVER_STATE=true;
        System.out.println(Utils.getServerUrl());
    }
}
