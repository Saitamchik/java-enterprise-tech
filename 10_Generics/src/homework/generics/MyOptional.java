package homework.generics;

public class MyOptional<T> {
    private static final MyOptional<?> EMPTY = new MyOptional<>(null);
    private final T value;

    private MyOptional(T value) {
        this.value = value;
    }

    public static <T> MyOptional of(T value) {
        if (value == null) {
            throw new InvalidParameterException();
        }
        return new MyOptional<>(value);
    }

    public static <T> MyOptional<T> ofNullable(T value) {
        if (value == null) {
            return (MyOptional<T>) EMPTY;
        }
        return new MyOptional<>(value);
    }

    public <T> T get() {
        if (value == null) {
            throw new InvalidParameterException();
        }
        return (T) value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public T orElse(T other) {
        if (value == null) {
            return other;
        }
        return value;
    }
}
