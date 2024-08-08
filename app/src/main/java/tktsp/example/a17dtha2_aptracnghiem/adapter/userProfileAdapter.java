package tktsp.example.a17dtha2_aptracnghiem.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tktsp.example.a17dtha2_aptracnghiem.R;
import tktsp.example.a17dtha2_aptracnghiem.model.User;

public class userProfileAdapter extends RecyclerView.Adapter<userProfileAdapter.ViewHolder> {

    @NonNull

    private List<User> listProfile;
    public userProfileAdapter( List<User> listProfile) {

        this.listProfile = listProfile;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử sinh viên
        View view =
                inflater.inflate(R.layout.profile_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user=listProfile.get(position);
        holder.txtName.setText(user.getName_user());
        holder.txtUsername.setText(user.getUsername());
        holder.txtEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return listProfile.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName,txtUsername,txtEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername=itemView.findViewById(R.id.txtUsername);
            txtName=itemView.findViewById(R.id.txtName);
            txtEmail=itemView.findViewById(R.id.txtEmail);

        }
    }


}


