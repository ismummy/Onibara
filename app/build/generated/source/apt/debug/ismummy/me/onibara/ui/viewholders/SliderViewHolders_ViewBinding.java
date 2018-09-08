// Generated code from Butter Knife. Do not modify!
package ismummy.me.onibara.ui.viewholders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.synnapps.carouselview.CarouselView;
import ismummy.me.onibara.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SliderViewHolders_ViewBinding implements Unbinder {
  private SliderViewHolders target;

  private View view2131231128;

  @UiThread
  public SliderViewHolders_ViewBinding(final SliderViewHolders target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.carouselView, "field 'carouselView' and method 'sliderClick'");
    target.carouselView = Utils.castView(view, R.id.carouselView, "field 'carouselView'", CarouselView.class);
    view2131231128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.sliderClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SliderViewHolders target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.carouselView = null;

    view2131231128.setOnClickListener(null);
    view2131231128 = null;
  }
}
