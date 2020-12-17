package com.polytech.bowling;

public class Spare {
    int ttl;

    public Spare(){
        ttl = 1;
    }

    public void decrement(){
        this.ttl -= 1;
    }
    public int getTTL(){
        return ttl;
    }
}
