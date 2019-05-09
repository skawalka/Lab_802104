package th.ac.su.ict.thanapa.firebasedemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etId, etGpa, etPhone, etAcademicYear, etTerm;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflate

        etName = findViewById(R.id.etName);
        etId = findViewById(R.id.etId);
        etGpa = findViewById(R.id.etGpa);
        etAcademicYear = findViewById(R.id.etAcademicYear);
        etTerm = findViewById(R.id.etTerm);
        etPhone = findViewById(R.id.etPhone);
        btnSave = findViewById(R.id.btnSave);

        //DatabaseReference reference = database.getReference("firstname");
        //reference.setValue("Thanapa1998");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkEmpty(etName.getText().toString()) && checkEmpty(etId.getText().toString()) &&
                        checkEmpty(etGpa.getText().toString()) && checkEmpty(etPhone.getText().toString()) &&
                        checkEmpty(etAcademicYear.getText().toString()) && checkEmpty(etTerm.getText().toString())){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
//                    DatabaseReference refId = database.getReference("Students").child(etId.getText().toString());
//                    DatabaseReference refName = refId.child("Name");
//                    DatabaseReference refGpa = refId.child("GPA");
//                    DatabaseReference refPhone = refId.child("Phone");
//                    refName.setValue(etName.getText().toString());
//                    refGpa.child(etAcademicYear.getText().toString()).child(etTerm.getText().toString()).setValue(Double.parseDouble(etGpa.getText().toString()));
//                    refPhone.setValue(etPhone.getText().toString());
                    DatabaseReference refStudent = database.getReference("Students");
                    Map student = new HashMap();
                    Map gpaYear = new HashMap();
                    Map gpaTerm = new HashMap();
                    student.put("Name", etName.getText().toString());
                    gpaTerm.put(etTerm.getText().toString(), Double.parseDouble(etGpa.getText().toString()));
                    gpaYear.put(etAcademicYear.getText().toString(), gpaTerm);
                    student.put("GPA", gpaYear);
                    refStudent.child(etId.getText().toString()).setValue(student);
                    Toast.makeText(MainActivity.this, "Successed", Toast.LENGTH_LONG).show();
                    resetValue();
                }
            }
        });



        etPhone.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    hideKeyboard(v);
                }
                return false;
            }
        });

    }
    private void resetValue(){
        etName.setText("");
        etId.setText("");
        etGpa.setText("");
        etPhone.setText("");
        etAcademicYear.setText("");
        etTerm.setText("");
    }
    private boolean checkEmpty(String value){
        if (!value.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    private void hideKeyboard(View vie){
        InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(vie.getWindowToken(),0);
    }
}
