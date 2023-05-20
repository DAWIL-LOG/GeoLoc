package log.dnn.geoloc.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import log.dnn.geoloc.AuthActivity;
import log.dnn.geoloc.R;
import log.dnn.geoloc.models.User;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private EditText txtUserName,txtEmail,txtPassword;
    private TextView txtError;
    private Button btnSignUp,btnSignIn;
    private String messageError = "";
    private FirebaseAuth mAuth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);


        txtUserName = view.findViewById(R.id.txt_username);
        txtEmail = view.findViewById(R.id.rtxt_email);
        txtPassword = view.findViewById(R.id.txt_password);
        btnSignUp = view.findViewById(R.id.btn_signup);
        btnSignIn = view.findViewById(R.id.btn_signin);
        txtError = view.findViewById(R.id.txt_error);

        btnSignUp.setOnClickListener(this);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AuthActivity)getActivity()).navHostController.navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        String username, email, password;

        username = txtUserName.getText().toString();
        email = txtEmail.getText().toString();
        password = txtPassword.getText().toString();

        if (isValidated(username,email,password)){

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                User user = new User(username, email);

                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(user).addOnCompleteListener(task1 -> {
                                                    if(task1.isSuccessful()){
                                                        Toast.makeText(getActivity(), R.string.auth_success
                                                                , Toast.LENGTH_SHORT).show();
                                                        ((AuthActivity)getActivity()).navHostController
                                                                .navigate(R.id.action_registerFragment_to_loginFragment);
                                                    }else{
                                                        Toast.makeText(getContext(), R.string.auth_failed, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                            } else {
                                // If sign in fails, display a message to the user
                                Toast.makeText(getActivity(), R.string.auth_failed, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }else{
            txtError.setVisibility(TextView.VISIBLE);
            txtError.setText(messageError);
        }
    }

    private boolean isValidated(String username, String email, String password) {

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            messageError = MessageError.REGISTER_REQUIRED;
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            messageError= MessageError.REGISTER_EMAIL_NOT_MATCH;
            return false;
        }

        // 8 character
        if(password.length()<= 8)
        {
            messageError = MessageError.REGISTER_PASSWORD_NOT_MATCH_LENGHT;
            return false;
        }
        //number
        if(!password.matches("(.*[0-9].*)"))
        {
            messageError = MessageError.REGISTER_PASSWORD_NOT_MATCH_NUMBER;
            return false;
        }
        //upper case
        if(!password.matches("(.*[A-Z].*)"))
        {
            messageError = MessageError.REGISTER_PASSWORD_NOT_MATCH_UPPERCASE;
            return false;
        }
        //symbol
        if(!password.matches("^(?=.*[!@-_]).*$")){
            messageError = MessageError.REGISTER_PASSWORD_NOT_MATCH_SYMBOL;
            return false;
        }

        return true;
    }
}