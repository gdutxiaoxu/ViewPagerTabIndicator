package com.xujun.administrator.customviewspecif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.xujun.administrator.customviewspecif.adapter.MenuAdapter;
import com.xujun.administrator.customviewspecif.transformer.CubeOutTransformer;
import com.xujun.administrator.customviewspecif.transformer.DefaultTransformer;
import com.xujun.administrator.customviewspecif.transformer.DepthPageTransformer;
import com.xujun.administrator.customviewspecif.transformer.TableTransformer;
import com.xujun.administrator.customviewspecif.transformer.TransFormerItem;
import com.xujun.administrator.customviewspecif.transformer.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    ListView mListView;
    private List<TransFormerItem> mTransFormerItems;
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        mListView= (ListView) findViewById(R.id.listView);

        mTransFormerItems = new ArrayList<>();
        mTransFormerItems.add(new TransFormerItem(DefaultTransformer.class));
        mTransFormerItems.add(new TransFormerItem(ZoomOutPageTransformer.class));
        mTransFormerItems.add(new TransFormerItem(DepthPageTransformer.class));
        mTransFormerItems.add(new TransFormerItem(CubeOutTransformer.class));
        mTransFormerItems.add(new TransFormerItem(TableTransformer.class));

        mMenuAdapter = new MenuAdapter(this, mTransFormerItems);
        mListView.setAdapter(mMenuAdapter);
    }
}
