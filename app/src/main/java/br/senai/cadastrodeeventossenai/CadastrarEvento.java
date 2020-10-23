package br.senai.cadastrodeeventossenai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.senai.cadastrodeeventossenai.database.EventoDAO;
import br.senai.cadastrodeeventossenai.modelo.Evento;

public class CadastrarEvento extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        setTitle("Agendamento de Eventos");
        carregarEvento();

        ///final EditText editTextData = (EditText) findViewById(R.id.editText_data);
        ///editTextData.addTextChangedListener(Mask.insert("##/##/####", editTextData));
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());///.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            editTextLocal.setText(evento.getLocal());
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v) {finish();}

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou) {
            Toast.makeText(CadastrarEvento.this, "Evento Agendado com Sucesso!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(CadastrarEvento.this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        eventoDAO.excluir(evento);
        Toast.makeText(CadastrarEvento.this, "Agendamento excluído com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}