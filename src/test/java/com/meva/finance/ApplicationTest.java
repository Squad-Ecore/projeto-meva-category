package com.meva.finance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

    @Test
    @DisplayName("test main")
    void main() {
        Application.main(new String[]{});
    }
}