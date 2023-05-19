package log.dnn.geoloc.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import log.dnn.geoloc.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnSignIn;
    private TextView txtError;
    private String messageError;

    private String[][] USERS = {{"willnono@gmail.com", "12345"},{"ngahemeniw@gmail.com", "12345"}};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        txtEmail = view.findViewById(R.id.txt_email);
        txtPassword = view.findViewById(R.id.txt_password);
        btnSignIn = view.findViewById(R.id.btn_signin);
        txtError = view.findViewById(R.id.txt_error);

        btnSignIn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if(isValidated()){

        }else{
            txtError.setVisibility(TextView.VISIBLE);
            txtError.setText(messageError);
        }
    }

    private boolean isValidated() {
        if(txtEmail.getText().length()== 0 && txtPassword.getText().length()==0){
            messageError = MessageError.LOGIN_REQUIRED;
            return true;
        }
        else {
            if (isFound(txtEmail.getText().toString(), txtPassword.getText().toString())){
                messageError = "it is OK";
                return true;
            }else{
                messageError = MessageError.LOGIN_USER_NOT_FOUND;
                return false;
            }

        }

    }

    private boolean isFound(String email, String password) {
        for (String[] user:USERS) {
            if(email == user[0] && password == user[1])
                return true;
        }
        return false;
    }
}