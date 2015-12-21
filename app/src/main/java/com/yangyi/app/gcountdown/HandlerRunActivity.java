package com.yangyi.app.gcountdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by GYH on 2015/12/21.
 */
public class HandlerRunActivity extends AppCompatActivity{

    private static final String THIS_TENT_DATA = "thisTentData";

    private String sendTimed = "";

    TextView textView ;
    private int recLen = 10000;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(recLen>=0){
                textView.setText("" + recLen);
                handler.postDelayed(this, 1000);
                recLen--;
            }
        }
    };

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
        getSupportActionBar().setTitle(sendTimed);
        textView = (TextView)findViewById(R.id.timetv);
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(THIS_TENT_DATA, sendTimed);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                HandlerRunActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void startIntentHandlerRun(Context context,String object){
        Intent intent = new Intent(context,HandlerRunActivity.class);
        intent.putExtra(THIS_TENT_DATA, object);
        context.startActivity(intent);
    }
}
