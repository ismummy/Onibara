package ismummy.me.onibara.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.EndPoints;
import ismummy.me.onibara.core.MemoryManager;
import ismummy.me.onibara.core.Requests;
import ismummy.me.onibara.models.Store;
import ismummy.me.onibara.ui.base.BaseActivity;
import ismummy.me.onibara.utils.Debug;
import ismummy.me.onibara.utils.Util;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getCategory();;
    }

    private void getCategory() {
        if (Util.isOnline()) {
            Requests.get(EndPoints.GET_CATEGORY, textHttpResponseHandlerCompany);
        } else {
            toastNetwork();
        }
    }

    private final TextHttpResponseHandler textHttpResponseHandlerCompany = new TextHttpResponseHandler() {
        @Override
        public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
            toastConnectionFailure();
            Debug.Log(responseString, throwable);
            getCategory();
        }

        @Override
        public void onSuccess(int statusCode, Header[] headers, String responseString) {
            Debug.Log(responseString);
            try {
                JSONObject jsonObject = new JSONObject(responseString);
                if (!jsonObject.getBoolean("error")) {

                    ArrayList<Store> stores = new ArrayList<>();
                    JSONArray data = jsonObject.getJSONArray("categories");
                    for (int i = 0; i < data.length(); i++) {
                        JSONObject categoryData = data.getJSONObject(i);
                        Store store = new Store();
                        store.setTitle(categoryData.getString("name"));
                        store.setSubTitle(categoryData.getString("sub_title"));
                        store.setImage(categoryData.getString("image"));
                        store.setId(categoryData.getString("id"));
                        stores.add(store);
                    }
                    MemoryManager.getInstance().putStore(stores);
                    SplashScreen.this.startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    SplashScreen.this.finish();
                } else {
                    toast(jsonObject.getString("message"));
                }
            } catch (JSONException e) {
                Debug.Log(e);
            }
        }

    };

}
