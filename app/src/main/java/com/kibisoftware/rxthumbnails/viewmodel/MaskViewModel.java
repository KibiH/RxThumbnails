package com.kibisoftware.rxthumbnails.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableBoolean;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.kibisoftware.rxthumbnails.Internet.FakeDownloader;
import com.kibisoftware.rxthumbnails.model.MaskInfo;
import com.kibisoftware.rxthumbnails.model.MaskModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by kibi on 07/07/17.
 */

public class MaskViewModel extends BaseObservable {

    private MaskModel maskModel;
    private FakeDownloader fakeDownloader;
    public final  ObservableBoolean isLoading = new ObservableBoolean(false);
    private String currentUrl;

    public MaskViewModel(Context context) {
        maskModel = new MaskModel(context);
        fakeDownloader = new FakeDownloader(context);

    }

    public List<MaskInfo> getThumbnailsList() {
        return maskModel.getThumbnailsList();
    }

    public Drawable getCurrentThumbnail() {
        return maskModel.getCurrentThumbnail();
    }

    public void setMask(int mask) {
        maskModel.setCurrentMask(mask);
    }

    public void onButtonClick(View view) {
        fakeDownloader.deleteDownloadedFiles();
        setMask(-1);
        notifyChange();
    }


    public void requestMask(String url) {
        currentUrl = url;

        downloaderObservable
                .observeOn(AndroidSchedulers.mainThread()).
                subscribeOn(Schedulers.io()).
                subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        isLoading.set(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        setMask(-1);
                        isLoading.set(false);
                    }

                    @Override
                    public void onComplete() {
                        isLoading.set(false);
                    }
                });

    }

    private void setDrawableByUrl(String url) {
        for (int i = 0; i< maskModel.getThumbnailsList().size(); i++) {
            MaskInfo info = maskModel.getThumbnailsList().get(i);
            if (info.maskUrl.contains(url)) {
                setMask(i);
                notifyChange();
            }
        }
    }

    private Observable<Integer> downloaderObservable = Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter emitter) throws Exception {
            // this on next is just called so I have a place to start the progress spinner
            emitter.onNext(1);
            fakeDownloader.assureDownloadedMask(currentUrl);
            setDrawableByUrl(currentUrl);
            emitter.onComplete();
        }
    });

}
