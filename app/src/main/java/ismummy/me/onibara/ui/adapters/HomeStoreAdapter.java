package ismummy.me.onibara.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismummy.me.onibara.R;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.models.Store;
import ismummy.me.onibara.ui.MainApplication;

/**
 * Created by root on 9/30/17.
 */

@SuppressWarnings("ALL")
public class HomeStoreAdapter extends RecyclerView.Adapter{

    private final ArrayList<Store> data;

    private CustomItemClickListener clickListener;

    public HomeStoreAdapter(ArrayList<Store> data, CustomItemClickListener listener) {
        this.data = data;
        clickListener = listener;
    }

    @Override
    public HomeStoreViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_store_row, parent, false);
        viewHolder = new HomeStoreViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, viewHolder.getPosition());
            }
        });

        return (HomeStoreViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Store store = data.get(position);


        ((HomeStoreViewHolder)holder).title.setText(store.getTitle());
        ((HomeStoreViewHolder)holder).desc.setText(store.getSubTitle());
        Glide.with(MainApplication.getInstance())
                .load(store.getImage())
                .error(R.drawable.deal)
                .placeholder(R.drawable.deal)
                .dontAnimate().into(((HomeStoreViewHolder)holder).imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class HomeStoreViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_sub_title)
        TextView desc;
        @BindView(R.id.iv_icon)
        ImageView imageView;

        public HomeStoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
