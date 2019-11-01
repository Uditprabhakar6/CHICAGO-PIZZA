package in.ac.sharda.chicagopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    Button sign_in,sign_up;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        sign_in = (Button) findViewById(R.id.signin);
        sign_up = (Button) findViewById(R.id.signup);

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinpage = new Intent(getApplicationContext(),login.class);
                startActivity(signinpage);
                finish();

            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signuppage = new Intent(getApplicationContext(),signup.class);
                startActivity(signuppage);
                finish();

            }
        });






    }
}
