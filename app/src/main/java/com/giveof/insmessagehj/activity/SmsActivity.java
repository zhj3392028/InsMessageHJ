package com.giveof.insmessagehj.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.giveof.insmessagehj.R;
import com.kogitune.activity_transition.ActivityTransition;
import com.kogitune.activity_transition.ExitActivityTransition;

public class SmsActivity extends AppCompatActivity {
    private ExitActivityTransition exitTransition;
    private Toolbar mToolbar;
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
