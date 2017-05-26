package com.example.gisle.prosjektoppgave;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by gisle on 20.04.2017.
 */

public class BoardAdapter extends BaseAdapter {

    private Context mContext;

    public BoardAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return 9;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new Button(mContext);
            button.setLayoutParams(new GridView.LayoutParams(parent.getWidth() / 3, parent.getWidth() / 3));

            button.setFocusable(false);
            button.setFocusableInTouchMode(false);
            button.setClickable(false);

            button.setTextSize(60);
        } else {
            button = (Button) convertView;
        }

        return button;
    }
}
