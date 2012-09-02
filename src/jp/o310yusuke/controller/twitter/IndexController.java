package jp.o310yusuke.controller.twitter;

import java.util.List;

import jp.o310yusuke.model.Tweet;
import jp.o310yusuke.service.TwitterService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class IndexController extends Controller {

    private TwitterService service = new TwitterService();

    @Override
    public Navigation run() throws Exception {
        List<Tweet> tweetList = service.getTweetList();
        requestScope("tweetList", tweetList);

        return forward("index.jsp");
    }
}
