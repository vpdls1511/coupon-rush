package me.ngyu.couponrush.domain.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class BusinessExceptionTest {

    @ParameterizedTest
    @EnumSource(ErrorCode.class)
    void BusinessException_모든_ErrorCode로_생성된다(ErrorCode errorCode) {
        BusinessException exception = new BusinessException(errorCode);

        assertThat(exception.getErrorCode()).isEqualTo(errorCode);
        assertThat(exception.getMessage()).isEqualTo(errorCode.getMessage());
    }

    @Test
    void ErrorCode_NOT_FOUND_속성이_올바르다() {
        ErrorCode code = ErrorCode.NOT_FOUND;

        assertThat(code.getCode()).isEqualTo("COUPON_001");
        assertThat(code.getMessage()).isEqualTo("쿠폰 스킴을 찾을 수 없습니다.");
        assertThat(code.getHttpStatus()).isEqualTo(404);
    }

    @Test
    void ErrorCode_ALREADY_ISSUED_속성이_올바르다() {
        ErrorCode code = ErrorCode.ALREADY_ISSUED;

        assertThat(code.getCode()).isEqualTo("COUPON_002");
        assertThat(code.getMessage()).isEqualTo("이미 발급된 쿠폰입니다.");
        assertThat(code.getHttpStatus()).isEqualTo(409);
    }

    @Test
    void ErrorCode_SOLD_OUT_속성이_올바르다() {
        ErrorCode code = ErrorCode.SOLD_OUT;

        assertThat(code.getCode()).isEqualTo("COUPON_003");
        assertThat(code.getMessage()).isEqualTo("쿠폰이 모두 소진되었습니다.");
        assertThat(code.getHttpStatus()).isEqualTo(409);
    }

    @Test
    void ErrorCode_DEACTIVATED_속성이_올바르다() {
        ErrorCode code = ErrorCode.DEACTIVATED;

        assertThat(code.getCode()).isEqualTo("COUPON_004");
        assertThat(code.getMessage()).isEqualTo("비활성화된 쿠폰 스킴입니다.");
        assertThat(code.getHttpStatus()).isEqualTo(400);
    }

    @Test
    void BusinessException은_RuntimeException을_상속한다() {
        BusinessException exception = new BusinessException(ErrorCode.NOT_FOUND);

        assertThat(exception).isInstanceOf(RuntimeException.class);
    }
}
