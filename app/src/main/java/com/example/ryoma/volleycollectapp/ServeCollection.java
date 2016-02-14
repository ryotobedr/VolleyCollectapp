package com.example.ryoma.volleycollectapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by ryoma on 2016/01/26.
 */
public class ServeCollection extends AppCompatActivity{

    static int i = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serve_collect_layout);

        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
//        final SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        final SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        String query_select = "SELECT * FROM users";


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // アイテムを追加します
        adapter.add("1");
        adapter.add("2");
        adapter.add("3");
        adapter.add("4");
        adapter.add("5");
        adapter.add("6");
        adapter.add("7");
        adapter.add("8");
        adapter.add("9");
        adapter.add("10");
        adapter.add("11");
        adapter.add("12");

        Spinner spinner = (Spinner) findViewById(R.id.Servespinner);
        // アダプターを設定します
        spinner.setAdapter(adapter);
        // スピナーのアイテムが選択された時に呼び出されるコールバックリスナーを登録します
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Spinner spinner = (Spinner) parent;
                // 選択されたアイテムを取得します
                String item = (String) spinner.getSelectedItem();
                //Toast.makeText(MainActivity.this, item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        final EditText uniformNumber = (EditText) findViewById(R.id.ServeNumberInput);
        Button addPlayer = (Button) findViewById(R.id.ServeAddPlayer);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                //int num = Integer.parseInt(number);
                //行追加
                LayoutInflater inflater = getLayoutInflater();
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.add_serve_layout,
                        null);

                ll.setId(0 + (100 * i));

                ((LinearLayout) findViewById(R.id.ServeData_Sheet)).addView(ll);

                Cursor c = db.query("users", new String[]{"number", "name"}, " number == ? ",
                        new String[]{"" + number}, null, null, null);

                boolean mov = c.moveToFirst();
                while (mov) {
                    TextView uniformNumber = (TextView) findViewById(R.id.UniformNumber2);
                    TextView playerName = (TextView) findViewById(R.id.PlayerName2);

                    uniformNumber.setId(1 + (100 * i));
                    playerName.setId(2 + (100 * i));

                    uniformNumber.setText(String.format("  %d  ", c.getInt(0)));
                    playerName.setText(String.format("  %s  ", c.getString(1)));

                    mov = c.moveToNext();
                }
                c.close();

                Button serveTokutenPlus = (Button) findViewById(R.id.ServeTokutenPlus);
                Button serveTokutenMinus = (Button) findViewById(R.id.ServeTokutenMinus);
                final TextView serveTokutenHonsu = (TextView) findViewById(R.id.ServeTokutenHonsu);
                final TextView serveRitu = (TextView) findViewById(R.id.ServeTokutenRitu);
                Button serveKoukaPlus = (Button) findViewById(R.id.ServeKoukaPlus);
                Button serveKoukaMinus = (Button) findViewById(R.id.ServeKoukaMinus);
                final TextView serveKoukaHonsu = (TextView) findViewById(R.id.ServeKoukaHonsu);
                final TextView serveKoukaRitu = (TextView) findViewById(R.id.ServeKoukaRitu);
                Button serveSittenPlus = (Button) findViewById(R.id.ServeSittenPlus);
                Button serveSittenMinus = (Button) findViewById(R.id.ServeSittenMinus);
                final TextView serveSittenHonsu = (TextView) findViewById(R.id.ServeSittenHonsu);
                final TextView serveSittenRitu = (TextView) findViewById(R.id.ServeSittenRitu);
                TextView serveHonsu = (TextView) findViewById(R.id.ServeHonsu1);

                serveTokutenPlus.setId(3 + (101 * i));
                serveTokutenMinus.setId(4 + (101 * i));
                serveTokutenHonsu.setId(5 + (101 * i));
                serveRitu.setId(6 + (101 * i));
                serveKoukaPlus.setId(7 + (101 * i));
                serveKoukaMinus.setId(8 + (101 * i));
                serveKoukaHonsu.setId(9 + (101 * i));
                serveKoukaRitu.setId(10 + (101 * i));
                serveSittenPlus.setId(11 + (101 * i));
                serveSittenMinus.setId(12 + (101 * i));
                serveSittenHonsu.setId(13 + (101 * i));
                serveSittenRitu.setId(14 + (101 * i));
                serveHonsu.setId(15 + (101 * i));



                i++;

            }
        });

        Button playerTouroku = (Button) findViewById(R.id.ServePlayerTouroku);
        playerTouroku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainintent = new Intent(ServeCollection.this, MainActivity.class);
                startActivity(mainintent);
            }
        });

    }

}
