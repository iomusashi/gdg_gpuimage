package com.fufulabs.lifebit.demo.filterapp;

import android.content.Context;
import android.graphics.Bitmap;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;

/**
 * Created by null on 11/15/14.
 */
public abstract class AbstractFilter {

  public    int            drawable;
  public    String         name;
  public    GPUImageFilter filter;
  protected Bitmap         lookup;
  protected Context        context;

  public AbstractFilter(int drawable, String name) {
    this.drawable = drawable;
    this.name = name;
  }

  public abstract void initialize();

  public void release() {
    this.filter = null;

    if (lookup != null) {
      lookup.recycle();
      lookup = null;
    }
  }
}
