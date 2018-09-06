package ismummy.me.onibara.ui.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ismummy.me.onibara.R;

public class StoreViewHolders extends RecyclerView.ViewHolder {

    public @BindView(R.id.rv_stores)
    RecyclerView recyclerView;

    public StoreViewHolders(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
}
