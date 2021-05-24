package com.example.filmtastic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingPage extends AppCompatActivity {
    private Button button1;
    private Button button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        button1= (Button) findViewById(R.id.button_log);
        button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLoginPage();
                }
        });
        button2= (Button) findViewById(R.id.button_sign);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignInPage();
            }
        });

        }

        public void openLoginPage(){
            Intent intent=new Intent(LandingPage.this,LoginPage.class);
            startActivity(intent);
        }

       public void openSignInPage(){
            Intent intent=new Intent(LandingPage.this,SignInPage.class);
            startActivity(intent);
        }

        public void submitbuttonHandler(View view) {
            //Decide what happens when the user clicks the submit button

        }

    }