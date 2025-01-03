# 멀티스레드

## 멀티태스킹

- 각 프로그램의 실행 시간을 분할해서 **마치 동시에 실행되는 것처럼** 하는 기법을 `시분할` 기법이라고 한다.  (0.01초 정도 수행하고 다음 프로그램을 0.01초 실행하면서 돌아가면서 실행하는 방식)

## 멀티프로세싱

- 컴퓨터 시스템에서 **둘 이상의** `프로세서`(CPU 코어)를 사용하여 여러 작업을 동시에 처리하는 기술

### 멀티프로세싱 VS 멀티태스킹

- **멀티프로세싱**
    - 여러 CPU를 사용하여 동시에 여러작업을 수행하는 것
    - `하드웨어` 기반으로 성능 향상
- **멀티태스킹**
    - 단일 CPU가 여러 작업을 동시에 수행하는 것처럼 보이게 하는 기술
    - `소프트웨어` 기반으로 CPU  시간을 분할하여 각 작업에 할당한다
- 둘은 여러 작업에 대해서 **동시에 처리하는 목적은 비슷**하지만, `멀티 태스킹`은 `하나의 CPU`에서 여러 개의 작업을 처리하는 반면, `멀티 프로세싱`은 `여러 개의 CPU`가 각각의 작업을 처리하는 것이라는 차이점이 있다.


## 프로세스

- 프로그램은 실제 실행하지 전까지는 *단순한 파일에 불과*
- 프로그램을 실행하면 `프로세스`가 만들어지고 `프로그램`이 실행된다
- 프로세스는 **실행 중인 프로그램**의 `인스턴스`이다

---

프로세스는 서로 격리가 되어 충돌해도 다른 프로세스에 영향을 미치지 않고 메모리에 직접 접근할 수 없다

---

## 스레드

- 프로세스는 하나 이상의 `스레드`를 **반드시 포함**한다
- 프로세스 내에서 실행되는 **작업의 단위**이다
- 스레드는 **순서**와 **실행 기간**을 *보장하지 않는다*

**공유메모리** : 같은 프로세스의 코드 섹션, 데이터 섹션, 힙은 프로세스 안의 모든 `스레드`가 공유한다

**개별 스택** : 각 스레드는 자신의 `스택`을 갖고 있다

- 스레드는 프로레스의 코드를 한줄 한줄 실행시키는 역할을 하고있다. (작업의 단위)

### 스레드와 스케줄링

- 스케줄링은 CPU 시간을 여러 작업에 나누어 **배분하는 방법**

### 컨텍스트 스위칭

- 컨텍스트 스위칭 과정에서 이전에 실행 중인 값을 메모리에 잠깐 저장하고, 이후에 다시 실행하는 시점에 저장한 값을 CPU에 다시 불러와야 한다
- 결과론적으로 컨텍스트 스위칭 과정에는 *약간의 비용이 발생*한다
- 멀티스레드는 컨텍스트 스위칭 과정이 필요하므로 **항상 효율적인 것은 아니다**

**웹 어플리케이션 서버**에서는 CPU연산이 필요한 작업보다는 대부분 사용자의 입력을 기다리거나 데이터베이스를 호출하고 결과를 기다리는 등 **I/O 작업이 많다**.

이 경우 `CPU코어` 숫자에 맞춰 `스레드` 수를 *맞추면 안된다*. 그 개수만큼 사용하고 `CPU`가 *놀게되는 현상이 발생한다*.

결국 `스레드` 숫자는 `CPU-바운드` 작업이 많은가 `I/O 작업`이 많은가에 따라 다르게 설정 해야한다

- **CPU-바운드 작업** : CPU 코어 수 + 1개
    - `CPU`를 거의 **100% 사용하는 작업**이므로 `스레드`는 **CPU 숫자에 최적화**
- **I/O-바운드 작업** : CPU 코어 수 보다 많은 스레드를 생성, CPU를 최대한 사용할 수 있는 숫자까지 스레도 생성
    - `CPU`를 *많이 사용하지 않으므로* 성능 테스트를 통해 CPU를 **최대한 활용하는 숫자까지 스레드 생성**
    - **단 너무 많은 `스레드` 생성 시 `컨텍스트 스위칭` 비용도 함께 증가함**

### 스레드의 생명 주기

**스레드의 상태**

- **NEW(새로운 상태) :** 스레드가 생성되었으나 아직 시작되지 않은 상태
- **Runnable(실행 가능 상태)**  : 스레드가 실행 중이거나 실행될 준비가 된 상태
- **Waiting(대기 상태)** : 스레드가 대기하고 있는 상태(**Blocked/Waiting/Timed Waiting)**
- **Terminated(종료 상태)** : 스레드의 실행이 완료된 상태

1) **New**

- 스레드가 **생성되고 아직 시작되지 않은 상태**
- 이 상태에서는 `Thread 객체`가 생성되지만,  s**tart() 메서드가 호출되지 않는 상태**

2) **Runnable**

- 스레드가 **실행될 준비가 된 상태**. 이 상태에서 스레드는 실제로 CPU에서 실행될 수 있다.
- start() 메서드가 호출되면 스레드는 이 상태로 들어감
- Runnable 상태의 모든 스레드는 동시에 실행된는 것이 아니다. `운영체제`의 `스케줄러`가 각 스레드에 CPU 시간을 할당하여 실행하기 때문에, Runnable 상태에 있는 스레드는 **스케줄러의 실행 대기열에 포함**되어 있다가 차례로 CPU에서 실행된다.
- 실행 대기열에 있든, CPU에서 실제로 실행되고 있든 Runnable 상태이다.  **자바에서 둘을 구분해서 확인할 수는 없다**

3) **Terminated**

- 스레드의 실행이 완료된 상태
- 정상적으로 종료되거나, 예외가 발생하여 종료된 경우 이상태로 들어간다
- 스레드는 한 번 종료되면 **다시 시작할 수 없다**

### 자바 스레드의 상태 전이 과정

- **New** → **Runnable** : `start()` 메서드를 호출하면 스레드가 `Runnable` 상태로 전이된다
- **Runnable → Blocked/Waiting/Timed Waiting** : 스레드가 락을 얻지 못하거나, `wait()` 또는 `sleep()` 메서드를 호출할 때 해당 상태로 전이된다
- **Blocked/Waiting/Timed Waiting → Runnable** : 스레드가 락을 얻거나, 기다림이 완료되면 다시 `Runnable` 상태로 돌아간다
- **Runnable → Terminated** : 스레드의 `run()` 메서드가 완료되면 스레드는 `Terminated` 상태가 된다

### 1) Thread 객체를 상속 받아 구현

### 스레드의 start() vs run()

- `스레드`의 재정의 한 **run() 메소드를 실행**하기 위해선 `스레드`를 **시작 시키는** **start()** 메소드를 실행시켜야 스레드 인스턴스가 실행이 된다
- **start()** 메소드는 `스레드`에 `스택` 공간을 **할당하면서** `스레드`를 **실행 시키는 메소드**이다

### 데몬 스레드

- 스레드는 `사용자 스레드`와 `데몬 스레드` 2가지 종류로 구분할 수 있다

**사용자 스레드**

- **프로그램의 주요 작업을 실행**한다
- **작업이 완료될 때까지 실행**된다
- 모든 `user 스레드`가 종료되면 `JVM`도 종료된다

**데몬 스레드**

- 백그라운드에서 보조적인 작업을 수행한다
- **모든 user 스레드가 종료되면 데몬 스레드는 자동으로 종료**된다

### 2) Runnable 인터페이스를 구현하는 방법

### Thread 상속 vs Runnable 구현

- 스레드를 구현할 때는 Runnable 구현을 지향한다

### Thread 클래스 상속 방식

**장점** 

- 간단한 구현 : `Thread` 클래스를 상속 받아 `run()` 메소드만 재정의하면 된다

**단점**

- 상속의 제한 : 자바는 **단일 상속만 허용**하므로 이미 다른 클래스를 상속받고 있는 경우 `Thread` 클래스를 *상속 받을 수 없다*
- 유연성 부족 : 인터페이스를 사용하는 방법에 비해 *유연성이 떨어진다*

### Runnable 인터페이스 구현 방식

**장점** 

- 상속의 자유로움 : 다른 클래스를 상속받아도 문제없이 구현할 수 있다
- 코드의 분리 : 스레드와 **실행할 작업을 분리**하여 코드의 가독성을 높일 수 있다
- 여러 스레드가 동일한 `Runnable` 객체를 공유할 수 있어 자원 관리를 효율적으로 할 수 있다

**단점**

- `Runnable` 객체를 생성하고 이를 `Thread`에 전달하는 과정이 추가됨

```java
public static void main(String[] args) {
	HelloRunnable runnable = new HelloRunnable();
	Thread thread = new Thread(runnable); // runnable 객체를 전달하는 과정이 추가됨
	thread.start();
}
```

### Thread Join

- **Join() 메서드가 필요한 순간**
    - `main 메서드`가 `스레드`에게 **작업을 내리고 값을 받거나 출력할 때** main 스레드는 각 스레드를 실행시키고 값을 기다리지 않는다. 그리하여 *제대로 된 값을 받지 못하는 현상*이 발생한다
    - `join` 사용시 해당 스레드가 종료될 때 까지 기다린다. 이때 기다리는 스레드는 `WAITING` 상태가 된다
        
        
    - `main` 스레드에서 코드를 실행하면 `thread1`과 `thread2`가 종료될 때 까지 기다린다. `main`스레드는 `WAITING` 상태가 된다
    - `thread1` 이 종료되지 않았다면 `main` 스레드는 join() 코드 안에서 더는 진행하지 않고 멈추어 기다린다. 그리고 스레드가 종료시 `main` 스레드는 `RUNNABLE` 상태가 되고 다음 코드로 이동한다
- **join()의 단점**
    - 다른 스레드가 완료가 **끝날 때 까지 무기한** 기다리는 단점이 있다.
- **join() 특정 시간 대기하기**
    - `join()`에는 두가지 메서드가 있다
        - **join()** : 호출 스레드는 대상 스레드가 완료될 때 까지 무한정 대기한다.
        - **join(ms)** : 호출 스레드는 **특정 시간 만큼만 대기**한다. 호출 스레드는 지정한 시간이 지나면서 다시 `RUNNABLE` 상태가 되면서 다음 코드를 수행한다

### Waiting(대기상태)

- 스레드가 다른 스레드의 특정 작업이 **완료**되기를 무기한 기다리는 상태이다
- `join()`을 호출하는 스레드는 대상 스레드가 `TERMINATED` 상태가 될 때 까지 대기한다. 대상 스레드가 `TERMINATED` 상태가 되면 호출 스레드는 다시 `RUNNABLE` 상태가 되면서 다음 코드를 수행한다

### 인터럽트

- 인터럽트를 사용하면 `WAITING`, `TIMED_WAITING` 같은 대기 상태의 스레드를 직접 깨워서, 작동하는 `RUNNABLE` 상태로 만들 수 있다
- 특정 `스레드`의 인스턴스에 `interrupt()` 메서드를 호출하면, 해당 스레드에 인터럽트가 발생한다
- `인터럽트`가 발생하면 해당 스레드에 `InterruptedException`이  발생한다
    - 이때 `인터럽트`를 받은 스레드는 대기 상태에서 깨어나 `RUNNABLE` 상태가 되고, 코드를 정상 수행한다
- `interrupt()` 를 호출했다고 즉각 `InterruptedException`이 발생하는 것은 아니다. 오직 `sleep()` 처럼 `InterruptedException`을 던지는 **메서드를 호출 하거나** 또는 **호출 중일** 때 예외가 발생한다
- 스레드의 `인터럽트`를 정상으로 돌리지 않으면 이후에도 **계속 인터럽트가 발생**한다
- 인터럽트의 목적을 달성하면 인터럽트 상태를 다시 **정상으로 돌려두어야 한다**

### isInterrupted() vs interrupted()

- `isInterrupted()` 는 단순히 인터럽트의 상태를 조회하는 메서드이다
- `interrupted()` 는 인터럽트 상태라면 true를 반환하고, 해당 스레드의 인터럽트 상태를 `false`로 변경한다
- 인터럽트 상태가 아니라면 `false`를 반환하고, 해당 스레드의 **인터럽트 상태를 변경하지 않는다**

### yield - 양보하기

- java의 스레드가 `RUNNABLE` 상태일 때, 운영체제의 `스케줄링`은 다음과 같은 상태들을 가질 수 있다
    - **실행 상태(Running)** : 스레드가 CPU에서 실제로 실행 중이다
    - **실행 대기 상태(Ready)** : 스레드가 실행될 준비가 되었지만, CPU가 바빠서 `스케줄링 큐`에서 대기 중
- 운영체제는 실행 상태의 스레드를 **잠깐만 실행**하고 `실행 대기 상태`로 만든다. 그리고 실행 대기 상태의 스레들을 잠깐만 `실행 상태`로 변경해서 실행한다. **이 과정을 반복한다**
- `Thread.yield()` 메서드는 현재 실행 중인 스레드가 자발적으로 CPU를 양보하여 다른 스레드가 실행될 수 있도록 한다
- `yield()` 메서드를 호출한 스레드는 `RUNNABLE` 상태를 유지하면서 CPU를 양보한다. 즉 이 스레드는 다시 `스케줄링 큐`에 들어가면서 다른 스레드에게 `CPU` **사용 기회를 넘긴다**
    - `yield()`는 `RUNNABLE` 상태를 유지하기 때문에, *양보할 스레드가 없으면* 본인 스레드가 계속 실행된다

## Volatile, 메모리 가시성

- `메인 메모리`는 CPU 입장에서 보면 거리도 멀고, 속도도 상대적으로 느리다. 대신에 상대적으로 가격이 저렴해서 큰 용량을 **쉽게 구성할 수 있다**
- CPU 연산은 **매우 빠르기** 때문에 CPU 가까이에 매우 빠른 메모리가 필요한데, 이것이 바로 `캐시 메모리` 이다. `캐시 메모리`는 CPU와 가까이 붙어있고, 속도도 매우 빠른 메모리이다.
- 현대 CPU 대부분은 코어 단위로 캐시 메모리를 각각 보유하고 있다
    - 각 스레드가 `runFalg` 값을 사용하면 CPU는 이 값을 효율적으로 처리하기 위해 먼저 `runFlag`를 캐시 메모리에 불러온다
    - 그리고 이후에는 캐시 메모리에 있는 `runFlag` 를 사용하게 된다
    - `main` 스레드는 runFlag를 false로 설정한다. 이때 캐시 메모리의 runFlag가 false로 설정된다
    - **여기서 핵심은 캐시 메모리의 `runFlag` 값만 변한다는 것이다. 메인 메모리의 값은 즉시 반영되지 않는다**
    - 그림과 같이 메인 메모리나 다른 스레드의 값이 반영되는 시기는 알 수 없고 극단적으로 **평생 반영이 안될수도 있다**
- **메모리 가시성**
    - 멀티스레드 환경에서는 한 스레드가 변경한 값이 다른 스레드에서 언제 보이지는에 대한 문제를 `메모리 가시성`이라 한다. 이름 그래도 변경한 값이 보이는가, 보이지 않는가의 문제이다
- `Volatile` 키워드는 캐시 메모리를 사용하지 않고, 값을 읽거나 쓸 때 항상 **메인 메모리에 직접 접근**한다
- 캐시 메모리를 사용할 때 보다 *성능이 느려지는 단점이* 있기 때문에 **꼭 필요한 곳**에서만 사용해야한다
- `Volatile` 또는 `스레드 동기화 기법`(synchronized, ReentrantLock)을 사용하면 **메모리 가시성 문제가 발생하지 않는다**

### Java Memory Model

- java Memory Model(JMM)은 자바 프로그램이 어떻게 메모리에 접근하고 수정할 수 있는지를 규정하며, 특히 멀티스레드 프로그래밍에서 스레드 간의 **상호작용을 정의**한다. 핵심은 여러 스레들의 작업 순서를 보장하는 `happens-before` 관계에 대한 정의이다.

### happens-before

- happens-before 관계는 자바 메모리 모델에서 스레드 간의 작업 순서를 정의하는 개념이다. A 작업이 B 작업보다 happens-before 관계에 있다면, A 작업에서의 **모든 메모리 변경 사항**은 B 작업에서 **볼 수 있다.** 즉, A 작업에서 변경된 내용은 B 작업이 시작되기 전에 **모두 메모리에 반영**된다.
- `happens-before` 관계는 이름 그대로, 한 동작이 다른 동작보다 먼저 발생함을 보장한다
- `happens-before` 관계는 스레드 간의 **메모리 가시성을 보장**하는 규칙이다
- `happens-before` 관계가 성립하면, 한 스레드의 작업을 다른 스레드에서 볼 수 있게 된다
- 즉, 한 스레드에서 수행한 작업을 다른 스레드가 참조할 때 **최신 상태가 보장**되는 것이다

### 동시성 문제

**임계 영역**

- 여러 스레드가 동시에 접근하면 데이터 불일치나 예상치 못한 동작이 발생할 수 있는 **위험하고 또 중요한 코드 부분을** 뜻한다
- 여러 스레드가 *동시에 접근해서는 안 되는* `공유 자원`을 **접근**하거나 **수정**하는 부분을 의미한다

### **synchronized 메서드**

- 모든 `객체(인스턴스)`는 내부에 자신만의 `락(lock)`을 가지고 있다
    - `모니터 락` 이라고도 부른다
- 스레드가 `synchronized` 키워드가 있는 메서드에 진입하려면 **반드시 해당 인스턴스의 락이 있어야한다**
- 인스턴스 락이 있는 스레드는 그대로 작업을 진행하고 락을 얻지 못한 스레드는 `BLOCKED`상태로 대기한다. 락을 회득 하기 전까지 무한정 대기한다
- 인스턴스 락을 가지고 있던 스레드가 작업을 종료되어 락은 반납하면 **락 획득을 대기하는 스레드는 자동으로 락을 획득**한다
- 락을 획득하는 순서는 보장되지 않는다
- **`synchronized`**는 큰 장점이자 단점이 *한 번에 하나의 스레드만 실행할 수 있다는 점이다*. 성능이 떨어질 수 있으므로 `synchronized`를 통해 동시에 실행할 수 없는 코드 구간은 **꼭 필요한 곳으로 한정해서 설정**해야 한다
    - **synchronized 코드 블럭 :** `synchronized()` 메서드를 통해 임계영역 부분에만 synchronized를 설정할 수 있다
    - **synchronized(this) :** 괄호 ()안에 들어가는 값은 락을 획득할 인스턴스  참조이다
    - 하나의 스레드만 실행할 수 있는 **안전한 임계 영역**은 가능한 **최소한의 범위에 적용해야 한다**. 그래야 동시에 여러 스레드가 실행할 수 있는 부분을 늘려서, **전체적인 처리 성능을 늘릴 수 있다**
- `스택 영역`은 각각의 스레드가 가지는 **별도의 메모리 공간**이다. 이 메모리 공간은 다른 스레드와 공유하지 않는다
    - 지역 변수는 스레드의 개별 저장 공간인 `스택 영역`에 생성된다
    - 따라서 **지역 변수는 절대로 다른 스레드와 공유되지 않는다**
    - 이런 이유로 지역 변수는 동기화에 대한 걱정을 하지 않아도 된다. **`synchronized`** *사용시 오히려 성능이 느려진다*

## concurrent.Lock

### **synchronized 단점**

- **무한 대기** : `BLOCKED`상태의 스레드는 락이 풀릴 때 까지 무한 대기한다
    - 특정 시간까지만 대기하는 타임아웃 X
    - 중간에 `인터럽트` X
- **공정성** : 락이 돌아왔을 때 `BLOCKED` 상태의 여러 스레드 중에 어떤 스레드가 락을 획득할 지 알 수 없다

### LockSupport 기능

`LockSupport` 는 스레드를 `WAITING` 상태로 변경한다

`WAITING` 상태는 누가 깨워주기 전까지는 계속 대기한다. 그리고 CPU 실행 스케줄링에 들어가지 않는다

**LockSupport 주요 기능**

- **park()** : 스레드를 `WAITING` 상태로 변경한다
- **parkNanos(nanos)** : 스레드를 나노초 동안만 `TIMED_WAITING` 상태로 변경한다
    - 지정한 나노초가 지나면 `TIMED_WAITING` 상태에서 빠져나오고 `RUNNABLE` 상태로 변경된다
- **unpark(thread)** : `WAITING` 상태의 대상 스레드를 `RUNNABLE` 상태로 변경한다

### ReentrantLock

**Lock 인터페이스** : 동시성 프로그래밍에서 쓰이는 안전한 임계 영역을 위한 락을 구현하는데 사용(synchronized 대체)

- `void lock()`
    - 락을 획득한다. 만약 다른 스레드가 이미 락을 획득했다면, 락이 풀릴 때까지 현재 스레드는 대기( `WAITING` )한다. 이 메서드는 인터럽트에 응답하지 않는다.
    - 예) 맛집에 한번 줄을 서면 끝까지 기다린다. 친구가 다른 맛집을 찾았다고 중간에 연락해도 포기하지 않고 기다린다.
- `void lockInterruptibly()`
    - 락 획득을 시도하되, 다른 스레드가 인터럽트할 수 있도록 한다. 만약 다른 스레드가 이미 락을 획득했다면, 현재 스레드는 락을 획득할 때까지 대기한다. 대기 중에 인터럽트가 발생하면 `InterruptedException` 이 발생하며 락 획득을 포기한다.
    - 예) 맛집에 한번 줄을 서서 기다린다. 다만 친구가 다른 맛집을 찾았다고 중간에 연락하면 포기한다.
- `boolean tryLock()`
    - 락 획득을 시도하고, 즉시 성공 여부를 반환한다. 만약 다른 스레드가 이미 락을 획득했다면 `false` 를 반환하고, 그렇지 않으면 락을 획득하고 `true` 를 반환한다. 인터럽트는 발생하지 않는다
    - 예) 맛집에 대기 줄이 없으면 바로 들어가고, 대기 줄이 있으면 즉시 포기한다.
- `boolean tryLock(long time, TimeUnit unit)`
    - **주어진 시간 동안** 락 획득을 시도한다. 주어진 시간 안에 락을 획득하면 `true` 를 반환한다. 주어진 시간이 지나도 락을 획득하지 못한 경우 `false` 를 반환한다. 이 메서드는 대기 중 인터럽트가 발생하면`InterruptedException` 이 발생하며 락 획득을 포기한다.
    - 예) 맛집에 줄을 서지만 특정 시간 만큼만 기다린다. 특정 시간이 지나도 계속 줄을 서야 한다면 포기한다. 친구가 다른 맛집을 찾았다고 중간에 연락해도 포기한다.
- `void unlock()`
    - 락을 해제한다. 락을 해제하면 락 획득을 대기 중인 스레드 중 하나가 락을 획득할 수 있게 된다. 락을 획득한 스레드가 호출해야 하며, 그렇지 않으면 `IllegalMonitorStateException` 이 발생할 수 있다.
    - 예) 식당안에 있는 손님이 밥을 먹고 나간다. 식당에 자리가 하나 난다. 기다리는 손님께 이런 사실을 알려주어야 한다. 기다리던 손님 중 한 명이 식당에 들어간다.

`ReentrantLock` 구현체가 synchronized에서 발생했던 공정성 문제도 해결해준다

**비공정 모드 (Non-fair mode)**

- 비공정 모드는 `ReentrantLock` 의 기본 모드이다. 이 모드에서는 **락을 먼저 요청한 스레드가 락을 먼저 획득한다는 보장이 없다**. 락을 풀었을 때, 대기 중인 스레드 중 아무나 락을 획득할 수 있다. 이는 락을 빨리 획득할 수 있지만, 특정 스레드가 장기간 락을 획득하지 못할 가능성도 있다.

**공정 모드 (Fair mode)** 

- 생성자에서 `true` 를 전달하면 된다. 예) `new ReentrantLock(true)`공정 모드는 **락을 요청한 순서대로 스레드가 락을 획득할 수 있게 한다**. 이는 먼저 대기한 스레드가 먼저 락을 획득하게 되어 스레드 간의 공정성을 보장한다. 그러나 이로 인해 **성능이 저하될 수 있다**.

**비공정 vs 공정 모드 정리**

- **비공정 모드 :** 성능을 중시하고, 스레드가 락을 빨리 획득할 수 있지만, **특정 스레드가 계속해서 락을 획득하지 못할 수 있다**.
- **공정 모드 :** 스레드가 락을 획득하는 순서를 보장하여 공정성을 중시하지만, **성능이 저하될 수 있다**.

### BLOCKED vs WAITING

- `BLOCKED` 상태는 자바의 `synchronized`에서 락을 획득하기 위해 대기할 때 사용한다
- `WAITING`, `TIMED_WAITING` 상태는 스레드가 특정 조건이나 시간 동안 대기할 때 발생하는 상태이다
- `WAITING` 상태는 다양한 상황에서 사용된다. `join()`, `park()`, `Object.wait()`와 같은 메서드 호출 시 `WAITING` 상태가 된다
- `TIMED_WAITING` 또한 `wait(long timeout)`, `join(log millis)`, `parkNanos(ns)` 등과 같이 `WAITING` 메서드와 **서로 짝이 맞게 존재**한다
- `BLOCKED`, `WAITING`, `TIMED_WAITING` 상태 모두 스레드가 대기하며, 실행 스케줄링에 들어가지 않기 때문에, CPU 입장에서는 **실행하지 않는 비슷한 상태**이다
- **인터럽트 :**
    - `BLOCKED` 상태는 인터럽트가 걸려도 대기 상태를 빠져나오지 못한다.
    - `WAITING`, `TIMED_WAITING` 상태는 인터럽트가 걸리면 대기 상태를 빠져나온다. 그래서 `RUNNABLE` 상태로 변한

### Object - wait, notify

- **Object.wait()**
    - 현재 스레드가 가진 `락`을 반납하고 대기(`WAITING`) 한다
    - 현재 스레드를 대기(`WAITING`) 상태로 전환한다. 이 메서드는 현재 스레드가 `synchronized` 블록이나 메서드에서 **락을 소유하고 있을 때만** 호출할 수 있다. 호출한 스레드는 락을 반납하고, **다른 스레드가 해당 락을 회득할 수 있도록 한다.**
- **Object.notify()**
    - 대기 중인 스레드 중 하나를 깨운다
    - 이 메서드는 `synchronized` 블록이나 메서드에서 호출되어야 한다. 깨운 스레드는 락을 닷 ㅣ획득할 기회를 얻게 된다. 만약 대기 중인 스레드가 여러 개라면, 그 중 하나만 깨워진다
- **Object.notifyAll()**
    - 대기 중인 모든 스레드를 깨운다
    - 이 메서드 역시 `synchronized` 블록이나 메서드에서 호출되어야 하며, **모든 대기 중인 스레드가 락을 획득할 수 있는 기회를 얻게 된다**.

### **wait(), notify()의 과정**

- wait()를 호출하면
    - 락을 반납한다
    - 스레드의 상태가 `RUNNABLE` → `WAITING`으로 변경된다
    - 스레드 대기 집합에서 관리된다 (대기중인 스레드가 여러개라면 순서가 보장되지 않는다)
        - 어떤 스레드가 깨워질지는 JVM 스펙에 명시되어 있지 않다. 따라서 **JVM 버전 환경등에 따라서 달라진다**
- 스레드 대기 집합에서 관리되는 스레드는 이후에 다른 스레드가 `notify()`를 통해 스레드 대기 집합에 **신호를 줘야 깨어날 수 있다**
- 깨어난 스레드는 `임계 영역`에 있다. `임계 영역`에 있는 코드를 실행하려면 **락이 필요하다**. 그러므로 스레드는 락을 획득하기 위해 `BLOCKED` 상태로 대기한다. (*임계 영역 안에서는* *2개의 스레드가 실행되면 큰 문제가 발생한다*)
- 이때 `임계 영역`의 코드 처음을 실행하는 것이 아니고 `wait()`를 호출한 부분 부터 실행된다
- `notify()`를 호출한 스레드가 종료되면 그때 `락`을 획득하여 나머지 코드를 실행하게 된다

### 스레드 기아

- 대기(`WAITING`) 상태의 스레드가 실행 순서를 계속 얻지 못해서 실행되지 않는 상황을 **스레드 기아(starvation)** 상태라 한다.
- 스레드 기아를 해결할 수 있는 방법으로는 **모든 스레드를 깨우는** `notifyAll()`를 사용하는 방법이 있지만, 효율성을 떨어질 수 밖에 없다

### Condition

- Condition condition = lock.newCondition()
    - `Condition`은 `ReentrantLock`을 사용하는 스레드가 대기하는 스레드 대기 공간이다
    - 참고로 `Object.wait()`에서 사용한 스레드 대기 공간은 모든 객체 인스턴스 내부에 기본으로 가지고 있다. 반면 `Lock(ReentrantLock)`을 사용하는 경우 이렇게 스레드 대기 공간을 직접 만들어서 사용해야 한다
- **condition.await()**
    - `Object.wait()`와 유사한 기능이다. 지정한 condition에 현재 스레드를 대기(`WAITING`) 상태로 보관한다. 이때 `ReentrantLock` 에서 획득한 락을 반납하고 대기 상태로 `condition`에 보관된다
- **condition.signal()**
    - `Object.notify()`와 유사한 기능이다. 지정한 condition에서 대기중인 스레드를 하나 깨운다. 깨어난 스레드는 `condiition`에서 빠져나온다

### Object.notify() vs Condition.signal()

- **Object.notify()**
    - 대기 중인 스레드 중 임의의 하나를 선택해서 깨운다. 스레드가 깨어나는 순서는 정의되어 있지 않으며, JVM 구현에 따라 다르다. 보통은 먼저 들어온 스레드가 먼저 수행되지만 구현에 따라 다를 수 있다
    - `synchronized` 블록 내에서 모니터 락을 가지고 있는 스레드가 호출해야 한다
- **Condition.signal()**
    - 대기 중인 스레드 중 하나를 깨우며, 일반적으로 FIFO 순으로 깨운다. 보통 `Condition`의 구현은 `Queue` 구조를 사용하기 때문에 FIFO 순서로 깨운다
    - `ReentratLock`을 가지고 있는 스레드가 호출해야 한다

### 스레드의 대기

**synchronized 대기**

- 대기 1 : 락 획득 대기
    - `BLOCKED` 상태로 락 획득 대기
    - `synchronized`를 시작할 때 락이 없으면 대기
    - 다른 스레드가 `synchronized`를 빠져나갈 때 **대기가 풀리며 락 획득 시도**
- 대기 2 : wait() 대기
    - `WAITING` 상태로 대기
    - `wait()`를 호출 했을 때 스레드 대기 집합에서 대기
    - 다른 스레드가 `notify()`를 호출 했을 때 빠져나감
- `synchronized`를 사용한 임계 영역에 들어가려면 모니터 락이 필요하다
- 모니터 락이 없으면 락 대기 집합에 들어가서 `BLOCKED` 상태로 락을 기다린다
- 모니터 락을 반납하면 **락 대기 집합에 있는 스레드 중 하나가 락을 획득**하고 `BLOCKED -> RUNNABLE` 상태가 된다
- `wait()`를 호출해서 스레드 대기 집합에 들어가기 위해서는 **모니터 락이 필요하다**
- 스레드 대기 집합에 들어가면 모니터 락을 반납한다
- 스레드가 `notify()`를 호출하면 스레드 대기 집합에 있는 스레드 중 하나가 스레드 대기 집합을 빠져나간다. 그리고 모니터 락 획득을 시도한다
    - 모니터 락을 획득하면 임계 영역을 수행한다
    - 모니터 락을 획득하지 못하면 **락 대기 집합에 들어가서 `BLOCKED` 상태로 락을 기다린다**

**Lock 대기**

- **대기 1 : ReentrantLock 락 획득 대기**
    - `ReentrantLock` 의 대기 큐에서 관리
    - `WAITING` 상태로 락 획득 대기
    - `lock.lock()`을 호출 했을 때 락이 없으면 대기
    - 다른 스레드가 `lock.unlock()`을 호출 했을 때 대기가 풀리며 락 획득 시도, 락을 획득하면 대기 큐를 빠져나감
- **대기 2 : await() 대기**
    - `Condition.await()`를 호출 했을 때, `condition` 객체의 스레드 대기 공간에서 관리
    - `WAITING` 상태로 대기
    - 다른 스레드가 `condition.signal()`을 호출했을 때 `condition` 객체의 스레드 대기 공간에서 빠져나감

## BlockingQueue

- **데이터 추가 차단** : 큐가 가득 차면 데이터 추가 작업( `put()` )을 시도하는 스레드는 공간이 생길 때까지 차단된다
- **데이터 획득 차단** : 큐가 비어 있으면 획득 작업( `take()` )을 시도하는 스레드는 큐에 데이터가 들어올 때까지 차단된다
- 큐에 스레드가 가득 찼을 경우 생각 할 수 있는 선택지가 있다
    - 예외를 던진다. 예외를 받아서 처리한다
    - 대기하지 않는다. 즉시 `false`를 반환한다
    - 대기한다
    - 특정 시간 만큼만 대기한다
- 이 문제들을 해결하기 위해 `BlockedQueue`는 **각 상황에 맞는 다양한 메서드를 제공한다**
- **Throws Exception - 대기시 예외**
    - **add(e)**: 지정된 요소를 큐에 추가하며, 큐가 가득 차면 `IllegalStateException` 예외를 던진다
    - **remove()**: 큐에서 요소를 제거하며 반환한다. 큐가 비어 있으면 `NoSuchElementException` 예외를 던진다
    - **element()**: 큐의 머리 요소를 반환하지만, 요소를 큐에서 제거하지 않는다. 큐가 비어 있으면
    `NoSuchElementException` 예외를 던진다
- **Special Value - 대기시 즉시 반환**
    - **offer(e)** : 지정된 요소를 큐에 추가하려고 시도하며, 큐가 가득 차면 `false` 를 반환한다
    - **poll()** : 큐에서 요소를 제거하고 반환한다. 큐가 비어 있으면 `null` 을 반환한다
    - **peek()** : 큐의 머리 요소를 반환하지만, 요소를 큐에서 제거하지 않는다. 큐가 비어 있으면 `null` 을 반환한다
- **Blocks - 대기**
    - **put(e)** : 지정된 요소를 큐에 추가할 때까지 대기한다. 큐가 가득 차면 공간이 생길 때까지 대기한다
    - **take()** : 큐에서 요소를 제거하고 반환한다. 큐가 비어 있으면 요소가 준비될 때까지 대기한다
    - **Examine (관찰)**: 해당 사항 없음
- **Times Out - 시간 대기**
    - **offer(e, time, unit)** : 지정된 요소를 큐에 추가하려고 시도하며, 지정된 시간 동안 큐가 비워지기를 기다리다가 시간이 초과되면 `false` 를 반환한다
    - **poll(time, unit)** : 큐에서 요소를 제거하고 반환한다. 큐에 요소가 없다면 지정된 시간 동안 요소가 준비되기를 기다리다가 시간이 초과되면 `null` 을 반환한다
- **Examine (관찰)** : 해당 사항 없음
