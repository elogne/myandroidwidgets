
package com.beanie.hcsample.dragdrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HCDragDropActivity extends Activity {

    private ArrayAdapter<String> adapter1;

    private ListView listView;

    private TextView textView;

    private LinearLayout layoutDropArea;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initializeUIElements();

        setupDragDropStuff();
    }

    private void setupDragDropStuff() {
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v, int position, long arg3) {
                final String title = (String) ((TextView) v).getText();
                final String textData = title + ":" + position;
                ClipData data = ClipData.newPlainText(title, textData);
                v.startDrag(data, new MyDragShadowBuilder(v), null, 0);
                return true;
            }
        });

        layoutDropArea.setOnDragListener(new OnDragListener() {

            public boolean onDrag(View v, DragEvent event) {
                System.out.println(v.getClass().getName());
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_ENTERED:
                        v.setBackgroundColor(Color.GRAY);
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        v.setBackgroundColor(Color.TRANSPARENT);
                        break;

                    case DragEvent.ACTION_DRAG_STARTED:
                        return processDragStarted(event);

                    case DragEvent.ACTION_DROP:
                        v.setBackgroundColor(Color.TRANSPARENT);
                        return processDrop(event);
                }
                return false;
            }
        });

    }

    private boolean processDrop(DragEvent event) {
        ClipData data = event.getClipData();
        if (data != null) {
            if (data.getItemCount() > 0) {
                Item item = data.getItemAt(0);
                String textData = (String) item.getText();
                String[] parts = textData.split(":");
                int index = Integer.parseInt(parts[1]);
                String listItem = parts[0];
                updateViewsAfterDropComplete(listItem, index);
                return true;
            }
        }
        return false;
    }

    private void updateViewsAfterDropComplete(String listItem, int index) {
        adapter1.remove(listItem);
        adapter1.notifyDataSetChanged();

        textView.setText(new SpannableString(listItem));
    }

    private void initializeUIElements() {
        listView = (ListView) findViewById(R.id.listView);

        textView = (TextView) findViewById(R.id.textView);

        layoutDropArea = (LinearLayout) findViewById(R.id.layoutDropArea);

        adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter1.add("List 1 Item 1");
        adapter1.add("List 1 Item 2");
        adapter1.add("List 1 Item 3");
        adapter1.add("List 1 Item 4");
        adapter1.add("List 1 Item 5");
        adapter1.add("List 1 Item 6");
        adapter1.add("List 1 Item 7");
        adapter1.add("List 1 Item 8");
        adapter1.add("List 1 Item 9");
        adapter1.add("List 1 Item 10");
        adapter1.add("List 1 Item 11");
        adapter1.add("List 1 Item 12");
        adapter1.add("List 1 Item 13");

        listView.setAdapter(adapter1);
    }

    private boolean processDragStarted(DragEvent event) {
        ClipDescription clipDesc = event.getClipDescription();
        if (clipDesc != null) {
            return clipDesc.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN);
        }
        return false;
    }

}
