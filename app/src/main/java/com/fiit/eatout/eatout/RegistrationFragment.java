package com.fiit.eatout.eatout;

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

import com.fiit.eatout.eatout.globalValues.global;
import com.fiit.eatout.eatout.network.SQLLogin;
import com.fiit.eatout.eatout.network.SQLRegistration;

/**
 * Fragment representing the login screen for Shrine.
 */
public class RegistrationFragment extends Fragment {



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.eout_registration_fragment, container, false);

        final TextInputLayout nameTextInput = view.findViewById(R.id.name_reg_text_input);
        final TextInputEditText nameEditText = view.findViewById(R.id.name_reg_edit_text);

        final TextInputLayout surnameTextInput = view.findViewById(R.id.surname_reg_text_input);
        final TextInputEditText surnameEditText = view.findViewById(R.id.surname_reg_edit_text);

        final TextInputLayout emailTextInput = view.findViewById(R.id.email_reg_text_input);
        final TextInputEditText emailEditText = view.findViewById(R.id.email_reg_edit_text);

        final TextInputLayout passwordTextInput = view.findViewById(R.id.password_reg_text_input);
        final TextInputEditText passwordEditText = view.findViewById(R.id.password_reg_edit_text);
        MaterialButton registrationButton = view.findViewById(R.id.registration_button);


        // Set an error if the password is less than 8 characters.
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPasswordValid(passwordEditText.getText()))
                {
                    passwordTextInput.setError(getString(R.string.eout_error_password));
                }
                else if (!isRegistrationSuccessful(nameEditText.getText(), surnameEditText.getText(), emailEditText.getText(), passwordEditText.getText())) {
                    emailTextInput.setError(getString(R.string.eout_error_registration));
                    emailTextInput.setError(getString(R.string.eout_error_registration));
                    emailTextInput.setError(getString(R.string.eout_error_registration));
                    passwordTextInput.setError(getString(R.string.eout_error_registration));
                } else {
                    emailTextInput.setError(null); // Clear the error
                    emailTextInput.setError(null); // Clear the error
                    emailTextInput.setError(null); // Clear the error
                    passwordTextInput.setError(null); // Clear the error
                    ((NavigationHost) getActivity()).navigateTo(new LoginFragment(), false); // Navigate to the next Fragment
                }
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

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 8;
    }

    private boolean isRegistrationSuccessful(Editable name, Editable surname, Editable email, Editable password) {
        global.regSuccess = false;
        SQLRegistration Reg;
        Reg = new SQLRegistration();
        Reg.start(name.toString(),surname.toString(),email.toString(),password.toString());

        try {
            Reg.join();
        }
        catch (InterruptedException ie)
        {
            Log.e("pass 0", ie.getMessage());
        }

        return global.regSuccess;
    }
}
