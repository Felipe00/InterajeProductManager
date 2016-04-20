package br.com.interaje.productmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.productmanager.model.Product;

/**
 * Created by rayquaza on 20/04/16.
 */
public class ProductAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        mLayoutInflater = LayoutInflater.from(context);
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return products.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_product, parent, false);

        TextView productName = (TextView) view.findViewById(R.id.productName);
        TextView productPrice = (TextView) view.findViewById(R.id.productPrice);

        productName.setText(products.get(position).getName());
        productPrice.setText(String.valueOf(products.get(position).getPrice()));

        return view;
    }
}
