package com.example.pathmilawk.minijobs;

//import android.app.Fragment;
//import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PathmilaWK on 3/11/2016.
 */
public class HomeFragment extends Fragment {
    View myView;
    FragmentManager fragmentManager=getFragmentManager();
    JobListFragment jobListFragment=new JobListFragment();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getChildFragmentManager().beginTransaction().add(R.id.job_list_frame, jobListFragment, "joblist").commit();
        myView=inflater.inflate(R.layout.home_layout, container, false);
        return myView;
    }
}
