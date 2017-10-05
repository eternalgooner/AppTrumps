package com.apptrumps.apptrumps.ui;

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
import android.widget.Toast;

import com.apptrumps.apptrumps.R;
import com.apptrumps.apptrumps.model.Card;
import com.apptrumps.apptrumps.utils.InitCardDeckUtils;

import java.util.ArrayList;

/**
 * Created by David on 30-Jul-17.
 */

public class ViewPacksActivity extends FragmentActivity implements ViewPager.PageTransformer, View.OnClickListener {
    private static final String TAG = ViewPacksActivity.class.getSimpleName();
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
        mPagerAdapter = new ViewPacksActivity.CardSlidePagerAdapter(getSupportFragmentManager());
        mPager.setPageTransformer(true, new ViewPacksActivity());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step. - go back to next card
            //mPager.setCurrentItem(mPager.getCurrentItem() - 1);

            //leave pack screen & back to menu
            super.onBackPressed();
        }
    }

    //method to transition to the next screen/card - as they are all stacked in a deck
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

//    @Override
//    public void statClicked(View v) {
//        Log.d(TAG, "statClicked() - look for match");
//        int statId = v.getId();
//        switch (statId){
//            case R.id.card_height:
//                statClicked("Height", ladsPack.get(mPager.getCurrentItem()).getHeight());
//                break;
//            case R.id.card_athleticism:
//                statClicked("Athleticism", ladsPack.get(mPager.getCurrentItem()).getAthleticism());
//                break;
//            case R.id.card_humour:
//                statClicked("Humour", ladsPack.get(mPager.getCurrentItem()).getHumour());
//                break;
//            case R.id.card_intelligence:
//                statClicked("Intelligence", ladsPack.get(mPager.getCurrentItem()).getIntelligence());
//                break;
//            case R.id.card_weapons:
//                statClicked("Weapons", ladsPack.get(mPager.getCurrentItem()).getWeapons());
//                break;
//            default:
//                Log.d(TAG, "no match found for button click - expecting stat item click");
//        }
//    }

    private void statClicked(String cat, int value) {
        String message = String.format("Cat pressed: %s, Value: %d", cat, value);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "in onClick() check which stat clicked");
        int statId = v.getId();
        switch (statId){
            case R.id.card_height:
                statClicked("Height", ladsPack.get(mPager.getCurrentItem()).getHeight());
                break;
            case R.id.card_athleticism:
                statClicked("Athleticism", ladsPack.get(mPager.getCurrentItem()).getAthleticism());
                break;
            case R.id.card_humour:
                statClicked("Humour", ladsPack.get(mPager.getCurrentItem()).getHumour());
                break;
            case R.id.card_intelligence:
                statClicked("Intelligence", ladsPack.get(mPager.getCurrentItem()).getIntelligence());
                break;
            case R.id.card_weapons:
                statClicked("Weapons", ladsPack.get(mPager.getCurrentItem()).getWeapons());
                break;
            default:
                Log.d(TAG, "no match found for button click - expecting stat item click");
        }
    }

    private class CardSlidePagerAdapter extends FragmentStatePagerAdapter {
        public CardSlidePagerAdapter(FragmentManager fragmentManager){
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "in CardSlidePagerAdapter - getItem(), position is:" + position);
            CardFragment cardFragment = new CardFragment().newInstance(position);
            cardFragment.setOnClickListener(ViewPacksActivity.this);
            return cardFragment;
        }

        @Override
        public int getCount() {
            return mNumCards;
        }

    }

}
