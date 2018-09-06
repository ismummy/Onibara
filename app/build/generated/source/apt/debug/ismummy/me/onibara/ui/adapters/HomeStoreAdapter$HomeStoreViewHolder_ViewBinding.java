// Generated code from Butter Knife. Do not modify!
package ismummy.me.onibara.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import ismummy.me.onibara.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeStoreAdapter$HomeStoreViewHolder_ViewBinding implements Unbinder {
  private HomeStoreAdapter.HomeStoreViewHolder target;

  @UiThread
  public HomeStoreAdapter$HomeStoreViewHolder_ViewBinding(HomeStoreAdapter.HomeStoreViewHolder target,
      View source) {
    this.target = target;

    target.title = Utils.findRequiredViewAsType(source, R.id.tv_title, "field 'title'", TextView.class);
    target.desc = Utils.findRequiredViewAsType(source, R.id.tv_sub_title, "field 'desc'", TextView.class);
    target.imageView = Utils.findRequiredViewAsType(source, R.id.iv_icon, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeStoreAdapter.HomeStoreViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.desc = null;
    target.imageView = null;
  }
}
