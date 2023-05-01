package func_classes;

import java.util.ArrayList;

public class Sala {
    private ArrayList<Pessoa> participantes;
    private ArrayList<Produto> produtos;
    private float valorTotal;
    private String nome;

    public Sala(String nome){
        this.nome = nome;
        this.produtos = new ArrayList<Produto>();
    }

    public void addProduto(Produto produto){
        produtos.add(produto);
    }

    public ArrayList<Produto> getProdutos(){
        return produtos;
    }

    public float getTotal(){
        return valorTotal;
    }

    public ArrayList<Pessoa> getParticipantes(){
        return participantes;
    }

    public void addParticipante(Pessoa novoParticipante){
        participantes.add(novoParticipante);
    }

    public void addConvidado(String nome, int iconID){
        participantes.add(new Pessoa(nome,iconID));
    }

    public String getNome(){
        return nome;
    }

}
