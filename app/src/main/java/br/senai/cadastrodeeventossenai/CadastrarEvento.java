package br.senai.cadastrodeeventossenai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDate;

public class CadastrarEvento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        setTitle("Agendamentos");
    }


    public void OnclickVoltar(View v) {
        finish();
    }




    public void OncClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.EditText_nomeEvento);
        EditText editTextdata = findViewById(R.id.editText_dataEvento);
        EditText editTextLocal = findViewById(R.id.editText_localEvento);

        String nomeEvento = editTextNome.getText().toString();
        String dataEvento = editTextdata.getText().toString();
        String localEvento = editTextLocal.getText().toString();

        Toast.makeText(CadastrarEvento.this, "Agendamento feito com sucesso!", Toast.LENGTH_SHORT).show();
        finish();

    }
}