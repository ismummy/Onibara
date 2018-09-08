package ismummy.me.onibara.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.loopj.android.http.TextHttpResponseHandler;

import net.steamcrafted.materialiconlib.MaterialIconView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.EndPoints;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.core.Requests;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.listeners.EndlessRecyclerviewScroll;
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.adapters.ProductsAdapter;
import ismummy.me.onibara.ui.base.BaseActivity;
import ismummy.me.onibara.utils.Debug;
import ismummy.me.onibara.utils.GridSpacingItemDecoration;
import ismummy.me.onibara.utils.Util;

public class ProductActivity extends BaseActivity {

    @BindView(R.id.rv_products)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar)
    TextView titleBar;
    @BindView(R.id.tv_barge)
    TextView barge;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private String title = "All";

    private ProductsAdapter adapter;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        if (getIntent().getStringExtra("title") != null) {
            title = getIntent().getStringExtra("title");
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleBar.setText(title);
        setCartBargeCount();
        setRecyclerView();
        if (title.equals("Deals"))
            title = "a";
        title = title.split(" ")[0];
        searchProduct(title.toLowerCase());
    }

    void setCartBargeCount() {
        barge.setText(String.valueOf(MemoryManager.getInstance().getCart().size()));
    }

    void setRecyclerView() {
        products = new ArrayList<>();
        adapter = new ProductsAdapter(products, productListener, cartListener);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        int spanCount = 2; // 3 columns
        int spacing = 10; // 50px
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, true));
        recyclerView.setAdapter(adapter);
        EndlessRecyclerviewScroll recyclerviewScroll = new EndlessRecyclerviewScroll(manager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                toast(page + " hey");
            }
        };
        recyclerView.addOnScrollListener(recyclerviewScroll);
    }

    CustomItemClickListener productListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(ProductActivity.this, ProductViewActivity.class);
            intent.putExtra("product", products.get(position));
            startActivity(intent);
        }
    };

    private void searchProduct(String input) {
        if (Util.isOnline()) {
            Requests.get(EndPoints.GET_PRODUCTS.replace("_KEYWORD_", input), textHttpResponseHandlerCompany);
        } else {
            toastNetwork();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCartBargeCount();
    }

    private final TextHttpResponseHandler textHttpResponseHandlerCompany = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            toastConnectionFailure();
            Debug.Log(responseString, throwable);
            searchProduct(title.toLowerCase());
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            Debug.Log(responseString);
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                if (!jsonObject.getBoolean("error")) {
                    JSONArray data = jsonObject.getJSONArray("products");
                    products.clear();
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject productData = data.getJSONObject(i);
                        Product product = new Product();
                        product.setName(productData.getString("name"));
                        product.setBrand(productData.getString("brand"));
                        product.setImage(productData.getString("image"));
                        product.setPrice(productData.getDouble("price"));
                        product.setRating(productData.getDouble("rating"));
                        product.setCategory(productData.getString("category"));
                        product.setMerchantName(productData.getString("merchant"));
                        product.setMerchantArea(productData.getString("merchant_area"));
                        product.setMerchantCity(productData.getString("merchant_city"));
                        product.setId(productData.getString("id"));
                        product.setSpec(productData.getString("spec"));
                        products.add(product);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    toast(jsonObject.getString("message"));
                }
            } catch (JSONException e) {
                Debug.Log(e);
            }
        }

        @Override
        public void onStart() {
            super.onStart();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onFinish() {
            super.onFinish();
            progressBar.setVisibility(View.GONE);
        }
    };


    CustomItemClickListener cartListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            setCartBargeCount();
        }
    };

    @OnClick(R.id.miv_search)
    void searchClick() {
        startActivity(new Intent(this, SearchActivity.class));
    }
}
