package com.example.filmtastic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    private Button button;
    private EditText user, password;
    private ProgressBar loginProgress;
    private FirebaseAuth mAuth;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        user=findViewById(R.id.userIdSign);
        password=findViewById(R.id.parolaSign);

        button= findViewById(R.id.button_log_summit);
        loginProgress=findViewById(R.id.progressBar2);
        mAuth=FirebaseAuth.getInstance();

        loginProgress.setVisibility(View.INVISIBLE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.VISIBLE);
                button.setVisibility(View.INVISIBLE);

                final String mail = user.getText().toString();
                final String pass = password.getText().toString();

                if(mail.isEmpty()||pass.isEmpty()){
                    showMessage("Verify all fields!");
                    button.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else{
                    signIn(mail,pass);
                }
            }
        });

    }

    private void signIn(String mail, String pass) {

        mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    loginProgress.setVisibility(View.INVISIBLE);
                    button.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else
                    showMessage(task.getException().getMessage());
                    button.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
            }
        });

    }

    private void updateUI() {
        intent= new Intent(this,MainPage.class);
        intent.putExtra("EXTRA_USER", String.valueOf(user));
        startActivity(intent);
        finish();

    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(),  s,Toast.LENGTH_LONG).show();
    }


}