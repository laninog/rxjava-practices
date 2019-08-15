package org.training.consumeapi;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.LinkedHashMap;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlowableFilterUsersByGenderTest {

    @Mock
    private FlowableFilterUsersByGender flowable;

    private TestSubscriber<LinkedHashMap> testSubscriber;

    @Before
    public void init() {
        testSubscriber = new TestSubscriber<>();

        when(flowable.invokeURL(anyString()))
                .thenReturn(Flowable.just(
                        "{\"results\": [" +
                                "{\"gender\": \"male\"}," +
                                "{\"gender\": \"male\"}," +
                                "{\"gender\": \"female\"}" +
                                "{\"gender\": \"female\"}" +
                                "{\"gender\": \"male\"}" +
                         "] }"));

        when(flowable.filterByGender(anyString())).thenCallRealMethod();
    }

    @Test
    public void givenWhenThen() {

        flowable.filterByGender("male")
                .subscribe(testSubscriber);

        testSubscriber.assertComplete();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(3);

    }

}
