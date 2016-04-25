package br.com.interaje.productmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.com.interaje.productmanager.DbUtilsProduct.StaticListProduct;
import br.com.interaje.productmanager.model.Category;
import br.com.interaje.productmanager.model.Product;

public class InsertActivity extends AppCompatActivity {

    EditText productName, productPrice, productCategory;
    Long idProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        productName = (EditText) findViewById(R.id.productName);
        productPrice = (EditText) findViewById(R.id.productPrice);
        productCategory = (EditText) findViewById(R.id.productCategory);

        setProductValues();
    }

    private void setProductValues() {
        if (getIntent().hasExtra("productId")) {
            idProduct = getIntent().getLongExtra("productId", 0l);
            productName.setText(getIntent().getStringExtra("productName"));
            String valuePrice = String.valueOf(getIntent().getDoubleExtra("productPrice", 0));
            productPrice.setText(valuePrice);
        }
    }

    public void insertProduct(View v) {
        Product product = new Product();
        Category category = new Category();

        Long idValue = Long.valueOf(StaticListProduct.productList.size() + 1);

        product.setId(idValue);
        product.setName(productName.getText().toString());
        category.setName(productCategory.getText().toString());

        product.setCategory(category);
        // Conversão direta para numeral (Double).
        product.setPrice(Double.parseDouble(productPrice.getText().toString()));

        StaticListProduct.productList.add(product);

        startActivity(new Intent(this, MainActivity.class));
        finish();

    }
}




