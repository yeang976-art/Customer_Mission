# Customer Mission - Console Commerce System

Java 콘솔 환경에서 동작하는 실시간 커머스 플랫폼 학습 프로젝트입니다.

Spring 5기 CH2 커머스 과제를 기반으로, 상품 목록 조회, 장바구니, 주문, 잔액, 회원 등급 할인, 예외 처리 흐름을 직접 구현했습니다.

> 과제 참고: https://teamsparta.notion.site/Spring-5-CH-2-3802dc3ef5148008b63be95a8cb049e3

---

## 프로젝트 목표

이 프로젝트의 핵심 목표는 단순한 콘솔 출력이 아니라, 커머스 서비스의 흐름을 Java 객체 간 역할과 책임으로 나누어 구현하는 것입니다.

특히 다음 흐름을 직접 설계했습니다.

- 회원 등록
- 회원 등급 선택
- 카테고리별 상품 목록 조회
- 상품 선택
- 장바구니 추가
- 잔액 입금
- 회원 등급별 할인 적용
- 주문 확정
- 잔액 차감
- 주문 취소
- 재고 차감 및 복구
- 잘못된 입력에 대한 예외 처리

---

## 실행 환경

- Language: Java
- JDK: JDK 26 기준으로 개발
- IDE: IntelliJ IDEA
- 실행 클래스: `customer_system.Main`

---

## 실행 방법

### IntelliJ IDEA에서 실행

1. 저장소를 클론합니다.
2. IntelliJ IDEA에서 `Customer_System_Pack` 모듈을 엽니다.
3. `Customer_System_Pack/src/customer_system/Main.java`를 실행합니다.

### 터미널에서 실행

```bash
javac -d out/production/Customer_System_Pack $(find Customer_System_Pack/src -name "*.java")
java -cp out/production/Customer_System_Pack customer_system.Main
```

---

## 주요 메뉴

프로그램 실행 후 회원 등록을 먼저 진행합니다.

```text
## 회원 등록 ##
회원 이름을 입력하시오
회원 이메일을 입력하시오
회원 등급을 입력하시오
```

이후 메인 메뉴에서 기능을 선택할 수 있습니다.

```text
[ 실시간 커머스 플랫폼 메인 ]
[1] 전자제품
[2] 의류
[3] 식품
[0] 종료      | 프로그램 종료

[ 주문 관리 ]
[91] 잔액 입금       | 결제에 사용할 금액을 입력합니다.
[92] 장바구니 확인    | 장바구니를 확인 후 주문합니다.
[93] 주문 취소       | 진행중인 주문을 취소합니다.


---

## 구현 기능

### 1. 회원 등록

`Customer` 객체를 생성하여 회원 이름, 이메일, 회원 등급, 잔액을 관리합니다.

관련 클래스:

- `Customer`
- `Grade`
- `CommerceSystem`

회원 등급은 `enum`으로 관리하며, 등급에 따라 할인율이 달라집니다.

```java
public enum Grade {
    SIENNA, COBALT, DANDELION, CYAN, SCARLET, CORAL, ARGENTO, ELDORA, CRIMSON, IMPERIAL;
}
```

---

### 2. 상품 카테고리

상품은 세 가지 카테고리로 구성되어 있습니다.

- 전자제품
- 의류
- 식품

각 카테고리는 `IProducts` 인터페이스를 구현합니다.

관련 클래스:

- `IProducts`
- `SmartDevices`
- `Clothes`
- `Foods`
- `Product`

---

### 3. 상품 선택

사용자가 카테고리를 선택하면 해당 카테고리의 상품 목록이 출력됩니다.

상품은 다음 정보를 가집니다.

- 상품명
- 가격
- 설명
- 재고

관련 클래스:

- `Product`
- `ListManager`

---

### 4. 장바구니

상품을 선택한 뒤 장바구니에 추가할 수 있습니다.

장바구니는 `Cart` 객체 리스트로 관리됩니다.

관련 클래스:

- `Cart`
- `ListManager`

현재 장바구니 상품 수량은 기본 1개 단위로 추가됩니다.

---

### 5. 잔액 입금

메인 메뉴에서 `91`을 입력하면 결제에 사용할 금액을 입금할 수 있습니다.

관련 클래스:

- `Customer`
- `CommerceSystem`
- `InvalidDepositAmountException`

---

### 6. 주문 확정

메인 메뉴에서 `92`를 입력하면 장바구니를 확인하고 주문을 확정할 수 있습니다.

주문 확정 시 다음 처리가 이루어집니다.

- 장바구니 총액 계산
- 회원 등급 할인 적용
- 최종 결제 금액 계산
- 잔액 부족 여부 확인
- 잔액 차감
- 상품 재고 차감
- 장바구니 초기화

관련 클래스:

- `ListManager`
- `Customer`
- `Cart`
- `Product`
- `InsufficientBalanceException`
- `InsufficientStockException`

---

### 7. 주문 취소

메인 메뉴에서 `93`을 입력하면 진행된 주문을 취소할 수 있습니다.

주문 취소 시 주문 기록을 기준으로 재고를 복구하고, 취소 가능한 주문 목록을 초기화합니다.

관련 클래스:

- `ListManager`
- `NoCancelableOrderException`

---

### 8. 예외 처리

잘못된 입력이나 불가능한 상태는 커스텀 예외로 처리합니다.

구현된 예외 클래스:

- `EmptyCartOrderException`
- `InsufficientBalanceException`
- `InsufficientStockException`
- `InvalidDepositAmountException`
- `InvalidStockAmountException`
- `NoCancelableOrderException`

대표 오류 메시지:

```text
[ERROR 400] 입력 형식 오류: 숫자만 입력할 수 있습니다.
[ERROR 402] 잔액이 부족하여 주문할 수 없습니다.
[ERROR 404] 유효하지 않은 항목입니다.
[ERROR 409] 장바구니가 비어 있어 주문할 수 없습니다.
[ERROR 409] 취소 가능한 주문이 없습니다.
[ERROR 422] 입금 금액 범위가 유효하지 않습니다.
```

---

## 프로젝트 구조

```text
Customer_System_Pack
└── src
    └── customer_system
        ├── Main.java
        ├── CommerceSystem.java
        ├── ConsoleUI.java
        ├── customermanager
        │   ├── Customer.java
        │   └── Grade.java
        ├── custom_exceptions
        │   ├── EmptyCartOrderException.java
        │   ├── InsufficientBalanceException.java
        │   ├── InsufficientStockException.java
        │   ├── InvalidDepositAmountException.java
        │   ├── InvalidStockAmountException.java
        │   └── NoCancelableOrderException.java
        ├── managment
        │   ├── DialogManager.java
        │   └── ListManager.java
        └── productmanager
            ├── Cart.java
            ├── Product.java
            ├── IProducts.java
            └── products
                ├── SmartDevices.java
                ├── Clothes.java
                └── Foods.java
```

---

## 클래스 역할

| 클래스 | 역할 |
|---|---|
| `Main` | 프로그램 시작점 |
| `CommerceSystem` | 전체 콘솔 흐름과 상태 전이 관리 |
| `ConsoleUI` | ANSI 색상 및 콘솔 스타일 관리 |
| `DialogManager` | 메뉴와 안내 문구 출력 관리 |
| `ListManager` | 상품 목록, 장바구니, 주문 흐름 관리 |
| `Customer` | 회원 정보와 잔액 관리 |
| `Grade` | 회원 등급과 할인율 관리 |
| `Product` | 상품 정보와 재고 관리 |
| `Cart` | 장바구니에 담긴 상품 정보 관리 |
| `IProducts` | 상품 카테고리 로딩 규약 |
| `SmartDevices`, `Clothes`, `Foods` | 카테고리별 상품 데이터 제공 |

---

## Java Core 학습 포인트

이 프로젝트에서 사용한 Java Core 개념은 다음과 같습니다.

- 클래스와 객체
- 생성자
- 캡슐화
- `private final` 필드
- `enum`
- 인터페이스
- 컬렉션 `List`, `ArrayList`
- 싱글톤 형태의 매니저 객체
- `switch` 표현식
- 커스텀 예외
- 예외 처리 흐름
- 콘솔 입출력
- 상태 플래그 기반 흐름 제어
- ANSI escape code를 활용한 콘솔 색상 출력

---

## Java Core 수준 평가

이 프로젝트는 Java Core 학습 관점에서 다음 단계의 구현을 목표로 했습니다.

- 단순 문법 사용을 넘어 클래스를 역할별로 분리하기
- 상품, 장바구니, 고객, 회원 등급을 각각 객체로 모델링하기
- 인터페이스를 사용해 상품 카테고리 로딩 규약 만들기
- enum을 사용해 회원 등급과 할인율 관리하기
- 커스텀 예외를 사용해 상황별 오류 흐름 분리하기
- 장바구니, 주문, 잔액, 재고 차감 흐름을 하나의 콘솔 서비스로 연결하기

현재 구현 기준으로 보면, 이 프로젝트는 Java Core Global Percentile 기준 **Top 55~45% 전후**의 학습 결과물로 볼 수 있습니다.

수강생 집단 기준으로는 **상위 30~40% 정도**를 목표 수준으로 잡았습니다.

이 평가는 다음 기준을 바탕으로 했습니다.

- 클래스와 객체를 단순 사용이 아니라 역할 단위로 분리했는가
- 컬렉션을 사용해 상품과 장바구니 데이터를 관리했는가
- enum, interface, custom exception을 실제 기능 흐름에 사용했는가
- 콘솔 입력, 상태 전이, 장바구니, 주문, 잔액, 재고 흐름이 연결되어 있는가
- 기능이 실제로 실행 가능한 콘솔 프로그램으로 이어졌는가

다만 아직 상위권 구현으로 보기에는 다음 부분이 더 필요합니다.

- 테스트 코드 작성
- 입력 처리와 비즈니스 로직의 더 명확한 분리
- 주문 객체의 독립적인 모델링
- 장바구니 수량 선택 기능
- 주문 취소와 재고 복구 흐름의 안정화
- 상태 플래그 의존도 감소
- 패키지명과 클래스명 정리

따라서 이 프로젝트는 “완성된 실무형 커머스 시스템”이라기보다는, Java Core 개념을 사용해 콘솔 기반 커머스 흐름을 직접 설계해본 중상위 학습 결과물입니다.

---

## 현재 한계와 개선 예정

현재 프로젝트는 학습용 콘솔 애플리케이션이며, 다음 부분은 개선이 필요합니다.

- 장바구니 상품 수량 선택 기능이 아직 제한적입니다.
- 주문 기록이 독립적인 주문 객체로 분리되어 있지 않습니다.
- 주문 취소와 재고 복구 흐름은 더 안정적으로 개선할 수 있습니다.
- `managment` 패키지명은 `management`로 수정하는 것이 좋습니다.
- 입금 금액 검증 범위와 예외 메시지의 금액 안내가 일치하도록 정리할 필요가 있습니다.
- 콘솔 입력 처리와 비즈니스 로직을 더 분리할 수 있습니다.
- JUnit 기반 테스트 코드는 아직 작성되어 있지 않습니다.

---

## 버전 기록

| 버전 | 내용 |
|---|---|
| v2.1.x | 필수 기능 및 장바구니 흐름 구현 |
| v2.2.x | 회원 등급 할인율 적용 |
| v2.3.x | 잔액 입금 기능 및 UI 개선 |
| v2.4.x | 잔액 연동 및 주문 결제 흐름 개선 |

---

## 회고

이번 프로젝트는 단순히 Java 문법을 사용하는 단계에서 벗어나, 콘솔 커머스 흐름을 객체 단위로 나누어 구현해본 프로젝트입니다.

특히 상태 변경과 흐름 제어의 차이를 직접 경험했습니다.

예를 들어 `isRunning = false`는 프로그램 종료 상태를 표시할 뿐, 이미 실행 중인 메서드 흐름을 자동으로 멈추지는 않습니다. 현재 메서드 흐름을 끊으려면 `return`이 필요합니다.

이 경험을 통해 콘솔 프로그램에서도 상태 전이와 책임 분리가 중요하다는 것을 배웠습니다.

