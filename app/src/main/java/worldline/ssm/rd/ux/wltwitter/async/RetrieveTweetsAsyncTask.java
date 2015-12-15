package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by isen on 10/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Integer, List<Tweet>> {


    private TweetChangeListener mListener;

    public RetrieveTweetsAsyncTask(TweetChangeListener mListener) {
        this.mListener = mListener;
    }

    public RetrieveTweetsAsyncTask() {

    }

    @Override
    protected List<Tweet> doInBackground(String... login) {
        List<Tweet> tweets;
        if (TextUtils.isEmpty(login[0])) {
            return null;
        } else {
             tweets = TwitterHelper.getTweetsOfUser(login[0]);
        }
        return tweets;
    }


    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        if(null != mListener){
            mListener.onTweetRetrieved(tweets);
        }

        //NOUVELLE METHODE ITERATOR QUE L'ON A VU EN COURS DE JAVA 2!!!!!!!!!!
        /*Iterator<Tweet> iter = tweets.iterator();
        while(iter.hasNext())
        {
            Log.d("TweetAsyncTask", iter.next().toString());
        }*/


    }
}


