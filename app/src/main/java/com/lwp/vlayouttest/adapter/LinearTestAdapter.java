package com.lwp.vlayouttest.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.lwp.vlayouttest.R;

public class LinearTestAdapter extends DelegateAdapter.Adapter<LinearTestAdapter.LinearTestAdapterViewHolder> {

    private LayoutHelper layoutHelper;
    private int itemCount;
    private Context mContext;

    public LinearTestAdapter(LayoutHelper layoutHelper) {
        this.layoutHelper = layoutHelper;
    }


    public LinearTestAdapter(LayoutHelper layoutHelper, int itemCount) {
        this.layoutHelper = layoutHelper;
        this.itemCount = itemCount;
    }

    static class LinearTestAdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView itemText;
        public static volatile int existing = 0;
        public static int createdTimes = 0;

        public LinearTestAdapterViewHolder(View itemView) {
            super(itemView);

            itemText = itemView.findViewById(R.id.title);

            createdTimes++;
            existing++;
        }

        @Override
        protected void finalize() throws Throwable {
            existing--;
            super.finalize();
        }
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public LinearTestAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if(mContext == null){
            mContext = parent.getContext();
        }

        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new LinearTestAdapterViewHolder(inflater.inflate(R.layout.item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LinearTestAdapterViewHolder linearTestAdapterViewHolder, int position) {

    }

    @Override
    protected void onBindViewHolderWithOffset(LinearTestAdapterViewHolder holder, int position, int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);

        holder.itemText.setText((String.valueOf(offsetTotal)));
        holder.itemView.setLayoutParams(new
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100));
        if (layoutHelper instanceof GridLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.YELLOW);
            holder.itemText.setTextColor(Color.BLACK);
        }
        if (layoutHelper instanceof FixLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.BLACK);
            holder.itemView.setLayoutParams(new
                    ViewGroup.LayoutParams(150, 150));
        }
        if (layoutHelper instanceof ScrollFixLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.GREEN);
            holder.itemView.setLayoutParams(new
                    ViewGroup.LayoutParams(180, 180));
        }
        if (layoutHelper instanceof FloatLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.RED);
            holder.itemText.setText("F");
            holder.itemView.setLayoutParams(new
                    ViewGroup.LayoutParams(300, 150));
        }
        if (layoutHelper instanceof ColumnLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.CYAN);
        }
        if (layoutHelper instanceof SingleLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.MAGENTA);
            holder.itemText.setText("I'm SingleLayoutHelper");
        }
        if (layoutHelper instanceof StickyLayoutHelper) {
            holder.itemText.setBackgroundColor(Color.LTGRAY);
        }

    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

}
