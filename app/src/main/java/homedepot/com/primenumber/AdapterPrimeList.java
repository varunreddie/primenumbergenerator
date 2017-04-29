package homedepot.com.primenumber;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by varun on 27/04/17.
 */
public class AdapterPrimeList extends RecyclerView.Adapter
        <RecyclerView.ViewHolder> {
    private final static String TAG = "AdapterPrimeList";
    private static List<Integer> mItemsList = null;
    private static RecycleViewListener mListener;
    private Context mCtx;

    public AdapterPrimeList(Context ctx, RecycleViewListener listener, List<Integer> itemsList) {
        mCtx = ctx;
        mListener = listener;
        mItemsList = itemsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HeaderViewHolder holderHeader = new HeaderViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_recycle_row, parent, false));
        return holderHeader;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
        headerViewHolder.textViewHeader.setText(mItemsList.get(position) + "");
    }

    @Override
    public int getItemCount() {
        synchronized (this)

        {
            return mItemsList.size();
        }
    }

    public void update(List<Integer> list) {
        mItemsList = list;
        notifyDataSetChanged();
    }

    public void clear() {
        mItemsList.clear();
        notifyDataSetChanged();
    }

    public interface RecycleViewListener {
        void onItemClick(int position, View v);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            textViewHeader = (TextView) itemView.findViewById(R.id.header_view);
            textViewHeader.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(getAdapterPosition(), view);
        }
    }
}
