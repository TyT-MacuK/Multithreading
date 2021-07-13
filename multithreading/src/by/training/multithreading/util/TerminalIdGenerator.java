package by.training.multithreading.util;

public class TerminalIdGenerator {
    private static long id = 0;

    private TerminalIdGenerator() {
    }

    public static long nextId() {
        return ++id;
    }
}
