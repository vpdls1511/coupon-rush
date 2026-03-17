package me.ngyu.couponrush.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("coupon_issuance")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponIssuance {

    @Id
    private Long id;
    private Long schemeId;
    private Long userId;
    @CreatedDate
    private LocalDateTime issuedAt;
}
