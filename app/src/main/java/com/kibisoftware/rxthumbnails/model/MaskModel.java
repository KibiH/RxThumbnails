package com.kibisoftware.rxthumbnails.model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.kibisoftware.rxthumbnails.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kibi on 08/07/17.
 */

public class MaskModel {

    // don't think we would normally have context in a model but added here
    // because an appcontext won't load drawables
    private Context context;
    private List<MaskInfo> thumbnailsList;

    // this is the position for the mask shown on screen
    private int currentPosition = -1;

    private int[] drawables = new int[] {
            R.drawable.thumbnail_1,
            R.drawable.thumbnail_2,
            R.drawable.thumbnail_3,
            R.drawable.thumbnail_4,
            R.drawable.thumbnail_5,
            R.drawable.thumbnail_6,
            R.drawable.thumbnail_7,
            R.drawable.thumbnail_8,
            R.drawable.thumbnail_9,
            R.drawable.thumbnail_10,
            R.drawable.thumbnail_11,
            R.drawable.thumbnail_12
    };

    private static final String URL_BASE = "http://www.fakeurl.com/maskinfo_";

    public MaskModel(Context context) {
        this.context = context;
        thumbnailsList = new ArrayList<MaskInfo>();

        loadThumbnailsList();
    }

    private void loadThumbnailsList() {

        for (int i = 0; i < drawables.length; i++) {
            MaskInfo info = new MaskInfo();

            info.thumbnail = ContextCompat.getDrawable(context, drawables[i]);
            info.maskUrl = new String(URL_BASE + i + "file");
            thumbnailsList.add(info);
        }
    }

    public List<MaskInfo> getThumbnailsList() {
        return thumbnailsList;
    }


    public void setCurrentMask(int current) {
        currentPosition = current;
        if (currentPosition >= thumbnailsList.size() || currentPosition < 0) {
            currentPosition = -1;
        }
    }

    public Drawable getCurrentThumbnail() {
        if (currentPosition > -1) {
            return thumbnailsList.get(currentPosition).thumbnail;
        }
        return null;
    }
}
