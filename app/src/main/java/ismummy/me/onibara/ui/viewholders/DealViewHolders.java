package ismummy.me.onibara.ui.viewholders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import ismummy.me.onibara.R;
import ismummy.me.onibara.ui.MainApplication;
import ismummy.me.onibara.ui.activities.ProductActivity;
import ismummy.me.onibara.utils.Debug;

public class DealViewHolders extends RecyclerView.ViewHolder {
    public DealViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @OnClick(R.id.layout_entertainment)
    void electronicClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "electronics");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_men_shoe)
    void menShoeClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "men shoes");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_men_clothing)
    void menClothClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "men clothing");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_deal)
    void dealClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "Deals");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_women_cloth)
    void womenCloth() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "women clothing");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_phones)
    void phoneClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "Phones and Tablets");
        MainApplication.getInstance().startActivity(intent);
    }

    @OnClick(R.id.layout_women_shoe)
    void womenShoeClick() {
        Intent intent = new Intent(MainApplication.getInstance(), ProductActivity.class);
        intent.putExtra("title", "women shoes");
        MainApplication.getInstance().startActivity(intent);
    }

}
