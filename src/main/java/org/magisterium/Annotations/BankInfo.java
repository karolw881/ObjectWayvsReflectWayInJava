package org.magisterium.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface BankInfo {
    String description() default ""; // Opis banku
    String headquarters() default ""; // Siedziba
    double capital() default 0.0; // Kapitał
    String aboutUs() default ""; // Informacje "O nas"
    String name() default ""; // Nazwa banku
}