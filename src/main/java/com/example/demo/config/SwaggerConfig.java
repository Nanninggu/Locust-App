package com.example.demo.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 이 클래스는 스프링 설정 클래스임을 나타냄
public class SwaggerConfig {

    @Bean // 이 메서드는 스프링 빈으로 등록됨
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public") // "public" 그룹으로 API를 묶음
                .pathsToMatch("/**") // 모�� 경로에 대해 API 문서를 생성
                .build();
    }

    @Bean // 이 메서드는 스프링 빈으로 등록됨
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation") // API 문서의 제목 설정
                        .version("1.0") // API 문서의 버전 설정
                        .description("API documentation for the application")); // API 문서의 설명 설정
    }
}
