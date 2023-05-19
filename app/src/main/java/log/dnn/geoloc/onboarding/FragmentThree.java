package log.dnn.geoloc.onboarding;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import log.dnn.geoloc.OnBoardingActivity;
import log.dnn.geoloc.OnSwipeListener;
import log.dnn.geoloc.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class FragmentThree extends Fragment {

    private Button button_end_onboarding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_on_boarding_three, container, false);

        this.button_end_onboarding = view.findViewById(R.id.btnendonboarding);

        this.button_end_onboarding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((OnBoardingActivity)getActivity()).navHostController.navigate(R.id.action_fragmentThree_to_mainActivity);
                getActivity().finish();
            }
        });


        view.setOnTouchListener(new OnSwipeListener(getContext()){

            @Override
            public void onSwipeRight() {
                ((OnBoardingActivity)getActivity()).navHostController.navigate(R.id.action_fragmentThree_to_fragmentTwo);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}