package jp.o310yusuke.controller.twitter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class TweetController extends Controller {

    @Override
    public Navigation run() throws Exception {
        return forward("tweet.jsp");
    }
}
