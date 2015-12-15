package worldline.ssm.rd.ux.wltwitter.ui.fragments;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetrieveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class TweetsFragment extends Fragment implements TweetChangeListener, AdapterView.OnItemClickListener{

	private RetrieveTweetsAsyncTask mRetrieveTweets;

	private ListView mListView;

	public TweetsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_wltwitter, container, false);

		mListView = (ListView)rootView.findViewById(R.id.tweetsListView);
		final ProgressBar progressBar = new ProgressBar(getActivity());
		progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
		progressBar.setIndeterminate(true);
		mListView.setEmptyView(progressBar);

		ViewGroup root = (ViewGroup) rootView.findViewById(R.id.tweetsRootRelativeLayout);
		root.addView(progressBar);

		mListView.setOnItemClickListener(this);
		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();
		final String login = PreferenceUtils.getLogin();

		if(!TextUtils.isEmpty(login))
		{
			mRetrieveTweets = new RetrieveTweetsAsyncTask(this);
			mRetrieveTweets.execute(login);
		}
	}

	private TweetListener mListener;
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if(activity instanceof TweetListener){
			mListener = (TweetListener)activity;
		}
	}

	@Override
	public void onTweetRetrieved(List<Tweet> tweets) {

		final ArrayAdapter<Tweet> arrayAdapter = new ArrayAdapter<Tweet>(getActivity(),android.R.layout.simple_list_item_1,tweets);
		mListView.setAdapter(arrayAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		final Tweet tweet = (Tweet)adapterView.getItemAtPosition(position);

		if(null != mListener){
			mListener.onViewTweet(tweet);
		}
	}
}
