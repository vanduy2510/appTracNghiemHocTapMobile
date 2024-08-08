package tktsp.example.a17dtha2_aptracnghiem.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.DAO.userDao;
import tktsp.example.a17dtha2_aptracnghiem.R;
import tktsp.example.a17dtha2_aptracnghiem.model.User;

public class userAdapter extends BaseAdapter {
    List<User> arrUser;
    public Activity context;
    public LayoutInflater inflater;
    userDao userDao;

    public userAdapter(List<User> arrUser, Activity context) {
        this.arrUser = arrUser;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        userDao = new userDao(context);
    }

    @Override
    public int getCount() {
        return arrUser.size();
    }

    @Override
    public Object getItem(int i) {
        return arrUser.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        ViewHolder holder;
       // if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.item_user,null);
            holder.txtUsename=(TextView) view.findViewById(R.id.txtUsername);
            holder.txtName=(TextView) view.findViewById(R.id.txtName);
            holder.txtEmail=(TextView) view.findViewById(R.id.txtEmail);
            holder.txtPer=(TextView) view.findViewById(R.id.txtPer);
             holder.txtPass=(TextView) view.findViewById(R.id.txtPass);
            view.setTag(holder);

        //}else {
            //holder=(ViewHolder) view.getTag();
        //}
        User user=arrUser.get(i);
        holder.txtUsename.setText(user.getUsername());
        holder.txtName.setText(user.getName_user());
        holder.txtEmail.setText(user.getEmail());
        holder.txtPer.setText(String.valueOf(user.getPermission()));
        holder.txtPass.setText(user.getPassword());
        return view;
    }


    public static class ViewHolder{
        TextView txtUsename,txtName,txtEmail,txtPer,txtPass;
    }
}
