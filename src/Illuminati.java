import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;


//Illuminati titel voor het spel?
public class Illuminati extends GameEngine{

    public static void main(String[] args) {
        Illuminati game = new Illuminati();
        game.runSketch();
    }

    @Override
    public void setupGame() {
        int schermBreedte = 900;
        int schermHoogte = 900;
        View view = new View(schermBreedte, schermHoogte);

        setView(view);
        size(schermBreedte, schermHoogte);

        Player speler1 = new Player(450,450,25,25);
        addGameObject(speler1);
    }

    @Override
    public void update() {

    }
}
