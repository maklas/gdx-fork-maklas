package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.Array.ArrayIterable;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Wrapper class to treat {@link Array} objects as if they were immutable. However, note that the values could be modified if they
 * are mutable.
 * @author David Saltares
 */
public class ImmutableArray<T> implements Iterable<T> {
    private final Array<T> array;
    private ArrayIterable<T> iterable;

    public ImmutableArray(Array<T> array) {
        this.array = array;
    }

    public int size () {
        return array.size;
    }

    public T get (int index) {
        return array.get(index);
    }

    public boolean contains (T value, boolean identity) {
        return array.contains(value, identity);
    }

    public int indexOf (T value, boolean identity) {
        return array.indexOf(value, identity);
    }

    public int lastIndexOf (T value, boolean identity) {
        return array.lastIndexOf(value, identity);
    }

    public T peek () {
        return array.peek();
    }

    public T first () {
        return array.first();
    }

    public T random () {
        return array.random();
    }

    public T[] toArray () {
        return array.toArray();
    }

    public <V> V[] toArray (Class<V> type) {
        return array.toArray(type);
    }

    public int hashCode() {
        return array.hashCode();
    }

    public boolean equals (Object object) {
        return array.equals(object);
    }

    public String toString () {
        return array.toString();
    }

    public String toString (String separator) {
        return array.toString(separator);
    }

    /**
     * Items of underlying array. Use with caution.
     */
    public T[] items(){
        return array.items;
    }

    @Override
    public Iterator<T> iterator () {
        if (iterable == null) {
            iterable = new ArrayIterable<T>(array, false);
        }

        return iterable.iterator();
    }

    /**
     * Creates new Array with the same content
     */
    public Array<T> cpyArray() {
        return new Array<T>(array);
    }


    /**
     * Calls service.execute on each objects of this array. Returns immidiately
     */
    public void parallelExecute(Executor service, Function<T, Runnable> consumer){
        array.parallelExecute(service, consumer);
    }

    /**
     * <li>Creates new Executor service at size of min(Array.size, maxThreads)</li>
     * <li>and submits all mapped Runnables to it. </li>
     * <li>Waits for min(end of all executions, specified amount of time)</li>
     * <li>Returns true if successfully executed on each of them by current Time</li>
     * <li>Calls shutdownNow if some tasks are still running after specified amount of time</li>
     */
    public boolean parallelExecuteAndWait(int maxThreads, long waitMillis, Function<T, Runnable> consumer) throws InterruptedException {
        return array.parallelExecuteAndWait(maxThreads, waitMillis, consumer);
    }

    /**
     * <li>Creates new Executor service at size of min(Array.size, maxThreads)</li>
     * <li>and submits all mapped callables to it. </li>
     * <li>Waits for min(end of all executions, specified amount of time)</li>
     * <li>Returns values which successfully mapped in parrarel</li>
     */
    public <V> Array<V> parallelInvokeAndWait(int maxThreads, long waitMillis, Function<T, Callable<V>> mapFunc) throws InterruptedException {
        return array.parallelInvokeAndWait(maxThreads, waitMillis, mapFunc);
    }

    /**
     * Submits mapped Callables for execution.
     */
    public <V> Array<Future<V>> parallelSubmit(ExecutorService service, Function<T, Callable<V>> mapFunc) {
        return array.parallelSubmit(service, mapFunc);
    }

    public T last(){
        return array.last();
    }

    /**
     * ForEach method implemetation for low Android SDK usage
     */
    public ImmutableArray<T> foreach(Consumer<T> c){
        array.forEach(c);
        return this;
    }

    /**
     * Collects mapped values into new Array
     */
    public <R> Array<R> map(Function<T, R> Function){
        return array.map(Function);
    }

    /**
     * Collects mapped values into new Array.
     * If Function returns <b>null</b>, it's not added in resulted array
     */
    public <R> Array<R> mapReduce(Function<T, R> Function){
        return array.mapReduce(Function);
    }


    /**
     * Summarizes arbitrary long value from items.
     */
    public long summarize(LongMapFunction<T> m){
        return array.summarize(m);
    }

    /**
     * Summarizes arbitrary double value from items.
     */
    public double summarize(DoubleMapFunction<T> m){
        return array.summarize(m);
    }

    /**
     * Checks items on predicate. Returns as soon as predicate returns true on any item
     */
    public boolean atLeastOneFits(Predicate<T> p){
        return array.atLeastOneFits(p);
    }

    public List<T> toList(){
        return array.toList();
    }
}
