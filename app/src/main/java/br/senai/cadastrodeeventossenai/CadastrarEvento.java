package br.senai.cadastrodeeventossenai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.senai.cadastrodeeventossenai.modelo.Evento;

public class CadastrarEvento extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_EDITADO = 11;
    private final int RESULT_CODE_EVENTO_EXCLUIDO = 12;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        setTitle("Agendamento de Eventos");
        carregarEvento();

        final EditText editTextData = (EditText) findViewById(R.id.editText_data);
        editTextData.addTextChangedListener(Mask.insert("##/##/####", editTextData));
    }

    private void carregarEvento() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            editTextLocal.setText(evento.getLocal());
            edicao = true;
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void oncClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        LocalDate data = LocalDate.parse(editTextData.getText().toString(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();

        if (edicao) {
            intent.putExtra("eventoEditado", evento);
            setResult(RESULT_CODE_EVENTO_EDITADO, intent);
        } else {
            intent.putExtra("novoEvento", evento);
            setResult(RESULT_CODE_NOVO_EVENTO, intent);
        }
        Toast.makeText(CadastrarEvento.this, "Agendamento feito com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        LocalDate data = LocalDate.parse(editTextData.getText().toString());
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        Intent intent = new Intent();

        intent.putExtra("eventoExcluido", evento);
        setResult(RESULT_CODE_EVENTO_EXCLUIDO, intent);

        Toast.makeText(CadastrarEvento.this, "Agendamento excluído com sucesso!", Toast.LENGTH_SHORT).show();
        finish();
    }
}