package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import States.State;
import func_classes.Pessoa;
import func_classes.Produto;

public class DetalhesProdutoActivity extends AppCompatActivity {
    private final State instance = State.getInstance();
    private int indexSala;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);

        LinearLayout detailsLayout = (LinearLayout)findViewById(R.id.details_layout);

        indexSala = getIntent().getIntExtra("indexSala", 0);
        id = getIntent().getIntExtra("id", 0);

        mostraDebitos(detailsLayout);
    }

    private void mostraDebitos(LinearLayout layout){
        Produto prod = encontraProduto();
        ArrayList<Pessoa> participantes = prod.getParticipantes();

        for(int i = 0 ; i<participantes.size() ; i++){
            TextView view = new TextView(this);
            String text = participantes.get(i).getNome() + " deve R$ " + prod.getValorDividido();

            view.setText(text);
            layout.addView(view);
        }

    }

    private Produto encontraProduto() {
        for(int i = 0; i<instance.getSala(indexSala).getProdutos().size() ; i++){
            if(id == instance.getSala(indexSala).getProdutos().get(i).getId()){
                return instance.getSala(indexSala).getProdutos().get(i);
            }
        }

        return null;
    }
}