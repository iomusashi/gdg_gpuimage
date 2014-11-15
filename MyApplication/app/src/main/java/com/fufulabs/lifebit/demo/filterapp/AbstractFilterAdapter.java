package com.fufulabs.lifebit.demo.filterapp;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by null on 11/15/14.
 */
public class AbstractFilterAdapter extends ArrayAdapter<AbstractFilter> {

  private Context                   _context;
  private ArrayList<AbstractFilter> _filters;
  private LayoutInflater            _inflater;
  private static int _lastPosition = -1;

  protected class ViewHolder {
    int            position;
    RelativeLayout layoutFilterContainer;
    ImageView      filterImage;
    TextView       filterNameText;
  }

  public AbstractFilterAdapter(Context context, int resourceId, ArrayList<AbstractFilter> filters) {
    super(context, resourceId, filters);
    _context = context;
    _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    _filters = filters;
  }

  public static void setLastPosition(int lastPosition) {
    _lastPosition = lastPosition;
  }

  @Override
  public int getCount() {
    return _filters.size();
  }

  @Override
  public AbstractFilter getItem(int position) {
    return _filters.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View row = convertView;
    final ViewHolder holder;
    final AbstractFilter filter = getItem(position);

    if (row == null) {
      row = _inflater.inflate(R.layout.list_filter, null, true);
      holder = new ViewHolder();
      holder.layoutFilterContainer = (RelativeLayout) row.findViewById(
          R.id.layout_filter_container);
      holder.filterImage = (ImageView) row.findViewById(R.id.image_filter);
      holder.filterNameText = (TextView) row.findViewById(R.id.text_filter_name);
      row.setTag(holder);
    } else {
      holder = (ViewHolder) row.getTag();
    }

    holder.position = position;
    if (holder.position == _lastPosition) {
      holder.layoutFilterContainer.setBackgroundColor(0x222222);
    } else {
      holder.layoutFilterContainer.setBackgroundColor(0x333333);
    }

    if (filter.drawable != Integer.MIN_VALUE) {
      holder.filterImage.setImageResource(filter.drawable);
    }
    if (!TextUtils.isEmpty(filter.name)) {
      holder.filterNameText.setText(filter.name);
    }
    return row;
  }
}
