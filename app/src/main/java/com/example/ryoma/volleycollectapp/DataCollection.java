package com.example.ryoma.volleycollectapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.concurrent.TimeoutException;

/**
 * Created by ryoma on 2015/12/22.
 */
public class DataCollection extends AppCompatActivity {

    public DataCollection(){}

    static int i = 1;
    LinearLayout ll[] = new LinearLayout[100];

    @Override
    protected void onCreate(final Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_collection);

        //TabHostオブジェクト取得
        TabHost tabhost = (TabHost)findViewById(android.R.id.tabhost);
        tabhost.setup();

        TabHost.TabSpec tab1 = tabhost.newTabSpec("tab1");
        tab1.setIndicator("スパイク・ブロック");
        tab1.setContent(R.id.tab1);
        tabhost.addTab(tab1);

        TabHost.TabSpec tab2 = tabhost.newTabSpec("tab2");
        tab2.setIndicator("サーブ");
        tab2.setContent(R.id.tab2);
        tabhost.addTab(tab2);

        TabHost.TabSpec tab3 = tabhost.newTabSpec("tab3");
        tab3.setIndicator("サーブカット");
        tab3.setContent(R.id.tab3);
        tabhost.addTab(tab3);

        tabhost.setCurrentTab(0);

        UserOpenHelper userOpenHelper = new UserOpenHelper(this);
//        final SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        final SQLiteDatabase db = userOpenHelper.getWritableDatabase();
        String query_select = "SELECT * FROM users";



        //スパイク・ブロック
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
                ll[i] = (LinearLayout) inflater.inflate(R.layout.add_row_layout,
                        null);

//                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.add_row_layout,
//                        null);

                ll[i].setId(0 + (100 * i));

//                Log.v("test", "ll" + ll);

                ((LinearLayout) findViewById(R.id.data_sheet)).addView(ll[i]);

                Cursor c = db.query("users", new String[]{"number", "name"}, " number == ? ",
                        new String[]{"" + number}, null, null, null);

                boolean mov = c.moveToFirst();
                while (mov) {
                    TextView uniformNumber = (TextView) findViewById(R.id.UniformNumber1);
                    TextView playerName = (TextView) findViewById(R.id.PlayerName1);

                    uniformNumber.setId(1 + (100 * i));
                    playerName.setId(2 + (100 * i));

                    uniformNumber.setText(String.format("  %d  ", c.getInt(0)));
                    playerName.setText(String.format("  %s  ", c.getString(1)));

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
                        spKetteiritu.setText(ketteiritu + " %");


                    }
                });

                spDasuMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View View) {
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
                        spKetteiritu.setText(ketteiritu + " %");

                    }
                });

                spTokutenPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        spKetteiritu.setText(ketteiritu + " %");
                    }
                });

                spTokutenMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        spKetteiritu.setText(ketteiritu + " %");

                    }
                });

                spSittenPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        spKetteiritu.setText(ketteiritu + " %");

                    }
                });

                spSittenMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                        spKetteiritu.setText(ketteiritu + " %");

                    }
                });

                blDasuPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

                        blRituint = bi.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        blRitu.setText(blRituint + " %");

                    }
                });

                blDasuMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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

                        blRituint = bi.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
                        blRitu.setText(blRituint + " %");

                    }
                });
                i++;

            }
        });

        //削除ボタン
        final EditText deletteNumberInput = (EditText) findViewById(R.id.DeliteNumberInput);
        Button deletePlayer = (Button) findViewById(R.id.deletePlayer);
        deletePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String deletenumberstr = deletteNumberInput.getText().toString();
                int strId = getResources().getIdentifier(deletenumberstr, "string", getPackageName());
                int resId = getResources().getIdentifier(deletenumberstr, "id", getPackageName());

                int deletenumberint = Integer.parseInt(deletenumberstr);

                ll[deletenumberint].removeAllViews();

                i = deletenumberint;

                //TextView deleteNumberStr = (TextView) findViewById(R.id.);

//                ViewParent viewParent = deleteNumberStr.getParent();
//
//                v = (View) viewParent;


//                LayoutInflater inflater = getLayoutInflater();
//                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.add_row_layout,
//                        null);

                //int llid = getResources().getIdentifier(ll, "string", getPackageName());

                Log.v("test", "deletenumberstr: " + deletenumberstr + " strId: " + strId + " resId:" + resId );
//                Log.v("test", "deletenumberstr: " + deletenumberstr + " strId: " + strId + " resId:" + resId +  " deleteNumberStr:" + deleteNumberStr );

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

        //サーブ
        Spinner spinner2 = (Spinner) findViewById(R.id.Servespinner);
        spinner2.setAdapter(adapter);
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

        final EditText uniformNumber2 = (EditText) findViewById(R.id.ServeNumberInput);
        Button addPlayer2 = (Button) findViewById(R.id.ServeAddPlayer);
        addPlayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber2.getText().toString();
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
                final TextView serveTokutenRitu = (TextView) findViewById(R.id.ServeTokutenRitu);
                Button serveKoukaPlus = (Button) findViewById(R.id.ServeKoukaPlus);
                Button serveKoukaMinus = (Button) findViewById(R.id.ServeKoukaMinus);
                final TextView serveKoukaHonsu = (TextView) findViewById(R.id.ServeKoukaHonsu);
                final TextView serveKoukaRitu = (TextView) findViewById(R.id.ServeKoukaRitu);
                Button serveSittenPlus = (Button) findViewById(R.id.ServeSittenPlus);
                Button serveSittenMinus = (Button) findViewById(R.id.ServeSittenMinus);
                final TextView serveSittenHonsu = (TextView) findViewById(R.id.ServeSittenHonsu);
                final TextView serveSittenRitu = (TextView) findViewById(R.id.ServeSittenRitu);
                final TextView serveHonsu = (TextView) findViewById(R.id.ServeHonsu1);

                serveTokutenPlus.setId(3 + (101 * i));
                serveTokutenMinus.setId(4 + (101 * i));
                serveTokutenHonsu.setId(5 + (101 * i));
                serveTokutenRitu.setId(6 + (101 * i));
                serveKoukaPlus.setId(7 + (101 * i));
                serveKoukaMinus.setId(8 + (101 * i));
                serveKoukaHonsu.setId(9 + (101 * i));
                serveKoukaRitu.setId(10 + (101 * i));
                serveSittenPlus.setId(11 + (101 * i));
                serveSittenMinus.setId(12 + (101 * i));
                serveSittenHonsu.setId(13 + (101 * i));
                serveSittenRitu.setId(14 + (101 * i));
                serveHonsu.setId(15 + (101 * i));

                serveTokutenPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if (serveHonsuint >= 0) {
                            serveHonsuint++;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if (serveTokutenHonsuint >= 0) {
                            serveTokutenHonsuint++;
                            serveTokutenHonsu.setText(serveTokutenHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");

                    }
                });

                serveTokutenMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if(serveHonsuint>=0){
                            serveHonsuint--;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if(serveTokutenHonsuint>=0){
                            serveTokutenHonsuint--;
                            serveTokutenHonsu.setText(serveTokutenHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");
                    }
                });

                serveKoukaPlus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if(serveHonsuint>=0){
                            serveHonsuint++;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if(serveKoukaHonsuint>=0){
                            serveKoukaHonsuint++;
                            serveKoukaHonsu.setText(serveKoukaHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");

                    }
                });

                serveKoukaMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if(serveHonsuint>=0){
                            serveHonsuint--;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if(serveKoukaHonsuint>=0){
                            serveKoukaHonsuint--;
                            serveKoukaHonsu.setText(serveKoukaHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");
                    }
                });

                serveSittenPlus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if(serveHonsuint>=0){
                            serveHonsuint++;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if(serveSittenHonsuint>=0){
                            serveSittenHonsuint++;
                            serveSittenHonsu.setText(serveSittenHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");
                    }
                });

                serveSittenMinus.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        String serveTokutenHonsustr = (String) serveTokutenHonsu.getText();
                        String serveHonsustr = (String) serveHonsu.getText();
                        long serveTokutenRitulong;

                        String serveKoukaHonsustr = (String) serveKoukaHonsu.getText();
                        long serveKoukaRitulong;

                        String serveSittenHonsustr = (String) serveSittenHonsu.getText();
                        long serveSittenRitulong;

                        int serveTokutenHonsuint = Integer.parseInt(serveTokutenHonsustr);
                        int serveHonsuint = Integer.parseInt(serveHonsustr);
                        int serveKoukaHonsuint = Integer.parseInt(serveKoukaHonsustr);
                        int serveSittenHonsuint = Integer.parseInt(serveSittenHonsustr);

                        if(serveHonsuint>=0){
                            serveHonsuint--;
                            serveHonsu.setText(serveHonsuint + "");
                        }
                        if(serveSittenHonsuint>=0){
                            serveSittenHonsuint--;
                            serveSittenHonsu.setText(serveSittenHonsuint + "");
                        }

                        serveTokutenRitulong = Math.round(serveTokutenHonsuint * 100.0 / serveHonsuint);
                        serveTokutenRitu.setText(serveTokutenRitulong + " %");

                        serveKoukaRitulong = Math.round(serveKoukaHonsuint * 100.0 / serveHonsuint);
                        serveKoukaRitu.setText(serveKoukaRitulong + " %");

                        serveSittenRitulong = Math.round(serveSittenHonsuint * 100.0 / serveHonsuint);
                        serveSittenRitu.setText(serveSittenRitulong + " %");
                    }
                });

                i++;

            }
        });

        Button playerTouroku2 = (Button) findViewById(R.id.ServePlayerTouroku);
        playerTouroku2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainintent = new Intent(DataCollection.this, MainActivity.class);
                startActivity(mainintent);
            }
        });


        //サーブカット
        final EditText uniformNumber3 = (EditText) findViewById(R.id.ServeCutNumberInput);
        Button addPlayer3 = (Button) findViewById(R.id.ServeCutAddPlayer);
        addPlayer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = uniformNumber3.getText().toString();
                //int num = Integer.parseInt(number);
                //行追加
                final LayoutInflater inflater = getLayoutInflater();
                LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.add_servecut_layout,
                        null);

                ll.setId(0 + (100 * i));

                ((LinearLayout) findViewById(R.id.ServeCutData_Sheet)).addView(ll);

                Cursor c = db.query("users", new String[]{"number", "name"}, " number == ? ",
                        new String[]{"" + number}, null, null, null);

                boolean mov = c.moveToFirst();
                while (mov) {
                    TextView uniformNumber = (TextView) findViewById(R.id.UniformNumber3);
                    TextView playerName = (TextView) findViewById(R.id.PlayerName3);

                    uniformNumber.setId(1 + (100 * i));
                    playerName.setId(2 + (100 * i));

                    uniformNumber.setText(String.format("  %d  ", c.getInt(0)));
                    playerName.setText(String.format("  %s  ", c.getString(1)));

                    mov = c.moveToNext();
                }
                c.close();

                Button aCutPlus = (Button) findViewById(R.id.AcutPlus);
                Button aCutMinus = (Button) findViewById(R.id.AcutMinus);
                final TextView aCutHonsu = (TextView) findViewById(R.id.AcutHonsuu);
                final TextView aCutRitu = (TextView) findViewById(R.id.AcutRitu);
                Button bCutPlus = (Button) findViewById(R.id.BcutPlus);
                Button bCutMinus = (Button) findViewById(R.id.BcutMinus);
                final TextView bCutHonsu = (TextView) findViewById(R.id.BcutHonsuu);
                final TextView bCutRitu = (TextView) findViewById(R.id.BcutRitu);
                Button cCutPlus = (Button) findViewById(R.id.CcutPlus);
                Button cCutMinus = (Button) findViewById(R.id.CcutMinus);
                final TextView cCutHonsu = (TextView) findViewById(R.id.CcutHonsuu);
                final TextView cCutRitu = (TextView) findViewById(R.id.CcutRitu);
                Button dCutPlus = (Button) findViewById(R.id.DcutPlus);
                Button dCutMinus = (Button) findViewById(R.id.DcutMinus);
                final TextView dCutHonsu = (TextView) findViewById(R.id.DcutHonsuu);
                final TextView dCutRitu = (TextView) findViewById(R.id.DcutRitu);
                final TextView servecutHonsuu = (TextView) findViewById(R.id.ServeCutHonsuu1);

                aCutPlus.setId(3 + (102 * i));
                aCutMinus.setId(4 + (102 * i));
                aCutHonsu.setId(5 + (102 * i));
                aCutRitu.setId(6 + (102 * i));
                bCutPlus.setId(7 + (102 * i));
                bCutMinus.setId(8 + (102 * i));
                bCutHonsu.setId(9 + (102 * i));
                bCutRitu.setId(10 + (102 * i));
                cCutPlus.setId(11 + (102 * i));
                cCutMinus.setId(12 + (102 * i));
                cCutHonsu.setId(13 + (102 * i));
                cCutRitu.setId(14 + (102 * i));
                dCutPlus.setId(15 + (102 * i));
                dCutMinus.setId(16 + (102 * i));
                dCutHonsu.setId(17 + (102 * i));
                dCutRitu.setId(18 + (102 * i));
                servecutHonsuu.setId(19 + (102 * i));

                // Aカット ボタン +
                aCutPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        long acutritulong;

                        String bcuthonsustr = (String) bCutHonsu.getText();
                        long bcutritulong;

                        String ccuthonsustr = (String) cCutHonsu.getText();
                        long ccutritulong;

                        String dcuthonsustr = (String) dCutHonsu.getText();
                        long dcutritulong;

                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (acuthonsuint >= 0) {
                            acuthonsuint++;
                            aCutHonsu.setText(acuthonsuint + "");
                        }
                        if (servecuthonsuint >= 0) {
                            servecuthonsuint++;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Aカット ボタン -
                aCutMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        String bcuthonsustr = (String) bCutHonsu.getText();
                        String ccuthonsustr = (String) cCutHonsu.getText();
                        String dcuthonsustr = (String) dCutHonsu.getText();
                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        long acutritulong;
                        long bcutritulong;
                        long ccutritulong;
                        long dcutritulong;

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (acuthonsuint > 0) {
                            acuthonsuint--;
                            aCutHonsu.setText(acuthonsuint + "");
                        }
                        if (servecuthonsuint > 0) {
                            servecuthonsuint--;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Bカット ボタン +
                bCutPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        long acutritulong;

                        String bcuthonsustr = (String) bCutHonsu.getText();
                        long bcutritulong;

                        String ccuthonsustr = (String) cCutHonsu.getText();
                        long ccutritulong;

                        String dcuthonsustr = (String) dCutHonsu.getText();
                        long dcutritulong;

                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (bcuthonsuint >= 0) {
                            bcuthonsuint++;
                            bCutHonsu.setText(bcuthonsuint + "");
                        }
                        if (servecuthonsuint >= 0) {
                            servecuthonsuint++;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Bカット ボタン -
                bCutMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        String bcuthonsustr = (String) bCutHonsu.getText();
                        String ccuthonsustr = (String) cCutHonsu.getText();
                        String dcuthonsustr = (String) dCutHonsu.getText();
                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        long acutritulong;
                        long bcutritulong;
                        long ccutritulong;
                        long dcutritulong;

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (bcuthonsuint > 0) {
                            bcuthonsuint--;
                            bCutHonsu.setText(bcuthonsuint + "");
                        }
                        if (servecuthonsuint > 0) {
                            servecuthonsuint--;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Cカット ボタン +
                cCutPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        long acutritulong;

                        String bcuthonsustr = (String) bCutHonsu.getText();
                        long bcutritulong;

                        String ccuthonsustr = (String) cCutHonsu.getText();
                        long ccutritulong;

                        String dcuthonsustr = (String) dCutHonsu.getText();
                        long dcutritulong;

                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (ccuthonsuint >= 0) {
                            ccuthonsuint++;
                            cCutHonsu.setText(ccuthonsuint + "");
                        }
                        if (servecuthonsuint >= 0) {
                            servecuthonsuint++;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Cカット ボタン -
                cCutMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        String bcuthonsustr = (String) bCutHonsu.getText();
                        String ccuthonsustr = (String) cCutHonsu.getText();
                        String dcuthonsustr = (String) dCutHonsu.getText();
                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        long acutritulong;
                        long bcutritulong;
                        long ccutritulong;
                        long dcutritulong;

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (ccuthonsuint > 0) {
                            ccuthonsuint--;
                            cCutHonsu.setText(ccuthonsuint + "");
                        }
                        if (servecuthonsuint > 0) {
                            servecuthonsuint--;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });


                // Dカット ボタン +
                dCutPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        long acutritulong;

                        String bcuthonsustr = (String) bCutHonsu.getText();
                        long bcutritulong;

                        String ccuthonsustr = (String) cCutHonsu.getText();
                        long ccutritulong;

                        String dcuthonsustr = (String) dCutHonsu.getText();
                        long dcutritulong;

                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (dcuthonsuint >= 0) {
                            dcuthonsuint++;
                            dCutHonsu.setText(dcuthonsuint + "");
                        }
                        if (servecuthonsuint >= 0) {
                            servecuthonsuint++;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                // Cカット ボタン -
                cCutMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String acuthonsustr = (String) aCutHonsu.getText();
                        String bcuthonsustr = (String) bCutHonsu.getText();
                        String ccuthonsustr = (String) cCutHonsu.getText();
                        String dcuthonsustr = (String) dCutHonsu.getText();
                        String servecuthonsustr = (String) servecutHonsuu.getText();

                        long acutritulong;
                        long bcutritulong;
                        long ccutritulong;
                        long dcutritulong;

                        int acuthonsuint = Integer.parseInt(acuthonsustr);
                        int bcuthonsuint = Integer.parseInt(bcuthonsustr);
                        int ccuthonsuint = Integer.parseInt(ccuthonsustr);
                        int dcuthonsuint = Integer.parseInt(dcuthonsustr);
                        int servecuthonsuint = Integer.parseInt(servecuthonsustr);

                        if (dcuthonsuint > 0) {
                            dcuthonsuint--;
                            dCutHonsu.setText(dcuthonsuint + "");
                        }
                        if (servecuthonsuint > 0) {
                            servecuthonsuint--;
                            servecutHonsuu.setText(servecuthonsuint + "");
                        }

                        acutritulong = Math.round(acuthonsuint * 100.0 / servecuthonsuint);
                        aCutRitu.setText(acutritulong + " %");

                        bcutritulong = Math.round(bcuthonsuint * 100.0 / servecuthonsuint);
                        bCutRitu.setText(bcutritulong + " %");

                        ccutritulong = Math.round(ccuthonsuint * 100.0 / servecuthonsuint);
                        cCutRitu.setText(ccutritulong + " %");

                        dcutritulong = Math.round(dcuthonsuint * 100.0 / servecuthonsuint);
                        dCutRitu.setText(dcutritulong + " %");

                    }
                });

                i++;

            }
        });

        Button playerTouroku3 = (Button) findViewById(R.id.ServeCutPlayerTouroku);
        playerTouroku3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainintent = new Intent(DataCollection.this, MainActivity.class);
                startActivity(mainintent);
            }
        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        // GridLayout内のアイテムをレイアウトサイズに合わせてストレッチ
        final GridLayout spGl = (GridLayout) findViewById(R.id.SpikeGrid);
        int childWidth = spGl.getWidth() / spGl.getColumnCount();
//        int childHeight = gl.getHeight() / gl.getRowCount();
        for (int i = 0; i < spGl.getChildCount()-1; i++) {
            spGl.getChildAt(i).setMinimumWidth(childWidth);
//           gl.getChildAt(i).setMinimumHeight(childHeight);
        }
    }

//    public void SpDasuu(View v){
//        Toast.makeText(getApplicationContext(), "スパイク打数!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void SpTokuten(View v) {
//        Toast.makeText(getApplicationContext(), "スパイク得点!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void SpSitten(View v) {
//        Toast.makeText(getApplicationContext(), "スパイク失点!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void Ketteiritu(View v) {
//        Toast.makeText(getApplicationContext(), "スパイク決定率!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void BlTokuten(View v){
//        Toast.makeText(getApplicationContext(), "ブロック得点!", Toast.LENGTH_SHORT).show();
//    }
//
//    public void Blritu(View v){
//        Toast.makeText(getApplicationContext(), "ブロック率!", Toast.LENGTH_SHORT).show();
//    }


}
