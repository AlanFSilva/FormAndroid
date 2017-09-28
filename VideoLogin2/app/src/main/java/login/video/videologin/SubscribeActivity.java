package login.video.videologin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class SubscribeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.login_btn){
            String email = ((EditText)findViewById(R.id.email_login)).getText().toString();
            String senha = ((EditText)findViewById(R.id.password_login)).getText().toString();

            //TODO submeter dados para cadastrar usuário no servidor
        }
    }
}
