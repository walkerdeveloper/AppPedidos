package br.com.unibratec.apppedidos;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AlterarPedidoActivity extends AppCompatActivity {

    private Pedido pedido;
    private TextView txtTitulo;
    private TextView txtDescricao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_pedido);

        Intent intent = this.getIntent();
        this.pedido = (Pedido) intent.getSerializableExtra("pedidoSelecionado");

        txtTitulo = (TextView) findViewById(R.id.txtTit);
        txtDescricao = (TextView) findViewById(R.id.txtDesc);

        txtTitulo.setText(this.pedido.getTitulo());
        txtDescricao.setText(this.pedido.getDescricao());

        Button btnPedido = (Button) findViewById(R.id.btnAlterar);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //To doo
                alterarPedido();
                Toast.makeText(getBaseContext(), " Alterado com Sucesso", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    private void alterarPedido() {
        try {
            @SuppressLint("WrongConstant") SQLiteDatabase db =
                    openOrCreateDatabase("database", SQLiteDatabase.CREATE_IF_NECESSARY, null);
            ContentValues cv = new ContentValues();
            cv.put("titulo", txtTitulo.getText().toString());
            cv.put("descricao", txtDescricao.getText().toString());
            String where = "id=?";
            String[] whereArgs = new String[]{String.valueOf(pedido.getId())};
            Log.i("Resultado","titulo: " + txtTitulo.getText().toString());
            Log.i("Resultado","titulo: " + new String[]{String.valueOf(pedido.getId())});
            db.update("pedidos", cv, "id = ?", new String[]{String.valueOf(pedido.getId())});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

