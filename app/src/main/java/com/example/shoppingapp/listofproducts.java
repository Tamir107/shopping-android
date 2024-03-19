package com.example.shoppingapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class listofproducts extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productslist);

        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance();

        dataSet = new ArrayList<>();
        recyclerView = findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // Retrieve data from Firestore
        firestore.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Extract data from each document
                                String name = document.getString("name");
                                String version = document.getString("version");
//                                int imageUrl = document.getString("image");
//                                // Assuming you have a unique identifier for each product
//                                int productId = document.getId();

                                // Create a DataModel object and add it to the list
                                DataModel product = new DataModel(name, version);
                                dataSet.add(product);
                            }

                            // Create and set adapter
                            adapter = new CustomeAdapter(dataSet);
                            recyclerView.setAdapter(adapter);
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}


//public class listofproducts extends AppCompatActivity {
//
//    private ArrayList<DataModel> dataSet;
//    private RecyclerView recyclerView;
//    private LinearLayoutManager layoutManager;
//    private CustomeAdapter adapter;
//    //private static final String TAG = "MainActivity";
//    //private FirebaseFirestore firestore;
//    //private List<Product> productList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.productslist);
//
//        dataSet = new ArrayList<>();
//        recyclerView =  findViewById(R.id.resView);
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        for ( int i =0 ; i < myData.nameArray.length ; i++){
//            dataSet.add(new DataModel(
//                    myData.nameArray[i],
//                    myData.versionArray[i],
//                    myData.drawableArray[i],
//                    myData.id_[i]
//            ));
//        }
//
//        adapter = new CustomeAdapter(dataSet);
//        recyclerView.setAdapter(adapter);
//
//
////        firestore = FirebaseFirestore.getInstance();
////
////        // Retrieve data from Firestore
////        firestore.collection("products")
////                .get()
////                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
////                    @Override
////                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
////                        if (task.isSuccessful()) {
////                            for (QueryDocumentSnapshot document : task.getResult()) {
////                                // Extract data from each document
////                                String name = document.getString("name");
////                                String version = document.getString("version");
////                                String imageUrl = document.getString("image");
////
////                                // Create a Product object and add it to the list
////                                DataModel product = new DataModel(name, version, imageUrl);
////                                dataSet.add(product);
////                            }
////
////                            // Data retrieval successful, do something with the productList
////                            // For example, update UI with the productList
////                            // updateUI(productList);
////                        } else {
////                            Log.d(TAG, "Error getting documents: ", task.getException());
////                        }
////                    }
////                });
//
//    }
//}