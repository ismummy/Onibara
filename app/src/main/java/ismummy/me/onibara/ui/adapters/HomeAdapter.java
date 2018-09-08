package ismummy.me.onibara.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ismummy.me.onibara.R;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.models.Store;
import ismummy.me.onibara.ui.MainApplication;
import ismummy.me.onibara.ui.activities.ProductActivity;
import ismummy.me.onibara.ui.viewholders.DealViewHolders;
import ismummy.me.onibara.ui.viewholders.SliderViewHolders;
import ismummy.me.onibara.ui.viewholders.StoreViewHolders;
import ismummy.me.onibara.utils.GridSpacingItemDecoration;

/**
 * Created by root on 9/30/17.
 */

@SuppressWarnings("ALL")
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SLIDDER = 0;
    private static final int TYPE_DEAL = 1;
    private static final int TYPE_STORES = 2;

    private ArrayList<Store> stores;
    private CustomItemClickListener clickListener;
    private Context context;

    public HomeAdapter(ArrayList<Store> store) {
        this.stores = store;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        switch (viewType) {
            case TYPE_SLIDDER:
                view = inflater.inflate(R.layout.custom_home_row1, parent, false);
                viewHolder = new SliderViewHolders(view);
                break;
            case TYPE_DEAL:
                view = inflater.inflate(R.layout.custom_home_row2, parent, false);
                viewHolder = new DealViewHolders(view);
                break;
            case TYPE_STORES:
                view = inflater.inflate(R.layout.custom_home_row3, parent, false);
                viewHolder = new StoreViewHolders(view);
                break;
            default:
                view = inflater.inflate(R.layout.custom_home_row2, parent, false);
                viewHolder = new DealViewHolders(view);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SliderViewHolders) {
            sliderHolder((SliderViewHolders) holder);
        } else if (holder instanceof DealViewHolders) {
            dealHolder((DealViewHolders) holder);
        } else if (holder instanceof StoreViewHolders) {
            storeHolder((StoreViewHolders) holder);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SLIDDER;
        } else if (position == 1) {
            return TYPE_DEAL;
        } else if (position == 2) {
            return TYPE_STORES;
        } else
            return 1;
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    void sliderHolder(SliderViewHolders holder) {

    }

    void dealHolder(DealViewHolders holder) {

    }

    void storeHolder(StoreViewHolders holder) {
        holder.recyclerView.setLayoutManager(new GridLayoutManager(MainApplication.getInstance(), 2));
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        holder.recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        HomeStoreAdapter adapter = new HomeStoreAdapter(stores, storeListener);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setAdapter(adapter);
    }

    CustomItemClickListener storeListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
            intent.putExtra("title", stores.get(position).getTitle());
            MainApplication.getInstance().startActivity(intent);
        }
    };
}