package by.training.multithreading.util;

public class VanIdGenerator {
    private static long id = 0;

    private VanIdGenerator() {
    }

    public static long nextId() {
        return ++id;
    }
}
