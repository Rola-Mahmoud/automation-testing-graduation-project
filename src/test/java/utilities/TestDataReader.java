package utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TestDataReader {
    public static Object[] getTestData(String testName, String... fields)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        List<Map<String, String>> data = mapper.readValue(
                new File("src/test/resources/testdata.json"),
                new TypeReference<List<Map<String, String>>>() {
                }
        );

        Map<String, String> record = data.stream()
                .filter(item -> testName.equals(item.get("testcase")))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Test data not found: " + testName)
                );

        return Arrays.stream(fields)
                .map(record::get)
                .toArray();
    }

    @DataProvider(name = "testData")
    public static Object[][] getData(Method method) throws IOException {

        return switch (method.getName()) {
            case "testValidLogin" -> new Object[][]{
                    getTestData("validLogin", "username", "password")
            };
            case "testInvalidLogin" -> new Object[][]{
                    getTestData("invalidLogin", "username", "password")
            };
            case "testEmptyFields" -> new Object[][]{
                    getTestData("emptyFields", "username", "password")
            };
            case "testEmployeeSearch" -> new Object[][]{
                    getTestData("employeeSearch", "employeeName")
            };
            case "testNotEmployeeSearch" -> new Object[][]{
                    getTestData("notEmployeeSearch", "employeeName")
            };
            case "testEmptyFirstName" -> new Object[][] {
                    getTestData("emptyFirstName", "firstName", "lastName")
            };
            case "testSuccessfulAddEmployee" -> new Object[][] {
                    getTestData("addEmployee", "firstName", "lastName")
            };
            default -> throw new RuntimeException(
                    "Unknown test: " + method.getName()
            );
        };
    }
}