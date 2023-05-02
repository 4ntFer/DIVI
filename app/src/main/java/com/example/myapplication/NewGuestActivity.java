package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import States.State;
import func_classes.Pessoa;
import func_classes.Produto;
import func_classes.Sala;

public class NewGuestActivity extends AppCompatActivity {
    private final State instance = State.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_guest);

        TextView caixaNome = (TextView)findViewById(R.id.ed_name_guest);
        Button button = (Button)findViewById(R.id.confirmar_button);
        int index = getIntent().getIntExtra("indexSala", 0);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = caixaNome.getText().toString();
                Sala sala = instance.getSala(index);

                sala.addParticipante(new Pessoa(name,0));
                finish();
            }
        });
    }
}