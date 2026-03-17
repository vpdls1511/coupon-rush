package me.ngyu.couponrush.repository;

import me.ngyu.couponrush.domain.CouponScheme;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CouponSchemeRepository extends ReactiveCrudRepository<CouponScheme, Long> {

    Flux<CouponScheme> findAllByActiveTrue();
}
