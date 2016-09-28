package nl.maartenpeels.csiweek2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

    public void onClick(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
