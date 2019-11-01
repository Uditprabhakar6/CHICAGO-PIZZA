package in.ac.sharda.chicagopizza;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.google.firebase.auth.FirebaseAuth.getInstance;


public class signup extends AppCompatActivity {

    EditText txt_fullName,txt_Username,txt_email,txt_password;
    Button btn_register;
    RadioButton radioGenderMale, radioGenderFemale;
    DatabaseReference databaseReference;
    String Gender ="";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        txt_fullName = (EditText)findViewById(R.id.fullname);
        txt_Username = (EditText)findViewById(R.id.username);
        txt_email = (EditText)findViewById(R.id.email);
        txt_password = (EditText)findViewById(R.id.password);
        btn_register = (Button)findViewById(R.id.register);
        radioGenderMale = (RadioButton)findViewById(R.id.male);
        radioGenderFemale = (RadioButton)findViewById(R.id.female);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("student");


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String fullName = txt_fullName.getText().toString();
                final String Username = txt_Username.getText().toString();
                final String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();

                if(radioGenderMale.isChecked()){

                    Gender = "Male";
                }
                if(radioGenderFemale.isChecked()){
                    Gender = "Female";
                }

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(signup.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(Username)){
                    Toast.makeText(signup.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(signup.this, "Please Enter FullName", Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(signup.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(getApplicationContext(),login.class));
                                    student information = new student(
                                            fullName,
                                            Username,
                                            email,
                                            Gender
                                    );

                                    FirebaseDatabase.getInstance().getReference("Customer")
                                            .child(getInstance().getCurrentUser().getUid())
                                            .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(signup.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext() ,MainActivity.class));

                                        }
                                    });

                                } else {
                                    Toast.makeText(signup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }
        });


    }

}
