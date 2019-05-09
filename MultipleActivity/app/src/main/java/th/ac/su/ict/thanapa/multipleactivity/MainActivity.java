package th.ac.su.ict.thanapa.multipleactivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et1, et2, et3;
    private Button btnSubmit;

    private static final int SETTING_ACTIVITY_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------- SET VALUE ON UI ----------------------------------------------
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        btnSubmit = findViewById(R.id.btnSubmit);
        //------- EVENT FUNCTION -----------------------------------------------
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = et1.getText().toString();
                String id = et2.getText().toString();
                double gpa = Double.parseDouble(et3.getText().toString());
                if (et1.getText().toString().isEmpty() || et2.getText().toString().isEmpty() || et3.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "information not empty", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("id", id);
//                    intent.putExtra("gpa", gpa);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", name);
                    bundle.putString("id", id);
                    bundle.putDouble("gpa", gpa);
                    intent.putExtra("student", bundle);
                    startActivity(intent);
                }
                hideKeyboard(v);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
            startActivityForResult(intent, SETTING_ACTIVITY_REQUEST_CODE);
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideKeyboard(View vie){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(vie.getWindowToken(),0);
    }
}
