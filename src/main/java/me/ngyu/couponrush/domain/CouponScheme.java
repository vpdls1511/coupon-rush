package me.ngyu.couponrush.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("coupon_scheme")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponScheme {

    @Id
    private Long id;
    private String name;
    private int totalQuantity;
    private int issuedQuantity;
    private boolean active;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static CouponScheme create(String name, int totalQuantity) {
        CouponScheme scheme = new CouponScheme();
        scheme.name = name;
        scheme.totalQuantity = totalQuantity;
        scheme.issuedQuantity = 0;
        scheme.active = true;
        return scheme;
    }
}
