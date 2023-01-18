# Eats-Mate-Backend
License: GPL v3

## 프로젝트 개요

늘어가는 1인가구와 더불어 혼자하는 것들 이를테면, 혼밥,혼행,혼영 등이 당연하게 받아들여지는 시대가 왔습니다.
저희는 이러한 1인가구의 증가라는 변화에 맞추어 `1인가구의 혼행` 이라는 주제로 맛집과 여행정보 소개 웹사이트를 구현하게 되었습니다.


## 프로젝트 아키텍쳐

<img width="714" alt="image" src="https://user-images.githubusercontent.com/42714724/213215167-7055a068-95a1-405e-9b8e-8a6c1be154b5.png">

1.유레카 서버 - client 서버들을 관리하는 서버

2.api-gateway - 외부에서 접근하는 서버, 클라이언트단에서 api-gateway로 요청을 하면 api-gateway에서 적합한 micro service에게 전달해준다. 해당 서버에 cors와 관련된 설정들을 함.

3.micro-service - 프론트에서 날라오는 요청들을 처리하는 마이크로 서비스, 핵심 기능인 유저,지도,리뷰 세개의 서비스로 구현함.


## 프로젝트 ERD

<img width="715" alt="image" src="https://user-images.githubusercontent.com/42714724/213216138-0aa6277a-fe6c-4e58-840b-75ef8444caf2.png">


## 사용기술

`Spring Boot`
`Spring Cloud`
`MaraiDB`
