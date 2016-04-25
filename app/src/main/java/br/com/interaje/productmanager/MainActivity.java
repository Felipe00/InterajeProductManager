package br.com.interaje.productmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.productmanager.DbUtilsProduct.StaticListProduct;
import br.com.interaje.productmanager.model.Product;

public class MainActivity extends AppCompatActivity {

    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = (ListView) findViewById(R.id.productList);

        final List<Product> products = StaticListProduct.productList;

        ProductAdapter adapter = new ProductAdapter(this, products);

        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView name = (TextView) view.findViewById(R.id.productName);
                TextView price = (TextView) view.findViewById(R.id.productPrice);

                //Convertendo para numeral
                Double valuePrice = Double.parseDouble(price.getText().toString());

                Intent intent = new Intent(MainActivity.this, InsertActivity.class);

                //intent.putExtra("product", product);
                intent.putExtra("productId", id);
                intent.putExtra("productName", name.getText().toString());
                intent.putExtra("productPrice", valuePrice);

                startActivity(intent);
                finish();

            }
        });

    }

    public void newProduct(View v) {
        startActivity(new Intent(this, InsertActivity.class));
    }

    // Lista fictícia.
    private List<Product> getProductList() {
        List<Product> list = new ArrayList<>();
        Product product = new Product();

        product.setId(1l);
        product.setName("Biscoito");
        product.setPrice(1.50);
        list.add(product);

        product = new Product();
        product.setId(2l);
        product.setName("Arroz");
        product.setPrice(4.00);
        list.add(product);

        return list;
    }
}
