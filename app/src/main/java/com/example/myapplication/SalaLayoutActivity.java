package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;

import Service.GenericCallback;
import Service.ServerConnection;
import States.State;
import func_classes.Pessoa;
import func_classes.Sala;

public class SalaLayoutActivity extends AppCompatActivity {
    private Context activity = this;
    private final State instance = State.getInstance(); //Carrega o estado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_rooms);

        Button add_room = (Button)findViewById(R.id.add_room_botton);
        LinearLayout rooms_layout = (LinearLayout) findViewById(R.id.rooms_layout);

        ArrayList<Sala> salas = instance.getListSala();

        add_room.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //cria um botão para uma nova sala
                handleClick(salas, rooms_layout);
            }
        });
    }


    private void handleClick(ArrayList<Sala> salas, LinearLayout rooms_layout) {
        String nome_sala = "Sala" + salas.size();
        Sala novaSala = new Sala(nome_sala);

        try{
            ServerConnection.createRoom(nome_sala, 1, new GenericCallback() {
                @Override
                public void execute(String str) {runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int index;
                        //Adiciona a uma nova sala à coleção de salas
                        novaSala.addParticipante(new Pessoa("Eu", 0));
                        salas.add(novaSala);
                        index  = salas.size() - 1;


                        /*Cria novo botão e define seu nome e seu
                        Listener para abrir o activity interno das salas*/
                        Button new_button = new Button(activity);

                        new_button.setText(nome_sala.toCharArray(), 0, nome_sala.length());

                        //Adiciona o botão ao layout e a coleção de botões de sala
                        rooms_layout.addView(new_button);


                        new_button.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                abrir_layout_produtos(index);
                            }
                        });
                    }
                });}
            });
        }catch (IOException e){
            Log.e("Exception", e.toString());
        }
    }

    private void abrir_layout_produtos(int indexSala){
        Intent intent_layout_produtos = new Intent(this,ProdutosLayoutActivity.class);
        intent_layout_produtos.putExtra("indexSala", indexSala);
        startActivity(intent_layout_produtos);
    }
}