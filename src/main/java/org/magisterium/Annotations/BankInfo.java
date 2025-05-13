package org.magisterium.Annotations;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // podczas działania programu
@Target({ElementType.TYPE , ElementType.METHOD , ElementType.CONSTRUCTOR}) // miej dostep do

@Inherited // dziedzicz annotacje
public @interface BankInfo  {
    String description() default ""; // Opis banku
    String headquarters() default ""; // Siedziba
    double capital() default 0.0; // Kapitał
    String aboutUs() default ""; // Informacje "O nas"
    String name() default ""; // Nazwa banku
}