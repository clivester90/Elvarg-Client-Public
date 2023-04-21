package com.runescape.engine.task.impl;

import com.runescape.Client;
import com.runescape.engine.task.Clock;
import com.runescape.engine.task.TaskUtils;

public class NanoClock extends Clock {

    long lastTimeNano;
    private long tmpNanoTime;

    public NanoClock() {
        this.lastTimeNano = System.nanoTime();
    }

    public void mark() {
        this.lastTimeNano = System.nanoTime();
    }

    public int wait(int var1, int var2) {

        long var3 = (long) var2 * 1000000L;
        long var5 = this.lastTimeNano - System.nanoTime();
        if (var5 < var3) {
            var5 = var3;
        }

        TaskUtils.sleep(var5 / 1000000L);
        long var7 = System.nanoTime();

        int var9;
        for (var9 = 0; var9 < 10 && (var9 < 1 || this.lastTimeNano < var7); this.lastTimeNano += (long) var1 * 1000000L) {
            ++var9;
        }

        if (this.lastTimeNano < var7) {
            this.lastTimeNano = var7;
        }

        return var9;

    }

}
