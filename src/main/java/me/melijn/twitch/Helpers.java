package me.melijn.twitch;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Helpers {

    public long getPing(String host, int port) {
        long ping;
        try (Socket socket = new Socket()) {
            InetSocketAddress inetSocketAddress = new InetSocketAddress(host, port);
            ping = System.currentTimeMillis();
            socket.connect(inetSocketAddress, 30_000);
            ping = System.currentTimeMillis() - ping;
        } catch (IOException e) {
            return -1;
        }
        return ping;
    }
}
