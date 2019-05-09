package th.ac.su.ict.thanapa.discountcalculator;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Key;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private TextView tvPercebtOff;
    private EditText etOriPrice;
    private SeekBar sbPercentOff;
    private CheckBox cbTax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      ------------ Variable ----------------------------------------------------------------------------
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvPercebtOff = findViewById(R.id.tvPercentOff);
        etOriPrice = findViewById(R.id.etOriPrice);
        sbPercentOff = findViewById(R.id.sbPercentOff);
        cbTax = findViewById(R.id.cbTax);
//      ------------ Event ----------------------------------------------------------------------------
        sbPercentOff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPercebtOff.setText(progress+"%");
                calculate(Double.parseDouble(etOriPrice.getText().toString()), sbPercentOff.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        etOriPrice.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && etOriPrice.getText().toString().length() > 0){
                    calculate(Double.parseDouble(etOriPrice.getText().toString()), sbPercentOff.getProgress());
                } else {
                    tvTotalPrice.setText("0.00");
                }
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN && etOriPrice.getText().toString().length() > 0) {
                    calculate(Double.parseDouble(etOriPrice.getText().toString()), sbPercentOff.getProgress());
                    hideKeyboard(v);
                }
                return false;
            }
        });
        cbTax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkEmpty(etOriPrice.getText().toString());
            }
        });

    }

    private void hideKeyboard(View vie){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(vie.getWindowToken(),0);
    }

    private void checkEmpty(String value){
        if (!value.isEmpty()){
            calculate(Double.parseDouble(value), sbPercentOff.getProgress());
        } else {
            tvTotalPrice.setText("0.00");
        }
        if (cbTax.isChecked()) {
            Toast.makeText(MainActivity.this, "checked include Tax.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "unchecked include Tax.", Toast.LENGTH_LONG).show();
        }
    }

    private void calculate(double price, int discout) {
        double total = 0;
        total = (price*(100-discout))/100;
        if (cbTax.isChecked()) {
            total *= 1.07;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        tvTotalPrice.setText(decimalFormat.format(total));
    }

    private void resetValue(){
        tvTotalPrice.setText("0.00");
        etOriPrice.setText("");
        sbPercentOff.setProgress(25);
        tvPercebtOff.setText("25%");
    }

}
