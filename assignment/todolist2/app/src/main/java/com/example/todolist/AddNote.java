package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNote extends AppCompatActivity {
    private tododbHelper dbHelper;
    private SQLiteDatabase database;
    private EditText edittext;
    private RadioGroup radiogroup;
    private RadioButton radiobutton;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);

        dbHelper = new tododbHelper(this);
        database =dbHelper.getWritableDatabase();
        radiogroup=findViewById(R.id.addnote_group);
        edittext=findViewById(R.id.addnote_text);
        button=findViewById(R.id.addnote_button);

        InputMethodManager input =(InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        if(input!=null)
        {
            input.showSoftInput(edittext,0);
        }
        radiobutton=findViewById(R.id.addnote_radioButton1);
        radiobutton.setChecked(true);

        button.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                CharSequence tt=edittext.getText();
                if(TextUtils.isEmpty(tt))
                {
                    Toast.makeText(AddNote.this,"No content",Toast.LENGTH_SHORT).show();
                    return ;
                }
                else
                {
                    if(database==null)
                    {
                        Toast.makeText(AddNote.this,"Error",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        ContentValues values=new ContentValues();
                        values.put("content",tt.toString().trim());
                        values.put("state",0);
                        values.put("date",System.currentTimeMillis());
                        values.put("priority",getpri());
                        if(database.insert("note",null,values)!=-1)
                        {
                            Toast.makeText(AddNote.this,"Succeed",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(AddNote.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                }
            }
        });


    }

    private int getpri()
    {
        switch (radiogroup.getCheckedRadioButtonId())
        {
            case R.id.addnote_radioButton1:
                return 1;
            case R.id.addnote_radioButton2:
                return 2;
            case R.id.addnote_radioButton3:
                return 3;
        }
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
        database=null;
        dbHelper.close();;
        dbHelper=null;
    }
}

