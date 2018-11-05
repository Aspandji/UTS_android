package aspandji.com.uts_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView txtUsername;
    Button btnDatabase;
    SessionManagement sessionManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManagement = new SessionManagement(MainActivity.this);
        btnDatabase = (Button) findViewById(R.id.btnDatabase);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        if(sessionManagement.isLoggedIn())
        {
            HashMap<String,String> loginUser = sessionManagement.getUserInformation();
            txtUsername.setText(loginUser.get(sessionManagement.KEY_EMAIL));
        }
//        btnLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sessionManagement.logoutUser();
//            }
        btnDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahMahasiswaActivity.class));
            }
        });

    }

    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(),
                HomeScreenActivity.class);
        startActivity(mIntent);
    }
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(this, "App onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "App onRestart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "App onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "App onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "App onDestroy", Toast.LENGTH_SHORT).show();
    }
}
