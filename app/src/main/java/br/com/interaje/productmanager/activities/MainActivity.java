package br.com.interaje.productmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.productmanager.R;
import br.com.interaje.productmanager.adapters.ProductAdapter;
import br.com.interaje.productmanager.models.Product;

public class MainActivity extends AppCompatActivity {

    private ListView productList;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = (ListView) findViewById(R.id.productList);

        products = getProductList();

        ProductAdapter adapter = new ProductAdapter(this, products);

        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product item = products.get(position);

                Intent intent = new Intent(MainActivity.this, InsertActivity.class);

                intent.putExtra("product", item);

                startActivity(intent);
                finish();

            }
        });

    }

    public void newProduct(View v) {
        startActivity(new Intent(this, InsertActivity.class));
        finish();
    }

    // Lista fictícia.
    private List<Product> getProductList() {
        List<Product> list = Product.listAll(Product.class);

        return list;
    }
}
