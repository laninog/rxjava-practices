package org.training.consumeapi;

import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.training.utils.Utils.invoke;

public class FlowableInvokeUsersAPI {

    private static final Logger log = LoggerFactory.getLogger(FlowableInvokeUsersAPI.class);

    public static void main(String[] args) {

        new FlowableInvokeUsersAPI()
                .invokeURL("https://randomuser.me/api/")
                .subscribe(result -> log.info("Result {}", result));

    }

    Flowable<String> invokeURL(String url) {
        return Flowable.just(url)
                .map(u -> invoke(u));
    }

}
