package com.gabri.turassic;

import android.graphics.Color;
import android.os.Bundle;

import com.gabri.turassic.fragment.CompleteStep;
import com.gabri.turassic.fragment.FirstStep;
import com.gabri.turassic.fragment.FourthStep;
import com.gabri.turassic.fragment.SecondStep;
import com.gabri.turassic.fragment.ThirdStep;
import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.TabStepper;

public class Register extends TabStepper {

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        boolean linear = getIntent().getBooleanExtra("linear", false);
        getSupportActionBar().hide();
        setErrorTimeout(1500);
        setLinear(linear);
        setTitle("Registration Processor");
        setAlternativeTab(true);
        setDisabledTouch();
        setPreviousVisible();
        setDarkPrimaryColor(Color.parseColor("#DD2C00"));


        addStep(createFragment(new FirstStep()));
        addStep(createFragment(new SecondStep()));
        addStep(createFragment(new ThirdStep()));
        addStep(createFragment(new FourthStep()));
        addStep(createFragment(new CompleteStep()));


        super.onCreate(savedInstanceState);
    }

    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

}
