package func_classes;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private int iconId;
    private ArrayList<Conta> debitos;
    private ArrayList<Conta> creditos;
    private boolean admin;

    public Pessoa(String nome, int iconId){
        this.nome = nome;
        this.iconId = iconId;
        debitos = new ArrayList<Conta>();
        creditos = new ArrayList<Conta>();
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

    public ArrayList<Conta> getDebitos(){
        return debitos;
    }

    public ArrayList<Conta> getCreditos(){
        return creditos;
    }

    public void addDebito(float val, Pessoa relacionado, Produto prod){
        debitos.add(new Conta(val,relacionado,prod));
    }

    public void addCredito(float val, Pessoa relacionado, Produto prod){
        creditos.add(new Conta(val,relacionado,prod));
    }

    public float getDebitoTotal(){
        float debitoTotal = 0;
        for(int i = 0; i < debitos.size() ; i++){
            debitoTotal += debitos.get(i).getValor();
        }
        return debitoTotal;
    }
}
