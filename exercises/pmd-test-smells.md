# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Test smells discussed in classes implemented by these rules are :

* Eager test
	* JUnitTestContainsTooManyAsserts.md
* Assertion roulette
	* JUnitTestContainsTooManyAsserts.md
* The Free Ride
	* JUnitTestContainsTooManyAsserts.md

On Apache commons collections using the rule 
JUnitTestContainsTooManyAsserts.md one of the smell found was :

`commons-collections/src/test/java/org/apache/commons/collections4/queue/TransformedQueueTest.java:85:    JUnitTestContainsTooManyAsserts:    Unit tests should not contain more than 1 assert(s).`

```
@SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testTransformedQueue_decorateTransform() {
        final Queue originalQueue = new LinkedList();
        final Object[] elements = {"1", "3", "5", "7", "2", "4", "6"};
        Collections.addAll(originalQueue, elements);
        final Queue<?> queue = TransformedQueue.transformedQueue(originalQueue,
                TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(elements.length, queue.size());
        for (final Object el : elements) {
            assertTrue(queue.contains(Integer.valueOf((String) el)));
            assertFalse(queue.contains(el));
        }

        assertFalse(queue.remove(elements[0]));
        assertTrue(queue.remove(Integer.valueOf((String) elements[0])));
    }
```

A valid version coul be to remove some assert and separate other in new tests :

```
@SuppressWarnings({ "rawtypes", "unchecked" })
    @Test
    public void testTransformedQueue_decorateTransform() {
        final Queue originalQueue = new LinkedList();
        final Object[] elements = {"1", "3", "5", "7", "2", "4", "6"};
        Collections.addAll(originalQueue, elements);
        final Queue<?> queue = TransformedQueue.transformedQueue(originalQueue,
                TransformedCollectionTest.STRING_TO_INTEGER_TRANSFORMER);
        assertEquals(elements.length, queue.size());
    }
```