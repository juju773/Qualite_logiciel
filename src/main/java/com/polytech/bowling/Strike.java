package com.polytech.bowling;

public class Strike {
    
    int ttl;

    public Strike(){
        ttl = 2;
    }

    public void decrement(){
        this.ttl -= 1;
    }
    public int getTTL(){
        return ttl;
    }
}
