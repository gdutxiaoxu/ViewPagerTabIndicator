package com.xujun.administrator.customviewspecif;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * 博客地址：http://blog.csdn.net/gdutxiaoxu
 * @author xujun
 * @time 2016/8/14 20:35.
 */
public class SplashFragment extends Fragment {

    private View mView;
    private ImageView mImageView;
    private Button mBtnExpercise;
    private static String index="position";
    private int lastPosition=3;

    static  int[] imageIds=new int[]{
            R.mipmap.tangyan_1,R.mipmap.tangyan_2,R.mipmap.tangyan_5,R.mipmap.tangyan_11
    };

    public static SplashFragment newInstance(int position){
        SplashFragment splashFragment = new SplashFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(index,position);
        splashFragment.setArguments(bundle);
        return splashFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_splash, container, false);
        mImageView= (ImageView) mView.findViewById(R.id.iv);
        mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mBtnExpercise= (Button) mView.findViewById(R.id.btn_experice);
        Bundle arguments = getArguments();
        if(arguments!=null){
           int  position=arguments.getInt(index);
            if(position==lastPosition){
                mBtnExpercise.setVisibility(View.VISIBLE);
            }
            if( position>=0&& position<=lastPosition){
                mImageView.setImageResource(imageIds[position]);
            }
        }


        return mView;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
