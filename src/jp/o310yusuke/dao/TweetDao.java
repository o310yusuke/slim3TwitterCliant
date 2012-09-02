package jp.o310yusuke.dao;

import java.util.List;

import jp.o310yusuke.meta.TweetMeta;
import jp.o310yusuke.model.Tweet;

import org.slim3.datastore.DaoBase;
import org.slim3.datastore.Datastore;

public class TweetDao extends DaoBase<Tweet> {

    private TweetMeta meta = (TweetMeta) m;

    public List<Tweet> getTweetList() {
        return Datastore
                .query(meta)
                .sort(meta.content.asc)
                .asList();
    }

}
