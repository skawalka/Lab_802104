package th.ac.su.ict.thanapa.multipleactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    Button btnBack, btnNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        btnBack = findViewById(R.id.btnBack);
        btnNew = findViewById(R.id.btnNew);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("student");
        String name = bundle.getString("name");
        String id = bundle.getString("id");
        double gpa = bundle.getDouble("gpa", -1);

        if (name.isEmpty() || id.isEmpty() || gpa == -1){
            tv1.setText("Missing Value");
            tv2.setText("");
            tv3.setText("");
        } else {
            tv1.setText(name);
            tv2.setText(id);
            tv3.setText(gpa+"");
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == R.id.btnBack){
                finish();
            }
            if (v == R.id.btnNew){
                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}
