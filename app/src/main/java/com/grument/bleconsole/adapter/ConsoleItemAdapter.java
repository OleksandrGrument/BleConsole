package com.grument.bleconsole.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grument.bleconsole.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsoleItemAdapter extends RecyclerView.Adapter<ConsoleItemAdapter.ViewHolder> {


    private List<String> consoleMessages;


    public ConsoleItemAdapter() {
        consoleMessages = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_rv_console, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String message = consoleMessages.get(position);

        holder.consoleMessageTextView.setText(message);

    }

    public void notifyAndShow(String message) {
        consoleMessages.add(message);
        notifyItemInserted(getItemCount());
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return consoleMessages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_console_message)
        TextView consoleMessageTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
