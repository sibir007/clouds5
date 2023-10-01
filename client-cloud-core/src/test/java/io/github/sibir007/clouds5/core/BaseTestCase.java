package io.github.sibir007.clouds5.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;

public class BaseTestCase {
    private AutoCloseable closeable;
    @BeforeEach void openMocks() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach void releaseMocks() throws Exception {
        closeable.close();
    }
}
