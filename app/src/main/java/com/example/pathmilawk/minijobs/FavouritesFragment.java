package com.example.pathmilawk.minijobs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
//import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PathmilaWK on 3/11/2016.
 */
public class FavouritesFragment extends Fragment{
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.favourites_layout, container, false);
        return myView;
    }
}
