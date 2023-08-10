package com.springmvc.EmployeeMVC.Vallidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy=UniqueEmailConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
	
	public String message() default " email already registered";
	
	Class<?> [] groups() default{};
    Class<? extends Payload>[] payload() default{};

}
