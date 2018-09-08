package ismummy.me.onibara.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import net.steamcrafted.materialiconlib.MaterialIconView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.listeners.EndlessRecyclerviewScroll;
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.activities.MainActivity;
import ismummy.me.onibara.ui.activities.ProductActivity;
import ismummy.me.onibara.ui.activities.ProductViewActivity;
import ismummy.me.onibara.ui.adapters.ProductsAdapter;
import ismummy.me.onibara.ui.base.BaseFragment;
import ismummy.me.onibara.utils.GridSpacingItemDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment {

    @BindView(R.id.rv_cart)
    RecyclerView recyclerView;

    @BindView(R.id.layout_no_cart)
    LinearLayout noCart;
    @BindView(R.id.layout_cart)
    RelativeLayout cart;


    private ProductsAdapter adapter;
    private ArrayList<Product> products;


    public static CartFragment newInstance() {
        return new CartFragment();
    }

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();
        products = MemoryManager.getInstance().getCart();

        if (products.isEmpty()) {
            noCart.setVisibility(View.VISIBLE);
            cart.setVisibility(View.GONE);
        } else {
            cart.setVisibility(View.VISIBLE);
            noCart.setVisibility(View.GONE);
        }
        setRecyclerView();
    }

    void setRecyclerView() {
        adapter = new ProductsAdapter(products, productListener, cartListener);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        EndlessRecyclerviewScroll recyclerviewScroll = new EndlessRecyclerviewScroll(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                toast(page + " hey");
            }
        };
        recyclerView.addOnScrollListener(recyclerviewScroll);
    }

    @OnClick(R.id.btn_continue_shopping)
    void continueShoppingClicked() {
        ((MainActivity) getActivity()).setViewPagerIndex(0);
    }

    CustomItemClickListener productListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(getActivity(), ProductViewActivity.class);
            intent.putExtra("product", products.get(position));
            startActivity(intent);
        }
    };
    CustomItemClickListener cartListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {

        }
    };

}
