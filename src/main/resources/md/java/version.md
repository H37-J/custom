## Java version

Generics

5 버전의 가장 중요한 신규 기능입니다. 기존에 컬렉션프레임워크를 이용하여 발생할 수 있는 ClassCastException을 컴파일 시간에 검증할 수 있습니다. 이러한 컴파일 검증 기능뿐만 아니라 코드에 대한 데이터를 명확하게 하여 가독성을 높일 수 있습니다.

Concurrency API

API 를 사용하여 병렬 프로그래밍 혹은 멀티 스레드를 손쉽게 구현할 수 있습니다.

Enumeration

자바 개발자들은 데이터 구조를 더 쉽게 정의하고 사용할 수 있는 열거형(Enum) 기능을 원했고 자바 5에 추가되었습니다. 이를 이용하여 클래스, 인터페이스 열거형으로, 소스 코드를 작성할 수 있습니다.

Auto Boxing/Unboxing

오토박싱/언박싱 기능을 통해 개발자가 기본형 데이터를 래퍼 클래스로 직접 변환하지 않아도 언어 차원에서 자동 변환이 가능하도록 보강되었습니다.

### version 8
Lambda Expression

메소드를 지칭하는 명칭 없이 구현부를 선언하는 익명 메소드 생성 문법입니다. 별도의 익명 클래스를 만들어서 선언하던 방식을 람다를 통해 대폭 간소화할 수 있으며, 함수형 프로그래밍, 스트림 API 그리고 컬럭션 프레임워크의 개선 등에 영향을 주었습니다.

Method Reference

메서드 레퍼런스는 특정 메서드만을 호출하는 람다의 축약형입니다. 메서드 레퍼런스를 새로운 기능이 아니라 하나의 메서드를 참조하는 람다를 편리하게 표현할 수 있는 문법으로 간주할 수 있습니다.



람다와 메서드 레퍼런스 단축 표현 예시

람다	메서드 레퍼런스

```java
public class test {
    public void Test1() {
        (Soccer s) -> s.getGoal() Soccer::getGoal
        () -> Thread.dumpStack() Thread::dumpStack
        (str, i) -> str.substring(i) String::substring
        (String s) -> System.out.println(s) System.out::println
    }
}
```

Interface Default Methods

Java SE 8에서는 인터페이스에 디폴트 메소드(Default Methods)라는 것이 추가되었습니다. 인터페이스는 메소드 정의만 할 수 있고 구현은 할 수 없었지만, 
Java SE 8에서부터는 디폴트 메소드라는 개념이 생겨 구현 내용도 인터페이스에 포함시킬 수 있습니다.

```java
// 일반적인 인터페이스 구현
public interface Soccer {
public void doShooting(int n);
}

// 디폴트 메소드를 사용하여 구현 내용을 인터페이스에 포함
public interface Soccer {
public default void doShooting(int n) {
System.out.println("doShooting(Shooting)");
}
}


// 디폴트 메소드가 구현된 인터페이스 또한 상속 받을수 있음
public interface Soccer {
public default void doShooting(int n) {
System.out.println("doShooting(Shooting)");
}
}

public interface SoccerChild extends Soccer {
}
```

Null 처리 Optional 추가

이전까지 변수의 null 여부를 검사하고 대응하기 위해 많은 노력을 했습니다. Java SE 8부터는 Optional이라는 구조체를 제공해서 이전보다 간편하게 NPE(Null Pointer Exception) 이슈에 대응할 수 있습니다.


```java
// Optional 구조체의 생성
public class test {
    Optional<String> optionalStr = Optional.empty();
    Optional<String> optionalStr = Optional.of("str");
    Optional<String> optionalStr = Optional.ofNullable("str");

    // java8 Optional 이전까지의 null 대응
    String value = null;
    String result = "";
    try {
        result = value.toUpperCase();
    } catch (NullPointerException exception) {
        throw new Exception();
    }

    // java8 Optional 이후의 null 대응
    String value = null;
    Optional<String> valueOpt = Optional.ofNullable(value);
    String result = valueOpt.orElseThrow(Exception::new).toUpperCase();
}
```