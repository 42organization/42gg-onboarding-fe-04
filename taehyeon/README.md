# 1일차 

## HTTP METHOD

### GET(read)

- 리소스 조회 메서드
- 쿼리스트링을 통해 데이터를 전달
    - ex) localhost/members/1?username=inpa&height=200
- 쿼리스트링 외에 메시지 바디를 사용해서 데이터를 전달할 수 있지만 서버에서 따로 구성해야 되기 때문에 지원하지않는 곳이 많아 권장하지 않음
- POST를 사용해도 되지만 캐싱이 불가능해서 GET을 사용

### POST(create)

- 전달한 데이터 처리/생성 요청 메서드
- 메시지 바디를 통해 데이터를 전달
- 만약 데이터 조회를 위해 JSON으로 데이터를 넘겨야 하는 경우 POST 사용

### PUT(update)

- 리소스를 수정하는 메서드
- 요청 메시지에 리소스가 있으면 덮어쓰고, 엎으면 새로 생성
    - ex) /member/100
- 데이터를 대체해야 하기 때문에, 리소스의 구체적인 경로를 보내줘야 함
    - ex) POST /members 멥버 새로 추가 PUT /members/1 1번 멥버 수정

### PATCH(update)

- 리소스의 부분을 수정하는 메서드
- 만약 PATCH를 지원하지 않는 서버라면 POST 사용

### DELETE(delete)

- 리소스를 제거하는 메서드

### HEAD

- GET과 동일하지만 서버에서 Body를 Return 하지 않음
- 응답의 상태 코드만 확인하는 경우 Resource를 받지 않고 오직 찾기만 원할때 사용(검사용)
- 서버의 응답 헤더를 확인해서 Resource가 수정 되었는지 확인 가능

### TRACE

- 검사용 메서드
- 서버에 도달 했을 때의 최종 패킷의 요청 패킷 내용을 받을 수 있음
- 요청의 최종 수신자는 반드시 송신자에게 수신한 메시지와 200을 Body로 보냄
- 최초 Clinet의 요철에는 Body가 포함될 수 없음

### OPTION

- 예비 요청에 사용되는 메서드
    - 예비 요청이란 본 요청을 하기 전에 안전한지 미리 검사하는 것
- 서버의 지원 가능한 HTTP 메서드와 출처를 받아 CORS 정책을 검사함

#### CORS(Cross Origin Resource Sharing)

- 교차 출처 리소스 공유 정책으로 다른 출처의 리소스 공유에 대한 허용/비허용 정책
- 출처는 프로토콜,도메인,포트만 비교
- 기본 동작 과정
    1. 클라이언트가 HTTP요청(예비 요청) 헤더에 Origin을 담아 서버에 전달
    2. 서버가 응답헤더에 Access-Control-Allow-Origin을 담아 클라이언트에 전달
    3. 클라이언트가 Origin과 Access-Control-Allow-Origin를 비교
- 프록시 서버를 사용해서 해결 가능

## HTTP 상태 코드

### 1XX(정보 제공)

- 임시 응답으로 현재 클라이언트의 요청까지는 처리되었으니 계속 진행하라는 의미(HTTP 1.1 버전부터 추가)
- 100 계속 진행
- 101 프로토콜 전환
- 102 처리중

### 2XX(성공)

- 클라이언트의 요청이 서버에서 성공적으로 처리
- *200 성공*
- *201 요청이 처리되어 리소가 생성*
- *202 요청은 접수하였지만, 처리가 완료되지 않음*
- 203 응답 헤더가 오리지널 서버로부터 제공된 것이 아니라 신뢰할수 없음
- 204 처리를 성공하였지만, 돌려줄 콘텐츠가 없음
- 205 

### 3XX(리다이렉션)

- 완전한 처리를 위해서 추가 동작이 필요한 경우
- 주로 서버의 주소 또는 요청한 URL의 웹 문서가 이동되었으니 그 주소로 다시 시도하라는 의미

### 4XX(클라이언트 에러)

- 없는 페이지를 요청하는 등 클라이언트의 요청 메시지 내용이 잘못된 경우를 의미

### 5XX(서버 에러)

- 서버 사정으로 메시지 처리에 문제가 발생한 경우
- 서버의 부하, DB 처리 과정 오류, 서버에세 Exception이 발생하는 경우

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