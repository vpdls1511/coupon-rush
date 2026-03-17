package me.ngyu.couponrush;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled("DB/Redis 연결이 필요한 통합 테스트 — 인프라 환경이 갖춰진 경우에만 실행")
@SpringBootTest
class CouponRushApplicationTests {

  @Test
  void contextLoads() {
  }

}
