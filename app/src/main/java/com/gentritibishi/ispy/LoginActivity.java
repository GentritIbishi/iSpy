package com.gentritibishi.ispy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText et_usernameEmailPhone, et_password;
    CheckBox ch_remember;
    Button bt_signIn, bt_createNewAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_usernameEmailPhone = findViewById(R.id.et_usernameEmailPhone);
        et_password = findViewById(R.id.et_password);
        ch_remember = findViewById(R.id.ch_remember);
        bt_signIn = findViewById(R.id.bt_signIn);
        bt_createNewAccount = findViewById(R.id.bt_createNewAccount);

        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String checkbox = preferences.getString("remember", "");
        if(checkbox.equals("true")){
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            startActivity(intent);
        }else if(checkbox.equals("false")){
            //do nothing, user see that need to login
        }

        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Allow2 allow2 = Allow2.getShared();
                allow2.env = Allow2EnvType.SANDBOX;
                String deviceName = Settings.Global.getString(getContentResolver(), Settings.Global.DEVICE_NAME);
                allow2.pair(et_usernameEmailPhone.getText().toString(), et_password.getText().toString(), deviceName);

//                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
//                startActivity(intent);
            }
        });

        ch_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();

                }else if(!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();

                }
            }
        });

    }
}