# 선착순 쿠폰 발급 서비스

온라인 커머스의 선착순 쿠폰 발급 기능을 제공하는 REST API 서버입니다.

---

## 기술 스택
> Java17, Spring Boot 3.x, Spring WebFlux, MySQL, R2DBC, Redis, Swagger, Gradle

## 아키텍처

**Layered Architecture**

```
src/main/java/com/example/coupon/
├── web/              # Controller, Request/Response DTO
├── service/          # 비즈니스 로직
├── domain/           # Entity, VO, 도메인 예외
├── repository/       # Spring Data R2DBC Repository
└── infra/            # Redis 연동
```

- 계층 간 의존 방향: `web → service → repository / infra`
- 도메인 로직은 `service` 계층에 집중
- DTO ↔ Entity 변환은 각 계층 경계에서 처리

---

## API 목록

> 인증은 API Gateway / Reverse Proxy에서 처리하며, 이 서버는 아래 헤더를 신뢰합니다.
> - 일반 사용자: `x-user-id`
> - 운영자: `x-admin-id`

| Method | Path | 행위자 | 설명 |
|---|---|---|---|
| `POST` | `/admin/coupon-schemes` | Admin | 쿠폰 스킴 등록 |
| `PATCH` | `/admin/coupon-schemes/{id}/deactivate` | Admin | 쿠폰 스킴 비활성화 |
| `GET` | `/coupons/available` | User | 발급 가능 쿠폰 목록 조회 (내 발급 여부 포함) |
| `POST` | `/coupons/{schemeId}/issue` | User | 쿠폰 발급 요청 |

---

## Task 체크리스트

### Phase 1 - 기반 세팅
- [ ] 프로젝트 구조 생성 (패키지 구성)
- [ ] MySQL 스키마 설계 및 R2DBC 설정
- [ ] Redis 연결 설정
- [ ] 공통 예외 처리 / 응답 포맷 정의

### Phase 2 - 어드민 기능
- [ ] 쿠폰 스킴 등록 API
- [ ] 쿠폰 스킴 비활성화 API

### Phase 3 - 유저 기능
- [ ] 발급 가능 쿠폰 목록 조회 API (내 발급 여부 포함)
- [ ] 쿠폰 발급 API (동시성 제어 포함)

### Phase 4 - 마무리
- [ ] Swagger 설정
- [ ] 동시성 테스트 (CountDownLatch / k6)
- [ ] README 최종 정리
