# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### Question 1

The assertion fails because floating-point arithmetic can sometimes introduce rounding errors due to the way computers store numbers in memory. In this case, the expression 3 * .4 should evaluate to 1.2, but due to the rounding error, it may result in a value slightly different from 1.2, for example, 1.2000000000000002.
To check floating-point equality, one should provide a delta to the assertion  to specify an acceptable range within which two values can be considered equal.
For the given example with a delta of  0.0001:

```java
assertEquals(3 * 0.4, 1.2f, 0.0001);
```

### Question 2

`assertEquals` compares the values of two objects, while `assertSame` compares their references.

This code snippet shows the difference between the two methods:

```java
String str1 = "hello";
String str2 = "hello";
String str3 = new String("hello");

assertEquals(str1, str2); // passes
assertEquals(str1, str3); // passes
assertSame(str1, str2);   // passes
assertSame(str1, str3);   // fails
```

### Question 3

One possible use case for the fail method is to implement a test that should always fail, regardless of the system under test's state. Such tests can be used as a way to verify that the testing framework is working correctly or to test error handling or logging mechanisms in the test environment.

For example, suppose we have a testing framework for a web application that includes a method to send emails to users. We can write a test case that always fails by using the fail method as follows:

```java
@Test
public void testSendEmail() {
   // send email to user
   boolean emailSent = sendEmailToUser("testuser@example.com");

   // the following line always fails, regardless of whether the email was sent successfully or not
   fail("Email sending test failed");
}
```

### Question 4

The @Test annotation in JUnit 4 allowed us to mark a test method as expecting a particular exception, which could be caught and verified using a try-catch block. However, this approach had some limitations, such as being verbose and potentially introducing boilerplate code into test methods.

`assertThrows` provides a more expressive, concise, and type-safe way of checking for expected exceptions in JUnit 5. It simplifies the process of writing tests and can make test code easier to understand and maintain.