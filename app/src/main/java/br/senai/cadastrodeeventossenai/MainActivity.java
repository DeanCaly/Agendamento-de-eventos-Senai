package br.senai.cadastrodeeventossenai;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.time.LocalDate;
import java.util.ArrayList;

import br.senai.cadastrodeeventossenai.modelo.Evento;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE_NOVO_EVENTO = 1;
    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int REQUEST_CODE_EDITAR_EVENTO = 2;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_EXCLUIDO = 12;

    private ListView listaDeEventos;
    private ArrayAdapter<Evento> adapterEventos;
    private int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Cadastro de Eventos");


        listaDeEventos = findViewById(R.id.ListView_eventos);
        ArrayList<Evento> eventos = new ArrayList<Evento>();

        adapterEventos = new ArrayAdapter<Evento>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, eventos);
        listaDeEventos.setAdapter(adapterEventos);

        definirOnClickListenerListView();
    }

    private void definirOnClickListenerListView() {
        listaDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEventos.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastrarEvento.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivityForResult(intent, REQUEST_CODE_EDITAR_EVENTO);
            }
        });
    }

    public void OnClickAgendarEvento(View v) {
        Intent intent = new Intent(MainActivity.this, CadastrarEvento.class);
        startActivityForResult(intent, REQUEST_CODE_NOVO_EVENTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_NOVO_EVENTO && resultCode == RESULT_CODE_NOVO_EVENTO) {
            Evento evento = (Evento) data.getExtras().getSerializable("novoEvento");
            id = id + 1;
            evento.setId(id);
            this.adapterEventos.add(evento);
        } else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EDITADO) {
            Evento eventoEditado = (Evento) data.getExtras().getSerializable("eventoEditado");
            for (int i = 0; i < adapterEventos.getCount(); i++) {
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId() == eventoEditado.getId()) {
                    adapterEventos.remove(evento);
                    adapterEventos.insert(eventoEditado, i);
                    break;
                }
            }
        } else if (requestCode == REQUEST_CODE_EDITAR_EVENTO && resultCode == RESULT_CODE_EVENTO_EXCLUIDO) {
            Evento eventoExcluido = (Evento) data.getExtras().getSerializable("eventoExcluido");
            for (int i = 0; i < adapterEventos.getCount(); i++) {
                Evento evento = adapterEventos.getItem(i);
                if (evento.getId() == eventoExcluido.getId()) {
                    adapterEventos.remove(evento);
                    break;
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}