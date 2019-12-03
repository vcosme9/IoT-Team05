package com.vicent.neverapp.ui.avisos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AvisosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AvisosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}