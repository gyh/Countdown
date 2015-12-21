package com.yangyi.app.gcountdown;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by GYH on 2015/12/21.
 */
public class HandlerThreActivity extends AppCompatActivity{

    private static final String THIS_TENT_DATA = "thisTentData";

    private String sendTimed = "";

    TextView textView ;

    private int relen = 10000;


    public static void startIntentHandlerThre(Context context,String object){
        Intent intent = new Intent(context,HandlerThreActivity.class);
        intent.putExtra(THIS_TENT_DATA, object);
        context.startActivity(intent);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    textView.setText("" + relen);
                    relen--;
            }
            super.handleMessage(msg);
        }
    };

    class Threadth implements Runnable{

        @Override
        public void run() {
            while (true){
                if(relen<0){
                    break;
                }else {
                    try {
                        Thread.sleep(1000);     // sleep 1000ms
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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
        getSupportActionBar().setTitle(sendTimed);
        textView = (TextView)findViewById(R.id.timetv);
        new Thread(new Threadth()).start();
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
                HandlerThreActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
