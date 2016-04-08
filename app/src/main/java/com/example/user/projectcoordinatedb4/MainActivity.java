package com.example.user.projectcoordinatedb4;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDB my;
    EditText id, latitude, longitude;
    TextView text1, text2, text3;
    Button btn1, btn2, btn3;
    SQLiteDatabase sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my=new myDB(this);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sql=my.getWritableDatabase();
                my.onUpgrade(sql, 1,2);
                sql.close();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sql=my.getReadableDatabase();
                Cursor cursor;
                cursor=sql.rawQuery("SELECT * FROM MEMBERDB;", null);
                String id2="아이디"+"\r\n";
                String latitude2="위도"+"\r\n";
                String longitude2="경도"+"\r\n";

                while(cursor.moveToNext()){
                    id2+=cursor.getString(0)+"\r\n";
                    latitude2+=cursor.getString(1)+"\r\n";
                    longitude2+=cursor.getString(2)+"\r\n";
                }
                id.setText(id2);
                latitude.setText(latitude2);
                longitude.setText(longitude2);
                cursor.close();
                sql.close();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sql = my.getWritableDatabase();
                sql.execSQL("INSERT INTO memberDB VALUES(" + id.getText().toString() + ",'" + latitude.getText().toString() + "','" + longitude.getText().toString() + "');");
                sql.close();
                Toast.makeText(getApplicationContext(), "정보가 저장되었습니다.", Toast.LENGTH_LONG).show();
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
