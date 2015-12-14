package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import java.util.Iterator;
import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;

/**
 * Created by isen on 10/12/2015.
 */
public class RetrieveTweetsAsyncTask extends AsyncTask<String, Void, List<Tweet>> {

    @Override
    protected List<Tweet> doInBackground(String... login) {
        List<Tweet> tweets;
        if (!TextUtils.isEmpty(login[0])) {
            return null;
        } else {
            tweets = TwitterHelper.getTweetsOfUser(login[0]);
        }
        return tweets;
    }


    protected void onPostExecute(List<Tweet> tweets) {
        super.onPostExecute(tweets);
        //int count = tweets.size();                                                                  //On peut aussi utiliser iterator fonction
        //for (int i = 0; i < tweets.size(); i++) {
          //  System.out.println("Twitter" + tweets.get(i).text);
            //Log.d("TweetAsyncTask", tweets.get(i).text);
        //}
        //Fonctionne pas
        Iterator<Tweet> iter = tweets.iterator();
        while(iter.hasNext())
        {
            Log.d("TweetAsyncTask", tweets.toString());
        }
    }
}


