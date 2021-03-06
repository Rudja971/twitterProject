package worldline.ssm.rd.ux.wltwitter;

import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetsFragment;
import worldline.ssm.rd.ux.wltwitter.utils.Constants;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class WLTwitterActivity extends Activity implements TweetChangeListener, TweetListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wltwitter);

		// Retrieve the login passed as parameter
		final Intent intent = getIntent();
		if (null != intent){
			final Bundle extras = intent.getExtras();
			if ((null != extras) && (extras.containsKey(Constants.Login.EXTRA_LOGIN))){
				// Retrieve the login
				final String login = extras.getString(Constants.Login.EXTRA_LOGIN);
				
				// Set as ActionBar subtitle
				getActionBar().setSubtitle(login);

				RetrieveTweetsAsyncTask task = new RetrieveTweetsAsyncTask();
				Log.d("Main","Before retrieveasync");
				task.execute(login);
			}
		}
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.container, new TweetsFragment()).commit();
		}


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wltwitter, menu);
		return true;
	}

	@Override
	public void onTweetRetrieved(List<Tweet> tweets) {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.actionLogout) {
			// Erase login and password in Preferences
			PreferenceUtils.setLogin(null);
			PreferenceUtils.setPassword(null);
			
			// Finish this activity, and go back to LoginActivity
			finish();
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onReTweet(Tweet tweet) {

	}

	@Override
	public void onViewTweet(Tweet tweet) {
		Toast.makeText(this,tweet.text,Toast.LENGTH_LONG).show();
	}
}
