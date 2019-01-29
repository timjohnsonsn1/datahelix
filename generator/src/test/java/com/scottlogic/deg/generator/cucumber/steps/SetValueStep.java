package com.scottlogic.deg.generator.cucumber.steps;

import com.scottlogic.deg.generator.cucumber.utils.CucumberTestState;
import cucumber.api.java.en.When;

import java.util.List;

public class SetValueStep {

    private CucumberTestState state;

    public SetValueStep(CucumberTestState state){
        this.state = state;
    }

    @When("{fieldVar} is in set:")
    public void whenFieldIsConstrainedBySetValue(String fieldName, List<Object> values) {
        this.state.addConstraint(fieldName, "in set", values);
    }

    @When("{fieldVar} is anything but in set:")
    public void whenFieldIsNotConstrainedBySetValue(String fieldName, List<Object> values) {
        this.state.addNotConstraint(fieldName, "in set", values);
    }
}
