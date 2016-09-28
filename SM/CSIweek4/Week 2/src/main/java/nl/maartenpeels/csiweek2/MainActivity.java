package nl.maartenpeels.csiweek2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int index = getIntent().getIntExtra("index", 0);
        CriminalProvider provider = new CriminalProvider(getResources());
        Criminal criminal = provider.getCriminal(index);

        ((TextView)findViewById(R.id.textView2)).setText(criminal.name);
        ((TextView)findViewById(R.id.textView4)).setText(criminal.gender);
        ((TextView)findViewById(R.id.textView6)).setText(String.valueOf(criminal.age));
        ((TextView)findViewById(R.id.textView8)).setText(criminal.bounty);
        ((TextView)findViewById(R.id.textView9)).setText(criminal.details);
        ((ImageView)findViewById(R.id.imageView)).setImageDrawable(criminal.img);
    }

    public void onClick(View v)
    {
        Intent i = new Intent(this, ReportActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        startActivity(new Intent(this, CriminalList.class));
        finish();

    }
}
