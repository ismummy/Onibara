package ismummy.me.onibara.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import ismummy.me.onibara.R;
import ismummy.me.onibara.ui.MainApplication;

/**
 * 6/13/2017.
 */

public class Util {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd_mm_yyyy_hh_mm_ss", Locale.ENGLISH);

    public static boolean isOnline() {
        if (MainApplication.getInstance().getApplicationContext() == null)
            return false;

        ConnectivityManager cm = (ConnectivityManager)
                MainApplication.getInstance().getApplicationContext()
                        .getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    /*public static boolean empty(MaterialEditText materialEditText) {
        return materialEditText.getText().toString().trim().length() <= 0;
    }

    public static boolean empty(EditText materialEditText) {
        return materialEditText.getText().toString().trim().length() <= 0;
    }

    public static String text(MaterialEditText editText) {
        return editText.getText().toString().trim();
    }

    public static int length(MaterialEditText editText){
        return text(editText).length();
    }

    public static boolean email(MaterialEditText editText) {
        return !empty(editText) && isValidEmail(text(editText));
    }
*/
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String getNairaUnitFormat(double amount) {
        String amounts = amount + "D";
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
        String currency = format.format(Double.parseDouble(amounts));
        return String.valueOf(getNaira() + currency.replace("$", ""));
    }

    private static String getNaira() {
        return MainApplication.getInstance().getString(R.string.naira);
    }


   /* private static String getNaira() {
        return MainApplication.getInstance().getString(R.string.naira);
    }*/

  /*  public static File getCompressedFile(String path, Context context) throws IOException {
        if (context == null)
            return new File(path);

        File fresh = new File(path);
        long size = fresh.length() / 1000; //To KB

        //doesn't need compression. Use as it is.
        if (Math.abs(size) <= 400) {
            return fresh;
        }


        File cacheDIR = context.getExternalCacheDir();
        if (cacheDIR == null)
            cacheDIR = context.getCacheDir();
        String tempCacheDIR = cacheDIR.getAbsolutePath() + "/Kwiiki/.Temp";
        File tempCacheFile = new File(tempCacheDIR);
        if (!tempCacheFile.exists())
            tempCacheFile.mkdirs();

        Bitmap bitmap = BitmapUtils.decodeImageFromFiles(path);
        if (bitmap == null) {
            //are you kidding me?
            return null;
        }
        File mainFile = new File(tempCacheFile, SIMPLE_DATE_FORMAT.format(new Date()) + new Random().nextInt(999999) + ".jpg");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, outputStream);

        FileOutputStream fileOutputStream = new FileOutputStream(mainFile);
        fileOutputStream.write(outputStream.toByteArray());
        fileOutputStream.flush();

        fileOutputStream.close();
        return mainFile;
    }

  */
}

