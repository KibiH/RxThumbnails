package com.kibisoftware.rxthumbnails.viewmodel;

import android.databinding.BaseObservable;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kibisoftware.rxthumbnails.model.MaskInfo;

/**
 * Created by kibi on 07/07/17.
 */

public class ItemMaskViewModel extends BaseObservable {
    private MaskInfo maskInfo;
    private MaskViewModel maskViewModel;

    public ItemMaskViewModel(MaskViewModel maskViewModel, MaskInfo maskInfo) {
        this.maskViewModel = maskViewModel;
        this.maskInfo = maskInfo;
    }


    public String getMaskUrl() {
        return maskInfo.maskUrl;
    }

    public Drawable getThumbnail() {
        return maskInfo.thumbnail;
    }

    public void onItemClick(View view) {
        // make the call to our faux loader
        // but we need to do this on the main screen viewmodel since all the UI happens there

        // pull a file name out of the URL (since we aren't really using the url
        maskViewModel.requestMask(getMaskUrl().substring(getMaskUrl().length() - 10));
    }

    public void setMask(MaskInfo maskInfo) {
        this.maskInfo = maskInfo;
        notifyChange();
    }

}
