package ismummy.me.onibara.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.OnClick;
import ismummy.me.onibara.R;
import ismummy.me.onibara.core.EndPoints;
import ismummy.me.onibara.ui.adapters.MainActivityAdapter;
import ismummy.me.onibara.ui.base.BaseActivity;
import ismummy.me.onibara.ui.fragments.CartFragment;
import ismummy.me.onibara.ui.fragments.HomeFragment;
import ismummy.me.onibara.ui.fragments.SavedFragment;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViewPager();
        setupDrawer();
    }

    private void setupDrawer() {
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void setupViewPager() {
        MainActivityAdapter adapter = new MainActivityAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance());
        adapter.addFragment(CartFragment.newInstance());
        adapter.addFragment(SavedFragment.newInstance());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_cart);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_action_saved);
    }

    //method to move change the current page another page from other fragment
    public void setViewPagerIndex(int viewPagerIndex) {
        viewPager.setCurrentItem(viewPagerIndex);
    }

    @OnClick(R.id.miv_search)
    void searchClick(){
        startActivity(new Intent(this, SearchActivity.class));
    }
}
