package jp.o310yusuke.dao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import jp.o310yusuke.meta.TweetMeta;
import jp.o310yusuke.model.Tweet;

import org.junit.Test;
import org.slim3.tester.AppEngineTestCase;

import com.google.appengine.api.datastore.Key;

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
    
    @Test(expected=IllegalArgumentException.class)
    public void createKeyById_ZERO() {
        tweetDao.createKeyById(0L);
    }
    
    @Test
    public void createKeyById_notZERO() {
        Key key = tweetDao.createKeyById(1L);
        assertThat(key.getParent(), is(nullValue()));
        assertThat(key.getKind(), is(TweetMeta.get().getKind()));
        assertThat(key.getId(), is(1L));
        assertThat(key.getName(), is(nullValue()));
    }
}
