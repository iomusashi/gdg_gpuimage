package com.fufulabs.lifebit.demo.filterapp.views.filters;

import com.fufulabs.lifebit.demo.filterapp.AbstractFilter;
import com.fufulabs.lifebit.demo.filterapp.R;

import jp.co.cyberagent.android.gpuimage.GPUImageColorMatrixFilter;

/**
 * Created by null on 11/15/14.
 */
public class RGB extends AbstractFilter {
  public RGB() {
    super(R.drawable.rgb, RGB.class.getSimpleName());
  }

  @Override
  public void initialize() {
    this.filter = new GPUImageColorMatrixFilter();
  }
}
