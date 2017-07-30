package com.apptrumps.apptrumps;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 30-Jul-17.
 */

public class CardFragment extends Fragment {
    private static final String TAG = CardFragment.class.getSimpleName();
    private static ArrayList<Card> ladsPack = InitCardDeckUtils.getLadsPack();
    private TextView mBtnStats;
    private TextView mBtnInfo;
    private ImageView mImage;
    private TextView mHeight;
    private TextView mWeapons;
    private TextView mHumour;
    private TextView mIntelligence;
    private TextView mAthleticism;
    private TextView mInfo;
    private CardView mCv1;
    private CardView mCv2;
    private CardView mCv3;
    private CardView mCv4;
    private CardView mCv5;
    private CardView mCvInfo;
    private boolean mIsStatsSelected = true;

    public CardFragment newInstance(int position){
        Log.d(TAG, "in CardFragment newInstance(), position is:" + position);
        CardFragment cardFragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("index", position);
        cardFragment.setArguments(args);
        return cardFragment;
    }

    public int getShownIndex(){
        return getArguments().getInt("index", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.card_layout_fragment, container, false);

        mBtnStats = (TextView) rootView.findViewById(R.id.card_stats);
        addClickListener(mBtnStats);
        mBtnInfo = (TextView) rootView.findViewById(R.id.card_info);
        addClickListener(mBtnInfo);
        mImage = (ImageView) rootView.findViewById(R.id.card_image);
        mHeight = (TextView) rootView.findViewById(R.id.card_height);
        mWeapons = (TextView) rootView.findViewById(R.id.card_weapons);
        mHumour = (TextView) rootView.findViewById(R.id.card_humour);
        mIntelligence = (TextView) rootView.findViewById(R.id.card_intelligence);
        mAthleticism = (TextView) rootView.findViewById(R.id.card_athleticism);
        mInfo = (TextView) rootView.findViewById(R.id.card_textview_info);

        mCv1 = (CardView) rootView.findViewById(R.id.cv_1);
        mCv2 = (CardView) rootView.findViewById(R.id.cv_2);
        mCv3 = (CardView) rootView.findViewById(R.id.cv_3);
        mCv4 = (CardView) rootView.findViewById(R.id.cv_4);
        mCv5 = (CardView) rootView.findViewById(R.id.cv_5);
        mCvInfo = (CardView) rootView.findViewById(R.id.cv_info);

        mImage.setBackground(getImage());
        Log.d(TAG, "setting name as:" + ladsPack.get(getShownIndex()).getName());
        mHeight.setText(ladsPack.get(getShownIndex()).getHeight()+"");
        Log.d(TAG, "setting height as:" + ladsPack.get(getShownIndex()).getHeight());
        mWeapons.setText(ladsPack.get(getShownIndex()).getWeapons()+"");
        Log.d(TAG, "setting weapons as:" + ladsPack.get(getShownIndex()).getWeapons());
        mHumour.setText(ladsPack.get(getShownIndex()).getHumour()+"");
        Log.d(TAG, "setting humour as:" + ladsPack.get(getShownIndex()).getHumour());
        mIntelligence.setText(ladsPack.get(getShownIndex()).getIntelligence()+"");
        Log.d(TAG, "setting intelligence as:" + ladsPack.get(getShownIndex()).getIntelligence());
        mAthleticism.setText(ladsPack.get(getShownIndex()).getAthleticism()+"");
        Log.d(TAG, "setting athleticism as:" + ladsPack.get(getShownIndex()).getAthleticism());
        mInfo.setText(ladsPack.get(getShownIndex()).getInfo());

        return rootView;
    }

    private Drawable getImage() {
        switch (getShownIndex()){
            case 0:
                return getResources().getDrawable(R.drawable.bear);
            case 1:
                return getResources().getDrawable(R.drawable.cat);
            case 2:
                return getResources().getDrawable(R.drawable.dog);
            case 3:
                return getResources().getDrawable(R.drawable.giraffe);
            case 4:
                return getResources().getDrawable(R.drawable.lion);
            case 5:
                return getResources().getDrawable(R.drawable.rabbit);
        }
        return null;
    }

    private void addClickListener(final TextView button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIsStatsSelected){
                    if(v.getId() == R.id.card_stats){
                        //do nothing as button already selected
                    }else{
                        //change state of both buttons reversing their style
                        mIsStatsSelected = false;
                        setBtnStatsToUnselectedAndBtnInfoToSelected();
                    }
                }else {
                    if(v.getId() == R.id.card_stats){
                        mIsStatsSelected = true;
                        setBtnStatsToSelectedAndBtnInfoToUnselected();
                    }
                }
            }
        });
    }

    private void setBtnStatsToSelectedAndBtnInfoToUnselected() {
        mBtnStats.setTextAppearance(R.style.buttonSelected);
        mBtnStats.setBackgroundResource(R.drawable.gradient_selected);

        mBtnInfo.setTextAppearance(R.style.buttonUnselected);
        mBtnInfo.setBackgroundResource(R.drawable.gradient_unselected);

        showStats();
    }

    private void showStats() {
        mCvInfo.setVisibility(View.GONE);
        mCv1.setVisibility(View.VISIBLE);
        mCv2.setVisibility(View.VISIBLE);
        mCv3.setVisibility(View.VISIBLE);
        mCv4.setVisibility(View.VISIBLE);
        mCv5.setVisibility(View.VISIBLE);
    }

    private void setBtnStatsToUnselectedAndBtnInfoToSelected() {
        mBtnStats.setTextAppearance(R.style.buttonUnselected);
        mBtnStats.setBackgroundResource(R.drawable.gradient_unselected);

        mBtnInfo.setTextAppearance(R.style.buttonSelected);
        mBtnInfo.setBackgroundResource(R.drawable.gradient_selected);

        showInfo();
    }

    private void showInfo() {
        mCvInfo.setVisibility(View.VISIBLE);
        mCv1.setVisibility(View.GONE);
        mCv2.setVisibility(View.GONE);
        mCv3.setVisibility(View.GONE);
        mCv4.setVisibility(View.GONE);
        mCv5.setVisibility(View.GONE);
    }
}
