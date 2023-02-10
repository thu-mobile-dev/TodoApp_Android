package org.tsinghua.todoapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_table")
public class Todo {
    @PrimaryKey
    private int number = 0;
    private String content = "";

    public Todo(int number, String content) {
        this.number = number;
        this.content = content;
    }


    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }
}
