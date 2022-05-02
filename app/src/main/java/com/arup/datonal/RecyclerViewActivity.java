package com.arup.datonal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewActivity extends RecyclerView.Adapter<RecyclerViewActivity.ViewHolder> {

    private final String[][] localDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView heading;
        TextView data;

        public ViewHolder(View view) {
            super(view);
            heading = (TextView) view.findViewById(R.id.Title);
            data = (TextView) view.findViewById(R.id.userId);
        }

        public TextView getHeading() {
            return heading;
        }

        public TextView getViewData() {
            return data;
        }
    }


    public RecyclerViewActivity(String[][] dataSet) {
        localDataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_page_recycler_view, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getHeading().setText(localDataSet[position][0]);
        viewHolder.getViewData().setText(localDataSet[position][1]);
    }


    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

