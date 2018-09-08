package ismummy.me.onibara.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.thefinestartist.finestwebview.FinestWebView;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/*
 * Base activity for all activities to setup common setting for all activity
 * like network fail
 *
 * */

@SuppressWarnings("ALL")
public abstract class BaseActivity extends AppCompatActivity {


    private volatile boolean isOn = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOn = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOn = true;
    }

    protected void toast(String message) {
        if (!isOn || isFinishing())
            return;

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void toastNetwork() {
        if (!isOn || isFinishing())
            return;
        Toast.makeText(this, "Check your internet connection and Try again!!!",
                Toast.LENGTH_LONG).show();
    }
    protected void toastConnectionFailure() {
        if (!isOn || isFinishing())
            return;

        Toast.makeText(this, "Error response from remote server. Please retry!!!",
                Toast.LENGTH_LONG).show();
    }

    protected void openInternalWebView(String endPoint){
        new FinestWebView.Builder(this).show(endPoint);
    }
}
