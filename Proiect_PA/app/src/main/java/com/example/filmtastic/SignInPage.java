package com.example.filmtastic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;

public class SignInPage extends AppCompatActivity {
    private Button button;
    ImageView photo;
    static int PReqCode=1;
    static int REQUESCODE=1;
    Uri pickedImage;

    private EditText user, password, first_name, last_name, date;
    private ProgressBar loadingProgress;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        last_name=findViewById(R.id.numeSign);
        first_name=findViewById(R.id.prenumeSign);
        date=findViewById(R.id.datanSign);
        user=findViewById(R.id.userIdSign);
        password=findViewById(R.id.parolaSign);
        loadingProgress=findViewById(R.id.progressBar);

        loadingProgress.setVisibility(View.INVISIBLE);

        mAuth=FirebaseAuth.getInstance();

        button= (Button) findViewById(R.id.button_sign_summit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                final String nume= last_name.getText().toString();
                final String prenume=first_name.getText().toString();
                final String daten=date.getText().toString();
                final String userID= user.getText().toString();
                final String parola=password.getText().toString();

                if(nume.isEmpty() || prenume.isEmpty() || daten.isEmpty() || userID.isEmpty() || parola.isEmpty()){
                    showMessage("Verify all fields!");
                    button.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.VISIBLE);
                }
                else {
                    CreateAccount(nume,prenume,daten,userID,parola);
                }
                openMainPage();
            }
        });

        photo = findViewById(R.id.userPhoto);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT>=22){
                    checkAndRequestPermission();
                }
                else{
                    openGallery();
                }
            }
        });



    }

    private void CreateAccount(String nume, String prenume, String daten, String userID, String parola) {

        mAuth.createUserWithEmailAndPassword(userID,parola).
                addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showMessage("Account created.");
                            updateUserInfo(nume,prenume,daten,pickedImage,mAuth.getCurrentUser());
                        }
                        else{
                            showMessage("Account creation failed"+ task.getException().getMessage());
                            button.setVisibility(View.VISIBLE);
                            loadingProgress.setVisibility(View.INVISIBLE);
                        }
                    }
                });

    }

    private void updateUserInfo(String nume, String prenume, String daten, Uri pickedImage, FirebaseUser currentUser) {
        StorageReference storageReference= FirebaseStorage.getInstance().getReference().child("users_photos");
        StorageReference imageFilePath = storageReference.child(pickedImage.getLastPathSegment());

        imageFilePath.putFile(pickedImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nume).setPhotoUri(uri).build();
                        currentUser.updateProfile(profileUpdate).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    showMessage("Register Complete");
                                    updateUI();
                                }
                            }
                        });
                    }
                });
            }
        });
    }

    private void updateUI() {
        Intent homeActivity = new Intent(SignInPage.this,MainPage.class);
        startActivity(homeActivity);
        finish();
    }

    private void showMessage(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private void openGallery() {
        Intent galleryIntent= new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent,REQUESCODE);
    }


    private void checkAndRequestPermission() {
        if(ContextCompat.checkSelfPermission(SignInPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(SignInPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Toast.makeText(SignInPage.this, "Accept for required permission",Toast.LENGTH_SHORT).show();
            }
            else{
                ActivityCompat.requestPermissions(SignInPage.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        }else{
            openGallery();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK && requestCode == REQUESCODE && data != null){
            //user picked an image
            pickedImage=data.getData();
            photo.setImageURI(pickedImage);
        }
    }

    public void openMainPage(){
        Intent intent=new Intent(SignInPage.this,MainPage.class);
        startActivity(intent);
    }


}