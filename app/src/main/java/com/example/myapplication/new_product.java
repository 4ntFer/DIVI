package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import States.State;
import func_classes.Pessoa;
import func_classes.Sala;
import func_classes.Produto;

public class new_product extends AppCompatActivity {
    private final State instance = State.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        TextView caixaNome = (TextView)findViewById(R.id.product_name_ed);
        TextView caixaValor = (TextView)findViewById(R.id.product_value_ed);
        Button confirmButton = (Button)findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //Pega e converte os elementos contido nas caixar para os valores necessarios
                String nome = caixaNome.getText().toString();
                float val = Float.parseFloat(caixaValor.getText().toString());
                ArrayList<Pessoa> participantes = new ArrayList<Pessoa>();

                addProd(nome,val,participantes);
                voltar();
            }
        });
    }

    private void addProd(String nome, float val, ArrayList<Pessoa> participantes){
        Pessoa user = instance.getUser();
        Sala sala = instance.getSala(getIntent().getIntExtra("indexSala", 0));

        Produto novoProduto;

        participantes.add(user);
        novoProduto = new Produto(user,participantes,val, 0,nome);

        sala.addProduto(novoProduto);
    }

    private void voltar(){
        finish();
    }
}