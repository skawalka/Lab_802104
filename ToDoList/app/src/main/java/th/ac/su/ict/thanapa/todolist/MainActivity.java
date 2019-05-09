package th.ac.su.ict.thanapa.todolist;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etList;
    private Button btnAdd;
    private Button btnDelete;
    private ListView listView;

    private ArrayList<String> toDoItem;
    private ArrayAdapter<String> arrayAdapter; //convert item in array for show in list view

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflate

        etList = findViewById(R.id.etList);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        toDoItem = new ArrayList<>(); //instantiate
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDoItem);
        listView.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(clickListener);
        etList.setOnKeyListener(keyListener);

    }

    View.OnKeyListener keyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (v == etList) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    //Log.d("key listener", "key pressed");
                    addItemToList(etList.getText().toString());
                    hideKeyboard(v);
                }
            }
            return false;
        }
    };

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnAdd) {
                addItemToList(etList.getText().toString());
                hideKeyboard(v);
            }
        }
    };

    private void hideKeyboard(View vie){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(vie.getWindowToken(),0);
        //inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private void addItemToList(String item){
        //toDoItem.add(text+"");
        if (!item.isEmpty()) {
            toDoItem.add(0, item);
            etList.setText("");
            arrayAdapter.notifyDataSetChanged();
        }
    }
}
