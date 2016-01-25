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
import android.widget.Toast;

import java.math.BigDecimal;

/**
 * Created by ryoma on 2015/12/22.
 */
public class DataCollection extends AppCompatActivity {

    public DataCollection(){}

    static int i = 1;

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_collection);

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

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
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

        final EditText uniformNumber = (EditText) findViewById(R.id.NumberInput);
        Button addPlayer = (Button) findViewById(R.id.addPlayer);
        addPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber.getText().toString();
                //int num = Integer.parseInt(number);
                //行追加
                LayoutInflater inflater = getLayoutInflater();
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.add_row_layout,
                        null);

                ll.setId(0 + (100 * i));

                ((LinearLayout) findViewById(R.id.data_sheet)).addView(ll);

                Cursor c = db.query("users", new String[]{"number", "name"}, " number == ? ",
                        new String[]{"" + number}, null, null, null);

                boolean mov = c.moveToFirst();
                while (mov) {
                    TextView uniformNumber = (TextView) findViewById(R.id.UniformNumber1);
                    TextView playerName = (TextView) findViewById(R.id.PlayerName1);

                    uniformNumber.setId(1 + (100 * i));
                    playerName.setId(2 + (100 * i));

                    uniformNumber.setText(String.format("  %d  ",c.getInt(0)));
                    playerName.setText(String.format("  %s  " , c.getString(1)));

                    mov = c.moveToNext();
                }
                c.close();
                //db.close();


                Button spDasuPlus = (Button) findViewById(R.id.SpDasuPlus);
                Button spDasuMinus = (Button) findViewById(R.id.SpDasuMinus);
                final TextView spDasu = (TextView) findViewById(R.id.SpikeDasu);
                Button spTokutenPlus = (Button) findViewById(R.id.SpTokutenPlus);
                Button spTokutenMinus = (Button) findViewById(R.id.SpTokutebMinus);
                final TextView spTokuten = (TextView) findViewById(R.id.SpikeTokuten);
                final TextView spKetteiritu = (TextView) findViewById(R.id.SpikeKetteiritu);
                Button spSittenPlus = (Button) findViewById(R.id.SpSittenPlus);
                Button spSittenMinus = (Button) findViewById(R.id.SpSittenMinus);
                final TextView spSitten = (TextView) findViewById(R.id.SpikeSitten);
                Button blDasuPlus = (Button) findViewById(R.id.BlDasuPlus);
                Button blDasuMinus = (Button) findViewById(R.id.BlDasuMinus);
                final TextView blTokuten = (TextView) findViewById(R.id.BlTokuten1);
                final TextView blRitu = (TextView) findViewById(R.id.Blritu1);

                spDasuPlus.setId(3 + (100 * i));
                spDasuMinus.setId(4 + (100 * i));
                spDasu.setId(5 + (100 * i));
                spTokutenPlus.setId(6 + (100 * i));
                spTokutenMinus.setId(7 + (100 * i));
                spTokuten.setId(8 + (100 * i));
                spKetteiritu.setId(9 + (100 * i));
                spSittenPlus.setId(10 + (100 * i));
                spSittenMinus.setId(11 + (100 * i));
                spSitten.setId(12 + (100 * i));
                blDasuPlus.setId(13 + (100 * i));
                blDasuMinus.setId(14 + (100 * i));
                blTokuten.setId(15 + (100 * i));
                blRitu.setId(16 + (100 * i));


                spDasuPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        long ketteiritu;

                        if (spDasuint >= 0) {
                            spDasuint++;
                            spDasu.setText(spDasuint + "");
                        }

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");


                    }
                });

                spDasuMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View View){
                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        long ketteiritu;

                        if (spDasuint >= 0) {
                            spDasuint--;
                            spDasu.setText(spDasuint + "");
                        }

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");

                    }
                });

                spTokutenPlus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        long ketteiritu;

                        spDasuint++;
                        spDasu.setText(spDasuint + "");
                        spTokutenint++;
                        spTokuten.setText(spTokutenint + "");

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");
                    }
                });

                spTokutenMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        long ketteiritu;

                        if (spDasuint >= 0) {
                            spDasuint--;
                            spDasu.setText(spDasuint + "");
                        }
                        if (spTokutenint >= 0) {
                            spTokutenint--;
                            spTokuten.setText(spTokutenint + "");
                        }

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");

                    }
                });

                spSittenPlus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();
                        String spSittenstr = (String) spSitten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        int spSittenint = Integer.parseInt(spSittenstr);
                        long ketteiritu;

                        if (spDasuint >= 0) {
                            spDasuint++;
                            spDasu.setText(spDasuint + "");
                        }
                        if (spTokutenint >= 0) {
                            spSittenint++;
                            spSitten.setText(spSittenint + "");
                        }

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");

                    }
                });

                spSittenMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String spDasustr = (String) spDasu.getText();
                        String spTokutenstr = (String) spTokuten.getText();
                        String spSittenstr = (String) spSitten.getText();

                        int spTokutenint = Integer.parseInt(spTokutenstr);
                        int spDasuint = Integer.parseInt(spDasustr);
                        int spSittenint = Integer.parseInt(spSittenstr);
                        long ketteiritu;

                        if (spDasuint >= 0) {
                            spDasuint--;
                            spDasu.setText(spDasuint + "");
                        }
                        if (spTokutenint >= 0) {
                            spSittenint--;
                            spSitten.setText(spSittenint + "");
                        }

                        ketteiritu = Math.round(spTokutenint * 100.0 / spDasuint);
                        spKetteiritu.setText(ketteiritu + "%");

                    }
                });

                blDasuPlus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Spinner settosu = (Spinner) findViewById(R.id.spinner);
                        double tmp;
                        double blRituint;

                        String blTokutenstr = (String) blTokuten.getText();
                        String settosustr = (String) settosu.getSelectedItem();

                        int blTokutenint = Integer.parseInt(blTokutenstr);
                        int settsuuint = Integer.parseInt(settosustr);

                        if (blTokutenint >= 0) {
                            blTokutenint++;
                            blTokuten.setText(blTokutenint + "");
                        }

                        tmp = blTokutenint * 1.0 / settsuuint * 1.0;
                        BigDecimal bi = new BigDecimal(String.valueOf(tmp));

                        blRituint = bi.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
                        blRitu.setText(blRituint + "%");

                    }
                });

                blDasuMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        Spinner settosu = (Spinner) findViewById(R.id.spinner);
                        double tmp;
                        double blRituint;

                        String blTokutenstr = (String) blTokuten.getText();
                        String settosustr = (String) settosu.getSelectedItem();

                        int blTokutenint = Integer.parseInt(blTokutenstr);
                        int settsuuint = Integer.parseInt(settosustr);

                        if (blTokutenint >= 0) {
                            blTokutenint--;
                            blTokuten.setText(blTokutenint + "");
                        }

                        tmp = blTokutenint * 1.0 / settsuuint * 1.0;
                        BigDecimal bi = new BigDecimal(String.valueOf(tmp));

                        blRituint = bi.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue();
                        blRitu.setText(blRituint + "%");

                    }
                });



                i++;

            }
        });

        Button playerTouroku = (Button) findViewById(R.id.PlayerTouroku);
        playerTouroku.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){
                Intent mainintent = new Intent(DataCollection.this,MainActivity.class);
                startActivity(mainintent);
            }
        });

    }


    public void SpDasuu(View v){
        Toast.makeText(getApplicationContext(), "スパイク打数!", Toast.LENGTH_SHORT).show();
    }

    public void SpTokuten(View v) {
        Toast.makeText(getApplicationContext(), "スパイク得点!", Toast.LENGTH_SHORT).show();
    }

    public void SpSitten(View v) {
        Toast.makeText(getApplicationContext(), "スパイク失点!", Toast.LENGTH_SHORT).show();
    }

    public void Ketteiritu(View v) {
        Toast.makeText(getApplicationContext(), "スパイク決定率!", Toast.LENGTH_SHORT).show();
    }

    public void BlTokuten(View v){
        Toast.makeText(getApplicationContext(), "ブロック得点!", Toast.LENGTH_SHORT).show();
    }

    public void Blritu(View v){
        Toast.makeText(getApplicationContext(), "ブロック率!", Toast.LENGTH_SHORT).show();
    }


}
