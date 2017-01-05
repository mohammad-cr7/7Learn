package com.example.sshahini.myapplication.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.sshahini.myapplication.ApiService;
import com.example.sshahini.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText firstNameEditText=(EditText)findViewById(R.id.text_first_name);
        final EditText lastNameEditText=(EditText)findViewById(R.id.text_last_name);
        final EditText ageEditText=(EditText)findViewById(R.id.text_age);

        final CheckBox cssCheckBox=(CheckBox)findViewById(R.id.css_checkbox);
        final CheckBox htmlCheckBox=(CheckBox)findViewById(R.id.html_checkbox);
        final CheckBox javaCheckBox=(CheckBox)findViewById(R.id.java_checkbox);

        final RadioButton maleRadio=(RadioButton)findViewById(R.id.male_radio);
        RadioButton femaleRadio=(RadioButton)findViewById(R.id.female_radio);

        final SwitchCompat hasJobSwitchCompat=(SwitchCompat)findViewById(R.id.has_job_switch);

        Button signUpButton=(Button)findViewById(R.id.button_signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService apiService=new ApiService(SignUpActivity.this);

                JSONObject requestJsonObject=new JSONObject();
                try {
                    requestJsonObject.put("first_name",firstNameEditText.getText().toString());
                    requestJsonObject.put("last_name",lastNameEditText.getText().toString());
                    requestJsonObject.put("has_job",hasJobSwitchCompat.isChecked());
                    requestJsonObject.put("age",ageEditText.getText().toString());

                    JSONArray skillsJsonArray=new JSONArray();
                    if (cssCheckBox.isChecked()){
                        skillsJsonArray.put("Css");
                    }

                    if (htmlCheckBox.isChecked()){
                        skillsJsonArray.put("Html");
                    }

                    if (javaCheckBox.isChecked()){
                        skillsJsonArray.put("java");
                    }

                    if (maleRadio.isChecked()){
                        requestJsonObject.put("gender","male");
                    }else {
                        requestJsonObject.put("gender","female");
                    }

                    requestJsonObject.put("skills",skillsJsonArray);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
