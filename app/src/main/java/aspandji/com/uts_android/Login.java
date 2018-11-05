package aspandji.com.uts_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    Button buttonLogin;
    SessionManagement sessionManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManagement = new SessionManagement(Login.this);
        edtEmail = (EditText) findViewById(R.id.editEmail);
        edtPassword = (EditText) findViewById(R.id.editPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        if (sessionManagement.isLoggedIn()) {
            goToActivity();
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if(email.equals("") || email.trim().isEmpty() || password.equals("") || password.trim().isEmpty() ) {
                    Toast.makeText(Login.this,"Username Password harus diisi",Toast.LENGTH_LONG).show();
                }
                else
                {
                    sessionManagement.createLoginSession(email, password);
                    goToActivity();
                }
            }
        });
    }
    private void goToActivity(){
        Intent mIntent = new Intent(getApplicationContext(),
                MainActivity.class);
        startActivity(mIntent);
    }
}
