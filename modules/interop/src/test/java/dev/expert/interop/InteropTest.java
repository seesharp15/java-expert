package dev.expert.interop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InteropTest {

    @Test
    void pureJavaSumWorks() {
        var adder = new NativeAdder();
        assertThat(adder.pureJavaSum(new int[]{1,2,3})).isEqualTo(6);
    }

    @Disabled("Requires JNI library build")
    @Test
    void nativeAdderLoadsAndComputes() {
        var adder = new NativeAdder();
        assertThat(adder.sum(new int[]{1,2,3})).isEqualTo(6);
    }
}
