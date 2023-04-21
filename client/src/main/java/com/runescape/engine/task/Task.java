package com.runescape.engine.task;

public class Task {

    Task next;
    public volatile int status;
    int type;
    public int intArgument;
    Object objectArgument;
    public volatile Object result;

    public Task() {
        this.status = 0;
    }
}
