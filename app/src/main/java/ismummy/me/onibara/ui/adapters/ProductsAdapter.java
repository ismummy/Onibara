package ismummy.me.onibara.ui.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.MainApplication;
import ismummy.me.onibara.utils.Util;

/**
 * Created by root on 9/30/17.
 */

@SuppressWarnings("ALL")
public class ProductsAdapter extends RecyclerView.Adapter {

    private final ArrayList<Product> data;

    private CustomItemClickListener clickListener, cartListener;

    public ProductsAdapter(ArrayList<Product> data, CustomItemClickListener listener, CustomItemClickListener cartListener) {
        this.data = data;
        clickListener = listener;
        this.cartListener = cartListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder viewHolder;

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_row, parent, false);
        viewHolder = new ProductViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(view, viewHolder.getPosition());
            }
        });

        return (ProductViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        holder = (ProductViewHolder) holder;
        final Product product = data.get(position);
        if (MemoryManager.getInstance().getCart().contains(product)) {
            ((ProductViewHolder) holder).added_to_cart.setVisibility(View.VISIBLE);
            ((ProductViewHolder) holder).add_to_cart.setVisibility(View.GONE);
        } else {
            ((ProductViewHolder) holder).added_to_cart.setVisibility(View.GONE);
            ((ProductViewHolder) holder).add_to_cart.setVisibility(View.VISIBLE);
        }
        final RecyclerView.ViewHolder finalHolder = holder;
        final RecyclerView.ViewHolder finalHolder1 = holder;
        ((ProductViewHolder) holder).add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MemoryManager.getInstance().updateCart(product);
                cartListener.onItemClick(((ProductViewHolder) finalHolder1).add_to_cart, position);
                ((ProductViewHolder) finalHolder).added_to_cart.setVisibility(View.VISIBLE);
                ((ProductViewHolder) finalHolder).add_to_cart.setVisibility(View.GONE);
            }
        });

        Glide.with(MainApplication.getInstance())
                .load(product.getImage())
                .error(R.drawable.deal)
                .placeholder(R.drawable.deal)
                .dontAnimate().into(((ProductViewHolder) holder).imageView);

        ((ProductViewHolder) holder).brand.setText(product.getBrand());
        ((ProductViewHolder) holder).name.setText(product.getName());
        ((ProductViewHolder) holder).price.setText(Util.getNairaUnitFormat(product.getPrice()));
        ((ProductViewHolder) holder).ratingBar.setRating((float) product.getRating());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.miv_add_to_cart)
        MaterialIconView add_to_cart;
        @BindView(R.id.miv_added_to_cart)
        MaterialIconView added_to_cart;
        @BindView(R.id.iv_icon)
        ImageView imageView;
        @BindView(R.id.tv_brand)
        TextView brand;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_price)
        TextView price;
        @BindView(R.id.rb_rating)
        RatingBar ratingBar;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

    CustomItemClickListener listener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {

        }
    };
}
