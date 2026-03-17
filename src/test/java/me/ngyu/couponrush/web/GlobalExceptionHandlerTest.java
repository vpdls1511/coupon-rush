package me.ngyu.couponrush.web;

import me.ngyu.couponrush.domain.exception.BusinessException;
import me.ngyu.couponrush.domain.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void BusinessException_NOT_FOUND는_404_응답을_반환한다() {
        BusinessException exception = new BusinessException(ErrorCode.NOT_FOUND);

        ResponseEntity<ApiResponse<Void>> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getError().getCode()).isEqualTo("COUPON_001");
        assertThat(response.getBody().getError().getMessage()).isEqualTo("쿠폰 스킴을 찾을 수 없습니다.");
    }

    @Test
    void BusinessException_ALREADY_ISSUED는_409_응답을_반환한다() {
        BusinessException exception = new BusinessException(ErrorCode.ALREADY_ISSUED);

        ResponseEntity<ApiResponse<Void>> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(409);
        assertThat(response.getBody().getError().getCode()).isEqualTo("COUPON_002");
    }

    @Test
    void BusinessException_SOLD_OUT는_409_응답을_반환한다() {
        BusinessException exception = new BusinessException(ErrorCode.SOLD_OUT);

        ResponseEntity<ApiResponse<Void>> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(409);
        assertThat(response.getBody().getError().getCode()).isEqualTo("COUPON_003");
    }

    @Test
    void BusinessException_DEACTIVATED는_400_응답을_반환한다() {
        BusinessException exception = new BusinessException(ErrorCode.DEACTIVATED);

        ResponseEntity<ApiResponse<Void>> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode().value()).isEqualTo(400);
        assertThat(response.getBody().getError().getCode()).isEqualTo("COUPON_004");
    }

    @Test
    void 처리되지_않은_예외는_500_응답을_반환한다() {
        Exception exception = new RuntimeException("예상치 못한 오류");

        ResponseEntity<ApiResponse<Void>> response = handler.handleException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
        assertThat(response.getBody().getError().getCode()).isEqualTo("INTERNAL_ERROR");
        assertThat(response.getBody().getError().getMessage()).isEqualTo("서버 내부 오류가 발생했습니다.");
    }
}
