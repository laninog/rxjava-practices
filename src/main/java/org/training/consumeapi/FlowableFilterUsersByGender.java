package org.training.consumeapi;

import com.jayway.jsonpath.JsonPath;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashMap;

import static org.training.utils.Utils.invoke;

public class FlowableFilterUsersByGender {

    private static final Logger log = LoggerFactory.getLogger(FlowableFilterUsersByGender.class);

    public static void main(String[] args) {

        new FlowableFilterUsersByGender()
                .filterByGender("male")
                .subscribe(result -> log.info("Result {}", result));

    }

    Flowable<LinkedHashMap> filterByGender(String gender) {
        Flowable flowable = invokeURL("https://randomuser.me/api/?results=10")
                .flatMap(res -> Flowable.fromIterable(JsonPath.parse(res).read("$.results", Collection.class)))
                .filter(a -> ((LinkedHashMap)a).get("gender").equals(gender));
        return flowable;
    }

    Flowable<String> invokeURL(String url) {
        return Flowable.just(url)
                .map(u -> invoke(u));
    }

}
