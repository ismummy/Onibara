// Generated code from Butter Knife. Do not modify!
package ismummy.me.onibara.ui.viewholders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.synnapps.carouselview.CarouselView;
import ismummy.me.onibara.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SliderViewHolders_ViewBinding implements Unbinder {
  private SliderViewHolders target;

  @UiThread
  public SliderViewHolders_ViewBinding(SliderViewHolders target, View source) {
    this.target = target;

    target.carouselView = Utils.findRequiredViewAsType(source, R.id.carouselView, "field 'carouselView'", CarouselView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SliderViewHolders target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.carouselView = null;
  }
}
