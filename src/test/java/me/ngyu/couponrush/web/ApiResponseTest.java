package me.ngyu.couponrush.web;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void success_데이터와_함께_생성된다() {
        String data = "테스트 데이터";
        ApiResponse<String> response = ApiResponse.success(data);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(data);
        assertThat(response.getError()).isNull();
    }

    @Test
    void success_null_데이터로_생성된다() {
        ApiResponse<Void> response = ApiResponse.success(null);

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isNull();
        assertThat(response.getError()).isNull();
    }

    @Test
    void error_코드와_메시지로_생성된다() {
        ApiResponse<Void> response = ApiResponse.error("COUPON_001", "쿠폰 스킴을 찾을 수 없습니다.");

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getData()).isNull();
        assertThat(response.getError()).isNotNull();
        assertThat(response.getError().getCode()).isEqualTo("COUPON_001");
        assertThat(response.getError().getMessage()).isEqualTo("쿠폰 스킴을 찾을 수 없습니다.");
    }

    @Test
    void error_ErrorDetail_구조가_올바르다() {
        ApiResponse<Void> response = ApiResponse.error("INTERNAL_ERROR", "서버 내부 오류가 발생했습니다.");

        ApiResponse.ErrorDetail errorDetail = response.getError();
        assertThat(errorDetail.getCode()).isEqualTo("INTERNAL_ERROR");
        assertThat(errorDetail.getMessage()).isEqualTo("서버 내부 오류가 발생했습니다.");
    }
}
