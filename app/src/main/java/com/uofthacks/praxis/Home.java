package com.uofthacks.praxis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Home extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "com.uofthacks.praxi.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

    }

    public void dash(View view) {
        EditText un = (EditText) findViewById(R.id.editText);
        String username = un.getText().toString();
        EditText pw = (EditText) findViewById(R.id.editText2);
        String password = pw.getText().toString();
        if(username.equals("ronak.patel") && password.equals("ronak"))
        {
            Intent intent = new Intent(this,DashBoard.class);
            intent.putExtra(EXTRA_MESSAGE, username);
            startActivity(intent);
        }
        else
        {
            Context context = getApplicationContext();
            CharSequence text = "Wrong Username & Password";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
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
}
