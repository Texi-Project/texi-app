package com.texi.app.domain;

import java.io.Serializable;

public class Payload <T extends Serializable> {
    T t;

    public Payload(T t) {
        this.t = t;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
