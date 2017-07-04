package com.gaoneng.androidleak;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "leak";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager manager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        int memory = manager.getMemoryClass();
        Log.d(TAG, "Max Heap Size=" + memory + " M");

        String[] strings = new String[]{
                str(R.string.static_title), str(R.string.inner_title), str(R.string.singleton_title),
                str(R.string.handler_title), str(R.string.threads_title), str(R.string.sensor_title)
        };
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        StaticActivity.start(MainActivity.this);
                        break;
                    case 1:
                        InnerActivity.start(MainActivity.this);
                        break;
                    case 2:
                        SingletonActivity.start(MainActivity.this);
                        break;
                    case 3:
                        HandlerActivity.start(MainActivity.this);
                        break;
                    case 4:
                        break;
                }
            }
        });
    }

    private String str(int res) {
        return getString(res);
    }
}
