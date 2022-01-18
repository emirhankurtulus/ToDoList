package com.emirhan.topnote.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.emirhan.topnote.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {
private ActivitySignupBinding binding;
private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
auth = FirebaseAuth.getInstance();


    }
    public void sign (View View)
    {
        Intent otherpage = new Intent(signup.this, login.class);
        startActivity(otherpage);
    }
    public void signup (View View)
    {
        String email = binding.mail.getText().toString();
        String password = binding.password.getText().toString();
        String confirmpassword = binding.confirmpassword.getText().toString();

if(email.equals("")  || password.equals("")  || confirmpassword.equals("") )
{
    binding.error.setText("Email and password cannot be empty");
}
else if (confirmpassword.equals(password)) {
    auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            Toast.makeText(signup.this, "Congrats! \uD83E\uDD73 you are ready for login now.", Toast.LENGTH_LONG).show();
            Intent otherpage = new Intent(signup.this, login.class);
            startActivity(otherpage);
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(signup.this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    });

}
else
{
    binding.error.setText("Passwords must be same");
}
    }


}