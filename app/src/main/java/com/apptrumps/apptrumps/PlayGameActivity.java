package com.apptrumps.apptrumps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 30-Jul-17.
 */

public class PlayGameActivity extends FragmentActivity implements ViewPager.PageTransformer{
    private static final String TAG = PlayGameActivity.class.getSimpleName();
    private int mNumCards;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private static final float MIN_SCALE = 0.75f;
    private ArrayList<Card> ladsPack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_screen_slide);

        ladsPack = InitCardDeckUtils.getLadsPack();
        mNumCards = ladsPack.size();

        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new CardSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new PlayGameActivity());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void transformPage(View page, float position) {
        int pageWIdth = page.getWidth();

        if(position < -1){
            page.setAlpha(0);
        }else if(position <= 0){
            page.setAlpha(1);
            page.setTranslationX(0);
            page.setScaleX(1);
            page.setScaleY(1);
        }else if(position <= 1){
            page.setAlpha(1 - position);
            page.setTranslationX(pageWIdth * -position);
            float scaleFactor = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position));
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        }else{
            page.setAlpha(0);
        }

    }

    private class CardSlidePagerAdapter extends FragmentStatePagerAdapter{
        public CardSlidePagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "in CardSlidePagerAdapter - getItem(), position is:" + position);
            return new CardFragment().newInstance(position);
        }

        @Override
        public int getCount() {
            return mNumCards;
        }

    }
}
