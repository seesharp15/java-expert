package dev.expert.io;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FileTransfer {
    private FileTransfer() {}

    public static long transfer(Path source, Path target) throws IOException {
        throw new UnsupportedOperationException("TODO: implement zero-copy transfer using FileChannel.transferTo");
    }
}

























































/*
ANSWER KEY:

 * Problem: copy a file efficiently.
 * Approach: zero-copy transfer between FileChannels in a loop until all bytes sent.
 * Why: avoids buffering in user space; illustrates NIO.2.

public static long transfer(Path source, Path target) throws IOException {
    try (FileChannel in = FileChannel.open(source, StandardOpenOption.READ);
         FileChannel out = FileChannel.open(target, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
        long size = in.size();
        long pos = 0;
        while (pos < size) {
            long sent = in.transferTo(pos, size - pos, out);
            pos += sent;
        }
        return size;
    }
}
*/
