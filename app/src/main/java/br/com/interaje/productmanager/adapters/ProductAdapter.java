package br.com.interaje.productmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.productmanager.R;
import br.com.interaje.productmanager.models.Category;
import br.com.interaje.productmanager.models.Product;

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
        TextView categoryName = (TextView) view.findViewById(R.id.categoryName);

        productName.setText(products.get(position).getName());
        productPrice.setText(String.valueOf(products.get(position).getPrice()));
        Category category = products.get(position).getCategory();
        categoryName.setText(category != null ? category.getName() : "");

        return view;
    }
}
