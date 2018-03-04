package poojab26.bakingapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import poojab26.bakingapp.Utils.Constants;
import poojab26.bakingapp.model.Step;

public class StepItemFragment extends Fragment {

    // boolean to keep track when user goes out of screen so as to pause the video.
    private boolean mOnScreen;

    private ArrayList<Step> mSteps;
    private int mStepPositionID;
    Button btnNext, btnPrev;

    public StepItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_item, container, false);
        btnNext = rootView.findViewById(R.id.btnNext);
        btnPrev = rootView.findViewById(R.id.btnPrev);

        TextView tvDescription = rootView.findViewById(R.id.tvStepDescription);
        tvDescription.setText(mSteps.get(mStepPositionID).getDescription());
        //setButtonClicks();

         StepItemFragment fragment = new StepItemFragment();
        fragment.setSteps(mSteps);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepItemFragment fragment = new StepItemFragment();
                fragment.setSteps(mSteps);
                fragment.setPosition(mStepPositionID+1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment, null)
                        .commit();

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StepItemFragment fragment = new StepItemFragment();
                fragment.setSteps(mSteps);
                fragment.setPosition(mStepPositionID-1);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment, null)
                        .commit();

            }
        });



        return rootView;
    }

    private void setButtonClicks(){
        final StepItemFragment fragment = new StepItemFragment();
        fragment.setSteps(mSteps);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.setPosition(mStepPositionID+1);

            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.setPosition(mStepPositionID-1);

            }
        });

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment, null)
                .commit();

    }
    public void setSteps(ArrayList<Step> steps){
        mSteps = steps;
    }

    public void setPosition(int position){
        mStepPositionID = position;
    }
}
