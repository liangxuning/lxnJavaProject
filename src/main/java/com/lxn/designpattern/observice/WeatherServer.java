package com.lxn.designpattern.observice;

public class WeatherServer {
    public static void main(String[] args) {
        Earth earth = new Earth();
        Satellite satellite = new Satellite();
        earth.addObserver(satellite);
        earth.setWeather("暴雨");
        earth.setWeather("黎明");
    }
}
