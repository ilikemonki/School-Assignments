package com.example.hm_1;

// Josue Crandall and Meng Cha
// CECS 453 Mobile Apps
// 2/20/20

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button mButtonSignUp;
    private Button mButtonLogin;
    private EditText mEditTextUsername;
    private EditText mEditTextPassword;

    private String mAccountsKey;
    private String mNewUsernameKey;
    private String mUsernameKey;
    private String mFailedLoginToast;
    private int mSignupRequestCode;

    private HashMap<String,String> mAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadResources();

        //Create hashmap for data
        mAccounts = (HashMap<String, String>) getIntent().getSerializableExtra(mAccountsKey);
        if(mAccounts == null) { mAccounts = new HashMap<>(); }

        setListeners();
    }

    // https://stackoverflow.com/questions/14292398/how-to-pass-data-from-2nd-activity-to-1st-activity-when-pressed-back-android
    //Retrieve results from activities
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == mSignupRequestCode && resultCode == RESULT_OK) {
            mAccounts = (HashMap<String, String>) data.getSerializableExtra(mAccountsKey);
            mEditTextUsername.setText(data.getStringExtra(mNewUsernameKey));
            if(mAccounts == null) {
                Toast.makeText(getApplicationContext(),
                        "PROGRAMMER FAILURE",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    //Initialize variables
    void loadResources() {
        Resources resources = getResources();
        mButtonSignUp = findViewById(R.id.button_sign_up);
        mButtonLogin = findViewById(R.id.button_login);
        mEditTextUsername = findViewById(R.id.input_user_name);
        mEditTextPassword = findViewById(R.id.input_password);
        mAccountsKey = resources.getString(R.string.accounts_key);
        mUsernameKey = resources.getString(R.string.username_key);
        mFailedLoginToast = resources.getString(R.string.failed_login_toast_text);
        mSignupRequestCode = resources.getInteger(R.integer.sign_up_success_request_code);
        mNewUsernameKey = resources.getString(R.string.new_username_key);
    }

    //Set listeners
    void setListeners() {
        //Listener for Sign Up button
        mButtonSignUp.setOnClickListener((button) -> {
            mEditTextUsername.setText("");
            prepAndStartActivityForResult(new Intent(this, Signup.class), mAccounts);
        });

        //Listener for Login button
        mButtonLogin.setOnClickListener((button) -> {
            //Get username and password
            String attempted_username = mEditTextUsername.getText().toString();
            String attempted_password = mEditTextPassword.getText().toString();

            //Checks if username and password is in the data
            if(mAccounts.containsKey(attempted_username) &&
                    mAccounts.get(attempted_username).compareTo(attempted_password) == 0)
            {
                Intent intent = new Intent(this, Welcome.class);
                intent.putExtra(mUsernameKey, attempted_username);
                prepAndStartActivity(intent, mAccounts);
            }
            else {
                mEditTextPassword.setText("");
                Toast.makeText(getApplicationContext(), mFailedLoginToast, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    //Create an activity
    void prepAndStartActivity(Intent intent, HashMap<String,String> accounts) {
        transitionPrep(intent, accounts);
        startActivity(intent);
    }
    //Create an activity for result
    void prepAndStartActivityForResult(Intent intent, HashMap<String,String> accounts) {
        transitionPrep(intent, accounts);
        startActivityForResult(intent, mSignupRequestCode);
    }
    //Set up intent to send values
    void transitionPrep(Intent intent, HashMap<String,String> accounts) {
        mEditTextPassword.setText("");
        intent.putExtra(mAccountsKey, accounts);
    }
}