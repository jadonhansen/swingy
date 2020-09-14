package com.swingy;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.*;

public class Validate {

    private static Validator validator;

    public Validate() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public void validateModel(Model model) {
        try {
            Set<ConstraintViolation<Model>> constraintViolations = validator.validate( model );
            if (constraintViolations.size() != 0) {
                System.out.println("problemssss");
            }
//            assertEquals( 0, constraintViolations.size());
        } catch (NoSuchElementException e) {
            System.out.println("No such element found on class -> Validate.java -> validateModel(): " + e);
        }
    }
}
