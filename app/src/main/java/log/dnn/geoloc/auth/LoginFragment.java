package log.dnn.geoloc.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import log.dnn.geoloc.AuthActivity;
import log.dnn.geoloc.MainActivity;
import log.dnn.geoloc.R;
import log.dnn.geoloc.SplashScreenActivity;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn, btnSignUp;
    private TextView txtError;
    private String messageError;
    private FirebaseAuth mAuth;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //Intent intent= new Intent(AuthActivity.this, MainActivity.class);
            //startActivity(intent);

            //getActivity().finish();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtEmail = view.findViewById(R.id.ltxt_email);
        txtPassword = view.findViewById(R.id.txt_password);
        btnSignIn = view.findViewById(R.id.btn_signin);
        btnSignUp = view.findViewById(R.id.btn_signup);
        txtError = view.findViewById(R.id.txt_error);

        btnSignIn.setOnClickListener(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AuthActivity)getActivity()).navHostController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

        String email = txtEmail.getText().toString();
        String password = txtPassword.getText().toString();


        txtError.setVisibility(TextView.VISIBLE);
        if(isValidated()){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                //FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent= new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                txtError.setText(MessageError.LOGIN_USER_NOT_FOUND);
                                Toast.makeText(getContext(), R.string.auth_failed,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            txtError.setText(messageError);
        }
    }

    private boolean isValidated() {
        if(txtEmail.getText().length()== 0 || txtPassword.getText().length()==0){
            messageError = MessageError.LOGIN_REQUIRED;
            return false;
        }
        return true;
    }
}