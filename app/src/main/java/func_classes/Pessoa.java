package func_classes;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private int iconId;
    private ArrayList<Conta> debito;
    private ArrayList<Conta> credito;
    private boolean admin;

    public Pessoa(String nome, int iconId){
        this.nome = nome;
        this.iconId = iconId;
    }

    public String getNome(){
        return nome;
    }

    public int getIconId(){
        return iconId;
    }

    public boolean isAdmin(){
        return admin;
    }

    public ArrayList<Conta> getDebito(){
        return debito;
    }

    public ArrayList<Conta> getCredito(){
        return credito;
    }

    public void addDebito(float val, Pessoa relacionado, Produto prod){
        debito.add(new Conta(val,relacionado,prod));
    }

    public void addCredito(float val, Pessoa relacionado, Produto prod){
        credito.add(new Conta(val,relacionado,prod));
    }
}
