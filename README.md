
## ViewPagerTabIndicator库使用说明

1）[仿网易新闻的顶部导航指示器](#1.1)

2）[Android打造不一样的新手引导页](#1.2)



## <span id="1.1">仿网易新闻的顶部导航指示器<span/>


---

**我们知道，页面导航器（Navigator）在几乎所有的项目中都会用到，平时大多数时候为了节省时间，都会直接在github上面拿别人的开源项目来用，最近自己在复习自定义View，就尝试封装了一下，源码参考项目[PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip)**

**转载请注明[原博客地址：](http://blog.csdn.net/gdutxiaoxu/article/details/52081609)** 

## 大家先来看一下效果图
### 基于文字的页面导航器
![](http://ww1.sinaimg.cn/large/9fe4afa0gw1f6dexhz7xbg208q0ganh1.gif)

### 基于图片的页面导航器
![](http://ww4.sinaimg.cn/large/9fe4afa0gw1f6dex72nl1g208q0ga7gx.gif)

## 使用方法
**主要步骤分为三步**
### 1)在xml文件里面
```java
<com.xujun.viewpagertabindicator.TabPagerIndicator
    android:id="@+id/pagerIndicator"
    android:layout_width="match_parent"
    android:layout_height="50dp"/>

<android.support.v4.view.ViewPager
    android:layout_weight="1"
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="0dp">
</android.support.v4.view.ViewPager>

```
### 2）在代码里面找到相应的控件
```java
 mPagerIndicator = (TabPagerIndicator) findViewById(R.id.pagerIndicator);
 mViewPager = (ViewPager) findViewById(R.id.viewPager);
```
### 3）初始化ViewPager的Adapter和将mViewPager和我们的mPagerIndicator绑定
```java
//必须先给ViewPager设置适配器
mViewPager.setAdapter(mPagerAdapter);
//接着将mViewPage和我们的mPagerIndicator绑定
mPagerIndicator.setViewPager(mViewPager);
```
### 注意事项，
* 如果是文字标题导航的，我们只需重写在适配器里面重写getPageTitle这个方法

```java
public CharSequence getPageTitle(int position) {
        return titles[position];
}
```

* 如果是图标导航的，我们的适配器需要实现这个借口TabPagerIndicator.IconTabProvider，并重写里面的public int getPageIconResId(int position)这个方法
```java
public class BaseIconAdapter extends FragmentPagerAdapter implements TabPagerIndicator.IconTabProvider {

    //省略了若干方法，有兴趣可以去看一下例子

    @Override
    public int getPageIconResId(int position) {
        return resIds[position];
    }
}

```
* 我们可以通过setIndicatorMode(IndicatorMode indicatorMode)这个方法设置不同的下滑线样式

```java
mPagerIndicator.setIndicatorMode(TabPagerIndicator.IndicatorMode.MODE_WEIGHT_EXPAND_NOSAME,
            true);
```
![](http://ww4.sinaimg.cn/large/9fe4afa0gw1f6dexpxlpgj208o0f3js2.jpg)

```java
mPagerIndicator.setIndicatorMode(TabPagerIndicator.IndicatorMode.MODE_WEIGHT_EXPAND_SAME,
            true);
```

![](http://ww2.sinaimg.cn/large/9fe4afa0gw1f6dexvwva1j208r0fddgk.jpg)

**关于下划线的 颜色，字体的颜色与大小的设置，请参照源码设置，这里就不列举了**

**转载请注明[原博客地址：](http://blog.csdn.net/gdutxiaoxu/article/details/52081609)** 

---


## <span id="1.2">Android打造不一样的 新手引导页面<span/>

## 效果图如下

![](http://ww1.sinaimg.cn/large/9fe4afa0gw1f70gqpy5ycg208q0gutlk.gif)

![](http://ww1.sinaimg.cn/large/9fe4afa0gw1f70graawzcg208q0gu4qr.gif)

![](http://ww3.sinaimg.cn/large/9fe4afa0gw1f70gs1snbyg208q0gub2c.gif)

![](http://ww3.sinaimg.cn/large/9fe4afa0gw1f70gs1snbyg208q0gub2c.gif)

![](http://ww1.sinaimg.cn/large/9fe4afa0gw1f70guih4kxg208q0gue82.gif)

## 1）首先我们先来看一下要怎样使用我们的circleIndicator控件

其实很简单，值需要两个步骤

1） 在xml布局文件里面

```java 
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    >
    <android.support.v4.view.ViewPager
        android:layout_below="@id/rl_header"
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"

        android:layout_marginTop="20dp">

    </android.support.v4.view.ViewPager>

    <com.xujun.administrator.customviewspecif.view.CirclePageIndicator
        android:id="@+id/circle_indicator"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="20dp">

    </com.xujun.administrator.customviewspecif.view.CirclePageIndicator>
</RelativeLayout>

```
2）在代码里面

```java 
mViewPager = (ViewPager) findViewById(R.id.viewPager);
mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.circle_indicator);

//注意下面初始化的顺序不可以调换
mFragemntAdapter = new BaseFragemntAdapter(
        getSupportFragmentManager(), mFragments);
mViewPager.setAdapter(mFragemntAdapter);

//将mCirclePageIndicator与我们的mViewPager绑定在一起
mCirclePageIndicator.setViewPager(mViewPager);

```

3）页面的切换效果

```java?linenums
DefaultTransformer defaultTransformer = new DefaultTransformer();
mViewPager.setPageTransformer(true, defaultTransformer);

```

### 扩展
1）在xml布局里面更改我们的样式

```java 
xmlns:app="http://schemas.android.com/apk/res-auto"
//例如更改我们移动小圆点的颜色
app:fillColor="#fff"


//其他属性的更改请参考以下我们自定义的属性

<declare-styleable name="CirclePageIndicator">
    <!-- Whether or not the indicators should be centered. -->
    <attr name="centered" />
    <!-- Color of the filled circle that represents the current page. -->
    <attr name="fillColor" format="color" />
    <!-- Color of the filled circles that represents pages. -->
    <attr name="pageColor" format="color" />
    <!-- Orientation of the indicator. -->
    <attr name="android:orientation"/>
    <!-- Radius of the circles. This is also the spacing between circles. -->
    <attr name="radius" format="dimension" />
    <!-- Whether or not the selected indicator snaps to the circles. -->
    <attr name="snap" format="boolean" />
    <!-- Color of the open circles. -->
    <attr name="strokeColor" format="color" />
    <!-- Width of the stroke used to draw the circles. -->
    <attr name="strokeWidth" />
    <!-- View background -->
    <attr name="android:background"/>
</declare-styleable>


```

2）在Java代码里面动态更改

```java
// 设置滑动的时候移动的小圆点是否跳跃
mCirclePageIndicator.setSnap(false);
//设置小圆点的半径
mCirclePageIndicator.setRadius(10 * density);
// 设置页面小圆点的颜色
mCirclePageIndicator.setPageColor(0x880000FF);
// 设置移动的小圆点的颜色
mCirclePageIndicator.setFillColor(0xFF888888);
// 设置外边框的颜色
mCirclePageIndicator.setStrokeColor(0xFF000000);
//设置外表框的宽度
mCirclePageIndicator.setStrokeWidth(2 * density);


```

