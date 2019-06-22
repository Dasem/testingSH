package com.shem.testing;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Utils {
    public static String getServerUrl() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        StringBuilder sb = new StringBuilder();
        sb.append("=====================================\n");
        sb.append("Выберите один из адресов для подключения\n");
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
                    sb.append(i.getHostAddress()).append("\n");
                }
            }
        }
        sb.append("=====================================\n");
        return sb.toString();
    }
}
