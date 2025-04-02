# Bookstore JUnit 5 Testing

This project demonstrates how to test a **Bookstore Management System** using **JUnit 5**, **Mockito**, and **JaCoCo**. It covers how to perform **unit tests** (defining them with JUnit 5), how to create **mocks** (defining them with Mockito), and how to measure **code coverage** using JaCoCo.

## Summary

### JUnit 5 Features Used:
- `@Test`: Marks test methods.
- `@BeforeEach`: Runs before each test.
- `@DisplayName`: Provides a readable test name.
- `assertThrows()`: Tests exceptions.

### Mockito:
- `mock()`: Mocks `BookRepository`.
- `when().thenReturn()`: Defines return values.
- `verify()`: Ensures methods were called.

### JaCoCo:
- Measures code coverage for unit tests.
- Generates reports in HTML format.
- Helps ensure sufficient test coverage.

This setup is a clean, modular, and maintainable way to test book management using **JUnit 5, Mockito, and JaCoCo**. 🚀

Play with the example by trying out the following two changes:
1. In the code of class `BookServiceTest.java` comment out the block that does not use Mockito and comment the block that does use Mockito. That way you will be able to appreciate the difference between using and not using mock objects.
2. Alter `pom.xml` by changing `<minimum>0.80</minimum>` to `<minimum>0.90</minimum>`. When you now run `mvn verify`, you will be alerted that the code coverage ratio specified, i.e. 90% is not met. 

## Running the Tests
To execute all tests, navigate to the project root and run:

```sh
mvn test
```

## Generating Code Coverage Report with JaCoCo
JaCoCo is used to measure test coverage. To generate a test coverage report, follow these steps:

1. **Run Tests with JaCoCo enabled:**

   ```sh
   mvn clean test
   ```

2. **Generate Coverage Report:**

   ```sh
   mvn jacoco:report
   ```

3. **View Report:**
   Open the generated HTML report located at:

   ```
   target/site/jacoco/index.html
   ```

4. **Check enough code line coverage is met:**
   Execute the following code in command line:

   ```sh
   mvn verify
   ```

## Mockito Methods
| Method | Description |
|--------|------------|
| `when(repository.findById("1")).thenReturn(Optional.of(book))` | Mocks behavior: When `findById("1")` is called, return `book`. |
| `verify(repository, times(1)).save(book)` | Ensures `save(book)` was called exactly once. |
| `assertThrows(RuntimeException.class, () -> { service.getBookById("999"); })` | Tests for exceptions when a book is not found. |

## Learn More
For a **detailed guide on JUnit 5** and its features, check out the [JUnit 5 Guide](./JUnit5-101.md).



