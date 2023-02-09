package org.tsinghua.todoapp;

public class Todo {
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
