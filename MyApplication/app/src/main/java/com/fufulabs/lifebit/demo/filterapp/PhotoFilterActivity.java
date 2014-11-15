package com.fufulabs.lifebit.demo.filterapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.fufulabs.lifebit.demo.filterapp.views.HorizontalListView;
import com.fufulabs.lifebit.demo.filterapp.views.filters.Amartoka;
import com.fufulabs.lifebit.demo.filterapp.views.filters.Clean;
import com.fufulabs.lifebit.demo.filterapp.views.filters.RGB;

import java.util.ArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class PhotoFilterActivity extends Activity {

  private static final int REQUEST_PICK_PHOTO = 1;
  private GPUImageView              _gpuImage;
  private ImageButton               _saveButton;
  private HorizontalListView        _filtersList;
  private ArrayList<AbstractFilter> _filters;
  private AbstractFilterAdapter     _adapter;
  private AbstractFilter            _lastUsedFilter;
  private int                       _lastFilterIndex;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_photo_filter);

    _gpuImage = (GPUImageView) findViewById(R.id.image_gpu);
    _saveButton = (ImageButton) findViewById(R.id.button_save);
    _saveButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        final String fileName = System.currentTimeMillis() + ".jpg";
        _gpuImage.saveToPictures(getString(R.string.app_name), fileName, new GPUImageView
            .OnPictureSavedListener() {
          @Override
          public void onPictureSaved(Uri uri) {
            Toast.makeText(PhotoFilterActivity.this, fileName + " saved.", Toast.LENGTH_LONG)
                .show();
          }
        });
      }
    });
    _filtersList = (HorizontalListView) findViewById(R.id.list_filters);

    initFilters();
    initAdapter();
    pickPhoto();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode) {
    case REQUEST_PICK_PHOTO:
      if (resultCode == RESULT_OK) {
        _gpuImage.setImage(data.getData());
      } else {
        finish();
      }
      break;
    default:
      super.onActivityResult(requestCode, resultCode, data);
      break;
    }
  }

  private void pickPhoto() {
    Intent actionPick = new Intent(Intent.ACTION_PICK);
    actionPick.setType("image/*");
    startActivityForResult(actionPick, REQUEST_PICK_PHOTO);
  }

  private void initFilters() {
    RGB rgb = new RGB();
    Clean clean = new Clean(this);
    Amartoka amartoka = new Amartoka(this);
    _filters = new ArrayList<AbstractFilter>();
    _filters.add(rgb);
    _filters.add(clean);
    _filters.add(amartoka);
  }

  private void initAdapter() {
    _adapter = new AbstractFilterAdapter(this, R.layout.list_filter, _filters);
    _lastFilterIndex = 0;
    _lastUsedFilter = _filters.get(_lastFilterIndex);
    AbstractFilterAdapter.setLastPosition(_lastFilterIndex);
    _filtersList.setAdapter(_adapter);
    _filtersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
          if (position == _lastFilterIndex) {
            return;
          }
          _lastFilterIndex = position;
          AbstractFilterAdapter.setLastPosition(position);
          _adapter.notifyDataSetChanged();

          _filters.get(position).initialize();
          GPUImageFilter imageFilter = _filters.get(position).filter;

          _gpuImage.setFilter(imageFilter);
          _gpuImage.requestRender();


          if (_lastUsedFilter == null) {
            _lastUsedFilter = _filters.get(position);
          } else {
            _lastUsedFilter.release();
            _lastUsedFilter = _filters.get(position);
          }

        } catch (OutOfMemoryError e) {
          Log.e("Filter", "Error", e);
        }
      }
    });
  }
}
