package com.xujun.administrator.customviewspecif;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xujun.administrator.customviewspecif.adapter.MenuAdapter;
import com.xujun.administrator.customviewspecif.transformer.CubeOutTransformer;
import com.xujun.administrator.customviewspecif.transformer.DefaultTransformer;
import com.xujun.administrator.customviewspecif.transformer.DepthPageTransformer;
import com.xujun.administrator.customviewspecif.transformer.TableTransformer;
import com.xujun.administrator.customviewspecif.transformer.TransFormerItem;
import com.xujun.administrator.customviewspecif.transformer.ZoomOutPageTransformer;
import com.xujun.administrator.customviewspecif.view.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "xujun";

    private RelativeLayout mRlHeader;
    private ImageView mIvIcon;
    private TextView mTvTitle;
    private ImageView mIvSelect;
    private ViewPager mViewPager;
    private CirclePageIndicator mCircleIndicator;

    List<Fragment> mFragments;
    private BaseFragemntAdapter mFragemntAdapter;
    private CirclePageIndicator mCirclePageIndicator;

    private PopupWindow mMenu;
    private ListView mListView;
    private ArrayList<TransFormerItem> mTransFormerItems;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();


    }

    public void setTextViewSpan(TextView view, int fontSize, int start, int end, int color) {

        SpannableString span = new SpannableString(view.getText());
        span.setSpan(new AbsoluteSizeSpan(fontSize), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setText(span);
    }

    private void initListener() {
        mCirclePageIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
        });

        mRlHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick:=");
                if (mMenu == null) {
                    mMenu = initPopWindow();
                }
                if (mMenu.isShowing()) {
                    mMenu.dismiss();
                } else {
                    mMenu.showAsDropDown(mRlHeader);
                }

            }
        });
    }

    private void initData() {

        mFragments = new ArrayList<>();
        mFragments.add(SplashFragment.newInstance(0));
        mFragments.add(SplashFragment.newInstance(1));
        mFragments.add(SplashFragment.newInstance(2));
        mFragments.add(SplashFragment.newInstance(3));
        mFragemntAdapter = new BaseFragemntAdapter(
                getSupportFragmentManager(), mFragments);
        DefaultTransformer defaultTransformer = new DefaultTransformer();
        mViewPager.setPageTransformer(true, defaultTransformer);
        mViewPager.setAdapter(mFragemntAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);

        final float density = getResources().getDisplayMetrics().density;
        //        mCirclePageIndicator.setBackgroundColor(0xFFCCCCCC);
        mCirclePageIndicator.setSnap(false);
        mCirclePageIndicator.setRadius(10 * density);
        mCirclePageIndicator.setPageColor(0x880000FF);
        mCirclePageIndicator.setFillColor(0xFF888888);
        mCirclePageIndicator.setStrokeColor(0xFF000000);
        mCirclePageIndicator.setStrokeWidth(2 * density);
    }

    private PopupWindow initPopWindow() {
        View view = View.inflate(this, R.layout.menu_pop, null);
        mMenu = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mListView = (ListView) view.findViewById(R.id.listView);

        mTransFormerItems = new ArrayList<>();
        mTransFormerItems.add(new TransFormerItem(DefaultTransformer.class));
        mTransFormerItems.add(new TransFormerItem(ZoomOutPageTransformer.class));
        mTransFormerItems.add(new TransFormerItem(DepthPageTransformer.class));
        mTransFormerItems.add(new TransFormerItem(CubeOutTransformer.class));
        mTransFormerItems.add(new TransFormerItem(TableTransformer.class));
        mMenuAdapter = new MenuAdapter(this, mTransFormerItems);

        mMenu.setFocusable(true);
        mMenu.update();


        mListView.setAdapter(mMenuAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "onItemClick:=" + position);

                TransFormerItem transFormerItem = mTransFormerItems.get(position);

                mTvTitle.setText("切换样式:" + transFormerItem.title);
                mViewPager.setPageTransformer(true, transFormerItem.mPageTransformer);
                mFragemntAdapter.notifyDataSetChanged();
                if (mMenu != null) {
                    mMenu.dismiss();
                }
            }
        });
        mMenu.setContentView(view);
        mMenu.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
        return mMenu;
    }

    private void initView() {
        mRlHeader = (RelativeLayout) findViewById(R.id.rl_header);

        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mIvSelect = (ImageView) findViewById(R.id.iv_select);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);


        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);
    }


}
