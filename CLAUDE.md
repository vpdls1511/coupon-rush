# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

선착순 쿠폰 발급 REST API 서버 (First-Come-First-Served Coupon Issuance System)

- **Language/Framework:** Java 17, Spring Boot 3.x (WebMVC)
- **Database:** MySQL via Spring Data R2DBC
- **Cache:** Redis
- **Build Tool:** Gradle (wrapper: `./gradlew`)
- **Base Package:** `me.ngyu.couponrush`

## Commands

```bash
./gradlew build          # Build
./gradlew bootRun        # Run application
./gradlew test           # Run all tests
./gradlew test --tests "me.ngyu.couponrush.SomeTest"  # Run a single test class
./gradlew clean build    # Clean build
```

Run with local profile: set `spring.profiles.active=local` or pass `--spring.profiles.active=local`.

## Architecture

Strict layered architecture with one-directional dependencies:

```
web → service → (repository | infra)
```

- **`web/`** — Controllers, Request/Response DTOs
- **`service/`** — Business logic
- **`domain/`** — Entities, Value Objects, Domain Exceptions
- **`repository/`** — Spring Data R2DBC repositories
- **`infra/`** — Redis integration

DTO ↔ Entity conversions happen at layer boundaries. No cross-layer skipping.

## Authentication Model

Authentication is handled upstream by an API Gateway/Reverse Proxy. This server trusts:
- `x-user-id` header — identifies the requesting user
- `x-admin-id` header — identifies an admin user

No JWT validation occurs within this service.

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/admin/coupon-schemes` | Admin: create coupon scheme |
| PATCH | `/admin/coupon-schemes/{id}/deactivate` | Admin: deactivate scheme |
| GET | `/coupons/available` | User: list available coupons with issuance status |
| POST | `/coupons/{schemeId}/issue` | User: request coupon (FCFS with concurrency control) |

## Git & Branch Convention

See `docs/CONVENTION.md` for full details.

- **Branches:** `main` (production), `develop` (integration), `feature/*`, `fix/*`
- **No direct commits** to `main` or `develop` — all changes via PR with review
- **Squash merge** into `main`/`develop`
- **Commit types:** `feat`, `fix`, `refactor`, `docs`, `test`, `chore`
- Feature branches are short-lived (1–3 days)

## Team & Agent Workflow

See `docs/TEAM.md` and `docs/agent/SAMPLE.md`.

- **Manager (Opus):** Business analysis, design review, documentation
- **Developers:** Architecture, implementation, code review, testing
- Tasks must be granular with task-by-task commits
- Plans must be approved before implementation begins
- Work logs follow the format in `docs/agent/SAMPLE.md` (saved under `docs/agent/YYYY-MM-DD - TASK_X.md`)
- Task definitions live in `docs/task/`

## Implementation Phases (from README)

1. **Phase 1:** Project structure, MySQL schema, Redis config, global exception handling
2. **Phase 2:** Admin features (coupon scheme CRUD)
3. **Phase 3:** User features — coupon listing and FCFS issuance with concurrency control
4. **Phase 4:** Swagger docs, load testing, final documentation

Concurrency control on the issuance endpoint is a core requirement.
