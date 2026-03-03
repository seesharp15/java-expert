package dev.expert.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public final class LengthPrefixedProtocol {
    private LengthPrefixedProtocol() {}

    public static void writeFrame(OutputStream out, String payload) throws IOException {
        throw new UnsupportedOperationException("TODO: implement length-prefixed writer");
    }

    public static String readFrame(InputStream in) throws IOException {
        throw new UnsupportedOperationException("TODO: implement length-prefixed reader");
    }
}

























































/*
ANSWER KEY:

 * Problem: define a tiny length-prefixed framing protocol over byte streams.
 * Approach: DataOutputStream/DataInputStream with 4-byte big-endian length + UTF-8 payload.
 * Why: avoids partial read confusion; good practice for simple TCP protocols.

public static void writeFrame(OutputStream out, String payload) throws IOException {
    byte[] bytes = payload.getBytes(StandardCharsets.UTF_8);
    DataOutputStream dos = new DataOutputStream(out);
    dos.writeInt(bytes.length);
    dos.write(bytes);
    dos.flush();
}

public static String readFrame(InputStream in) throws IOException {
    DataInputStream dis = new DataInputStream(in);
    int len = dis.readInt();
    byte[] bytes = dis.readNBytes(len);
    if (bytes.length != len) throw new IOException("unexpected EOF");
    return new String(bytes, StandardCharsets.UTF_8);
}
*/
