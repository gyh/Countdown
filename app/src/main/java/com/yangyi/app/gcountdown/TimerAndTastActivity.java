package com.yangyi.app.gcountdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by GYH on 2015/12/7.
 */
public class TimerAndTastActivity extends AppCompatActivity{

    private static final String THIS_TENT_DATA = "thisTentData";

    private String sendTimed = "";

    Timer timer = new Timer();
    TextView textView ;

    private int relen = 10000;

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(relen<0){
                        timer.cancel();
                    }else {
                        textView.setText(relen+"");
                    }
                }
            });
            relen --;
        }
    };

    public static void startIntentTimeAndTast(Context context,String object){
        Intent intent = new Intent(context,TimerAndTastActivity.class);
        intent.putExtra(THIS_TENT_DATA, object);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_task);
        if(savedInstanceState!=null && savedInstanceState.getString(THIS_TENT_DATA) != null){
            sendTimed = savedInstanceState.getString(THIS_TENT_DATA);
        }else {
            sendTimed = getIntent().getStringExtra(THIS_TENT_DATA);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        textView = (TextView)findViewById(R.id.timetv);
        timer.schedule(timerTask, 1000, 1000);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(THIS_TENT_DATA,sendTimed);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                TimerAndTastActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
