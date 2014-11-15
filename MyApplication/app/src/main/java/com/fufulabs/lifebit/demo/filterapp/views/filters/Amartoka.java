package com.fufulabs.lifebit.demo.filterapp.views.filters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.fufulabs.lifebit.demo.filterapp.AbstractFilter;
import com.fufulabs.lifebit.demo.filterapp.R;

import jp.co.cyberagent.android.gpuimage.GPUImageLookupFilter;

/**
 * Created by null on 11/15/14.
 */
public class Amartoka extends AbstractFilter {

  public Amartoka(Context context) {
    super(R.drawable.amarto, Amartoka.class.getSimpleName());
    this.context = context;
  }

  @Override
  public void initialize() {
    GPUImageLookupFilter amartoka = new GPUImageLookupFilter();
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.RGB_565;
    lookup = BitmapFactory.decodeResource(context.getResources(), R.drawable.lookup_amatorka,
        options);
    amartoka.setBitmap(lookup);
    this.filter = amartoka;
  }
}
