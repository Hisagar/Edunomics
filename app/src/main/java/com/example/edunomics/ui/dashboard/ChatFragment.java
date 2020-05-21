package com.example.edunomics.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edunomics.MesageAdapter;
import com.example.edunomics.Message;
import com.example.edunomics.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private ImageButton sendBtn;
    private EditText txtMsg;
    private DatabaseReference dbr;
    private RecyclerView recyclerView;
    String receiverid="";
    private ArrayList<Message> list=new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        sendBtn=root.findViewById(R.id.btnSend);
        txtMsg=root.findViewById(R.id.txtMsg);
        recyclerView=root.findViewById(R.id.chatRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final String userid=FirebaseAuth.getInstance().getUid();

        if(userid=="44ZLvpAMAgeddlxSXgGTBj2F6wo1")
        {
            receiverid="xezcuuXzXaeHqnEISBRRmtx0CAA2";
        }else
        {
            receiverid="44ZLvpAMAgeddlxSXgGTBj2F6wo1";
        }
        dbr=FirebaseDatabase.getInstance().getReference().child("Message");
        Query query=dbr.orderByChild("isSent").equalTo(true);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    list.clear();
                   for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                   {
                       Message message=new Message(dataSnapshot1.child("sender").getValue().toString(),dataSnapshot1.child("msg").getValue().toString());
                       list.add(message);
                   }
                   recyclerView.setAdapter(new MesageAdapter(list,getActivity(),userid));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=txtMsg.getText().toString();
                if(!msg.isEmpty())
                {
                    String key= FirebaseDatabase.getInstance().getReference().child("Message").push().getKey();
                    dbr.child(key).child("sender").setValue(userid);
                    dbr.child(key).child("receiver").setValue(receiverid);
                    dbr.child(key).child("msg").setValue(msg);
                    dbr.child(key).child("isSent").setValue(true);
                }else
                {
                    Toast.makeText(getActivity(), "Please type in message box!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return root;
    }
}
