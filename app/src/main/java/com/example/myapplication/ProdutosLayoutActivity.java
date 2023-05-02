package com.example.myapplication;

import android.content.Context;
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

public class ProdutosLayoutActivity extends AppCompatActivity {
    private final State instance = State.getInstance(); //Carreaga o estado
    private Context activity = this;
    private int indexSala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_products);

        Button add_product = (Button)findViewById(R.id.add_product_button);
        Button add_user = (Button)findViewById(R.id.add_user_button);
        Button mostrarDeb = (Button)findViewById(R.id.mostrar_debitos);
        LinearLayout product_layout = (LinearLayout) findViewById (R.id.products_layout);

        indexSala = getIntent().getIntExtra("indexSala", 0);
        Sala sala = instance.getSala(indexSala);
        ArrayList<Produto> produtos = sala.getProdutos();

        criaBotoes(product_layout, produtos);

        add_user.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent_leyout_add_convidado = new Intent(activity, NewGuestActivity.class);
                openNewActivity(intent_leyout_add_convidado);
            }
        });

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_leyout_criar_produto = new Intent(activity, NewProductLayout.class);
                openNewActivity(intent_leyout_criar_produto);
            }
        });

        mostrarDeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_leayout_debitos = new Intent(activity, DebitosSalaActivity.class);
                openNewActivity(intent_leayout_debitos);
            }
        });
    }

    private void criaBotoes(LinearLayout product_layout, ArrayList<Produto> produtos) {

        for(int i = 0; i < produtos.size() ; i++){
            Button newButton = new Button(this);
            Produto produto = produtos.get(i);
            String nomeProd = produto.getNome();
            int id = i;

            newButton.setText(nomeProd.toCharArray(), 0, nomeProd.length());

            newButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intentProd = new Intent(activity, DetalhesProdutoActivity.class);
                    intentProd.putExtra("id",id);
                    openNewActivity(intentProd);
                }
            });

            product_layout.addView(newButton);
        }
    }

    private void openNewActivity(Intent activity){
        activity.putExtra("indexSala", indexSala);
        startActivityForResult(activity, 0);
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        recreate();
    }
}