package br.com.interaje.productmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.interaje.productmanager.model.Product;

public class MainActivity extends AppCompatActivity {

    private ListView productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = (ListView) findViewById(R.id.productList);

        ProductAdapter adapter = new ProductAdapter(this, getProductList());

        productList.setAdapter(adapter);

    }

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
