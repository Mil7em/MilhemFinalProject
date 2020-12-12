package mlhm.mohammed.milhemfinalproject.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mlhm.mohammed.milhemfinalproject.MainActivity;
import mlhm.mohammed.milhemfinalproject.MyUtils.MyValidations;
import mlhm.mohammed.milhemfinalproject.R;

//1 XML
public class LogInActivity extends AppCompatActivity {
    //2
    private EditText etEmail,etPassword;
    private Button btnLogin,btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //11. check if I signed in before
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null)//user signed in before
        {
            Intent i= new Intent(getBaseContext(), MainActivity.class);
            finish();
            startActivity(i);
        }
        setContentView(R.layout.activity_log_in);
        //3
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        //4
        btnLogin.setOnClickListener(new View().OnClickListener() {

            public void onClick(View v); {
                //5
                validateForm();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(i);
            }
        });
    }
    //5
    private void validateForm(){
        //1
        String email=etEmail.getText().toString();
        String passw=etPassword.getText().toString();
        //2
        boolean isOk=true;
        //3
        if (email.length()<5 || email.indexOf('@')==0 || email.indexOf('@')>=email.length()-2 ||
                email.indexOf('.') == 0 || email.indexOf('.') > email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@')) {
            isOk = false;
            etEmail.setError("Wrong Email Address Please Rewrite");
        }
        //4
        MyValidations myValidations = new MyValidations();
        if (myValidations.validatepassword(passw) == false) {
            isOk = false;
            etPassword.setError("Invalid Password!");
        }
        //5
        if (isOk){
            logIn(email,passw);
        }
    }
    private void logIn(String email,String passw){
        //6
        FirebaseAuth auth=FirebaseAuth.getInstance();
        //7
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //8
                if(task.isSuccessful()){
                    //9
                    Intent i=new Intent(LogInActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else {
                    //10
                    Toast.makeText(LogInActivity.this,"Faild",Toast.LENGTH_SHORT).show();
                    etEmail.setError(task.getException().getMessage());
                }
            }
        });
    }
}