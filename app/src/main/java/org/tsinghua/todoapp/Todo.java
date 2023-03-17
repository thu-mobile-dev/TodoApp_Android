package org.tsinghua.todoapp;

public class Todo {
    private int number = 0;
    private String content = "";
    private String detail = "";

    public Todo(int number, String content) {
        this.number = number;
        this.content = content;
    }

    public Todo(int number, String content, String detail) {
        this.number = number;
        this.content = content;
        this.detail = detail;
    }


    public int getNumber() {
        return number;
    }

    public String getContent() {
        return content;
    }

    public String getDetail() {
        return detail;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
