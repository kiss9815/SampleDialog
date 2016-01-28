package com.example.tacademy.sampledialog;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dialog");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Dialog message.....");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Cancel Click", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "No Click", Toast.LENGTH_SHORT).show();
            }
        });
//        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
//            }
//        });   // back 키가 먹으면 동작하는 코드

        builder.setCancelable(false); //back 키로 다욜로그 끄는 거 막는 것
        builder.create().show();

    }

    String[] items = {"item 1" , "item 2" , "item 3" , "item 4"};

    public void showListDialog(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Dialog");

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "item : " + items[which], Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    public void showSingle(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Dialog");

        builder.setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "item : " + items[which], Toast.LENGTH_SHORT).show();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }


    public void showMultiple(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Dialog");

        final boolean[] selectedArray = new boolean[items.length]; // 멀피초이스 사용법
        selectedArray[0] = true;
        selectedArray[2] = true;

        builder.setMultiChoiceItems(items, selectedArray, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selectedArray[which] = isChecked;
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StringBuilder sb = new StringBuilder();
                for(int i =0; i<selectedArray.length; i++){
                    if(selectedArray[i]){
                        sb.append(items[i]).append(", ");
                    }
                }
                Toast.makeText(MainActivity.this, "Yes Click : " + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    Handler mHandler = new Handler(Looper.getMainLooper());
    public void showProgress(View view){
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("download....");
        dialog.setMessage("file downloading....");
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
    }

    public void showProgressHorizontal(View view) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL); // dialog style 바꾸기
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("download....");
        dialog.setMessage("file downloading...");
        dialog.setMax(100);
        dialog.show();

        dialog.setProgress(50);
        dialog.setSecondaryProgress(70);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 2000);
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
