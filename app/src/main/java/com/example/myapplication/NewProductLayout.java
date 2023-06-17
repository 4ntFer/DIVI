package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import States.State;
import func_classes.Pessoa;
import func_classes.Sala;
import func_classes.Produto;

public class NewProductLayout extends AppCompatActivity {
    private final State instance = State.getInstance();
    private int indexSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        TextView caixaNome = (TextView)findViewById(R.id.product_name_ed);
        TextView caixaValor = (TextView)findViewById(R.id.product_value_ed);
        LinearLayout partyLayout = (LinearLayout)findViewById(R.id.party_layout);
        Button confirmButton = (Button)findViewById(R.id.confirm_button);

        indexSala = getIntent().getIntExtra("indexSala", 0);

        ArrayList<CheckBox> listaCaixaParticipantes = criaCaixasParticipantes(partyLayout);

        confirmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Pega e converte os elementos contido nas caixar para os valores necessarios
                String nome = caixaNome.getText().toString();
                float val = Float.parseFloat(caixaValor.getText().toString());
                ArrayList<Pessoa> participantes = new ArrayList<Pessoa>();

                addParticipantes(listaCaixaParticipantes, participantes);
                addProd(nome,val,participantes);
                voltar();
            }
        });
    }

    private void addProd(String nome, float val, ArrayList<Pessoa> participantes){
        Sala sala = instance.getSala(getIntent().getIntExtra("indexSala", 0));
        //O primeiro a entrar na sala, será o pagante (mudar futuramente)
        Pessoa user = sala.getParticipantes().get(0);
        int prodId = sala.getProdutos().size();

        Produto novoProduto;

        novoProduto = new Produto(prodId, user,participantes,val, 0,nome);

        sala.addProduto(novoProduto);
    }

    private void voltar(){
        finish();
    }

    private ArrayList<CheckBox> criaCaixasParticipantes(LinearLayout layout){
        ArrayList<Pessoa> participantesSala = instance.getSala(indexSala).getParticipantes();
        ArrayList<CheckBox> caixasPart = new ArrayList<CheckBox>();

        for(int i = 0 ; i<participantesSala.size() ; i++){
            CheckBox userBox = new CheckBox(this);
            String name = participantesSala.get(i).getNome();

            userBox.setText(name.toCharArray(),0,name.length());
            caixasPart.add(userBox);
            layout.addView(userBox);
        }

        return caixasPart;
    }

    private void addParticipantes(ArrayList<CheckBox> part, ArrayList<Pessoa> participantesProd){
        int index = getIntent().getIntExtra("indexSala", 0);
        ArrayList<Pessoa> participantesSala = instance.getSala(index).getParticipantes();

        for(int i = 0 ; i<part.size() ; i++){

            if(part.get(i).isChecked()){
                Pessoa user = participantesSala.get(i);
                participantesProd.add(user);
            }
        }
    }
}