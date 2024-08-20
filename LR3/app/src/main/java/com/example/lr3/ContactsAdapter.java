package com.example.lr3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    private Context context;
    private List<Contact> contacts;

    public ContactsAdapter(Context context, List<Contact> contacts) {
        this.context = context;
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhoneNumber());
        holder.emailTextView.setText(contact.getEmail());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
            context.startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Sharing Contact");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Name: " + contact.getName() +
                    "\nPhone: " + contact.getPhoneNumber());
            context.startActivity(Intent.createChooser(emailIntent, "Send Email"));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, phoneTextView, emailTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name);
            phoneTextView = itemView.findViewById(R.id.phone);
            emailTextView = itemView.findViewById(R.id.email);
        }
    }
}

