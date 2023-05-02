package func_classes;

import java.util.ArrayList;

public class Produto {
     private ArrayList<Pessoa> participantes;
     private Pessoa pagador;
     private String nome;
     private int iconeId;

     private int id;
     private float valor;
     private ArrayList<Float> todosValores;

     private float valorDividido;

     public Produto(int id, Pessoa pagador, ArrayList<Pessoa> participantes, float valor, int iconeId, String nome){
          this.pagador = pagador;
          this.participantes = participantes;
          this.valor = valor;
          this.iconeId = iconeId;
          this.nome = nome;
          this.id = id;

          calculaConta();
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

     public int getId(){return id;}

     public void setIcone(int id){
          iconeId = id;
     }

     public void setNome(String nome){
          this.nome = nome;
     }

     public void addValor(float val){
          todosValores.add(val);
     }

     private void calculaConta(){
          valorDividido = valor/participantes.size();
          for(int i = 0; i<participantes.size() ; i++){
               participantes.get(i).addDebito(valorDividido, null, this);
          }
     }

     public void setId(int id){
          this.id = id;
     }

     public ArrayList<Pessoa> getParticipantes(){
          return participantes;
     }

     public float getValorDividido(){
          return valorDividido;
     }
}
