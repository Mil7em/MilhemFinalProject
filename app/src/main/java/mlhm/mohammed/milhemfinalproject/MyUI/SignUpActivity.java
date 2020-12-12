package mlhm.mohammed.milhemfinalproject.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import mlhm.mohammed.milhemfinalproject.MyUtils.MyValidations;
import mlhm.mohammed.milhemfinalproject.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFirstName,etLastName,etPhone,etEmail2,etPassWord2,etPassWord1;
    /**
     * כפתור רשימת ניתונים
     */
    private Button btnSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etFirstName=findViewById(R.id.etFirstName);
        etLastName=findViewById(R.id.etLastName);
        etPhone=findViewById(R.id.etPhone);
        etEmail2=findViewById(R.id.etEmail2);
        etPassWord2=findViewById(R.id.etPassWord2);
        etPassWord1=findViewById(R.id.etPassWord1);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     *המתודה בודקת תקינות טופס ההרשמה ואם תקין מבצעת הרשמה
     */
    private void validateForm(){
        String passw2 = etPassWord2.getText().toString();
        String passw1 = etPassWord1.getText().toString();
        String fname = etFirstName.getText().toString();
        String lname = etLastName.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail2.getText().toString();

        boolean isOk=true;
        if(fname.length()<2)
        {
            isOk=false;
            etFirstName.setError("At least to letters");
        }
        if (email.length()<5 || email.indexOf('@')==0 || email.indexOf('@')>=email.length()-2 ||
                email.indexOf('.') == 0 || email.indexOf('.') > email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@'))
        {
            isOk=false;
            etEmail2.setError("Wrong Email Address Please Rewrite");
        }
        if(passw1.equals(passw2)==false)
        {
            isOk=false;
            etPassWord2.setError("passwords must be the same!");
        }
        else {
            MyValidations myValidations = new MyValidations();
            if (myValidations.validatepassword(passw1) == false) {
                isOk = false;
                etPassWord1.setError("Invalid Password!");
            }
        }

        if(isOk)
        {
            //toDo: create account and return to sign in screen/close this screen
            createNewAccount(email,passw1,fname,lname,phone);
        }


    }

    /**
     *
     * @param email
     * @param passw1
     * @param fname
     * @param lname
     * @param phone
     */
    private void createNewAccount(String email, String passw1, String fname, String lname, String phone)
    {//1

        // FirebaseAuth auth=FirebaseAuth.getInstance();
        //
        //2
        OnCompleteListener<AuthResult> listener=new OnCompleteListener<AuthResult>() {
            //RESPONS
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUpActivity.this,"Successfuly Signing up",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Signing up, Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    etEmail2.setError("Signing up, Failed"+task.getException().getMessage());
                }

            }
        };
        //3
        FirebaseAuth.createUserWithEmailAndPassword(email,passw1).addOnCompleteListener(listener);
    }
}