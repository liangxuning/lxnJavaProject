package observice;

import java.util.Observable;
import java.util.Observer;

public class Satellite implements Observer {
    private String weather;
    @Override
    public void update(Observable o, Object arg) {
        weather = (String) arg;
        System.out.println("当前天气" + weather);
    }
}
