package org.firstinspires.ftc.teamcode.xcentrics.util;

public abstract class QUS {
    boolean needs_write = false;

    public void update() {
        if (needs_write) {
            write();
            needs_write = false;
        }
    }

    protected abstract void write();

}