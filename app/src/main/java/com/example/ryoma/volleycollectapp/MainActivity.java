package com.example.ryoma.volleycollectapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
        final SQLiteDatabase db = userOpenHelper.getWritableDatabase();

        final EditText uniformNumber = (EditText) findViewById(R.id.UniformNumberInput);
        final EditText playerName = (EditText) findViewById(R.id.PlayerNameInput);

        //続きはここから、登録ボタンの追加から
        Button tourokuBotton = (Button) findViewById(R.id.Touroku);
        tourokuBotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                String name = playerName.getText().toString();

                //int number = Integer.parseInt(numberText);

                ContentValues insertValues = new ContentValues();
                insertValues.put("number", number);
                insertValues.put("name", name);
                long id = db.insert("users", null, insertValues);

                Log.v("DB_TEST", "id: " + id + " UniformNumber: " + number + " Name: " + name);
            }
        });

        Button updateButton = (Button) findViewById(R.id.Update);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                String name = playerName.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("number", number);
                    db.update("users", updateValues, "name=?", new String[]{name});
                }
            }
        });

        Button deleteButton = (Button) findViewById(R.id.Delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                String name = playerName.getText().toString();

                if (name.equals("")) {
                    Toast.makeText(MainActivity.this, "名前を入力してください。",
                            Toast.LENGTH_SHORT).show();
                } else {
                    db.delete("users", "name=?", new String[]{name});
                }
            }
        });

        Button deleteAllButton = (Button) findViewById(R.id.AllDelete);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                String name = playerName.getText().toString();

                db.delete("users", null, null);
            }
        });

        Button showDatabase = (Button) findViewById(R.id.ShowDatabaseButton);
        showDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dbIntent = new Intent(MainActivity.this,
                        ShowDatebase.class);
                startActivity(dbIntent);
            }
        });


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
}
