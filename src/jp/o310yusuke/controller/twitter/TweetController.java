package jp.o310yusuke.controller.twitter;

import jp.o310yusuke.service.TwitterService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class TweetController extends Controller {

    private TwitterService service = new TwitterService();
    
    @Override
    public Navigation run() throws Exception {
        service.tweet(new RequestMap(request));
        return redirect(basePath);
    }
}
