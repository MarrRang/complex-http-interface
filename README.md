# COMPLEX-HTTP-INTERFACE

![Spring](https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=Spring&logoColor=white)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

---
The complex-http-interface-model is a Kotlin library created to simplify the handling of custom models as parameters in Spring Boot's HTTP interface.  
It's especially useful in situations where directly using a custom model as a parameter with the @GetExchange annotation may not be straightforward.

### **Feature**
* Allowing parameters as a model when using GetExchange in HttpInterface. 

### **Usage**

#### 1. Add the library as a dependency in your project.

   ```kotlin
   dependencies {
       implementation "com.marrrang:complex-http-interface-model:{Version}"
   }
   ```

#### 2. When using Auto Configuration in Spring, add the following configuration to the application.yml file. The packageName configuration determines the base package for which the user-defined model will be used as a reference for retrieval.

   ```
   http-interface:
      complex:
         packageName: "your.base.package.name"
   ```

#### 3. When configuring the HTTP interface's proxy, register the ComplexArgumentResolver to handle custom models conveniently. Use the customArgumentResolver option to specify the resolver.

   ```kotlin
   @Configuration
   @EnableComplexResolver
   class HttpInterfaceConfiguration(
       private val complexArgumentResolver: ComplexArgumentResolver,
   ) { /** Your Code **/ }
   ```
   
#### 4. With the complex-http-interface-model library, the YourCustomModel can be injected seamlessly into the controller method, providing a convenient solution for handling complex models in HTTP requests.
   
   ```kotlin
   @HttpExchange
   interface PersonalClient {
      @GetExchange("/api/path")
      fun yourFunction(@RequestComplexParam request: YourCustomModel) {}
      
      ...
   }
   ```

---

`complex-http-interface-model`은 Spring Boot의 HTTP 인터페이스에서 사용자 정의 모델을 매개변수로 처리하는 것을 간편하게 만들기 위해 만들어진 Kotlin 라이브러리입니다.
특히 @GetExchange 어노테이션을 사용하여 매개변수로 직접 사용하는 것이 간단하지 않은 상황에서 유용합니다.

### **기능**

* HttpInterface에서 GetExchange를 사용할 때 모델을 파라미터로 허용합니다.

### **사용법**

#### 1. 의존성 추가

   ```kotlin
   dependencies {
       implementation "com.marrrang:complex-http-interface-model:{Version}"
   }
   ```

#### 2. Spring의 Auto Configuration 사용 시 아래 필수 설정 추가. packageName 설정은 사용자 지정 모델을 어느 패키지 기반으로 찾을 지 지정하는 설정입니다. 

   ```yaml
   http-interface:
      complex:
        packageName: "your.base.package.name"
   ```

#### 2. `@EnableComplexResolver` 어노테이션 추가, Bean 주입

   ```kotlin
   @Configuration
   @EnableComplexResolver
   class HttpInterfaceConfiguration(
       private val complexArgumentResolver: ComplexArgumentResolver,
   ) { /** Your Code **/ }
   ```

#### 3. `@RequestParam` 대신 `@RequestComplexParam` 어노테이션 사용

   ```kotlin
   @HttpExchange
   interface PersonalClient {
      @GetExchange("/api/path")
      fun yourFunction(@RequestComplexParam request: YourCustomModel) {}
      
      ...
   }
   ```
