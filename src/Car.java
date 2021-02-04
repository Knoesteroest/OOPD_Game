
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.sound.Sound;

public class Car extends GameEngine{
    String name;
    String model;
    int wheels;
    int doors;
    int aantalInzittenden = 3;

    public static void main(String[] args) {
        Car car = new Car();

        car.runSketch();
    }

    @Override
    public void setupGame() {
        Sound toeter = new Sound(this, "sound/horn.mp3");
        toeter.loop(0);
        toeter.play();
    }

    @Override
    public void update() {

    }
}

