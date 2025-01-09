package org.magisterium.Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited // Adnotacja będzie dziedziczona
public @interface BankInfo {
    String description() default ""; // Opis banku
    String headquarters() default ""; // Siedziba
    double capital() default 0.0; // Kapitał
    String aboutUs() default ""; // Informacje "O nas"
    String name() default ""; // Nazwa banku
}