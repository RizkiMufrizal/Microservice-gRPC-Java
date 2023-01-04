CREATE TABLE IF NOT EXISTS tb_end_point
(
    id               VARCHAR(36)        NOT NULL PRIMARY KEY,
    backend          VARCHAR(10)        NOT NULL,
    backend_function VARCHAR(15)        NOT NULL,
    url              VARCHAR(200)       NOT NULL,
    method           VARCHAR(6)         NOT NULL,
    timeout          MEDIUMINT UNSIGNED NOT NULL,
    connect_timeout  MEDIUMINT UNSIGNED NOT NULL,
    UNIQUE KEY u_backend_function (backend, backend_function)
) engine = InnoDB;

CREATE INDEX idx_backend_function ON tb_end_point (backend, backend_function);

CREATE TABLE IF NOT EXISTS tb_harmonized
(
    id                     VARCHAR(36)       NOT NULL PRIMARY KEY,
    backend                VARCHAR(10)       NOT NULL,
    backend_code           VARCHAR(10)       NOT NULL,
    backend_desc           VARCHAR(50)       NOT NULL,
    harmonized_code        VARCHAR(10)       NOT NULL,
    harmonized_desc        VARCHAR(50)       NOT NULL,
    harmonized_http_status SMALLINT UNSIGNED NOT NULL,
    is_error               BOOLEAN           NOT NULL,
    UNIQUE KEY u_backend_code (backend, backend_code)
) engine = InnoDB;

CREATE INDEX idx_backend_code ON tb_harmonized (backend, backend_code);

CREATE TABLE IF NOT EXISTS tb_system_parameter
(
    param_name  VARCHAR(100) NOT NULL PRIMARY KEY,
    description VARCHAR(50)  NOT NULL,
    param_value VARCHAR(100) NOT NULL
) engine = InnoDB;