package com.scottlogic.deg.generator.cucumber.steps;

import com.scottlogic.deg.generator.cucumber.utils.CucumberTestState;
import cucumber.api.java.en.When;

public class StringValueStep {

    private CucumberTestState state;

    public StringValueStep(CucumberTestState state){
        this.state = state;
    }

    @When("{fieldVar} is {operator} {string}")
    public void whenFieldIsConstrainedByTextValue(String fieldName, String constraintName, String value) throws Exception {
        this.state.addConstraint(fieldName, constraintName, value);
    }

    @When("{fieldVar} is anything but {operator} {string}")
    public void whenFieldIsNotConstrainedByTextValue(String fieldName, String constraintName, String value) throws Exception {
        this.state.addNotConstraint(fieldName, constraintName, value);
    }
}
