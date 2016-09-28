package nl.maartenpeels.csiweek2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CriminalList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_list);

        ListView listview = (ListView) findViewById(R.id.listView);
        final String[] criminals = getResources().getStringArray(R.array.names);
        listview.setAdapter(
                new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        criminals
                )
        );

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = criminals[position];
                openActivity(name);
            }
        });
    }

    private void openActivity(String name)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("name", name);
        startActivity(i);
    }
}
