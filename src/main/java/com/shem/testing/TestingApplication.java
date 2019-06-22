package com.shem.testing;

import com.shem.testing.gui.Starter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.net.SocketException;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) throws SocketException {
        SpringApplication.run(TestingApplication.class, args);
        System.out.println(Utils.getServerUrl());
        Starter.guiLaunch();
    }
}
