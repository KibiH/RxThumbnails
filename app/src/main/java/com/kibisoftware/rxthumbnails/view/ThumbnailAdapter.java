package com.kibisoftware.rxthumbnails.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kibisoftware.rxthumbnails.R;
import com.kibisoftware.rxthumbnails.databinding.ItemMaskBinding;
import com.kibisoftware.rxthumbnails.model.MaskInfo;
import com.kibisoftware.rxthumbnails.viewmodel.ItemMaskViewModel;
import com.kibisoftware.rxthumbnails.viewmodel.MaskViewModel;

import java.util.Collections;
import java.util.List;

/**
 * Created by kibi on 07/07/17.
 */

public class ThumbnailAdapter extends RecyclerView.Adapter {

    private MaskViewModel maskViewModel;
    private List<MaskInfo> thumbnailsList;

    public ThumbnailAdapter() {
        this.thumbnailsList = Collections.emptyList();
    }

    @Override
    public ThumbnailAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMaskBinding itemMaskBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_mask,
                        parent, false);
        return new ThumbnailAdapterViewHolder(maskViewModel, itemMaskBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ThumbnailAdapterViewHolder)holder).bindMask(thumbnailsList.get(position));
    }

    @Override
    public int getItemCount() {
        return thumbnailsList.size();
    }

    public void setMaskViewModel(MaskViewModel maskViewModel) {
        this.maskViewModel = maskViewModel;
        thumbnailsList = maskViewModel.getThumbnailsList();
        notifyDataSetChanged();
    }


    public static class ThumbnailAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemMaskBinding itemMaskBinding;
        MaskViewModel maskViewModel;

        public ThumbnailAdapterViewHolder(MaskViewModel maskViewModel,ItemMaskBinding itemMaskBinding) {
            super(itemMaskBinding.itemThumbnail);
            this.itemMaskBinding = itemMaskBinding;
            this.maskViewModel = maskViewModel;
        }

        void bindMask(MaskInfo maskInfo) {
            if (itemMaskBinding.getItemMaskViewModel() == null) {
                itemMaskBinding.setItemMaskViewModel(
                        new ItemMaskViewModel(maskViewModel, maskInfo));
            } else {
                itemMaskBinding.getItemMaskViewModel().setMask(maskInfo);
            }
        }
    }
}
