package com.lupinesoft.roomdatabase_note_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InsertActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    public static final String NOTE_ADDED = "note_add";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        editText = findViewById(R.id.edittext_addNote_ID);
        button = findViewById(R.id.button_addNote_ID);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resIntent = new Intent();
                if(TextUtils.isEmpty(editText.getText())){
                    setResult(RESULT_CANCELED, resIntent);
                }else{
                    String strNote = editText.getText().toString();
                    resIntent.putExtra(NOTE_ADDED, strNote);
                    setResult(RESULT_OK, resIntent);
                }
                finish();
            }
        });
    }
}
