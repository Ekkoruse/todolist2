package com.example.todolist;

import java.util.Date;

public class Note
{
    public final long id;
    public Date date;
    public int state;
    public String content;
    public int priority;

    public Note(long id) {
        this.id = id;
    }
}
