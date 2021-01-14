package com.mulesoft;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        List<String> foo = new ArrayList<>();
        foo.add("1");
        Assertions.assertEquals("1", foo.stream().findFirst().get());
    }
}
