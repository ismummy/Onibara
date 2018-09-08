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
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.MainApplication;
import ismummy.me.onibara.utils.Util;

/**
 * Created by root on 9/30/17.
 */

@SuppressWarnings("ALL")
public class ProductSearchAdapter extends RecyclerView.Adapter {

    private final ArrayList<Product> data;

    private CustomItemClickListener clickListener;

    public ProductSearchAdapter(ArrayList<Product> data, CustomItemClickListener listener) {
        this.data = data;
        clickListener = listener;
    }

    @Override
    public ProductSearchViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_search_row, parent, false);
        viewHolder = new ProductSearchViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, viewHolder.getPosition());
            }
        });

        return (ProductSearchViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = data.get(position);


        ((ProductSearchViewHolder) holder).title.setText(product.getName());
        ((ProductSearchViewHolder) holder).price.setText(Util.getNairaUnitFormat(product.getPrice()));
        Glide.with(MainApplication.getInstance())
                .load(product.getImage())
                .error(R.drawable.deal)
                .placeholder(R.drawable.deal)
                .dontAnimate().into(((ProductSearchViewHolder) holder).imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ProductSearchViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView title;
        @BindView(R.id.tv_price)
        TextView price;
        @BindView(R.id.iv_icon)
        ImageView imageView;

        public ProductSearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
