package org.training.twitter;

import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import static java.lang.System.getenv;

public class TwitterConfiguration {

    public static final String MSG_FORMAT = "{}\n------------------\n {} -> {}\n------------------\n";

    public static Configuration getCredentials() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(false)
                .setOAuthConsumerKey(getenv("CONSUMER_KEY"))
                .setOAuthConsumerSecret(getenv("CONSUMER_SECRET"))
                .setOAuthAccessToken(getenv("ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(getenv("ACCESS_TOKEN_SECRET"));
        return cb.build();
    }

}
