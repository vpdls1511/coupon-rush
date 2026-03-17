package me.ngyu.couponrush.repository;

import me.ngyu.couponrush.domain.CouponIssuance;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CouponIssuanceRepository extends ReactiveCrudRepository<CouponIssuance, Long> {

    Mono<Boolean> existsBySchemeIdAndUserId(Long schemeId, Long userId);

    Flux<CouponIssuance> findAllBySchemeId(Long schemeId);
}
