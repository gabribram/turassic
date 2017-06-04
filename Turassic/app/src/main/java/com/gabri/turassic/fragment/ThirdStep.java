package com.gabri.turassic.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gabri.turassic.R;
import com.github.fcannizzaro.materialstepper.AbstractStep;

/**
 * @author Francesco Cannizzaro (fcannizzaro).
 */
public class ThirdStep extends AbstractStep {

    private int i = 1;
    private Button button;
    private final static String CLICK = "click";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.step_third, container, false);
        button = (Button) v.findViewById(R.id.save_button);

        if (savedInstanceState != null)
            i = savedInstanceState.getInt(CLICK, 0);

//        button.setText(Html.fromHtml("Tap <b>" + i + "</b>"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                mStepper.getExtras().putInt(CLICK, i);
            }
        });

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        state.putInt(CLICK, i);
    }

    @Override
    public String name() {

        return "DOCUMENTS";

    }

    @Override
    public boolean isOptional() {
        return true;
    }


    @Override
    public void onStepVisible() {
    }

    @Override
    public void onNext() {
        System.out.println("onNext");
    }

    @Override
    public void onPrevious() {
        System.out.println("onPrevious");
    }

//    @Override
//    public String optional() {
//        return "You can skip";
//    }

    @Override
    public boolean nextIf() {
        return i > 1;
    }

    @Override
    public String error() {
        return "<b>You must click!</b> <small>this is the condition!</small>";
    }
}
