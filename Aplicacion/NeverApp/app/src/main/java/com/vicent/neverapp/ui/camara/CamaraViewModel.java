package com.vicent.neverapp.ui.camara;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CamaraViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CamaraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}