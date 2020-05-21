package com.example.edunomics;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MesageAdapter extends RecyclerView.Adapter<MesageAdapter.MessageViewHolder> {
    private ArrayList<Message>list=new ArrayList<>();
    private Context context;
    private String user;


    public MesageAdapter(ArrayList<Message> list, Context context, String user) {
        this.list = list;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_layout,parent,false);
        MessageViewHolder viewHolder=new MessageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
            if(list.get(position).getSender().equals(user))
            {
                holder.txtMsg.setBackgroundColor(Color.GREEN);
                holder.linearLayout.setGravity(Gravity.RIGHT);
            }else
            {
                holder.txtMsg.setBackgroundColor(Color.RED);
                holder.linearLayout.setGravity(Gravity.LEFT);
            }
            holder.txtMsg.setText(list.get(position).getMsg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView txtMsg;
        private LinearLayout linearLayout;
        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMsg=itemView.findViewById(R.id.txtLinearMsg);
            linearLayout=itemView.findViewById(R.id.linearmsg);
        }
    }
}
