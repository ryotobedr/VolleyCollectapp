package com.example.ryoma.volleycollectapp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by ryoma on 2015/12/21.
 */
public class ShowDatebase extends Activity {

    public ShowDatebase(){}

    //final private int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
    //final private int MP = ViewGroup.LayoutParams.MATCH_PARENT;

    @Override
    protected void onCreate(Bundle savedInstanceStace) {
        super.onCreate(savedInstanceStace);
        setContentView(R.layout.show_database);

        TableLayout tablelayout = new TableLayout(this);
        setContentView(tablelayout);

        TableRow tableRow = new TableRow(this);
        tablelayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView textView = new TextView(this);
        textView.setText(String.format(" 背番号     選手名"));
        textView.setTextSize(25.0f);
        tableRow.addView(textView);

        /*
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);
        */

//        TextView uniformNumber = (TextView) findViewById(R.id.UniformNumber);
//        setContentView(uniformNumber);
//        TextView playerName = (TextView) findViewById(R.id.PlayerName);
//        setContentView(playerName);



        UserOpenHelper helper = new UserOpenHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        // queryメソッドの実行例
        Cursor c = db.query("users", new String [] { "number", "name" }, null, null, null, null, null);

        //ここから続き
        boolean mov = c.moveToFirst();
        while (mov){
            /*
            TableRow tableRow = new TableRow(this);
            tablelayout.addView(tableRow, new TableLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            TextView uniformNumber = new TextView(this);
            uniformNumber.setText(String.format("%d", c.getInt(0)));
            */


            TextView player = new TextView(this);
            player.setText(String.format("      %d          %s", c.getInt(0), c.getString(1)));
            player.setTextSize(25.0f);
            mov = c.moveToNext();
            tablelayout.addView(player);

        }
        c.close();
        db.close();

    }

}
