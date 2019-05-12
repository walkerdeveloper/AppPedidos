package br.com.unibratec.apppedidos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.unibratec.apppedidos.R;

public class CadastrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText txtTitulo = (EditText) findViewById(R.id.txtTitulo);
                EditText txtDescricao = (EditText) findViewById(R.id.txtDescricao);

                @SuppressLint("WrongConstant") SQLiteDatabase db = openOrCreateDatabase("database", SQLiteDatabase.CREATE_IF_NECESSARY, null);
                ContentValues ctv = new ContentValues();
                ctv.put("titulo", txtTitulo.getText().toString());
                ctv.put("descricao", txtDescricao.getText().toString());

                db.insert("pedidos", "id", ctv);
                Toast.makeText(getBaseContext(), " Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
