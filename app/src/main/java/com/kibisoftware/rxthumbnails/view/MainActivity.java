package com.kibisoftware.rxthumbnails.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;

import com.kibisoftware.rxthumbnails.R;
import com.kibisoftware.rxthumbnails.databinding.ActivityMainBinding;
import com.kibisoftware.rxthumbnails.viewmodel.MaskViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding thumbnailActivityBinding;
    private MaskViewModel maskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDataBinding();
        setupListThumbnailView(thumbnailActivityBinding.maskList);
    }

    private void initDataBinding() {
        thumbnailActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        maskViewModel = new MaskViewModel(this);

        thumbnailActivityBinding.setMaskViewModel(maskViewModel);
    }

    @Override
    public void onResume() {
        super.onResume();
        ThumbnailAdapter thumbnailAdapter = (ThumbnailAdapter) thumbnailActivityBinding.maskList.getAdapter();

        // I'm passing the viewModel to the adapter so I can pass it to the viewmodel of the list
        // items. This seems inelegant to me, but I was unclear how to connect viewmodel actions
        // on the list with viewmodel stuff in the main view... this probably is not good
        thumbnailAdapter.setMaskViewModel(maskViewModel);

    }

    private void setupListThumbnailView(RecyclerView maskList) {
        ThumbnailAdapter adapter = new ThumbnailAdapter();
        maskList.setAdapter(adapter);
        maskList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}
