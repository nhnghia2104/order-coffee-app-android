package com.cogeek.tncoffee.ui.userorder.tracking;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cogeek.tncoffee.R;
import com.cogeek.tncoffee.models.TimelineOrder;
import com.cogeek.tncoffee.models.TrackingOrder;
import com.cogeek.tncoffee.ui.userorder.UserOrderOverviewAdapter;

import org.w3c.dom.Text;

import java.util.List;

public class OrderTrackingAdapter extends RecyclerView.Adapter<OrderTrackingAdapter.ViewHolder> {

    private List<TrackingOrder> trackingOrderList;

    public OrderTrackingAdapter(List<TrackingOrder> timelineOrderList) {
        this.trackingOrderList = timelineOrderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_tracking_row, parent, false);
        return new OrderTrackingAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return trackingOrderList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TrackingOrder trackingOrder = trackingOrderList.get(position);
        if (position == 0) {
            holder.getLineTop().setVisibility(View.GONE);
            holder.getCurrent().setVisibility(View.VISIBLE);
            holder.getCircle().setVisibility(View.INVISIBLE);
            holder.getTxtStatus().setTextColor(Color.parseColor("#323232"));
        }
        holder.getTxtStatus().setText(trackingOrder.getStatus());
        holder.getTxtTime().setText(trackingOrder.getTimeFormat());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtStatus, txtTime;
        private final View vLineTop, vCircle, vCurrent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStatus = itemView.findViewById(R.id.txt_timeline_status);
            txtTime = itemView.findViewById(R.id.txt_timeline_time);
            vLineTop = itemView.findViewById(R.id.v_line_top);
            vCircle = itemView.findViewById(R.id.v_circle);
            vCurrent = itemView.findViewById(R.id.v_circle_current);
        }

        public TextView getTxtStatus() {
            return txtStatus;
        }

        public TextView getTxtTime() {
            return txtTime;
        }

        public View getLineTop() {
            return vLineTop;
        }

        public View getCircle() {
            return vCircle;
        }

        public View getCurrent() {
            return vCurrent;
        }
    }
}
