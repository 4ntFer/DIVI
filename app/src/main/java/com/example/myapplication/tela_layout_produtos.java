package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import States.State;
import func_classes.Produto;
import func_classes.Sala;

public class tela_layout_produtos extends AppCompatActivity {
    State instance = State.getInstance(); //Carreaga o estado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_products);

        Button add_product = (Button)findViewById(R.id.add_product_button);
        LinearLayout product_layout = (LinearLayout) findViewById (R.id.products_layout);

        Sala sala = instance.getSala(getIntent().getIntExtra("indexSala", 0));
        ArrayList<Produto> produtos = sala.getProdutos();

        criaBotoes(product_layout, produtos);


        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrir_criar_produto(getIntent().getIntExtra("indexSala", 0));
            }
        });
    }

    private void criaBotoes(LinearLayout product_layout, ArrayList<Produto> produtos) {
        for(int i = 0; i < produtos.size() ; i++){
            Button newButton = new Button(this);
            Produto produto = produtos.get(i);
            String nomeProd = produto.getNome();

            newButton.setText(nomeProd.toCharArray(), 0, nomeProd.length());
            newButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {

                }
            });

            product_layout.addView(newButton);
        }
    }

    private void abrir_criar_produto(int indexSala){
        Intent intent_leyout_criar_produto = new Intent(this, new_product.class);
        int index = getIntent().getIntExtra("indexSala", 0);
        intent_leyout_criar_produto.putExtra("indexSala", indexSala);
        startActivityForResult(intent_leyout_criar_produto, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        recreate();
    }
}