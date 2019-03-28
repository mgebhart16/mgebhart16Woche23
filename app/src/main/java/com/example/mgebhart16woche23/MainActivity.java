package com.example.mgebhart16woche23;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView notes;
    FloatingActionButton button;
    String collectionName = "Note";
    List<Note> list = new ArrayList<>();
    boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseFirestore db = FirebaseFirestore.getInstance();





        notes = findViewById(R.id.gridView);
        button = findViewById(R.id.addButton);





        Note n = new Note("12", true, true, "12.12.2012","messageTest");
        list.add(n);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = true;
                Intent myIntent = new Intent(v.getContext(), Activity_2.class);
                startActivityForResult(myIntent, 0);
            }
        });

    if(isClicked == true) {
        db.collection(collectionName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Note note = document.toObject(Note.class);

                                //TextView tv = findViewById(R.id.textView);
                                //tv.setText(note.toString());


                                list.add(note);

                                Log.d("firestoreDemo.get", note.toString());
                            }
                        } else {
                            Log.w("firestoreDemo.get", "Error getting documents.", task.getException());
                        }
                    }
                });
        notes.setAdapter(new ToDoAdapter(this, list));

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }


}
