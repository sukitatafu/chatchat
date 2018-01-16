package com.splash.billy.chatchat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Billy on 15/01/2018.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UsersHolder> {

    List<com.splash.billy.chatchat.User> users;
    private LayoutInflater inflater;
    private Context context;

    SharedPreferences mylocaldata;

    public UserListAdapter(Context context, List<com.splash.billy.chatchat.User> users) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.users = users;

        mylocaldata = context.getSharedPreferences("mylocaldata", MODE_PRIVATE);
    }

    @Override
    public UsersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.usercard, parent, false);
        UsersHolder holder = new UsersHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UsersHolder holder, int position) {
        com.splash.billy.chatchat.User current = users.get(position);
        holder.setData(current, position);
        holder.setListeners();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView tvName, tvEmail, tvNomor;
        CardView thisuser;

        int position;
        com.splash.billy.chatchat.User current;

        public UsersHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

            thisuser = (CardView)itemView.findViewById(R.id.cvItemUser);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            tvNomor = (TextView)itemView.findViewById(R.id.tvNomor);

        }

        public void setData(com.splash.billy.chatchat.User current, int position) {
            tvName.setText(current.getNama());
            tvEmail.setText(current.getEmail());
            tvNomor.setText(current.getTelepon());
            String uid = mylocaldata.getString("uid","");
            this.position = position;
            this.current = current;
        }

        public void setListeners() {

        }
    }
}


