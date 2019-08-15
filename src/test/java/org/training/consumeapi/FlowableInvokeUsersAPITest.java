package org.training.consumeapi;

import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;

public class FlowableInvokeUsersAPITest {

    private FlowableInvokeUsersAPI flowable;

    private TestSubscriber<String> testSubscriber;

    @Before
    public void init() {
        flowable = new FlowableInvokeUsersAPI();
        testSubscriber = new TestSubscriber<>();
    }

    @Test
    public void givenWhenThen() {

        flowable.invokeURL("https://randomuser.me/api/").subscribe(testSubscriber);

        testSubscriber.assertComplete();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(1);

    }

}
