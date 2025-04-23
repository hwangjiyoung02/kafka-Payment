## Kafka를 활용한 대량의 트랜잭션을 처리하는 이커머스 주문 및 결제 시스템 개발
-  DDD 기반의 헥사고날 아키텍처로 Applicaiton 설계 및 개발<br />
-  Kafka로 대용량 트랜잭션(주문 및 결제)을 실시간 처리 기능 설계 및 개발<br />

## 환경구성
![Overal_Architecture](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/e40f3347-48be-4140-8798-0a20c1512264)
1. JDK 21
2. Spring Boot 3.2.4
4. MySQL 8.0 based Docker
5. Kafka Cluster based Docker


## Postman Public API URL
## 이커머스 주문/결제 시스템
PG사 TEST API 연동(+결제위젯)
### 결제 기능 개발 - TDD 기반 카드 결제 승인 기능
![스크린샷 2024-06-07 오후 3 23 11](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/95cfcff2-274a-458e-a849-994205355ce6)
### 결제 기능 개발 - TDD 기반 카드 결제 취소 기능
![스크린샷 2024-06-07 오후 4 20 26](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/90796985-832b-4afc-a948-266161a92b79)
### 결제 기능 개발 - TDD 기반 카드 결제 정산 기능
![스크린샷 2024-06-07 오후 4 30 57](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/263c998c-9cdd-42c5-a825-8c01ad5ea69a)
## 정산 기능 고도화 with Kafka
![kafka-excample-arch](https://github.com/user-attachments/assets/fc1a3db7-750b-4ed1-b2f4-5a7f6eaca064)