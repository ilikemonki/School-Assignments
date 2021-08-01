package com.example.hm_1;

// Josue Crandall and Meng Cha
// CECS 453 Mobile Apps
// 2/20/20

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;
    private EditText mEditTextPasswordRetyped;
    private EditText mEditTextEmail;
    private EditText mEditTextPhone;
    private Button mButtonSignUp;

    private String mAccountsKey;
    private String mNewUsernameKey;
    private String mToastUnfilledFields;
    private String mToastDuplicateUsername;
    private String mToastPasswordsNotMatched;
    private String mToastEmailFormatInvalid;
    private String mToastPhoneFormatInvalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loadResources();

        //Listener for Sign Me Up button
        mButtonSignUp.setOnClickListener((button) -> {
            //Get user's sign up information
            HashMap<String,String> accounts = loadAccounts();
            String name = mEditTextUsername.getText().toString();
            String pass = mEditTextPassword.getText().toString();
            String passRetyped = mEditTextPasswordRetyped.getText().toString();
            String email = mEditTextEmail.getText().toString();
            String phone = mEditTextPhone.getText().toString();

            //Check for validations
            if(accounts == null) { failedSignUpToast("THIS IS A PROGRAMMING ERROR"); }
            else if(!validateAllFieldsFilled(name, pass, passRetyped, email, phone))
            { failedSignUpToast(mToastUnfilledFields); }
            else if(!validateUniqueUsername(accounts, name))
            { failedSignUpToast(mToastDuplicateUsername); }
            else if(!validatePasswordsMatch(pass, passRetyped))
            { failedSignUpToast(mToastPasswordsNotMatched); }
            else if(!validateEmailFormatting(email))
            { failedSignUpToast(mToastEmailFormatInvalid); }
            else if(!validatePhoneFormatting(phone))
            { failedSignUpToast(mToastPhoneFormatInvalid); }
            //If everything passes, add account to data and send user to login page.
            else {
                accounts.put(mEditTextUsername.getText().toString(),
                        mEditTextPassword.getText().toString());

                Intent intent = new Intent();
                //Prepare to send results back
                intent.putExtra(mAccountsKey, accounts);
                intent.putExtra(mNewUsernameKey, name);

                //Send results
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    //Initialize variables
    void loadResources() {
        Resources resources = getResources();
        mEditTextUsername = findViewById(R.id.input_new_username);
        mEditTextPassword = findViewById(R.id.input_new_password);
        mEditTextPasswordRetyped = findViewById(R.id.input_new_password_retype);
        mEditTextEmail = findViewById(R.id.input_new_email);
        mEditTextPhone = findViewById(R.id.input_new_phone);
        mButtonSignUp = findViewById(R.id.button_new_signup);
        mAccountsKey = resources.getString(R.string.accounts_key);
        mNewUsernameKey = resources.getString(R.string.new_username_key);
        mToastUnfilledFields = resources.getString(R.string.toast_unfilled_fields);
        mToastDuplicateUsername = resources.getString(R.string.toast_duplicate_username);
        mToastPasswordsNotMatched = resources.getString(R.string.toast_passwords_dont_match);
        mToastEmailFormatInvalid = resources.getString(R.string.toast_email_formatting);
        mToastPhoneFormatInvalid = resources.getString(R.string.toast_phone_formatting);
    }

    //Load account data from main activity
    HashMap<String,String> loadAccounts() {
        return (HashMap<String, String>) getIntent().getSerializableExtra(mAccountsKey);
    }

    //Pop up toast for failed sign up
    void failedSignUpToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }

    //Check if all fields are filled
    boolean validateAllFieldsFilled(String username, String password, String passwordRetyped,
                                    String email, String phone)
    {
        if(username.isEmpty() || password.isEmpty() || passwordRetyped.isEmpty() ||
                email.isEmpty() || phone.isEmpty()) return false;
        else { return true; }
    }

    //Check for unique username
    boolean validateUniqueUsername(HashMap<String,String> accounts, String username) {
        if(accounts.containsKey(username)) return false;
        else { return true; }
    }

    //Check pw and retype pw
    boolean validatePasswordsMatch(String password, String passwordRetyped) {
        if(password.compareTo(passwordRetyped) != 0) return false;
        else { return true; }
    }

    //Check email format
    // https://stackoverflow.com/questions/12947620/email-address-validation-in-android-on-edittext
    boolean validateEmailFormatting(String email) {
        if(email.isEmpty()) return false;
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { return false; }
        else { return true; }
    }

    // Correctly doing this seems to involve a library, the basic regex patterns
    // don't seem to cover all valid phones, or reject all invalid phone numbers.
    // So for now I'm just doing this simple manual validation.
    //Check phone format
    boolean validatePhoneFormatting(String phone) {
        // Validates form: (123)456-7890
        int index = 0;

        if(phone.length() != 13) { return false; }
        if(phone.charAt(index++) != '(') { return false; }
        for(int i = 0; i < 3; ++i) {
            if(!charIsDigit(phone.charAt(index++))) { return false; }
        }
        if(phone.charAt(index++) != ')') { return false; }
        for(int i = 0; i < 3; ++i) {
            if(!charIsDigit(phone.charAt(index++))) { return false; }
        }
        if(phone.charAt(index++) != '-') { return false; }
        for(int i = 0; i < 4; ++i) {
            if(!charIsDigit(phone.charAt(index++))) { return false; }
        }

        return true;
    }

    //Check if char is a number.
    boolean charIsDigit(char c){ return (c >= '0' && c <= '9'); }
}
