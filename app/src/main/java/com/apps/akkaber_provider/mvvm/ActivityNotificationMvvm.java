package com.apps.akkaber_provider.mvvm;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.apps.akkaber_provider.model.NotificationModel;
import com.apps.akkaber_provider.model.UserModel;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ActivityNotificationMvvm extends AndroidViewModel {
    private static final String TAG = "ActivityNotiMvvm";
    private Context context;

    private MutableLiveData<List<NotificationModel>> notificationLiveData;
    private MutableLiveData<Boolean> isLoadingLivData;

    private CompositeDisposable disposable = new CompositeDisposable();


    public ActivityNotificationMvvm(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public MutableLiveData<List<NotificationModel>> getNotification() {
        if (notificationLiveData == null) {
            notificationLiveData = new MutableLiveData<>();
        }
        return notificationLiveData;
    }


    public MutableLiveData<Boolean> getIsLoading() {
        if (isLoadingLivData == null) {
            isLoadingLivData = new MutableLiveData<>();
        }
        return isLoadingLivData;
    }

    //_________________________hitting api_________________________________

    public void getNotifications(UserModel userModel) {
        isLoadingLivData.setValue(true);

       /* Api.getService(Tags.base_url)
                .getNotifications("Bearer " + userModel.getData().getToken(), Tags.api_key, userModel.getData().getId() + "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Response<NotificationDataModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable.add(d);
                    }

                    @Override
                    public void onSuccess(@NonNull Response<NotificationDataModel> response) {
                        isLoadingLivData.setValue(false);
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                if (response.body().getStatus() == 200) {
                                    notificationLiveData.setValue(response.body().getData());
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d(TAG, "onError: ", e);
                    }
                });*/

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }

}
