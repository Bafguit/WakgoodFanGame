package com.fastcat.labyrintale.interfaces;

public interface EventCallback<T> {
  void onStart(T obj);

  void onComplete(T obj);
}
