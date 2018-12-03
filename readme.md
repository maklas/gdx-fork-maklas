# Increased functionality for Libgdx in favor of java8.

## Added classes:
`com/badlogic/gdx/utils/Consumer.java`

`com/badlogic/gdx/utils/DoubleMapFunction.java`

`com/badlogic/gdx/utils/ImmutableArray.java`

`com/badlogic/gdx/utils/LongMapFunction.java`

`com/badlogic/gdx/utils/MapFunction.java`

`com/badlogic/gdx/utils/Optional.java`

`com/badlogic/gdx/utils/Supplier.java`


## AddedMethods:
`OrthographicCamera.setPosition(x, y)`

`Vector2.angle(x, y)`

`Button.addChangeListener(Consumer<Boolean> listener)`

`SelectBox.addChangeListener(Consumer<T> listener)`

`Slider.addChangeLsitener(Consumer<Float> listener)`

`Actor.addChangeListener(Runnable onChangeAction)`

`Stage.<init>(Batch batch)`

`Array.<init>(List list)`

`Array.parallelExecute(Executor service, MapFunction<T, Runnable> consumer)`

`Array.parallelExecuteAndWait(int maxThreads, long waitMillis, MapFunction<T, Runnable> consumer)`
 
`Array.parallelInvokeAndWait(int maxThreads, long waitMillis, MapFunction<T, Callable<V>> mapFunc)`

`Array.parallelSubmit(ExecutorService service, MapFunction<T, Callable<V>> mapFunc)`

`Array.last()`

`Array.sortAndRet()`

`Array.foreach(Consumer<T> c)`

`Array.callAndClear(Consumer<T> c)`

`Array.filter(Predicate<T> p)`

`Array.map(MapFunction<T, R> mapFunction)`

`Array.mapReduce(MapFunction<T, R> mapFunction)`

`Array.cpy()`

`Array.count(Predicate<T> p)`

`Array.summarize(LongMapFunction<T> m)`

`Array.summarize(DoubleMapFunction<T> m)`

`Array.atLeastOneFits(Predicate<T> p)`

`Array.removeDuplicates(boolean identity)`

`Array.toList()`


## Note:
None of implementations is changed. Only added new functions and classes for an ease of use,
so that something like this could be possible:


Item item = getItemIds()  
    .map(id -> inventory.getItem(id))  
    .filter(ItemUtils::canBeBought)  
    .sortAndRet(sortType.getComparator())  
    .random();  
