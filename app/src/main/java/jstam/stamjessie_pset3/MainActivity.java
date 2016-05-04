package jstam.stamjessie_pset3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addToList(View view) {

        user_input = (EditText) findViewById(R.id.user_todo_input);
        screen_list = new ListView(this);
        screen_list = (ListView) findViewById(R.id.screen_list_view);
        todo_item = user_input.getText().toString();
        todoAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, todo_list);

        // use adapter to put todo_list information to screen_list
        screen_list.setAdapter(todoAdapter);

        todo_list.add(todo_item);

        // this method will refresh your listview manually
        todoAdapter.notifyDataSetChanged();

    }
}
