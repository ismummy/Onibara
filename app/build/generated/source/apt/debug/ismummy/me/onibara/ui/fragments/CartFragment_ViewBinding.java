// Generated code from Butter Knife. Do not modify!
package ismummy.me.onibara.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import ismummy.me.onibara.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CartFragment_ViewBinding implements Unbinder {
  private CartFragment target;

  private View view2131231048;

  @UiThread
  public CartFragment_ViewBinding(final CartFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_continue_shopping, "method 'continueShoppingClicked'");
    view2131231048 = view;
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
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131231048.setOnClickListener(null);
    view2131231048 = null;
  }
}
