# 프로젝트 이름

이 프로젝트는 Spring Boot를 사용하여 구현된 웹 애플리케이션입니다. 이 애플리케이션은 사용자 인증 및 API 문서화를 포함한 여러 기능을 제공합니다.

## 주요 기능

- **사용자 인증**: Spring Security를 사용하여 사용자 인증을 처리합니다.
- **API 문서화**: Swagger를 사용하여 API 문서를 자동으로 생성합니다.
- **H2 데이터베이스**: H2 인메모리 데이터베이스를 사용하여 애플리케이션 데이터를 저장합니다.

## 기술 스택

- **Java**
- **Spring Boot**
- **Spring Security**
- **Swagger**
- **H2 Database**
- **Gradle**

## 설정

### H2 데이터베이스 설정

`src/main/resources/application.properties` 파일에서 H2 데이터베이스 설정을 확인할 수 있습니다.

```ini
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.settings.web-allow-others=true
spring.datasource.initialization-mode=always
```

### Swagger 설정

Swagger 설정은 `src/main/java/com/example/demo/config/SwaggerConfig.java` 파일에서 확인할 수 있습니다.

```java
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0")
                        .description("API documentation for the application"));
    }
}
```

## 실행 방법

1. 프로젝트를 클론합니다.
   ```sh
   git clone https://github.com/your-repo/project-name.git
   ```
2. 프로젝트 디렉토리로 이동합니다.
   ```sh
   cd project-name
   ```
3. Gradle을 사용하여 애플리케이션을 빌드하고 실행합니다.
   ```sh
   ./gradlew bootRun
   ```
4. 애플리케이션이 실행되면, 브라우저에서 `http://localhost:8080`으로 접속합니다.

## API 엔드포인트

- **로그인**: `/login` (POST)
- **H2 콘솔**: `/h2-console`
- **Swagger UI**: `/swagger-ui.html`

## 예외 처리

사용자 정의 예외는 `com.example.demo.exception.UserNotFoundException` 클래스에서 정의됩니다.

```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
```