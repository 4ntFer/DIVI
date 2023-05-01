package func_classes;

import java.util.ArrayList;

public class Produto {
     private ArrayList<Pessoa> participantes;
     private Pessoa pagador;
     private String nome;
     private int iconeId;
     private float valor;
     private ArrayList<Float> todosValores;

     public Produto(Pessoa pagador, ArrayList<Pessoa> participantes, float valor, int iconeId, String nome){
          this.pagador = pagador;
          this.participantes = participantes;
          this.valor = valor;
          this.iconeId = iconeId;
          this.nome = nome;
     }

     public String getNome(){
          return nome;
     }

     public int iconeID(){
          return  iconeId;
     }

     public float getValor(){
          return valor;
     }

     public void setIcone(int id){
          iconeId = id;
     }

     public void setNome(String nome){
          this.nome = nome;
     }

     public void addValor(float val){
          todosValores.add(val);
     }
}
