package com.example.lab0201;

import com.google.gson.annotations.SerializedName;

public class Example {
    @SerializedName("main")
    Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
