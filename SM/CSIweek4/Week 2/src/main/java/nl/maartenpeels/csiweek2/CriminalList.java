package nl.maartenpeels.csiweek2;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ActionMenuItemView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CriminalList extends AppCompatActivity {
    List<Criminal> criminals;
    public Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_list);
        resources = getResources();

        CriminalProvider provider = new CriminalProvider(resources);
        criminals = provider.getCriminals();
        CriminalListAdapter criminalProvider = new CriminalListAdapter(this, criminals);

        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(criminalProvider);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openActivity(position);
            }
        });
    }

    private void openActivity(int index)
    {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("index", index);
        startActivity(i);
    }


}
