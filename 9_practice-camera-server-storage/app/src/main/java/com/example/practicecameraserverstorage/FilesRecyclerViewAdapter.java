package com.example.practicecameraserverstorage;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicecameraserverstorage.data.FileData;

import java.util.ArrayList;

public class FilesRecyclerViewAdapter extends RecyclerView.Adapter<FilesRecyclerViewAdapter.Holder> {

    private ArrayList<FileData> arrayListFileData = new ArrayList();
    private View.OnClickListener listener = null;

    public FilesRecyclerViewAdapter(View.OnClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Holder holder = new Holder(layoutInflater.inflate(R.layout.list_item_file, parent, false), this.listener);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.bind(this.arrayListFileData.get(position));
    }

    @Override
    public int getItemCount() {
        return this.arrayListFileData.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItem(ArrayList<FileData> arrayListFileData) {
        this.arrayListFileData.clear();
        this.arrayListFileData.addAll(arrayListFileData);
        this.notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private View view;
        private TextView textViewFileName = null;

        public Holder(@NonNull View itemView, View.OnClickListener listener) {
            super(itemView);
            this.view = itemView;
            this.textViewFileName = (TextView) this.view.findViewById(R.id.textViewFIleName);

            this.view.setOnClickListener(listener);
        }

        public void bind(FileData fileData) {
            this.textViewFileName.setText(fileData.getFileName());
            this.view.setTag(fileData.getPath());
        }
    }
}
