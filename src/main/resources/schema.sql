CREATE TABLE IF NOT EXISTS coupon_scheme
(
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    total_quantity  INT          NOT NULL,
    issued_quantity INT          NOT NULL DEFAULT 0,
    active          TINYINT(1)   NOT NULL DEFAULT 1,
    created_at      DATETIME(6)  NOT NULL,
    updated_at      DATETIME(6)  NOT NULL
);

CREATE TABLE IF NOT EXISTS coupon_issuance
(
    id        BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
    scheme_id BIGINT      NOT NULL,
    user_id   BIGINT      NOT NULL,
    issued_at DATETIME(6) NOT NULL,
    CONSTRAINT fk_issuance_scheme FOREIGN KEY (scheme_id) REFERENCES coupon_scheme (id),
    CONSTRAINT uq_scheme_user UNIQUE (scheme_id, user_id)
);

CREATE INDEX IF NOT EXISTS idx_issuance_scheme_id ON coupon_issuance (scheme_id);
