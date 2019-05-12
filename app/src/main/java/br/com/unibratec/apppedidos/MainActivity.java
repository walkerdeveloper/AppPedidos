package br.com.unibratec.apppedidos;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db = null;
    private SimpleCursorAdapter adt = null;
    private ArrayList<Integer> inds;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criação do banco

        db = openOrCreateDatabase("database", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        String pedidos = "CREATE TABLE IF NOT EXISTS pedidos " +
                        "(_id INTEGER PRIMARY KEY autoincrement," +
                        "titulo VARCHAR (50)," +
                        " descricao VARCHAR(50))";

        db.execSQL(pedidos);

        //Cadastrar Pedido
        Button btnPedido = (Button) findViewById(R.id.btnPedido);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CadastrarActivity.class));
            }
        });

        //ListView lista = (ListView) findViewById(R.id.lvpedido);
        //ArrayAdapter<Pedido> adapter = new PedidoAdapter(this, adicionarPedido());
        // lista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView lista = (ListView) findViewById(R.id.lvpedido);
        ArrayAdapter<Pedido> adapter = new PedidoAdapter(this,  recuperarPedidos());
        lista.setAdapter(adapter);

    }

    private ArrayList<Pedido> recuperarPedidos(){
        Cursor cursor = db.rawQuery("Select * From Pedidos", null);
        ArrayList<Pedido> list = new ArrayList<>();

        int indiceId = cursor.getColumnIndex("_id");
        int indiceTitulo = cursor.getColumnIndex("titulo");
        int indiceDescricao = cursor.getColumnIndex("descricao");
        //cursor.moveToFirst();
        while ( cursor.moveToNext() ){
            Pedido p = new Pedido(cursor.getString(indiceTitulo), cursor.getString(indiceDescricao));
            list.add(p);
            Log.i("Resultado","Descricão: " + cursor.getString(indiceDescricao));
            Log.i("Resultado","Titulo: " + cursor.getString(indiceTitulo));
            //cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
}
