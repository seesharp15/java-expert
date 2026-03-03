package dev.expert.interop;

public class NativeAdder {
    static {
        // TODO: load JNI library (libnativeadder.dylib/.so)
    }

    public native int sum(int[] values);

    public int pureJavaSum(int[] values) {
        int acc = 0;
        for (int v : values) acc += v;
        return acc;
    }
}

























































/*
ANSWER KEY:

static {
    try {
        System.loadLibrary("nativeadder");
    } catch (UnsatisfiedLinkError e) {
        // ignore in test environments
    }
}
*/
