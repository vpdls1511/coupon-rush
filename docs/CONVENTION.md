# Git Commit Convention
> type 으로 행동을 나타내고, 간결한 메시지로 내용을 알 수 있어야 한다.

## type (필수)
- feat: 기능 추가
- fix: 버그 수정
- refactor: 리팩토링 (동작 변경 없음)
- docs: 문서 수정
- test: 테스트 코드
- chore: 설정, 빌드 등 기타

## 규칙
메시지는 핵심만 간결하게 작성해야한다.


# Branch Strategy
> 기능은 feature 단위로 나뉘며, main 브랜치에는 PR이 마무리 되지 않은 상태로 머지를 할 수 없다.
> 브랜치 전략은 Git Flow를 기반으로 한다.

## Git Flow
Git Flow는 Git을 기반으로 한 브랜칭 모델로, 소프트웨어 개발에서 기능 개발, 릴리스, 유지보수를 체계적으로 관리하기 위해 고안된 워크플로이다. 2010년 Vincent Driessen가 제안했으며, 이후 많은 팀이 표준적인 Git 브랜치 전략으로 채택했다.

핵심 개념
- 메인 브랜치(main, develop): 제품 배포용(main)과 개발 통합용(develop)으로 구분
- 기능 브랜치(feature): 새로운 기능 개발용, develop에서 분기
- 릴리스 브랜치(release): 배포 전 안정화 및 테스트 단계
- 핫픽스 브랜치(hotfix): 긴급 수정 사항을 main에서 직접 처리

## 머지 전략
- 모든 main, develop 브랜치 머지는 PR을 통해서만 가능하다.
- 최소 1명 이상의 리뷰와 테스트 통과를 필수로 한다.
- feature 브랜치는 1~3일 이내의 짧은 수명을 유지한다.
- merge 후 브랜치는 즉시 삭제한다.
- squash merge를 기본 전략으로 사용한다.
