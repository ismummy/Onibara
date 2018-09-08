// Generated code from Butter Knife. Do not modify!
package ismummy.me.onibara.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import ismummy.me.onibara.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CartFragment_ViewBinding implements Unbinder {
  private CartFragment target;

  private View view2131231050;

  @UiThread
  public CartFragment_ViewBinding(final CartFragment target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.rv_cart, "field 'recyclerView'", RecyclerView.class);
    target.noCart = Utils.findRequiredViewAsType(source, R.id.layout_no_cart, "field 'noCart'", LinearLayout.class);
    target.cart = Utils.findRequiredViewAsType(source, R.id.layout_cart, "field 'cart'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_continue_shopping, "method 'continueShoppingClicked'");
    view2131231050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.continueShoppingClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CartFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.noCart = null;
    target.cart = null;

    view2131231050.setOnClickListener(null);
    view2131231050 = null;
  }
}
