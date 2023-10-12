package io.github.sibir007.clouds5.core.services.transactionsentity.db.spi;

import io.github.sibir007.clouds5.core.transactions.Transaction.TransactionType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionTypeAnnotation {
    TransactionType value() default TransactionType.DEFAULT;
}
