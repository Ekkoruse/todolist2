package com.example.todolist;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
    private CheckBox checkbox;
    private TextView contenttext;
    private TextView datetext;
    private View deletebtn;

    private final Operators operators;

    public NoteViewHolder(@NonNull View itemView, Operators operators) {
        super(itemView);

        checkbox = itemView.findViewById(R.id.checkbox);
        contenttext = itemView.findViewById(R.id.text_content);
        datetext = itemView.findViewById(R.id.text_date);
        deletebtn = itemView.findViewById(R.id.btn_delete);
        this.operators = operators;
    }

    public void bind(final Note note)
    {
        contenttext.setText(note.content);
        datetext.setText(SIMPLE_DATE_FORMAT.format(note.date));
        checkbox.setOnCheckedChangeListener(null);
        checkbox.setChecked(note.state!=0);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                note.state=b?1:0;
                operators.updatenote(note);
            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operators.deletenote(note);
            }
        });

        if(note.state==1)
        {
            contenttext.setTextColor(Color.GRAY);
            contenttext.setPaintFlags(contenttext.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }
        else
        {
            contenttext.setTextColor(Color.BLACK);
            contenttext.setPaintFlags(contenttext.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        int color;
        if(note.priority==1)
        {
            color=Color.RED;
        }
        else if(note.priority==2)
        {
            color=Color.YELLOW;
        }
        else
        {
            color=Color.GREEN;
        }

        itemView.setBackgroundColor(color);
    }
}
