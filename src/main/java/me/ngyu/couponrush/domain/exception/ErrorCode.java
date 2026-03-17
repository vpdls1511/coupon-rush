package me.ngyu.couponrush.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND("COUPON_001", "쿠폰 스킴을 찾을 수 없습니다.", 404),
    ALREADY_ISSUED("COUPON_002", "이미 발급된 쿠폰입니다.", 409),
    SOLD_OUT("COUPON_003", "쿠폰이 모두 소진되었습니다.", 409),
    DEACTIVATED("COUPON_004", "비활성화된 쿠폰 스킴입니다.", 400);

    private final String code;
    private final String message;
    private final int httpStatus;
}
