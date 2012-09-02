package jp.o310yusuke.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.o310yusuke.dao.TweetDao;
import jp.o310yusuke.model.Tweet;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class TwitterServiceTest extends AppEngineTestCase {

    private TwitterService service = new TwitterService();
    private TweetDao tweetDao  = new TweetDao();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }

    @Test
    public void tweet() throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("content", "Hello");
        Tweet tweeted = service.tweet(input);
        assertThat(tweeted, is(notNullValue()));

        Tweet stored = tweetDao.get(tweeted.getKey());
        assertThat(stored.getContent(), is("Hello"));
    }
    
    @Test
    public void getTweetList() throws Exception {
        Tweet tweet = new Tweet();
        tweet.setContent("Hello");
        tweetDao.put(tweet);
        
        List<Tweet> tweetList = service.getTweetList();
        assertThat(tweetList.size(), is(1));
        assertThat(tweetList.get(0).getContent(), is("Hello"));
    }
}
