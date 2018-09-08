package ismummy.me.onibara.core;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ismummy.me.onibara.models.Product;
import ismummy.me.onibara.models.Store;
import ismummy.me.onibara.ui.MainApplication;

/**
 * Created by root on 9/25/17.
 */

@SuppressWarnings("ALL")
public class MemoryManager {

    private static MemoryManager sInstance;
    private final SharedPreferences mSharedPreferences;
    private final SharedPreferences.Editor editor;
    private static final String PREF_NAME = "Onibara";
    private static final int PREF_MODE = 0;
    private static final String KEY_CART = "_PRODUCTS IN CART_";
    private static final String KEY_STORE = "_PRODUCT CATEGORY_";
    private static final String KEY_LAST_SEARCH = "_LAST PRODUCT SEARCH_";


    private MemoryManager() {
        mSharedPreferences = MainApplication.getInstance().getApplicationContext()
                .getSharedPreferences(PREF_NAME, PREF_MODE);
        editor = mSharedPreferences.edit();
    }

    public static synchronized MemoryManager getInstance() {
        if (sInstance == null) sInstance = new MemoryManager();
        return sInstance;
    }

    public void updateCart(Product product) {
        ArrayList<Product> cart = getCart();
        if (cart != null) {
            if (!cart.contains(product)) {
                cart.add(product);
                putCart(cart);
            }
        } else {
            cart = new ArrayList<>();
            cart.add(product);
            putCart(cart);
        }
    }

    public void putCart(ArrayList<Product> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY_CART, json);
        editor.commit();
    }

    public void saveLastSearch(String search) {
        Gson gson = new Gson();
        String json = gson.toJson(search);
        editor.putString(KEY_LAST_SEARCH, json);
        editor.commit();
    }

    public String getLastSearch() {
        return mSharedPreferences.getString(KEY_LAST_SEARCH, "");
    }


    public ArrayList<Product> getCart() {
        if (mSharedPreferences.getString(KEY_CART, null) != null) {
            Gson gson = new Gson();
            String json = mSharedPreferences.getString(KEY_CART, null);
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }

    public void putStore(ArrayList<Store> arrayList) {
        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString(KEY_STORE, json);
        editor.commit();
    }

    public ArrayList<Store> getStore() {
        if (mSharedPreferences.getString(KEY_STORE, null) != null) {
            Gson gson = new Gson();
            String json = mSharedPreferences.getString(KEY_STORE, null);
            Type type = new TypeToken<ArrayList<Store>>() {
            }.getType();
            return gson.fromJson(json, type);
        }
        return new ArrayList<>();
    }


}
