package jp.o310yusuke.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import jp.o310yusuke.model.Tweet;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

public class TweetDaoTest extends AppEngineTestCase {

    private TweetDao tweetDao = new TweetDao();

    @Test
    public void test() throws Exception {
        assertThat(tweetDao, is(notNullValue()));
    }
    
    @Test
    public void getTweetList() throws Exception {
        Tweet tweet = new Tweet();
        tweet.setContent("Hello");
        tweetDao.put(tweet);
        
        List<Tweet> tweetList = tweetDao.getTweetList();
        assertThat(tweetList.size(), is(1));
        assertThat(tweetList.get(0).getContent(), is("Hello"));
    }
}
