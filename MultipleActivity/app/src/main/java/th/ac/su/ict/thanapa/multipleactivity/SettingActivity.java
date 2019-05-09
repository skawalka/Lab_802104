package th.ac.su.ict.thanapa.multipleactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    private Button btnRed, btnGreen, btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        //------- SET VALUE ON UI ----------------------------------------------
        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);
        //------- EVENT FUNCTION -----------------------------------------------
        btnRed.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == btnRed){
                Intent intent = new Intent();
                intent.putExtra("selected_color", "RED");
                setResult(RESULT_OK, intent);
                finish();
            }
            if (v == btnGreen){
                Intent intent = new Intent();
                intent.putExtra("selected_color", "GREEN");
                setResult(RESULT_OK, intent);
                finish();
            }
            if (v == btnBlue){
                Intent intent = new Intent();
                intent.putExtra("selected_color", "BLUE");
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };
}
