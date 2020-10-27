package br.senai.cadastrodeeventossenai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        final EditText editTextData = (EditText) findViewById(R.id.editText_data);
        editTextData.addTextChangedListener(Mask.insert("##/##/####", editTextData));


        Button btn_salvar = (Button) findViewById(R.id.btn_salvar);
        btn_salvar.setOnClickListener(new View.OnClickListener() {

            public String validar() {
                String campoVazio = "";
                EditText editTextNome = (EditText) findViewById(R.id.editText_nome);
                EditText editTextData = (EditText) findViewById(R.id.editText_data);
                EditText editTextLocal = (EditText) findViewById(R.id.editText_local);

                if (editTextNome.getText().toString().equals("")) {
                    campoVazio = "Campo Nome é obrigatório. ";
                    editTextNome.setError("Este campo é obrigatório");
                }
                if (editTextLocal.getText().toString().equals("")) {
                    campoVazio = "Campo Local é obrigatório. ";
                    editTextLocal.setError("Este campo é obrigatório");
                }
                if (editTextData.getText().toString().equals("")) {
                    campoVazio = "Campo Data é obrigatório. ";
                    editTextData.setError("Este campo é obrigatório");
                }

                return campoVazio;
            }

            @Override
            public void onClick(View v) {

                EditText editTextNome = findViewById(R.id.editText_nome);
                EditText editTextData = findViewById(R.id.editText_data);
                EditText editTextLocal = findViewById(R.id.editText_local);

                String nome = editTextNome.getText().toString();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String data = String.format(editTextData.getText().toString(), formatter);

                String local = editTextLocal.getText().toString();

                Evento evento = new Evento(id, nome, data, local);

                String campoVazio = validar();
                if (campoVazio.equals("")) {
                    EventoDAO eventoDAO = new EventoDAO(getBaseContext());
                    boolean salvou = eventoDAO.salvar(evento);
                    Toast.makeText(CadastrarEvento.this, "Evento Agendado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(CadastrarEvento.this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);
            editTextNome.setText(evento.getNome());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            editTextData.setText(String.format(evento.getData(), formatter));

            editTextLocal.setText(evento.getLocal());
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v) {finish();}


    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String data = String.format(editTextData.getText().toString(), formatter);
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean excluiu = eventoDAO.excluir(evento);
        if (excluiu) {
            finish();
            Toast.makeText(CadastrarEvento.this, "Agendamento excluído com sucesso!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CadastrarEvento.this, "Erro ao excluir", Toast.LENGTH_SHORT).show();
        }
    }
}