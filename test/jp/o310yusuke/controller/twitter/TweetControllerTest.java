package jp.o310yusuke.controller.twitter;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import jp.o310yusuke.model.Tweet;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;

public class TweetControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param("content", "Hello");

        tester.start("/twitter/tweet");
        TweetController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/twitter/"));

        Tweet stored = Datastore.query(Tweet.class).asSingle();
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getContent(), is("Hello"));

    }
}
