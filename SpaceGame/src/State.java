/**
 * all the super class of states
 */

import java.awt.*;

public abstract class State {

     private static State currentState = null;

     public State() {

     }

     public static void setState(State state){
          currentState = state;
     }

     public static State getState(){
          return currentState;
     }

     //CLASS
     protected GamePanel game;

     public State(GamePanel game){
          this.game = game;
     }

     public abstract void tick();

     public abstract void render(Graphics g);


}
