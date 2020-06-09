package com.example.celebrare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView recyclerView;
    private FirestoreRecyclerAdapter firestoreRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseFirestore = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler);

        Query query = firebaseFirestore.collection("Users").document("newcollection").collection("collection1");

        FirestoreRecyclerOptions<Model> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Model>().
                setQuery(query, Model.class).build();

        firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<Model, MyViewHolder>(firestoreRecyclerOptions) {

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleitem, parent, false);
                return new MyViewHolder(v);
            }

            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Model model) {
                holder.textname.setText(model.getName());
                holder.Collegename.setText(model.getCollege());
                Picasso.get().load(model.getimageurl()).into(holder.imageView);
            }
        };

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(firestoreRecyclerAdapter);

    }

    private class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textname , Collegename;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imagesingleitem);
            Collegename = itemView.findViewById(R.id.college);
            textname = itemView.findViewById(R.id.name);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firestoreRecyclerAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        firestoreRecyclerAdapter.stopListening();
    }
}