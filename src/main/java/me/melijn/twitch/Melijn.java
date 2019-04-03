package me.melijn.twitch;

import org.kitteh.irc.client.library.Client;
import org.kitteh.irc.client.library.feature.twitch.TwitchListener;
import org.kitteh.irc.client.library.feature.twitch.TwitchSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Melijn {

    private Client client;
    public final static String CHANNEL = "#pixelhamster";
    private final Logger logger = LoggerFactory.getLogger(Melijn.class.getName());

    public Melijn() {
        init();
    }

    public static void main(String[] args) {
        new Melijn();
    }

    private void init() {
        client = Client.builder()
                .server()
                .host("irc.chat.twitch.tv")
                .port(6697)
                .password("oauth:u3v70ypusa9o6whdpzx4ow80ldxkjv")

                .then()
                .nick("melijn")
                .build();
        TwitchSupport.addSupport(client);
        client.connect();
        client.addChannel(CHANNEL);

        client.setInputListener(logger::info);
        new TwitchListener(client);
        client.getEventManager().registerEventListener(new EventHandler(new Helpers()));

        client.sendMessage(CHANNEL, "Connected!");
    }
}
