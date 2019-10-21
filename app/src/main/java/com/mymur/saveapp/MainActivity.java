package com.mymur.saveapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button setTextBtn;
    TextView textView;
    SharedPreferences myPreferences;
    SharedPreferences.Editor myEditor;

    String textValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate");
        setContentView(R.layout.activity_main);
        setTextBtn = findViewById(R.id.setTextBtn);
        textView = findViewById(R.id.textView);

        myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        myEditor = myPreferences.edit();

        textValue = myPreferences.getString("TextValue", null);
        if (textValue != null) {
            textView.setText(textValue);
        }



    }
        @Override
        protected void onStop(){
        super.onStop();
        myEditor.putString("TextValue", textView.getText().toString());
        myEditor.commit();
        Log.d("MainActivity", "onStop");
        }




    //метод изменения содержимого в текствью
    public void onSetTxtBtnClick(View view) {
        final Context context = view.getContext();
        final EditText input = new EditText(context);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Dialog");
            builder.setView(input);
            // Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textView.setText(input.getText().toString());

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }




}
