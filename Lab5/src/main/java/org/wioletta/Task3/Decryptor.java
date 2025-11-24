package org.wioletta.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

public class Decryptor extends FilterInputStream {
    private final int keyOffset;
    private static final Logger logger = LogManager.getLogger(Decryptor.class);
    private final ResourceBundle messages;

    public Decryptor(InputStream in, char keyChar, ResourceBundle messages) {
        super(in);
        this.keyOffset = keyChar;
        this.messages = messages;
    }

    @Override
    public int read() throws IOException {
        int b = super.read();
        if (b == -1) return -1;
        logger.debug("{}: {}", messages.getString("decryptingByte"), b);
        return b - keyOffset;
    }
}
