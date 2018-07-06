package com.badlogic.gdx.utils;

public final class Optional<T> {

    private static final Optional<?> EMPTY = new Optional(null);

    private final T value;

    private Optional() {
        this.value = null;
    }

    public static<T> Optional<T> empty() {
        @SuppressWarnings("unchecked")
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }


    private Optional(T value) {
        this.value = value;
    }

    public static <T> Optional<T> ofNonNull(T value) {
        if (value == null)
            throw new NullPointerException();
        return new Optional<T>(value);
    }

    public static <T> Optional<T> ofNullable(T value) {
        if (value != null) return new Optional<T>(value);
        else return empty();
    }

    public T get() {
        return value;
    }

    public boolean isPresent() {
        return value != null;
    }

    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }


    public Optional<T> filter(Predicate<? super T> predicate) {
        if (!isPresent())
            return this;
        else if (predicate.evaluate(value))
            return this;
        else
            return empty();
    }

    public<U> Optional<U> map(MapFunction<T, U> mapper) {
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.map(value));
        }
    }


    public<U> Optional<U> flatMap(MapFunction<T, Optional<U>> mapper) {
        if (!isPresent())
            return empty();
        else {
            return mapper.map(value);
        }
    }

    public T orElse(T other) {
        return value != null ? value : other;
    }

    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Optional)) {
            return false;
        }

        Optional<?> other = (Optional<?>) obj;
        return value == other.value;
    }

    @Override
    public String toString() {
        if (value == null){
            return "Optional.empty";
        } else {
            return "Optional{" +
                    "value=" + value +
                    '}';
        }
    }
}
