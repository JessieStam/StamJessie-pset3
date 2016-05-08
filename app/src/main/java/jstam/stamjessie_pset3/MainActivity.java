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

                if (currentColor.equals(unfinished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.GRAY);
                    currentColor = finished;
                }
                else if (currentColor.equals(finished)) {
                    screen_list.getChildAt(position).setBackgroundColor(Color.WHITE);
                    currentColor = unfinished;
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        todo_item = user_input.getText().toString();
        // item_list = screen_list;
        // todo_list_save = todo_list.get.toStringArrayList();

        outState.putString("todo_item", todo_item);
        outState.putStringArrayList("todo_list", todo_list_save);
    }

    @Override
    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);

        user_input.setText(saveInstanceState.getString(todo_item));
        //item_list = saveInstanceState.getString(item_list);
        todo_list = saveInstanceState.getStringArrayList("todo_list");
    }

    public void addToList(View view) {

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        todo_item = user_input.getText().toString();

        // add user input to ListView
        todo_list.add(todo_item);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        id = id + 1;

        // write some code that cleans the EditText

    }
}
