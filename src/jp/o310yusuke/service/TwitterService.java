package jp.o310yusuke.service;

import java.util.List;
import java.util.Map;

import jp.o310yusuke.bean.TweetBean;
import jp.o310yusuke.dto.TweetDto;

import org.slim3.util.BeanUtil;

public class TwitterService {

    private TweetDto tweetDto = new TweetDto();

    public TweetBean tweet(Map<String, Object> input) {
        TweetBean tweetBean = new TweetBean();
        BeanUtil.copy(input, tweetBean);
        tweetBean = tweetDto.put(tweetBean);
        
        return tweetBean;
    }

    public List<TweetBean> getTweetList() {
        return tweetDto.getTweetList();
    }

}
