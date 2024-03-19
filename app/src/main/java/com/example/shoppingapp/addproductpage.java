package com.example.shoppingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class addproductpage extends AppCompatActivity {

    private EditText editTextProductName;
    private EditText editTextProductDescription;
    private EditText editTextProductPrice;
    private EditText editTextProductAmount;
    private Button buttonAddProduct;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        // Get references to EditText fields and Button
        editTextProductName = findViewById(R.id.editTextProductName);
        editTextProductDescription = findViewById(R.id.editTextProductDescription);
        editTextProductPrice = findViewById(R.id.editTextProductPrice);
        editTextProductAmount = findViewById(R.id.editTextProductAmount);
        buttonAddProduct = findViewById(R.id.buttonAddProduct);

        // Set OnClickListener for the Button
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve input data from EditText fields
                String productName = editTextProductName.getText().toString().trim();
                String productDescription = editTextProductDescription.getText().toString().trim();
                double productPrice = Double.parseDouble(editTextProductPrice.getText().toString().trim());
                double productAmount = Double.parseDouble(editTextProductAmount.getText().toString().trim());

                // Create a new product map
                Map<String, Object> product = new HashMap<>();
                product.put("name", productName);
                product.put("description", productDescription);
                product.put("price", productPrice);
                product.put("amount", productAmount);

                // Add the product to Firestore
                firestore.collection("products")
                        .add(product)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                // Product added successfully
                                Toast.makeText(addproductpage.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                                // Finish the activity
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error adding product
                                Toast.makeText(addproductpage.this, "Error adding product: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
