package login.video.videologin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            String email = ((EditText)findViewById(R.id.email_login)).getText().toString();
            String senha = ((EditText)findViewById(R.id.password_login)).getText().toString();
            boolean manterOn = ((CheckBox)findViewById(R.id.keepOn_chkBox)).isChecked();

            //TODO verigficar dados e ir para dashboard
        }
    }
}
