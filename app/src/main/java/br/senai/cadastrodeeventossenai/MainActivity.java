package br.senai.cadastrodeeventossenai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.time.LocalDate;
import java.util.ArrayList;

import br.senai.cadastrodeeventossenai.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private ListView listaDeEventos;
    private ArrayAdapter<Evento> adapterEventos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Eventos");


        listaDeEventos = findViewById(R.id.ListView_eventos);

        ArrayList<Evento> eventos = this.CriarlistaDeEventos();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, eventos);

        listaDeEventos.setAdapter(adapterEventos);



    }

    private ArrayList<Evento> CriarlistaDeEventos() {

        ArrayList<Evento> eventos = new ArrayList<Evento>();
        eventos.add(new Evento("Aula de POO", LocalDate.of(2020, 10, 19), "Senai"));
        return eventos;

    }


    public void OnClickAgendarEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastrarEvento.class);
        startActivity(intent);
    }



}