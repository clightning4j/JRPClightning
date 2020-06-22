package jrpc.clightning.model;

import java.util.HashMap;
import java.util.Map;

public class CLightningModelMediator {

    private static CLightningModelMediator SINGLETON;

    public static CLightningModelMediator getInstance(){
        if(SINGLETON == null){
            SINGLETON = new CLightningModelMediator();
        }
        return SINGLETON;
    }

    private Map<String, Object> repository;

    private CLightningModelMediator(){
        this.repository = new HashMap<>();
    }

    public void store(String key, Object value){
        //TODO check value
        this.repository.put(key, value);
    }

    public Object getValue(String key){
        //TODO check value
        if(this.repository.containsKey(key)){
            return this.repository.get(key);
        }
        return null;
    }

    public boolean containsValue(String key){
        return this.repository.containsKey(key);
    }
}
