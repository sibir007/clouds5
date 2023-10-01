package io.github.sibir007.clouds5.testjunit.bookstoread.extension.template;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class TypedParameterResolver<T> implements ParameterResolver {
    private T data;
    public TypedParameterResolver(T data){
        this.data = data;

    }
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class parameterClass = parameterContext.getParameter().getType();
        return parameterClass.isInstance(data);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return data;
    }
}
