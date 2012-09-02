package jp.o310yusuke.service;

import java.util.List;
import java.util.Map;

import jp.o310yusuke.dao.TweetDao;
import jp.o310yusuke.model.Tweet;

import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Transaction;

public class TwitterService {

    private TweetDao tweetDao = new TweetDao();

    public Tweet tweet(Map<String, Object> input) {
        Tweet tweet = new Tweet();
        BeanUtil.copy(input, tweet);
        Transaction tx = Datastore.beginTransaction();
        tweetDao.put(tweet);
        tx.commit();
        return tweet;
    }

    public List<Tweet> getTweetList() {
        return tweetDao.getTweetList();
    }

}
