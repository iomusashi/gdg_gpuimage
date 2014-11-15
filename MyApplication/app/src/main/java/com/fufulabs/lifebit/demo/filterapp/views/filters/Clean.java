package com.fufulabs.lifebit.demo.filterapp.views.filters;

import android.content.Context;

import com.fufulabs.lifebit.demo.filterapp.AbstractFilter;
import com.fufulabs.lifebit.demo.filterapp.R;

import java.util.LinkedList;
import java.util.List;

import jp.co.cyberagent.android.gpuimage.GPUImageBrightnessFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageContrastFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageFilterGroup;
import jp.co.cyberagent.android.gpuimage.GPUImageSharpenFilter;

/**
 * Created by null on 11/15/14.
 */
public class Clean extends AbstractFilter {

  public Clean(Context context) {
    super(R.drawable.clean, Clean.class.getSimpleName());
    this.context = context;
  }

  @Override
  public void initialize() {
    List<GPUImageFilter> filters = new LinkedList<GPUImageFilter>();
    filters.add(new GPUImageBrightnessFilter(0.1f));
    filters.add(new GPUImageContrastFilter(1.1f));
    filters.add(new GPUImageSharpenFilter(0.3f));
    this.filter = new GPUImageFilterGroup(filters);
  }
}
