# 42gg-onboarding-be-04

### 개발 환경 세팅

- IDE는 IntelliJ 추천
- IntelliJ Community 버전에서 Ultimate 버전 업그레이드 하기 (학생 라이센스 신청)
    
     Email address : 대학 재학생 혹은 졸업생일 경우 학교 이메일 또는 42계정 이메일(”intraId”@student.42seoul.kr) 사용 가능
    
    https://goddaehee.tistory.com/215
    
- MySQL 설치
    - MySQLWorkbench 이용
    - MySQL workbench에서 외부 접속하기  
        [https://velog.io/@woals4815/MySQL-외부-접속-하기MySQLWorkbench-이용]  
        
- redis 설치

</br>

## 공통 조건
- 온보딩 프로젝트는 개인 계정으로 fork하여 진행하고 PR로 제출합니다.
- git / github / code 컨벤션은 42gg notion에 있는 자료를 적극 반영 합니다.  
</br>

## EX00 Scheduler 사용해보기
- `org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler` 를 이용해서 매 분마다 log를 출력하는 프로그램 만들어보기

</br>

## EX01 로그백 파일 확인

- 최대 30일 로그 기록, 각각 최대 용량 400(all), 500(myLog), 100(error)MB
    
    ```jsx
    logs
      ㄴ all.log //당일 전체로그 기록
    	ㄴ error.log // 당일 에러로그 기록
    	ㄴ myLog.log // 당일 사용자커스텀로그(함수 호출,종료 순서대로 기록) 기록
    	ㄴ allLogDays
    		 ㄴ //일자별 전체로그 기록
      ㄴ errorLogDays
    		 ㄴ //일자별 에러로그 기록
      ㄴ myLogDays
    		 ㄴ //일자별 사용자커스텀로그(함수 호출,종료 순서대로 기록) 기록
    ```
    
- 로그백 파일을 확인을 통한 현재 로그 출력 방식 이해하기
- 참고자료
    
    [[Spring] 스프링 부트에서 로그(Log) 사용하기 - Logback (Sync, AsyncAppender)](https://loosie.tistory.com/829)

