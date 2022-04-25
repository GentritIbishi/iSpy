package com.gentritibishi.ispy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    Button bt_logout;
    ListView lvChildren;
    String jsonObj = null;
    ArrayList<Children> childrenArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jsonObj = extras.getString("jsonObj");
        }

        bt_logout = findViewById(R.id.bt_logout);
        lvChildren = findViewById(R.id.lvChildren);
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
            }
        });

        childrenArrayList = new ArrayList<>();
        try {
            loadDatainListview();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadDatainListview() throws JSONException {
        if(jsonObj == null && jsonObj.isEmpty()){
            //do nothing
        }else {
            JSONObject jsonObject = new JSONObject(jsonObj);
            String status = jsonObject.getString("status");
            Integer pairId = jsonObject.getInt("pairId");
            String token = jsonObject.getString("token");
            String nameOfDevice = jsonObject.getString("name");
            Integer userId = jsonObject.getInt("userId");
            JSONArray childrenArray = jsonObject.getJSONArray("children");

            for(int i=0;i<childrenArray.length();i++)
            {
                //load data as obj
                JSONObject obj = childrenArray.getJSONObject(i);
                Integer id = obj.getInt("id");
                String name = obj.getString("name");
                Children children = new Children(id, name);
                childrenArrayList.add(children);

            }

            ChildrenListAdapter childrenListAdapter = new ChildrenListAdapter(DashboardActivity.this, childrenArrayList);
            lvChildren.setAdapter(childrenListAdapter);
        }
    }

}