package jp.o310yusuke.dao;

import java.util.List;

import jp.o310yusuke.meta.TweetMeta;
import jp.o310yusuke.model.Tweet;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Key;

public class TweetDao extends DaoBase<Tweet> {

    private TweetMeta meta = (TweetMeta) m;

    public List<Tweet> getTweetList() {
        return Datastore
                .query(meta)
                .sort(meta.createDate.desc)
                .asList();
    }

    public Key createKeyById(long id) {
        if(id == 0L) {
            throw new IllegalArgumentException();
        }
        return Datastore.createKey(meta, id);
    }

}
