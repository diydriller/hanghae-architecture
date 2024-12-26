## ERD 설계
![ERD](https://github.com/user-attachments/assets/fd7580f3-0537-4578-a339-80cb4aa376b7)
* lecture
  * 특강의 정보를 담는 테이블
* lecture_schedule
  * 같은 특강이라도 날짜별로 달라지는 정보를 담는 테이블
  * lecture 테이블과 lecture_schedule 테이블 기준 N:1의 관계를 가진다.
  * user 테이블과 lecture_schedule 테이블 기준 N:1의 관계를 가진다.
* enrollment
  * 사용자가 특강 신청시 등록되는 테이블
  * user 테이블과 enrollment 테이블 기준 N:1의 관계를 가진다.
  * lecture_schedule 테이블과 enrollment 테이블 기준 N:1의 관계를 가진다.
* user
  * 사용자 정보를 담는 테이블

---

## 동시성 문제
* 사용자가 특강을 신청할 때 특강의 정원보다 인원이 더 많은지 확인하기 위해서
  lecture_schedule 테이블에 enrollment_count 필드를 두었다. 물론 필드를 
  두지 않고 매번 enrollment 테이블과의 join을 통해 인원을 셀 수 있지만 오버헤드가
  발생한다고 판단했다.
* 사용자가 특강을 신청하면 lecture_schedule을 조회하고 enrollment_count 필드를
  확인해서 capacity보다 더 작은지 확인한다. enrollment_count가 capacity보다
  더 작다면 enrollment_count를 하나 늘린다. 이때 동시성 문제가 발생할 수 있는 부부은
  enrollment_count 필드를 조회해서 capacity보다 작으면 하나 늘리는 부분이다.
  동시에 여러 유저가 같은 enrollment_count 필드에 접근할 수 있기 때문이다.
* 요구사항에서 다수의 인스턴스 어플리케이션이 동작해도 문제가 없도록 해야한다고 적혀있어서
  데이터베이스 lock을 사용하기로 했다.

---

## optimistic lock
* optimistic lock은 자원에 lock을 걸지 않고 application level에서 lock을 구현한다.
  version과 같은 별도의 컬럼을 추가하고 해당 컬럼의 변경여부로 트랜잭션의 충돌을 감지한다.

## pessimistic lock
* pessimistic lock은 자원에 lock을 걸어서 다른 트랜잭션의 접근을 막는다.
* lock은 shared lock과 exclusive lock이 있다. shared lock은 다른 shared lock만 허용하고
  exclusive lock은 다른 lock을 허용하지 않는다.

--- 

## 동시성 제어
* pessimistic lock의 exclusive lock을 사용해서 동시성 문제를 해결했다. 

  