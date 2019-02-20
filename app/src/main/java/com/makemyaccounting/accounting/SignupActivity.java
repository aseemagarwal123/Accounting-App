package com.makemyaccounting.accounting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.makemyaccounting.dashboard.R;

public class SignupActivity extends AppCompatActivity {
    EditText username,email,password1,password2;
    SharedPreferences sp;
    final String MYPREFER = "signup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sp = getSharedPreferences( MYPREFER, Context.MODE_PRIVATE);
        username=(EditText)findViewById(R.id.user_signup);
        email=(EditText)findViewById(R.id.email_signup);
        password1=(EditText)findViewById(R.id.pass_signup);
        password2=(EditText)findViewById(R.id.conpass_signup);

    }

    public void toreg(View view) {

        String user = username.getText().toString().trim();
        String email_signup = email.getText().toString().trim();
        String password1_signup = password1.getText().toString().trim();
        String password2_signup = password2.getText().toString().trim();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("user",user);
        editor.putString("email",email_signup);
        editor.putString("password1",password1_signup);
        editor.putString("password2",password2_signup);
        editor.commit();







        Intent intent2=new Intent(getApplicationContext(),RegistrationActivity.class);
        startActivity(intent2);
    }
}
