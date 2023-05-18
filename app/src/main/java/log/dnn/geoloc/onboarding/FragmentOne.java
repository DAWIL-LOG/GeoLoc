package log.dnn.geoloc.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavGraph;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import log.dnn.geoloc.OnBoardingActivity;
import log.dnn.geoloc.OnSwipeListener;
import log.dnn.geoloc.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentOne extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_on_boarding_one, container, false);
        view.setOnTouchListener(new OnSwipeListener(getContext()){
            @Override
            public void onSwipeLeft() {
                ((OnBoardingActivity)getActivity()).navHostController.navigate(R.id.action_fragmentOne_to_fragmentTwo);
            }
        });
        //Initialize gesture

        // Inflate the layout for this fragment
        return view;
    }

}