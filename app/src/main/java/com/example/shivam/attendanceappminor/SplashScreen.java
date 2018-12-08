package com.example.shivam.attendanceappminor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Admin will upload batch wise data of all the students before the usage of application.
        // Redirect to LoginScreen where the option for choosing the type is given.(Check for tinyDB values)
        //If logged_in = true, what kind of login is it. and then redirect directly to the dashboard of respective user.

    }
}
