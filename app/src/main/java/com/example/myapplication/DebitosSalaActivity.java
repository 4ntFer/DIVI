package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import States.State;
import func_classes.Conta;
import func_classes.Pessoa;
import func_classes.Sala;

public class DebitosSalaActivity extends AppCompatActivity {
    private final State instance = State.getInstance();
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debitos_sala);

        LinearLayout debitosLayout = (LinearLayout)findViewById(R.id.debitos_layout);
        index = getIntent().getIntExtra("indexSala", 0);

        mostraDebitos(debitosLayout);
    }

    private void mostraDebitos(LinearLayout layout){
        ArrayList<Pessoa> part = instance.getSala(index).getParticipantes();

        for(int i = 0 ; i < part.size() ; i++){
            TextView view =  new TextView(this);

            String text;
            float debitos = 0;
            ArrayList<Conta> deb = part.get(i).getDebitos();

            for(int j = 0 ; j < deb.size() ; j++){
                debitos += deb.get(j).getValor();
            }
            text = part.get(i).getNome() + " deve ao todo R$ " + debitos;

            view.setText(text.toCharArray(),0,text.length());
            layout.addView(view);
        }
    }
}