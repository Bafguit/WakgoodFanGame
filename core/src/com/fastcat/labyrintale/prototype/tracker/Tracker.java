package com.fastcat.labyrintale.prototype.tracker;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Tracker<T> {
  private T t;

  public T get() {
    return t;
  }

  public boolean isEmpty() {
    return t == null;
  }
}
