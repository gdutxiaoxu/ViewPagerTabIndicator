# 仿网易新闻的顶部导航指示器

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
