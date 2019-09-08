package com.lxn.designpattern.observice;

import java.util.Observable;

public class Earth extends Observable {
    private String weather = "晴朗";

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
        setChanged();
        notifyObservers(weather);
    }

}
