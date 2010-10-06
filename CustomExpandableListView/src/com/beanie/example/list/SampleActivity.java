
package com.beanie.example.list;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.beanie.example.list.adapter.ExpandableListAdapter;
import com.beanie.example.list.classes.Vehicle;
import com.beanie.example.list.data.MockDataProvider;

public class SampleActivity extends Activity implements Runnable
{
    private ExpandableListAdapter adapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Retrive the ExpandableListView from the layout
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listView);
        
        listView.setOnChildClickListener(new OnChildClickListener()
        {
            
            @Override
            public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
            {
                Toast.makeText(getBaseContext(), "Child clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        
        listView.setOnGroupClickListener(new OnGroupClickListener()
        {
            
            @Override
            public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
            {
                Toast.makeText(getBaseContext(), "Group clicked", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Initialize the adapter with blank groups and children
        // We will be adding children on a thread, and then update the ListView
        adapter = new ExpandableListAdapter(this, new ArrayList<String>(),
                new ArrayList<ArrayList<Vehicle>>());

        // Set this blank adapter to the list view
        listView.setAdapter(adapter);

        // This thread randomly generates some vehicle types
        // At an interval of every 2 seconds
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run()
    {
        final int ITEMS = 15;
        int count = 0;
        while (count != ITEMS)
        {
            count++;
            adapter.addItem(MockDataProvider.getRandomVehicle("Vehicle no. " + count));

            // Notify the adapter
            handler.sendEmptyMessage(1);
            try
            {
                // Sleep for two seconds
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    private Handler handler = new Handler()
    {

        @Override
        public void handleMessage(Message msg)
        {
            adapter.notifyDataSetChanged();
            super.handleMessage(msg);
        }

    };
}
