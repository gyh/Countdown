package com.yangyi.app.gcountdown;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{


    private String[] strTypes = new String[]{"使用Java的Timer与TimerTask",
            "TimerTask与Handler",
            "Handler自循环",
            "Handler与Thread（不占用UI线程）",
            "Handler与Runnable（最简单型）"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initListView();
    }


    private void initListView(){
        ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new MyAdapter(this,this.strTypes));
        listView.setOnItemClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                NavigationIn.navigatiionTimeAndTast(MainActivity.this,strTypes[position]);
                break;
            case 1:
                NavigationIn.navigationTimeAndHandler(MainActivity.this,strTypes[position]);
                break;
            case 2:
                NavigationIn.navigationHandlerAc(MainActivity.this,strTypes[position]);
                break;
            case 3:
                NavigationIn.navigationHandlerThre(MainActivity.this, strTypes[position]);
                break;
            case 4:
                NavigationIn.navigationHandlerRun(MainActivity.this,strTypes[position]);
                break;
        }
    }


    class MyAdapter extends BaseAdapter{

        String[] strings;

        LayoutInflater listContainer;

        MyAdapter(Context context,String[] strings){
            this.strings = strings;
            listContainer = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return strings.length;
        }

        @Override
        public Object getItem(int position) {
            return strings[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = null;
            if(convertView==null){
                convertView = listContainer.inflate(R.layout.item_listview,null);
                textView = (TextView)convertView.findViewById(R.id.itemTv);
                convertView.setTag(textView);
            }else {
                textView = (TextView)convertView.getTag();
            }
            textView.setText(strings[position]);
            return convertView;
        }
    }
}
