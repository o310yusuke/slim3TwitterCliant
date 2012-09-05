package jp.o310yusuke.dto;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;

import jp.o310yusuke.bean.TweetBean;
import jp.o310yusuke.model.Tweet;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

public class TweetDtoTest extends AppEngineTestCase {
    TweetDto tweetDto = new TweetDto();

    @Test
    public void transformModelToBean_登録済みModel() {
        Tweet tweet = createTweet();
        TweetBean bean = tweetDto.transformModelToBean(tweet);
        
        assertThat(bean.getId(), is(tweet.getKey().getId()));
        assertThat(bean.getContent(), is(tweet.getContent()));
        assertThat(bean.getCreateDate(), is(tweet.getCreateDate()));
    }
    
    @Test
    public void transformBeanToModel_空Bean() {
        TweetBean bean = new TweetBean();
        Tweet tweet = tweetDto.transformBeanToModel(bean);
        
        assertThat(tweet.getKey().getId(), is(notNullValue()));
        assertThat(tweet.getContent(), is(nullValue()));
        assertThat(tweet.getCreateDate(), is(notNullValue()));
    }
    
    @Test
    public void transformBeanToModel_新規Tweet() {
        TweetBean bean = new TweetBean();
        bean.setContent("Hello");
        Tweet tweet = tweetDto.transformBeanToModel(bean);
        
        assertThat(tweet.getKey().getId(), is(notNullValue()));
        assertThat(tweet.getContent(), is("Hello"));
        assertThat(tweet.getCreateDate(), is(notNullValue()));
    }
    
    @Test
    public void transformBeanToModel_既存Tweet() {
        TweetBean bean = createUpdateTweetBean();
        Tweet tweet = tweetDto.transformBeanToModel(bean);
        
        assertThat(tweet.getKey().getId(), is(1L));
        assertThat(tweet.getContent(), is("Hello"));
        assertThat(tweet.getCreateDate(), is(bean.getCreateDate()));
    }
    
    @Test
    public void getTweetList_0件() {
        List<TweetBean> beanList = tweetDto.getTweetList();
        assertThat(beanList.size(), is(0));
    }
    
    @Test
    public void getTweetList_1件() {
        Tweet tweet = createTweet();
        List<TweetBean> beanList = tweetDto.getTweetList();
        assertThat(beanList.size(), is(1));
        assertThat(beanList.get(0).getContent(), is("Hello"));
        assertThat(beanList.get(0).getId(), is(tweet.getKey().getId()));
        assertThat(beanList.get(0).getCreateDate(), is(tweet.getCreateDate()));
    }
    
    @Test
    public void getTweetList_2件() {
        createTweet();
        createTweet();
        List<TweetBean> beanList = tweetDto.getTweetList();
        assertThat(beanList.size(), is(2));
        assertThat(beanList.get(0).getContent(), is("Hello"));
        assertThat(beanList.get(1).getContent(), is("Hello"));
    }
    
    @Test
    public void put_新規Tweet() {
        TweetBean newBean = new TweetBean();
        newBean.setContent("Hello");
        
        TweetBean putBean = tweetDto.put(newBean);
        assertThat(putBean.getId(), is(notNullValue()));
        assertThat(putBean.getContent(), is("Hello"));
        assertThat(putBean.getCreateDate(), is(notNullValue()));
    }
    
    @Test
    public void put_更新Tweet() {
        TweetBean updateBean = createUpdateTweetBean();
        
        TweetBean putBean = tweetDto.put(updateBean);
        assertThat(putBean.getId(), is(1L));
        assertThat(putBean.getContent(), is("Hello"));
        assertThat(putBean.getCreateDate(), is(updateBean.getCreateDate()));
    }


    // -------------------------------------
    private Tweet createTweet() {
        Tweet tweet = new Tweet();
        tweet.setContent("Hello");
        Datastore.put(tweet);
        return tweet;
    }
    
    private TweetBean createUpdateTweetBean() {
        TweetBean updateBean = new TweetBean();
        updateBean.setId(1L);
        updateBean.setContent("Hello");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012, 0, 1);
        updateBean.setCreateDate(calendar.getTime());
        return updateBean;
    }

}
