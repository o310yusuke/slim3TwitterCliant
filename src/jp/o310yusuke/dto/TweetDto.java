package jp.o310yusuke.dto;

import java.util.ArrayList;
import java.util.List;

import jp.o310yusuke.bean.TweetBean;
import jp.o310yusuke.dao.TweetDao;
import jp.o310yusuke.model.Tweet;

public class TweetDto {

    private TweetDao tweetDao = new TweetDao();
    
    public TweetBean transformModelToBean(Tweet tweet) {
        TweetBean bean = new TweetBean();
        bean.setId(tweet.getKey().getId());
        bean.setContent(tweet.getContent());
        bean.setCreateDate(tweet.getCreateDate());
        return bean;
    }

    public Tweet transformBeanToModel(TweetBean bean) {
        Tweet tweet = new Tweet();
        if(bean.getId() != null) {
            tweet.setKey(tweetDao.createKeyById(bean.getId()));
        }
        tweet.setContent(bean.getContent());
        if(bean.getCreateDate() != null) {
            tweet.setCreateDate(bean.getCreateDate());
        }
        tweetDao.put(tweet);
        return tweet;
    }

    public List<TweetBean> getTweetList() {
        List<Tweet> tweetList = tweetDao.getTweetList();
        List<TweetBean> beanList = new ArrayList<TweetBean>();
        
        for(Tweet tweet : tweetList) {
            TweetBean bean = new TweetBean();
            bean = transformModelToBean(tweet);
            beanList.add(bean);
        }
        return beanList;
    }

    public TweetBean put(TweetBean inputBean) {
        
        Tweet tweet = transformBeanToModel(inputBean);
        tweetDao.put(tweet);
        TweetBean putBean = transformModelToBean(tweet);
        
        return putBean;
    }

}
