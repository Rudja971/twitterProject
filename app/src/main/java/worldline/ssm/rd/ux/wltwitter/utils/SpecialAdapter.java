package worldline.ssm.rd.ux.wltwitter.utils;

/**
 * Created by isen on 15/12/2015.
 */
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;


//Classe permettant d'avoir un tweet sur deux de couleur bleu.
public class SpecialAdapter extends ArrayAdapter {

    //private int[] colors = new int[] { 0x30FF0000, 0x300000FF };
    private int[] colors = new int[] { 0xFFFFFFFF, 0xFF33B5E5 };

    public SpecialAdapter(Context context, int resource, List<Tweet> objects){
        super(context, resource, objects);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        int colorPos = position % colors.length;
        view.setBackgroundColor(colors[colorPos]);
        return view;
    }
}
