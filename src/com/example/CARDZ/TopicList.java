package com.example.CARDZ;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.parse.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: tony
 * Date: 6/16/13
 * Time: 2:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopicList extends Activity {

    final private int NEW_TOPIC = 1;
    ListView lv;
    private List<String> data;
    ArrayAdapter adapter;
    Button addButton;
    int selectedItemPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_list);
        Parse.initialize(this, "P8vTRXzXsHTGKI9X0g2pZaUwNeE35CKbBtXXDJHG", "zNcWTpND2CF1xSX0XO1HaBXlr0uXw7tiLL1gAY82");

        lv = (ListView) findViewById(R.id.topics);

        data = new ArrayList<String>();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lv.setAdapter(adapter);
//        registerForContextMenu(lv);


        final Intent cardIntent = new Intent(this, MyActivity.class);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(cardIntent);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int pos, long id) {
                selectedItemPosition = pos;
                startActionMode(modeCallBack);
                view.setSelected(true);
                Log.v("long clicked", "pos" + " " + pos);

                return true;
            }
        });

        final Intent topicIntent = new Intent(this, MakeTopic.class);
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(topicIntent, NEW_TOPIC);
            }
        });

        loadTopics();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("Topic", "onactivityresult");
        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_TOPIC) {
                loadTopics();

            }
        }
    }

    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle(adapter.getItem(selectedItemPosition).toString());
            mode.getMenuInflater().inflate(R.menu.topic_context_menu, menu);
            return true;
        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            int id = item.getItemId();
            switch (id) {
                case R.id.delete: {
                    ParseObject testObject = new ParseObject("Topics");
//                    testObject.remove(adapter.getItem(selectedItemPosition).toString());
//                    testObject.saveInBackground();
                    adapter.remove(adapter.getItem(selectedItemPosition));
                    adapter.notifyDataSetChanged();
                    break;
                }
                case R.id.add: {
                    final Intent addIntent = new Intent(getApplication(), MakeCard.class);
                    startActivity(addIntent);
                    break;
                }
                default:
                    return false;
            }
            return true;

        }
    };

    private void loadTopics() {
        final ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading Topics");
        progress.show();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Topics");
//        query.whereExists("Topic");
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> topicList, ParseException e) {
                if (e == null) {
                    adapter.clear();
                    for (ParseObject object : topicList) {
                        adapter.add(object.get("topic"));
                    }
                    progress.dismiss();
                    adapter.notifyDataSetChanged();

                    Log.d("score", "Retrieved " + topicList.size() + " scores");
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }


}
