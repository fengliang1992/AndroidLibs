package com.fltry.androidlibs.ui;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.SparseArray;

public class FragmentFactory {
    public static SparseArray<Fragment> mFragments = new SparseArray<Fragment>();

    public static Fragment createFragment(int position, Context context) {
        Fragment fragment;
        fragment = mFragments.get(position); // 在集合中取出来Fragment
        if (fragment == null) { // 如果再集合中没有取出来 需要重新创建
            if (position == 0) {
                fragment = new SdkFragment();
            } else if (position == 1) {
                fragment = new UtilFragment();
            } else if (position == 2) {
                fragment = new WeightFragment();
            }
            if (fragment != null) {
                mFragments.put(position, fragment);// 把创建好的Fragment存放到集合中缓存起来
            }
        }
        return fragment;
    }
}
