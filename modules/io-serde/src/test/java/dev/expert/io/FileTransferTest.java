package dev.expert.io;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class FileTransferTest {

    @Test
    void transfersFileUsingZeroCopy() throws Exception {
        Path src = Files.createTempFile("transfer-src", ".txt");
        Path dest = Files.createTempFile("transfer-dest", ".txt");
        Files.writeString(src, "hello world");

        long bytes = FileTransfer.transfer(src, dest);

        assertThat(bytes).isEqualTo(Files.size(src));
        assertThat(Files.readString(dest)).isEqualTo("hello world");
    }
}
