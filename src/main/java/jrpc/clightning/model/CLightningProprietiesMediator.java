package jrpc.clightning.model;

import java.util.HashMap;
import java.util.Map;
import jrpc.clightning.exceptions.CLightningException;

public class CLightningProprietiesMediator {

  private static CLightningProprietiesMediator SINGLETON;

  public static CLightningProprietiesMediator getInstance() {
    if (SINGLETON == null) {
      SINGLETON = new CLightningProprietiesMediator();
    }
    return SINGLETON;
  }

  private Map<String, Object> repository;

  private CLightningProprietiesMediator() {
    this.repository = new HashMap<>();
  }

  public void store(String key, Object value) {
    // TODO check value
    this.repository.put(key, value);
  }

  public <T> T getValue(String key) {
    if (key == null || key.isEmpty()) {
      throw new CLightningException("Key inside the CLightningProprietiesMediator null");
    }
    if (this.repository.containsKey(key)) {
      return (T) this.repository.get(key);
    }
    return null;
  }

  public boolean containsValue(String key) {
    return this.repository.containsKey(key);
  }

  public void clean() {
    this.repository.clear();
  }
}
