package org.training.advance.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ChannelStore {

    private static Map<ChannelId, Channel> store = new HashMap<>();

    public static void add(Channel channel) {
        store.put(channel.id(), channel);
    }

    public static Channel get(ChannelId channelId) {
        return store.get(channelId);
    }

    public static Channel get() {
        Iterator<ChannelId> iterator = store.keySet().iterator();
        if(iterator.hasNext())
            return store.get(iterator.next());
        else
            return null;
    }

}
