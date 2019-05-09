package th.ac.su.ict.thanapa.customlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] studentName = new String[]{"Thanapa Chanatipakorn", "Tunchanok Cherngkeeree"};
    String[] studentId = new String[]{"13600179", "13600181"};
    int[] studentImage = new int[]{
            R.drawable.photo_camera, R.drawable.transform_box
    };

    ListView list;

    private ArrayList<String> toDoItem;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------- SET VALUE ON UI ----------------------------------------------
        list = findViewById(R.id.list);

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", studentName[i]);
            hm.put("listview_discription", studentId[i]);
            hm.put("listview_image", Integer.toString(studentImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};
        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.layout_customlist, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list);
        androidListView.setAdapter(simpleAdapter);
    }
}
