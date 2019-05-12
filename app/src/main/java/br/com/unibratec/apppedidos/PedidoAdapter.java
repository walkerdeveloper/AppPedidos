package br.com.unibratec.apppedidos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PedidoAdapter extends ArrayAdapter<Pedido> {

    private Context context;
    private ArrayList<Pedido> lista;

    public PedidoAdapter(Context context, ArrayList<Pedido> pedidos) {
        super(context, R.layout.linha, pedidos);
        this.context = context;
        this.lista = pedidos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView txtTitulo = (TextView) rowView.findViewById(R.id.text_titulo);
        TextView txtDescricao = (TextView) rowView.findViewById(R.id.text_descricao);

        txtTitulo.setText(lista.get(position).getTitulo());
        txtDescricao.setText(lista.get(position).getDescricao());

        return rowView;
    }
}
