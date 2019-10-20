package org.training.advance;

import io.netty.buffer.ByteBuf;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.netty.protocol.http.client.HttpClient;
import io.reactivex.netty.protocol.http.client.HttpClientRequest;
import io.reactivex.netty.protocol.http.client.HttpClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class FlowableABCUrlContent {

    private static final Logger log = LoggerFactory.getLogger(FlowableABCUrlContent.class);

    public static void main(String[] args) throws MalformedURLException {

        Observable<URL> sources = Observable.just(new URL("http://www.google.com"));

        HttpClientRequest<ByteBuf, ByteBuf> localhost = HttpClient.newClient("localhost", Integer.parseInt("80")).createGet("");

        /*sources.flatMap(u -> HttpClient.newClient(u.getHost(), u.getPort())
                .createGet(u.getPath()));

        Observable<ByteBuf> packets =
                sources
                        .flatMap(url -> HttpClient
                                .newClient(url.getHost(), url.getPort())
                                .createGet(url.getPath()))
                        .flatMap(HttpClientResponse::getContent);*/

        List<String> words = Arrays.asList("alpha", "beta", "gamma", "delta", "epsilon");

        Flowable<String> result = Flowable.fromIterable(words)
                .flatMap(word -> Flowable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Flowable.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        result.subscribe(l -> log.info("{}", l));

    }

}
