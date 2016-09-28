package nl.maartenpeels.csiweek2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Maarten Peels on 9/16/2016.
 */
@SuppressLint("InflateParams")  // See: https://code.google.com/p/android-developer-preview/issues/detail?id=1203
public class CriminalListAdapter extends ArrayAdapter<Criminal> {
    private Context context;
    private List<Criminal> criminals;

    public CriminalListAdapter(Context context, List<Criminal> criminals) {
        super(context, R.layout.activity_criminal_list, criminals);
        this.criminals = criminals;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Criminal requestedCriminal = criminals.get(position);

        View criminalView = convertView;

        if(criminalView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            criminalView  = inflater.inflate(R.layout.criminallistitem, null);
        }

        TextView nameView = (TextView) criminalView.findViewById(R.id.textView11);
        nameView.setText(requestedCriminal.name);

        TextView bountyView = (TextView) criminalView.findViewById(R.id.textView12);
        bountyView.setText(requestedCriminal.bounty);

        ImageView imageView = (ImageView)  criminalView.findViewById(R.id.imageView3);
        imageView.setImageDrawable(requestedCriminal.img);

        return criminalView;
    }
}
