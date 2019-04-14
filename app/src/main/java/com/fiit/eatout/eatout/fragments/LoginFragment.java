package com.fiit.eatout.eatout.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fiit.eatout.eatout.NavigationHost;
import com.fiit.eatout.eatout.R;
import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.SQLLogin;

/**
 * Fragment representing the login screen for Shrine.
 */
public class LoginFragment extends Fragment {



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.eout_login_fragment, container, false);

        final TextInputLayout emailTextInput = view.findViewById(R.id.email_text_input);
        final TextInputEditText emailEditText = view.findViewById(R.id.email_edit_text);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_edit_text);
        MaterialButton nextButton = view.findViewById(R.id.next_button);
        MaterialButton registerButton = view.findViewById(R.id.register_button);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((NavigationHost) getActivity()).navigateTo(new RegistrationFragment(), true); // Navigate to the next Fragment
            }
        });

        // Set an error if the password is less than 8 characters.
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //test mode - omitting login

                ((NavigationHost) getActivity()).navigateTo(new CafeGridFragment(), false); // Navigate to the next Fragment

                //release mode - fully functional login
                /*
                if (!isPasswordValid(passwordEditText.getText()))
                {
                    passwordTextInput.setError(getString(R.string.eout_error_password));
                }
                else if (!isLoginSuccessful(emailEditText.getText(), passwordEditText.getText())) {
                    emailTextInput.setError(getString(R.string.eout_error_login));
                    passwordTextInput.setError(getString(R.string.eout_error_login));
                } else {
                    emailTextInput.setError(null); // Clear the error
                    passwordTextInput.setError(null); // Clear the error
                    ((NavigationHost) getActivity()).navigateTo(new CafeGridFragment(), false); // Navigate to the next Fragment
                }
                */
            }
        });

        // Clear the email error once something typed.
        emailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isValidEmailAddress(emailEditText.getText())) {
                    emailTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });

        // Clear the password error once more than 8 characters are typed.
        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError(null); //Clear the error
                }
                return false;
            }
        });
        return view;
    }

    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */

    public static boolean isValidEmailAddress(Editable text){
        return text.length() > 0;
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    private boolean isLoginSuccessful(Editable email, Editable password) {

        SQLLogin Login;
        Login = new SQLLogin();
        Login.start(email.toString(), password.toString());

        try {
            Login.join();
        }
        catch (InterruptedException ie)
        {
            Log.e("pass 0", ie.getMessage());
        }

        return global.userID != "-1";
    }
}
