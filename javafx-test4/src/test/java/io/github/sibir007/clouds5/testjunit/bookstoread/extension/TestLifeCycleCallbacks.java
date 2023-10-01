package io.github.sibir007.clouds5.testjunit.bookstoread.extension;

import org.junit.jupiter.api.extension.*;

import java.util.Collections;

import static org.junit.jupiter.api.extension.ExtensionContext.Namespace.GLOBAL;

//1.BeforeAllCallback
//2.BeforeAll
//3.BeforeEachCallback
//4.BeforeEach
//5.BeforeTestExecution
//6.Test
//7.AfterTestExecution
//8.AfterEach
//9.AfterEachCallback
//10.AfterAll
//11.AfterAllCallback
public class TestLifeCycleCallbacks implements
        BeforeAllCallback,
        AfterAllCallback,
        BeforeTestExecutionCallback,
        AfterTestExecutionCallback {
    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        long startTime = extensionContext.getStore(GLOBAL).get("TEST_CLASS", long.class);
        long timeTook = System.currentTimeMillis() - startTime;
        extensionContext.publishReportEntry(Collections.singletonMap(
                "Summary",
                String.format(
                        "%s took %d ms",
                        extensionContext.getDisplayName(),
                        timeTook)));
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {

        long startTime = extensionContext.getStore(GLOBAL).get("TEST", long.class);
        long timeTook = System.currentTimeMillis() - startTime;
        extensionContext.publishReportEntry(Collections.singletonMap(
                "Summary",
                String.format(
                        "%s took %d ms",
                        extensionContext.getDisplayName(),
                        timeTook)
        ));
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        extensionContext.getStore(GLOBAL).put("TEST_CLASS", System.currentTimeMillis());
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        extensionContext.getStore(GLOBAL).put("TEST", System.currentTimeMillis());
    }
}
