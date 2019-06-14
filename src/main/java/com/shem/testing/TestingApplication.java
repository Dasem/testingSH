package com.shem.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) throws SocketException {
        SpringApplication.run(TestingApplication.class, args);

        Enumeration e = NetworkInterface.getNetworkInterfaces();
        System.out.println("=====================================");
        System.out.println("Выберите один из адресов для подключения");
        while(e.hasMoreElements())
        {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            if (n.isLoopback() || !n.isUp()){
                continue;
            }
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements())
            {
                InetAddress i = (InetAddress) ee.nextElement();
                if (i instanceof Inet4Address) {
                    System.out.println(i.getHostAddress());
                }
            }
        }
        System.out.println("=====================================");

    }

}
