package jstam.stamjessie_pset3;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * To-Do application
 * Jessie Stam
 * 10560599
 *
 * This application allows the uer to make a simple to-do list, to which items can be added and
 * from which items can be selected and removed.
 *
 * Unfortunately I wasn't able to make the application function when I linked the DBHelper to my
 * MainActivity, so the SQLite Database is not used since I found it more important for the
 * application to function partially. However, I did finish the DBHelper, so I hope that you will
 * take that into consideration. Thanks in advance.
 */

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todo_list;
    ArrayList<String> todo_list_save;
    ListView screen_list;
    ArrayAdapter<String> todoAdapter;

    EditText user_input;
    String todo_item;
    String currentColor;
    String finished = "finished";
    String unfinished = "unfinished";
    Integer id = 0;
    //DBHelper storyName = new DBHelper(InputStream);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            todo_list = new ArrayList<>();
        } else {
            todo_list = savedInstanceState.getStringArrayList("todo_list");
        }

        user_input = (EditText) findViewById(R.id.user_todo_input);
        screen_list = new ListView(this);
        screen_list = (ListView) findViewById(R.id.screen_list_view);
        todoAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_list);
        currentColor = unfinished;


        /*
         * set onclick listener for ListView items to check/uncheck them
         */
        screen_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // if item is selected, change color to gray
                if (currentColor.equals(unfinished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    currentColor = finished;
                }
                // if item is not selected, change color back to white
                else if (currentColor.equals(finished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.WHITE);
                    currentColor = unfinished;
                }
            }
        });

        /*
         * set long click listener for removing items
         */
        screen_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View string, int position, long id) {

                // remove the item at the touched position and update data
                todo_list.remove(position);
                todoAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    /*
     * Save data for when screen is rotated or application is shut down
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save to-do items
        todo_item = user_input.getText().toString();

        outState.putString("todo_item", todo_item);
        outState.putStringArrayList("todo_list", todo_list_save);
    }

    /*
     * Restore data after screen is rotated or application is restarted
     */
    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        // restore to-do items
        user_input.setText(saveInstanceState.getString(todo_item));
        todo_list = saveInstanceState.getStringArrayList("todo_list");
    }

    /*
     * Adds an item to the list
     */
    public void addToList(View view) {

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        todo_item = user_input.getText().toString();

        // add user input to ListView
        todo_list.add(todo_item);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // update id for to do item
        id = id + 1;

        // clear the input line after text is added
        user_input.getText().clear();
    }
}
