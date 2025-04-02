# JUnit 5: A Beginner's Guide

## Introduction
JUnit 5 is a modern testing framework for Java, offering more flexibility and improved features over JUnit 4. It consists of three main components:

- **JUnit Platform**: Foundation for launching testing frameworks on the JVM.
- **JUnit Jupiter**: Provides new programming models and test APIs.
- **JUnit Vintage**: Allows running JUnit 4 and 3 tests on JUnit 5.

**CLARIFICATION**. Distinction between Plugins and Dependencies.

In Maven, both **plugins** and **dependencies** are core concepts, but they serve very different purposes in the build lifecycle. Plugins provide functionality during the build process. They define actions Maven can perform—like compiling code, packaging it into a JAR, running tests, generating reports, etc. Examples:
- `maven-compiler-plugin` – compiles Java code.
- `maven-surefire-plugin` – runs unit tests.
- `maven-jar-plugin` – packages code into a JAR.

Plugins are often used inside the `<build>` section of your `pom.xml`.

Dependencies are libraries or frameworks that your project depends on to run or compile. They become part of your project's classpath. Examples:
- `junit:junit` – for testing.
- `org.springframework:spring-core` – Spring framework core.
- `com.google.guava:guava` – Google's core libraries.

Dependencies are used inside the `<dependencies>` section of your `pom.xml`.

## 1. Setting Up JUnit 5 with Maven
Add the following dependencies in your `pom.xml`:

```xml
<dependencies>
    <!-- JUnit 5 API -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.12.1</version>
        <scope>test</scope>
    </dependency>
    
    <!-- JUnit 5 Engine -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.12.1</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

## 2. Writing a Simple JUnit 5 Test
Create a test class with assertions:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void additionShouldBeCorrect() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 should equal 5");
    }
}
```

## 3. JUnit 5 Annotations

| Annotation | Description |
|------------|------------|
| `@Test` | Marks a method as a test case. |
| `@BeforeEach` | Runs before each test method. |
| `@AfterEach` | Runs after each test method. |
| `@BeforeAll` | Runs once before all test methods. |
| `@AfterAll` | Runs once after all test methods. |
| `@DisplayName` | Provides a custom test name. |
| `@Disabled` | Skips the test. |

Example:

```java
import org.junit.jupiter.api.*;

class LifecycleTest {
    @BeforeAll
    static void setup() { System.out.println("Runs once before all tests"); }

    @BeforeEach
    void init() { System.out.println("Runs before each test"); }

    @Test
    @DisplayName("Test 1")
    void testOne() { System.out.println("Executing Test 1"); }

    @AfterEach
    void tearDown() { System.out.println("Runs after each test"); }

    @AfterAll
    static void cleanup() { System.out.println("Runs once after all tests"); }
}
```

## 4. Assertions in JUnit 5
JUnit 5 provides various assertion methods:

```java
import static org.junit.jupiter.api.Assertions.*;

@Test
void testAssertions() {
    assertEquals(5, 2 + 3, "Addition assertion");
    assertTrue(3 > 2, "True assertion");
    assertFalse(2 > 3, "False assertion");
    assertThrows(ArithmeticException.class, () -> { int result = 1 / 0; });
}
```

## 5. Parameterized Tests
JUnit 5 allows running tests with different inputs using `@ParameterizedTest`.

```java
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class ParameterizedExampleTest {
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8})
    void testEvenNumbers(int number) {
        assertTrue(number % 2 == 0);
    }
}
```

## 6. Using Mockito with JUnit 5
Mockito is used for mocking dependencies in unit tests.

Add the following dependency:

```xml
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.16.1</version>
    <scope>test</scope>
</dependency>
```

Example of a test with Mockito:

```java
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    @Mock
    private BookRepository repository;
    
    @InjectMocks
    private BookService service;
    
    @Test
    void testFindBookById() {
        Book book = new Book("1", "JUnit 5", "John Doe");
        when(repository.findById("1")).thenReturn(Optional.of(book));
        
        Book result = service.getBookById("1");
        assertEquals("JUnit 5", result.getTitle());
    }
}
```

## 7. Running JUnit 5 Tests with Maven
To execute all tests, use:

```sh
mvn test
```

## 8. Adding JaCoCo for Code Coverage
JaCoCo is used to measure test coverage in your project. To integrate JaCoCo, add the following plugin to your `pom.xml` within the `<build>` element, which defines how your Maven project is built. It's where you configure everything related to the build process, such as plugins, resources, directory structure, and final artifact details.

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
    <executions>
        <execution>
            <id>prepare-agent</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>verify</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

### Running JaCoCo
1. **Run tests with JaCoCo enabled:**

   ```sh
   mvn clean test
   ```

2. **Generate the coverage report:**

   ```sh
   mvn jacoco:report
   ```

3. **View the report:**
   Open the HTML report located at:

   ```
   target/site/jacoco/index.html
   ```

## Conclusion
JUnit 5 provides a powerful and flexible testing framework with improved annotations, assertions, and integration with tools like Mockito and JaCoCo. By leveraging its features, you can write cleaner, more maintainable tests for your Java applications.

## References
- [JUnit 5 documentation](https://junit.org/junit5/)
- [Mockito documentation](https://site.mockito.org/)
- [JaCoCo documentation](https://www.eclemma.org/jacoco/)

