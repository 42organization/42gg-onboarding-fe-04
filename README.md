## 1일차 
- IDE는 IntelliJ 사용
- IntelliJ Community 버전에서 Ultimate 버전 업그레이드 하기 (학생 라이센스 신청)
    - Email address : 대학 재학생 혹은 졸업생일 경우 학교 이메일 또는 42계정 이메일(”intraId”@student.42seoul.kr) 사용 가능 [https://goddaehee.tistory.com/215](https://goddaehee.tistory.com/215)
- MySQL 설치
- DataGrip 설치 [https://luminitworld.tistory.com/82](https://luminitworld.tistory.com/82)
- redis 설치
    - macOS [https://herojoon-dev.tistory.com/170](https://herojoon-dev.tistory.com/170)
    - windows [https://inpa.tistory.com/entry/REDIS-📚-Window10-환경에-Redis-설치하기](https://inpa.tistory.com/entry/REDIS-%F0%9F%93%9A-Window10-%ED%99%98%EA%B2%BD%EC%97%90-Redis-%EC%84%A4%EC%B9%98%ED%95%98%EA%B8%B0)

- HTTP METHOD [https://inpa.tistory.com/entry/WEB-🌐-HTTP-메서드-종류-통신-과정-💯-총정리](https://inpa.tistory.com/entry/WEB-%F0%9F%8C%90-HTTP-%EB%A9%94%EC%84%9C%EB%93%9C-%EC%A2%85%EB%A5%98-%ED%86%B5%EC%8B%A0-%EA%B3%BC%EC%A0%95-%F0%9F%92%AF-%EC%B4%9D%EC%A0%95%EB%A6%AC)
- HTTP Status Code [https://hongong.hanbit.co.kr/http-상태-코드-표-1xx-5xx-전체-요약-정리/](https://hongong.hanbit.co.kr/http-%EC%83%81%ED%83%9C-%EC%BD%94%EB%93%9C-%ED%91%9C-1xx-5xx-%EC%A0%84%EC%B2%B4-%EC%9A%94%EC%95%BD-%EC%A0%95%EB%A6%AC/)
- HTTP 헤더 [https://gmlwjd9405.github.io/2019/01/28/http-header-types.html](https://gmlwjd9405.github.io/2019/01/28/http-header-types.html)
- 크롬 개발자도구 (Network, Application 위주로) [https://velog.io/@remon/개발자-도구의-기능을-뜯어보자](https://velog.io/@remon/%EA%B0%9C%EB%B0%9C%EC%9E%90-%EB%8F%84%EA%B5%AC%EC%9D%98-%EA%B8%B0%EB%8A%A5%EC%9D%84-%EB%9C%AF%EC%96%B4%EB%B3%B4%EC%9E%90)
- REST API 란 무엇인가?
- 쿠키와 세션, JWT토큰
- MVC, MVC2
- 인프라
    - HTTPS
    - DNS 서버
    - CI/CD
- 깃 컨벤션 숙지하기
    - [https://www.notion.so/Github-convention-256a505f743a4b4285866dba6851cfc5](https://www.notion.so/Github-convention-256a505f743a4b4285866dba6851cfc5?pvs=21)
- 각자 intraid으로 된 브랜치를 만든 후 intraid로 폴더를 만들고 1일차 md로 작성해서 pr


## 2일차

- RDB, RDBMS
- RDB와 NoSql의 차이
- 테이블, 컬럼, 스키마
- 데이터 모델링 [https://opentutorials.org/course/3883/25259](https://opentutorials.org/course/3883/25259)
    - 데이터 정규화
- 트랜젝션(Transection)
- DB의 무결성
- sql문
- DB 직접 연결 해보기
    - datagrip으로 연동 해보기
- 2일차 md로 작성해서 pr


## 3일차

- @Embedded, @Embeddable
- 연관 관계 설정
    - @OneToOne, @ManyToOne, @ManyToMany, @OneToMany
    - @ManyToMany의 단점
    - 중간 테이블
    - 연관관계의 주인
    - PK, FK
- ERD작성
    - 학생들이 수강신청을 하는 홈페이지를 만들 예정입니다.
        - 학생은 이름과 비밀번호를 필수로 가지고 있습니다.
        - 강의는 교수명, 강의이름, 현재 신청인원을 필수로 가지고 있습니다.
        - 강의를 신청하려면 학생 이름과 비밀번호를 입력해야합니다.
- 작성한 ERD를 [https://dbdiagram.io/](https://dbdiagram.io/) 로 만들어서 제출
- 3일차 md으로 작성해서 pr
- erd는 사진으로 제출


## 4 ~ 5일차

- Junit5
- 개발방법론(TDD, BDD, DDD, ATDD)
- 선택한 개발론에 맞게 API 명세 작성
- 코드 컨벤션 숙지하기
    - [https://www.notion.so/c0425406535c47df89714df16b585f19?pvs=4](https://www.notion.so/c0425406535c47df89714df16b585f19?pvs=21)
- 명세는 사진으로 제출


## 8 ~ 9일차

- @Entitiy, @Dto(request, response), @Controller, @Service @Repository, @Valid
- Exception 처리
- 빌더 패턴
    - 빌더패턴을 사용하지 않고 ToEntity 만들기
- 안간단한 수강신청 게시판 만들기
    - 학생 등록하기
    - 학생 중퇴하기
        - 한 학생은 4개까지 수강신청을 할 수 있습니다.
        - 학생은 16개의 강의를 수강완료할 경우 졸업합니다.
        - 학생은 16개의 강의를 초과하여 수강완료할 수 없습니다.
    - 강의 신청하기
        - 졸업한 학생은 강의를 신청할 수 없습니다.
        - 한 강의는 10명까지 신청할 수 있습니다.
    - 강의 신청 취소하기
    - 강의 등록하기
    - 강의 수정하기
    - 강의 삭제하기
    - 강의 마감하기 (강의는 마감과 동시에 수강완료로 가정합니다.)
    - 졸업자 명단 가져오기
    - 졸업자 명단 페이지네이션
- 개인별 코드 작성


## 10일차

- 프록시 객체
    - findbyall, getbyall
- Join 개념 파악
    - Cross Join, Inner Join, Outer Join, Self Join, Fetch Join 등등
- cross join 카디션 곱은 언제 생기는가
- N + 1문제
- 작성한 코드 리팩토링


## 11 ~ 12일차

- Postman
    - Postman 사용법
    - 테스트해서 API마다 응답 헤더 확인
    - ../return 제출할 때 같이 제출
- 개인별 테스트 코드 작성 및 확인
- 제출
