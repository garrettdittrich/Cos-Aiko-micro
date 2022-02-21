package com.capstone.cos_aiko;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.capstone.cos_aiko.model.User;
import com.capstone.cos_aiko.model.UserResponse;
import com.capstone.cos_aiko.remote.ApiUtils;
import com.capstone.cos_aiko.remote.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {
    EditText username;
    EditText password, confirmPwd;
    EditText fname;
    EditText lname;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userService = ApiUtils.getUserService();
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        confirmPwd = (EditText) findViewById(R.id.confirmpassword);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
    }

    public void registerButton(View view) {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        String confirmPwdText = confirmPwd.getText().toString();
        String fnameText = fname.getText().toString();
        String lnameText = lname.getText().toString();

        // check fields before creating user
        if (validateSignUp(fnameText, lnameText, usernameText, passwordText, confirmPwdText)) {
            // capitalize the first letter of first and last name
            fnameText = fnameText.substring(0, 1).toUpperCase() + fnameText.substring(1);
            lnameText = lnameText.substring(0, 1).toUpperCase() + lnameText.substring(1);

            // attempt user registration
            User user = new User(fnameText, lnameText, usernameText, passwordText);
            register(user);
        }
    }

    private void register(User user) {
        // Make API call with parameter email and password
        Call<UserResponse> call = userService.createUser(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) { // user successfully registered
                    UserResponse user = response.body();
                    // login successful
                    Toast.makeText(getApplicationContext(), "Hello Javatpoint", Toast.LENGTH_SHORT).show();
                    Intent tabPage = new Intent(getApplicationContext(), TabPage.class);
                    startActivity(tabPage);
                } else { // no successfully registered
                    Toast.makeText(getApplicationContext(), response.errorBody().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // check entered information
    private boolean validateSignUp(String fName, String lName, String username, String password, String confirmPwd) {
        if (fName == null || fName.trim().length() == 0) { // validate first name
            Toast.makeText(getApplicationContext(), "First Name is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (lName == null || lName.trim().length() == 0) { // validate last name
            Toast.makeText(getApplicationContext(), "Last Name is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (username == null || username.trim().length() == 0) { // validate email
            Toast.makeText(getApplicationContext(), "Email is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password == null || password.trim().length() == 0) { // validate password
            Toast.makeText(getApplicationContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.equals(confirmPwd)) {
            Toast.makeText(getApplicationContext(), "Password and Confirm Password MUST match!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}