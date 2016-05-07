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

    ArrayList<String> todo_list = new ArrayList<>();
    ListView screen_list;
    ArrayAdapter<String> todoAdapter;

    EditText user_input;
    String todo_item;
    String currentColor;
    String finished = "finished";
    String unfinished = "unfinished";
    Integer position;
    Integer color;


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // This bundle has also been passed to onCreate.
        position = savedInstanceState.getInt("Position");
        color = savedInstanceState.getInt("Color");
    }

    @Override
    protected void onCreate(Bundle onRestoreInstanceState) {
        super.onCreate(onRestoreInstanceState);
        setContentView(R.layout.activity_main);

        user_input = (EditText) findViewById(R.id.user_todo_input);
        screen_list = new ListView(this);
        screen_list = (ListView) findViewById(R.id.screen_list_view);
        todoAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_list);
        currentColor = unfinished;

        if(onRestoreInstanceState != null) {
            position = onRestoreInstanceState.getInt("Position");
            color = onRestoreInstanceState.getInt("Color");
        }

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



    public void addToList(View view) {

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        todo_item = user_input.getText().toString();

        // add user input to ListView
        todo_list.add(todo_item);

        // refresh ListView
        todoAdapter.notifyDataSetChanged();

        // write some code that cleans the EditText

    }
}
