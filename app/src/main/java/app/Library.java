package app;

import lombok.extern.slf4j.Slf4j;

import java.util.logging.Logger;

@Slf4j
public class Library {
    public boolean someLibraryMethod() {
        log.info("classloader： {}", this.getClass().getClassLoader());
        return true;
    }
}
