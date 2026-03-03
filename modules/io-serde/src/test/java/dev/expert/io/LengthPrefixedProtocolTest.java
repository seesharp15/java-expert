package dev.expert.io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

class LengthPrefixedProtocolTest {

    @Test
    void roundTripsSingleFrame() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        LengthPrefixedProtocol.writeFrame(baos, "ping");

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        String frame = LengthPrefixedProtocol.readFrame(bais);
        assertThat(frame).isEqualTo("ping");
    }

    @Test
    void handlesUtf8() throws Exception {
        String payload = "προσ";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        LengthPrefixedProtocol.writeFrame(baos, payload);
        String decoded = LengthPrefixedProtocol.readFrame(new ByteArrayInputStream(baos.toByteArray()));
        assertThat(decoded).isEqualTo(payload);
    }
}
