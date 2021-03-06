## 한국산업기술대학교 시간표 API 관리 어플리케이션
한국산업기술대학교 강의 API 관리 어플리케이션 만들기

<!--
[old project go](https://github.com/doorisopen/kpu-schedule/tree/64eab9c9a2c1f03a27407228bf0ed4361a17d07f)
-->

* start: 2019.11.30 
* refactoring: 2020.04.16

## 프로젝트 환경
* Spring Boot 2.2.6
* Gradle
* JPA
* Querydsl
* junit5
* H2, MySQL
* lombok
* thymeleaf

## Info
![preview](images/preview.JPG)

## 패키지 구조
```
src/main
│
├─/java/kpuapi/kpulecture
│   ├─api
│   │  └─dto
│   ├─config 
│   │  └─auth 
│   ├─controller 
│   │  └─form
│   ├─domain 
│   │  ├─posts
│   │  ├─school 
│   │  └─user
│   ├─scraping
│   └─service 
│      ├─posts
│      └─school
└─/resources
```

## TODO
* [x] 강의 데이터 웹 스크래핑
* [x] 프로젝트 환경설정
  + [x] View 환경
  + [x] H2 데이터베이스
  + [x] JPA, DB 설정, 동작확인
* [x] 도메인 분석 설계
  + [x] 도메인 모델과 테이블 설계
  + [x] 엔티티 클래스 개발
* [x] 애플리케이션 구현
  + [x] 교수 기능
    - [x] 교수 등록
    - [x] 교수 조회
    - [x] 기능 테스트 코드
  + [x] 강의 기능 
    - [x] 강의 등록 
    - [x] 강의 수정
    - [x] 강의 조회
    - [x] 기능 테스트 코드
* [x] 웹 계층 개발
  + [x] 교수 기능
  + [x] 강의 기능
  + [x] 전공 기능
* [ ] API 개발
  + [x] 강의 조회 API
  + [ ] 강의 검색
  + [x] API 사용량 조회용 API
  + [x] 게시판 REST API
    - [x] 등록
    - [x] 조회
    - [x] 수정
    - [x] 삭제
* [x] API Usage Chart
  + [x] Data Binding
* [ ] 소셜 로그인
  + [x] 구글
  + [ ] 네이버
  + [x] 테스트 케이스 오류 수정
* [x] 배포: 서버 중지
  + [x] Travis CI/CD
  + [x] Nginx 무중단배포