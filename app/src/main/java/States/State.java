package States;

import java.util.ArrayList;

import func_classes.Pessoa;
import func_classes.Sala;

public class State {
    private static State instance = null;
    private ArrayList<Sala> salas = new ArrayList<Sala>();
    //private Pessoa User = new Pessoa("Eu", 0);

    public static State getInstance() {
        if(instance == null){
            instance = new State();
        }

        return instance;
    }

    public ArrayList<Sala> getListSala(){
        return salas;
    }

    public Sala getSala(int index){
        return salas.get(index);
    }

    /*public Pessoa getUser(){
        return  User;
    }*/
}
