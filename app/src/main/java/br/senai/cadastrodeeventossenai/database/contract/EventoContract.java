package br.senai.cadastrodeeventossenai.database.contract;

import br.senai.cadastrodeeventossenai.database.entity.EventoEntity;

public class EventoContract {

    public static final String criarTabela() {
        return "CREATE TABLE " + EventoEntity.TABLE_NAME + " (" +
                EventoEntity._ID + " INTEGER PRIMARY KEY," +
                EventoEntity.COLUMN_NAME_NOME + " TEXT," +
                EventoEntity.COLUMN_NAME_DATA + " TEXT," +
                EventoEntity.COLUMN_NAME_LOCAL + " TEXT)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTIS " + EventoEntity.TABLE_NAME;
    }
}
