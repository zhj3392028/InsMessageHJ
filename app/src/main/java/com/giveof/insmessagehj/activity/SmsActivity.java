package com.giveof.insmessagehj.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.giveof.insmessagehj.R;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;

public class SmsActivity extends AppCompatActivity {
    private ExitActivityTransition exitTransition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActivityTransition.with(getIntent()).to(findViewById(R.id.card)).start(savedInstanceState);
        exitTransition = ActivityTransition.with(getIntent()).to(findViewById(R.id.card)).start(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        exitTransition.exit(this);
    }
}
