package me.melijn.twitch;

import net.engio.mbassy.listener.Handler;
import org.kitteh.irc.client.library.event.channel.ChannelMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventHandler {

    private final Helpers helpers;
    private final Logger logger = LoggerFactory.getLogger(EventHandler.class.getName());


    public EventHandler(Helpers helpers) {
        this.helpers = helpers;
    }

    @Handler
    public void onChannelMessage(ChannelMessageEvent event) {
        logger.info(event.getChannel().getName() + "/" + event.getActor().getUserString() + "<" + event.getActor().getNick() + "> : " + event.getMessage());
        if (event.getMessage().equalsIgnoreCase("!ping")) {
            event.sendReply("Bot ping: " + helpers.getPing("irc.chat.twitch.tv", 6697) + "ms");
        }
    }
}
