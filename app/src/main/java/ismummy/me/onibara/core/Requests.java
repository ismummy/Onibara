package ismummy.me.onibara.core;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * 6/13/2017.
 */

public final class Requests {

    private static final AsyncHttpClient asyncHttpClient = new AsyncHttpClient(true, 80, 443);

    public static final String BASE_URL = "https://www.onibara.connectrail.net/app";
    public static final String MEDIA_URI = "http://www.onibara.connectrail.net/";

    public static final String AUTH = "Auth";

    public static void post(String endPoint, RequestParams requestParams, TextHttpResponseHandler textHttpResponseHandler) {
        asyncHttpClient.setResponseTimeout(30 * 10000);
        asyncHttpClient.setTimeout(30 * 10000);
        asyncHttpClient.setResponseTimeout(30 * 10000);
        //asyncHttpClient.addHeader(AUTH, MemoryManager.getInstance().getToken());
        asyncHttpClient.post(BASE_URL + endPoint, requestParams, textHttpResponseHandler);

    }

    public static void post(String endPoint, TextHttpResponseHandler callBack) {
        post(endPoint, null, callBack);
    }

    public static void get(String endPoint, TextHttpResponseHandler responseHandler) {
        asyncHttpClient.setResponseTimeout(30 * 10000);
        asyncHttpClient.setTimeout(30 * 10000);
        asyncHttpClient.setResponseTimeout(30 * 10000);
        //asyncHttpClient.addHeader(AUTH, MemoryManager.getInstance().getToken());
        String url = BASE_URL + endPoint;
        asyncHttpClient.get(url, null, responseHandler);
    }

    public static void put(String endPoint, RequestParams requestParams, TextHttpResponseHandler textHttpResponseHandler) {
        asyncHttpClient.setResponseTimeout(30 * 10000);
        asyncHttpClient.setTimeout(30 * 10000);
        asyncHttpClient.setResponseTimeout(30 * 10000);
        //asyncHttpClient.addHeader(AUTH, MemoryManager.getInstance().getToken());
        asyncHttpClient.put(BASE_URL + endPoint, requestParams, textHttpResponseHandler);
    }
}
