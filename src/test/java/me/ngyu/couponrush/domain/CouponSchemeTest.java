package me.ngyu.couponrush.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CouponSchemeTest {

    @Test
    void create_정상적으로_생성된다() {
        CouponScheme scheme = CouponScheme.create("테스트 쿠폰", 100);

        assertThat(scheme.getName()).isEqualTo("테스트 쿠폰");
        assertThat(scheme.getTotalQuantity()).isEqualTo(100);
        assertThat(scheme.getIssuedQuantity()).isEqualTo(0);
        assertThat(scheme.isActive()).isTrue();
        assertThat(scheme.getId()).isNull();
        assertThat(scheme.getCreatedAt()).isNull();
        assertThat(scheme.getUpdatedAt()).isNull();
    }

    @Test
    void create_다른_이름과_수량으로_생성된다() {
        CouponScheme scheme = CouponScheme.create("여름 할인", 500);

        assertThat(scheme.getName()).isEqualTo("여름 할인");
        assertThat(scheme.getTotalQuantity()).isEqualTo(500);
        assertThat(scheme.getIssuedQuantity()).isEqualTo(0);
        assertThat(scheme.isActive()).isTrue();
    }
}
