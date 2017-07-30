package com.apptrumps.apptrumps;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by David on 30-Jul-17.
 */

public class CardFragment extends Fragment {
    private static final String TAG = CardFragment.class.getSimpleName();
    private static ArrayList<Card> ladsPack = InitCardDeckUtils.getLadsPack();
    private TextView mName;
    private TextView mHeight;
    private TextView mWeapons;
    private TextView mHumour;
    private TextView mIntelligence;
    private TextView mAthleticism;

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

        mName = (TextView) rootView.findViewById(R.id.card_name);
        mHeight = (TextView) rootView.findViewById(R.id.card_height);
        mWeapons = (TextView) rootView.findViewById(R.id.card_weapons);
        mHumour = (TextView) rootView.findViewById(R.id.card_humour);
        mIntelligence = (TextView) rootView.findViewById(R.id.card_intelligence);
        mAthleticism = (TextView) rootView.findViewById(R.id.card_athleticism);

        mName.setText(ladsPack.get(getShownIndex()).getName());
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

        return rootView;
    }
}
