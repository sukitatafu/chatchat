package com.splash.billy.chatchat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Billy on 15/01/2018.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatsHolder> {

    List<com.splash.billy.chatchat.Chat> chats;
    private LayoutInflater inflater;
    private Context context;

    SharedPreferences mylocaldata;

    public ChatListAdapter(Context context, List<com.splash.billy.chatchat.Chat> chats) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.chats = chats;


        mylocaldata = context.getSharedPreferences("mylocaldata", MODE_PRIVATE);
    }

    @Override
    public ChatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.chat_card, parent, false);
        ChatsHolder holder = new ChatsHolder(view); ;
        return holder;
    }

    @Override
    public void onBindViewHolder(ChatsHolder holder, int position) {
        com.splash.billy.chatchat.Chat current = chats.get(position);
        holder.setData(current, position);
        holder.setListeners();

    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ChatsHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView tvSender, tvTanggal, tvChat;
        CardView thischat;

        int position;
        com.splash.billy.chatchat.Chat current;

        public ChatsHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            thischat = (CardView)itemView.findViewById(R.id.cvItemChat);
            tvChat = (TextView)itemView.findViewById(R.id.tvChat);
            tvSender = (TextView)itemView.findViewById(R.id.tvSender);
            tvTanggal = (TextView)itemView.findViewById(R.id.tvTanggal);
        }
        public void setData(com.splash.billy.chatchat.Chat current, int position){
            tvChat.setText(current.getPesan());
            tvSender.setText(current.getSender().getNama());
            SimpleDateFormat dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dateString = dformat.format(new Date(Long.parseLong(current.getTanggal().toString())));
            tvTanggal.setText(dateString);

            String uid = mylocaldata.getString("uid","");

            if( current.getSender().getTelepon().equals( uid )){
                thischat.setCardBackgroundColor(Color.parseColor("#eeffdd"));
            }else {
                thischat.setCardBackgroundColor(Color.parseColor("#ffffff"));
            }
            this.position = position;
            this.current = current;

        }
        public void setListeners(){

        }

    }
}

