package dev.danteh.coins.utils.menu.callback;

import java.io.Serializable;

public interface TypeCallback<T> extends Serializable {
  void callback(T paramT);
}