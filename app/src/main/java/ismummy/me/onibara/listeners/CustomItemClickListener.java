package ismummy.me.onibara.listeners;

import android.view.View;

public interface CustomItemClickListener {
    public void onItemClick(View v, int position);
}
/*

public interface CustomItemClickListener<M, V> {
    public void onItemClick(V v, M data);
}
*/
