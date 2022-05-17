package com.example.geoquiz;

import android.widget.ImageView;
import android.widget.TextView;

public class Question {
    int textResID;
    int imgResID;

    public Question(int textResID, int imgResID) {
        this.textResID = textResID;
        this.imgResID = imgResID;
    }
}
