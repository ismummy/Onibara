package ismummy.me.onibara.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.ui.MainApplication;
import ismummy.me.onibara.ui.base.BaseActivity;
import ismummy.me.onibara.utils.Util;

public class ProductViewActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbar)
    TextView titleBar;
    @BindView(R.id.tv_barge)
    TextView barge;
    @BindView(R.id.btn_add_to_cart)
    RelativeLayout addToCart;
    @BindView(R.id.tv_brand)
    TextView brand;
    @BindView(R.id.tv_name)
    TextView name;
    @BindView(R.id.iv_icon)
    ImageView imageView;
    @BindView(R.id.tv_price)
    TextView price;
    @BindView(R.id.rb_rating)
    RatingBar ratingBar;
    @BindView(R.id.tv_desc)
    TextView desc;
    @BindView(R.id.tv_merchant_name)
    TextView mName;
    @BindView(R.id.tv_category)
    TextView category;
    @BindView(R.id.tv_merchant_city)
    TextView city;
    @BindView(R.id.tv_merchant_area)
    TextView area;

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        if (getIntent().getSerializableExtra("product") != null) {
            product = (Product) getIntent().getSerializableExtra("product");
        } else {
            finish();
        }

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titleBar.setText(product.getBrand());
        setCartBargeCount();

        brand.setText(product.getBrand());
        name.setText(product.getName());
        Glide.with(MainApplication.getInstance())
                .load(product.getImage())
                .error(R.drawable.deal)
                .placeholder(R.drawable.deal)
                .dontAnimate().into(imageView);
        price.setText(Util.getNairaUnitFormat(product.getPrice()));
        ratingBar.setRating((float) product.getRating());
        desc.setText(product.getSpec());
        mName.setText(product.getMerchantName());
        category.setText(product.getCategory());
        city.setText(product.getMerchantCity());
        area.setText(product.getMerchantArea());
    }

    void setCartBargeCount() {
        barge.setText(String.valueOf(MemoryManager.getInstance().getCart().size()));
        if (MemoryManager.getInstance().getCart().contains(product)) {
            addToCart.setVisibility(View.GONE);
        } else {
            addToCart.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.btn_add_to_cart)
    void btnClick() {
        MemoryManager.getInstance().updateCart(product);
        setCartBargeCount();
    }
}
