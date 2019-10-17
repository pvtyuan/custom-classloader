package app;

import lombok.extern.slf4j.Slf4j;
import service.Service;

import java.util.logging.Logger;

@Slf4j
public class Library implements Service {

    @Override
    public void hello() {
        log.info("log cl: {}", log.getClass().getClassLoader());
        log.info("classloader： {}", this.getClass().getClassLoader());
    }
}
