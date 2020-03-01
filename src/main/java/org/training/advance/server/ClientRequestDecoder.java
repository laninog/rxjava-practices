package org.training.advance.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;
import java.util.List;

public class ClientRequestDecoder extends MessageToMessageDecoder<ByteBuf> {

    private static final Logger log = LoggerFactory.getLogger(ClientRequestDecoder.class);

    private final Charset charset;

    public ClientRequestDecoder() {
        this(Charset.defaultCharset());
    }

    public ClientRequestDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel registered");
        log.info("{}", ctx.channel().remoteAddress());
        ChannelStore.add(ctx.channel());
        super.channelRegistered(ctx);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        String decoded = msg.toString(charset).replaceAll("[^\\.?0-9]+", " ");
        if ("".equals(decoded.trim()))
            decoded = "0";

        log.info("Decoded {}", decoded);

        out.add(new MessageCurrency(decoded));
    }

}
