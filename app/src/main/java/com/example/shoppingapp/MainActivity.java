package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Authenticate user with Firebase Authentication
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                    // Navigate to the main activity or any other screen
                                    startActivity(new Intent(MainActivity.this, addproductpage.class));
                                    finish(); // Finish the current activity
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}



//package com.example.shoppingapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.shoppingapp.listofproducts;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText editTextEmail;
//    private EditText editTextPassword;
//    private Button buttonLogin;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore firestore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mAuth = FirebaseAuth.getInstance();
//        firestore = FirebaseFirestore.getInstance();
//
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonLogin = findViewById(R.id.buttonLogin);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String email = editTextEmail.getText().toString().trim();
//                final String password = editTextPassword.getText().toString().trim();
//
//                if (email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Check if the user exists in Firestore
//                firestore.collection("users")
//                        .whereEqualTo("email", email)
//                        .whereEqualTo("password", password)
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    if (!task.getResult().isEmpty()) {
//                                        // User exists in Firestore, proceed with login
//                                        mAuth.signInWithEmailAndPassword(email, password)
//                                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                    @Override
//                                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                                        if (task.isSuccessful()) {
//                                                            // Sign in success, update UI with the signed-in user's information
//                                                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                                                            // Navigate to the main activity or any other screen
//                                                            startActivity(new Intent(MainActivity.this, listofproducts.class));
//                                                            finish(); // Finish the current activity
//                                                        } else {
//                                                            // If sign in fails, display a message to the user.
//                                                            Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
//                                                        }
//                                                    }
//                                                });
//                                    } else {
//                                        // User does not exist in Firestore
//                                        Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    Toast.makeText(MainActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
//    }
//}




//package com.example.shoppingapp;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//public class MainActivity extends AppCompatActivity {
//
//    private EditText editTextEmail;
//    private EditText editTextPassword;
//    private Button buttonLogin;
//    private FirebaseFirestore firestore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        firestore = FirebaseFirestore.getInstance();
//
//        editTextEmail = findViewById(R.id.editTextEmail);
//        editTextPassword = findViewById(R.id.editTextPassword);
//        buttonLogin = findViewById(R.id.buttonLogin);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String email = editTextEmail.getText().toString().trim();
//                final String password = editTextPassword.getText().toString().trim();
//
//                if (email.isEmpty() || password.isEmpty()) {
//                    Toast.makeText(MainActivity.this, "Email and password are required", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                // Query Firestore to check if user credentials are valid
//                firestore.collection("users")
//                        .document(email)
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                                if (task.isSuccessful()) {
//                                    DocumentSnapshot document = task.getResult();
//                                    if (document.exists()) {
//                                        // User exists in Firestore, check password
//                                        String storedPassword = document.getString("password");
//                                        if (storedPassword.equals(password)) {
//                                            // Passwords match, login successful
//                                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
//                                            // Proceed with your login logic (e.g., start a new activity)
//                                        } else {
//                                            // Passwords don't match
//                                            Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
//                                        }
//                                    } else {
//                                        // User does not exist in Firestore
//                                        Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
//                                    }
//                                } else {
//                                    // Error retrieving document
//                                    Toast.makeText(MainActivity.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//        });
//    }
//}






//package com.example.shoppingapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class MainActivity extends AppCompatActivity {
//
//    FirebaseFirestore firestore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        firestore = FirebaseFirestore.getInstance();
//
//        Map<String,Object> user = new HashMap<>();
//        user.put("firstname", "Daniel");
//        user.put("lastname", "Korkus");
//        user.put("password", "1234");
//        user.put("email", "Daniel@gmail.com");
//
//        firestore.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//            @Override
//            public void onSuccess(DocumentReference documentReference) {
//                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//}