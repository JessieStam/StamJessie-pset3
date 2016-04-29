package jstam.stamjessie_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> todo_list = new ArrayList<String>();
    // hier zit nog een error
    private ListView screen_list = new ListView();
    private ArrayAdapter<String> todoAdapter = new ArrayAdapter<String>
            (this, android.R.layout.simple_list_item_1, todo_list);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addToList(View view) {

        // get user input and add to the todo_list list
        EditText user_input = (EditText)findViewById(R.id.user_todo_input);
        todo_list.add(user_input.toString());

        // define the listview
        screen_list = (ListView) findViewById(R.id.screen_list_view);

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);


    }
}
