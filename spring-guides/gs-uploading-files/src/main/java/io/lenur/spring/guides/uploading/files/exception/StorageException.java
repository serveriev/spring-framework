package io.lenur.spring.guides.uploading.files.exception;

import java.io.IOException;

public class StorageException extends RuntimeException {
    public StorageException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public StorageException(String msg) {
        super(msg);
    }
}
