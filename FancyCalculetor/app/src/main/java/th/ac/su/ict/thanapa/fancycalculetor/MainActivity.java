package th.ac.su.ict.thanapa.fancycalculetor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shashank.sony.fancytoastlib.FancyToast;

import life.sabujak.roundedbutton.RoundedButton;

public class MainActivity extends AppCompatActivity {

    RoundedButton roundedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roundedButton = findViewById(R.id.roundedButton);

        roundedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FancyToast.makeText(MainActivity.this,"Hello World !",FancyToast.LENGTH_LONG,FancyToast.DEFAULT,true).show();
            }
        });


    }
}
