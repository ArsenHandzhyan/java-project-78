### Hexlet tests and linter status:
[![Actions Status](https://github.com/ArsenHandzhyan/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ArsenHandzhyan/java-project-78/actions)
[![Actions Status](https://github.com/ArsenHandzhyan/java-project-78/actions/workflows/main.yml/badge.svg)](https://github.com/ArsenHandzhyan/java-project-78/actions)
[![Maintainability](https://api.codeclimate.com/v1/badges/5baa4f89b8a4df7f0402/maintainability)](https://codeclimate.com/github/ArsenHandzhyan/java-project-78/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/5baa4f89b8a4df7f0402/test_coverage)](https://codeclimate.com/github/ArsenHandzhyan/java-project-78/test_coverage)

## Hexlet Validator Library: Professional Edition

Introducing Hexlet Validator: a robust and user-friendly data validation library for Java developers. This library empowers you to:

- **Enforce data integrity**: Ensure the accuracy and consistency of your data across applications.
- **Simplify validation logic**: Define complex validation rules with ease and clarity.
- **Improve code quality**: Write concise and maintainable code with the help of chainable methods.
- **Reduce development time**: Focus on core functionality without worrying about tedious validation logic.

### Key Features:

- **Extensible Schema System**: Create and combine different schema types for diverse validation needs.
- **Intuitive API**: A simple and well-documented API makes adoption and usage effortless.
- **Chainable Methods**: Compose fluent validation chains for enhanced code readability.
- **Null-safe Design**: Handles null values gracefully and provides meaningful error messages.

### Wide Range of Applications:

- **Web Applications**: Validate user input forms, ensuring valid and secure data.
- **Configuration Files**: Ensure proper configuration formats and prevent errors.
- **API Requests/Responses**: Validate data payloads for robust and reliable communication.
- **Data Processing**: Enhance data integrity and reliability in various processing pipelines.

### Example Usage:

```java
Validator v = new Validator();

// Define individual schema rules for name and age
StringSchema nameSchema = v.string().required().minLength(3);
NumberSchema ageSchema = v.number().positive();

// Combine schemas for complex validation
Map<String, BaseSchema> userSchemaMap = new HashMap<>();
userSchemaMap.put("name", nameSchema);
userSchemaMap.put("age", ageSchema);

// Define a MapSchema with validation rules and size constraint
MapSchema userSchema = v.map().shape(userSchemaMap).sizeof(2);

// Validate user data against the defined schema
Map<String, Object> userData = new HashMap<>();
userData.put("name", "John Doe");
userData.put("age", 30);

boolean isValid = userSchema.isValid(userData);

if (isValid) {
    System.out.println("User data is valid!");
} else {
    System.out.println("User data is invalid!");
}
