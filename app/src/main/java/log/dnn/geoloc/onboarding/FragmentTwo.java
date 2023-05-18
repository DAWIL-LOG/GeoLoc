package log.dnn.geoloc.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import log.dnn.geoloc.OnBoardingActivity;
import log.dnn.geoloc.OnSwipeListener;
import log.dnn.geoloc.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentTwo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_on_boarding_two, container, false);
        view.setOnTouchListener(new OnSwipeListener(getContext()){
            @Override
            public void onSwipeLeft() {
                ((OnBoardingActivity)getActivity()).navHostController.navigate(R.id.action_fragmentTwo_to_fragmentThree);
            }

            @Override
            public void onSwipeRight() {
                ((OnBoardingActivity)getActivity()).navHostController.navigate(R.id.action_fragmentTwo_to_fragmentOne);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}