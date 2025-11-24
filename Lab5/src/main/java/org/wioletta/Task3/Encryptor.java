package org.wioletta.Task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;

public class Encryptor extends FilterOutputStream {
    private final int keyOffset;
    private static final Logger logger = LogManager.getLogger(Encryptor.class);
    private final ResourceBundle messages;

    public Encryptor(OutputStream out, char keyChar, ResourceBundle messages) {
        super(out);
        this.keyOffset = keyChar;
        this.messages = messages;
    }

    @Override
    public void write(int b) throws IOException {
        logger.debug("{}: {}", messages.getString("encryptingByte"), b);
        super.write(b + keyOffset);
    }
}