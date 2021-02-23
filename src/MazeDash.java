import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;


//MazeDash titel voor het spel?
public class MazeDash extends GameEngine{

    public static void main(String[] args) {
        MazeDash game = new MazeDash();
        game.runSketch();
    }

    //@Override
    public void setupGame() {
        int schermBreedte = 900;
        int schermHoogte = 900;
        View view = new View(schermBreedte, schermHoogte);

        setView(view);
        size(schermBreedte, schermHoogte);

        Player speler1 = new Player(450,450);
        addGameObject(speler1);
        PowerUp koekje = new PowerUp(350,350,25,25, this);
        addGameObject(koekje);

    }

    //@Override
    public void update() {

    }
}
