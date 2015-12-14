package worldline.ssm.rd.ux.wltwitter.interfaces;

import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by Dan on 13/12/2015.
 */
public interface TweetListener {
    public void onReTweet(Tweet tweet);

    public void onViewTweet(Tweet tweet);



}
