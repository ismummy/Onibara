package ismummy.me.onibara.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.loopj.android.http.TextHttpResponseHandler;

import net.steamcrafted.materialiconlib.MaterialIconView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import cz.msebera.android.httpclient.Header;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.EndPoints;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.core.Requests;
import ismummy.me.onibara.listeners.CustomItemClickListener;
import ismummy.me.onibara.listeners.EndlessRecyclerviewScroll;
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.adapters.ProductSearchAdapter;
import ismummy.me.onibara.ui.base.BaseActivity;
import ismummy.me.onibara.utils.Debug;
import ismummy.me.onibara.utils.Util;

public class SearchActivity extends BaseActivity {

    @BindView(R.id.rv_search_products)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.edt_search)
    EditText search;
    @BindView(R.id.miv_clear)
    MaterialIconView clear;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ProductSearchAdapter adapter;
    private ArrayList<Product> products;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setRecyclerView();
        input = MemoryManager.getInstance().getLastSearch();
        searchProduct(input);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    void setRecyclerView() {
        products = new ArrayList<>();
        adapter = new ProductSearchAdapter(products, productListener);
        LinearLayoutManager manager = new LinearLayoutManager(this);
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

    CustomItemClickListener productListener = new CustomItemClickListener() {
        @Override
        public void onItemClick(View v, int position) {
            Intent intent = new Intent(SearchActivity.this, ProductViewActivity.class);
            intent.putExtra("product", products.get(position));
            startActivity(intent);
        }
    };

    @OnTextChanged(value = R.id.edt_search, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void lengthTextChanged() {
        input = search.getText().toString().trim().toLowerCase();
        if (!input.isEmpty()) {
            clear.setVisibility(View.VISIBLE);
            MemoryManager.getInstance().saveLastSearch(input);
            searchProduct(input);
        } else {
            clear.setVisibility(View.GONE);
        }

    }

    private void searchProduct(String input) {
        if (Util.isOnline()) {
            Requests.get(EndPoints.GET_PRODUCTS.replace("_KEYWORD_", input), textHttpResponseHandlerCompany);
        } else {
            toastNetwork();
        }
    }

    private final TextHttpResponseHandler textHttpResponseHandlerCompany = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            toastConnectionFailure();
            Debug.Log(responseString, throwable);
            searchProduct(input);
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

    @OnClick(R.id.miv_clear)
    void clearClick() {
        search.setText("");
        clear.setVisibility(View.GONE);
    }
}
