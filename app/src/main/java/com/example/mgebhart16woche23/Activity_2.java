package com.example.mgebhart16woche23;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Activity_2 extends Activity {
    CheckBox checkBoxWichtig;
    CheckBox checkBoxDatum;
    EditText datetext;
    boolean wichtig = false;
    boolean dringend = false;
    String dateString;
    EditText noteText;
    String date;
    String ID = "0";
    FirebaseFirestore db;
    String collectionName = "Note";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_layout);
        checkBoxWichtig = (CheckBox) findViewById(R.id.checkBoxWichtig);
        checkBoxDatum = (CheckBox) findViewById(R.id.checkBoxDate);
        datetext = findViewById(R.id.dateText);
        noteText = findViewById(R.id.textNote);

        db = FirebaseFirestore.getInstance();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
       // firestore.setFirestoreSettings(settings);


        Button ok = (Button) findViewById(R.id.ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                saveNotes();
                finish();
            }

        });
        Button cancel = (Button) findViewById(R.id.cancle_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });

        checkBoxWichtig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked())
                {
                    Toast.makeText(Activity_2.this, "Wichtig ausgewählt!", Toast.LENGTH_LONG).show();
                    wichtig = true;
                }
            }
        });

        checkBoxDatum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    Toast.makeText(Activity_2.this, "Dringend ausgewählt!", Toast.LENGTH_LONG).show();
                    dringend = true;
                    //SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

                         //date = (datetext.getText().toString());

                }
            }
        });

        int a = (Integer.valueOf(ID) + 1);
        ID = String.valueOf(a);
    }
    public void saveNotes()
    {
        Note note = new Note(ID, wichtig, dringend, datetext.getText().toString(), noteText.getText().toString());
        db.collection(collectionName)
                .document(String.valueOf(note.getID()))
                .set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("firestoreDemo.set", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firestoreDemo.set", "Error writing document", e);
                    }
                });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        db.collection(collectionName)
                .document(note.getID())
                .update("message", "bar")
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("firestoreDemo.update", "DocumentSnapshot successfully updated!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("firestoreDemo.update", "Error updating document", e);
                    }
                });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
