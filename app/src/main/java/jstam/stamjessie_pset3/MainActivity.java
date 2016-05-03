package jstam.stamjessie_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import android.app.ListActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> todo_list = new ArrayList<>();
    // ListView screen_list = new ListView(this);
    ArrayAdapter<String> todoAdapter = new ArrayAdapter<>
            (this, android.R.layout.simple_list_item_1, todo_list);
    final EditText user_input = (EditText)findViewById(R.id.user_todo_input);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addToList(View view) {

        ListView screen_list = (ListView) findViewById(R.id.screen_list_view);

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        String todo_item = user_input.getText().toString();
        todo_list.add(todo_item);


        // get user input and add to the todo_list list
        // todo_list.add(user_input.toString());

        // define the ListView
        // screen_list = (ListView) findViewById(R.id.screen_list_view);

        // screen_list.setAdapter(todoAdapter);

        // this method will refresh your listview manually
        todoAdapter.notifyDataSetChanged();
    }


}
