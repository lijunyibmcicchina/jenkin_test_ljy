
-- 创建 OrderStatus 表
CREATE TABLE IF NOT EXISTS order_status (
                                            order_status_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            order_status_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
    );

-- 创建 OrderType 表
CREATE TABLE IF NOT EXISTS order_type (
                                          order_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          order_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
    );

-- 创建 ServiceType 表
CREATE TABLE IF NOT EXISTS service_type (
                                            service_type_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            service_type_name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255)
    );

-- 创建 User 表
CREATE TABLE IF NOT EXISTS user (
                                    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    user_name VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    full_name VARCHAR(255),
    role ENUM('PROVIDER', 'CUSTOMER') NOT NULL DEFAULT 'CUSTOMER'
    );

-- 创建 NDISService 表
CREATE TABLE IF NOT EXISTS ndis_service (
                                            service_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            service_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL,
    service_type_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (service_type_id) REFERENCES service_type(service_type_id),
    FOREIGN KEY (provider_id) REFERENCES user(user_id)
    );

-- 创建 ServiceOrder 表
CREATE TABLE IF NOT EXISTS service_order (
                                             order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             user_id BIGINT NOT NULL,
                                             order_type_id BIGINT NOT NULL,
                                             order_status_id BIGINT NOT NULL,
                                             service_id BIGINT NOT NULL,
                                             order_date DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                             created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                             updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                             FOREIGN KEY (user_id) REFERENCES user(user_id),
    FOREIGN KEY (order_type_id) REFERENCES order_type(order_type_id),
    FOREIGN KEY (order_status_id) REFERENCES order_status(order_status_id),
    FOREIGN KEY (service_id) REFERENCES ndis_service(service_id)
    );

-- 创建 ClinicalServiceConfiguration 表
CREATE TABLE IF NOT EXISTS clinical_service_configuration (
                                                              config_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                              order_id BIGINT NOT NULL,
                                                              max_session_per_week INT NOT NULL,
                                                              required_doctor_approval BOOLEAN DEFAULT FALSE,
                                                              therapy_duration_minutes INT NOT NULL,
                                                              created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                                              updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                                              FOREIGN KEY (order_id) REFERENCES service_order(order_id)
    );

---- 创建 IndependentServiceConfiguration 表
--CREATE TABLE IF NOT EXISTS independent_service_configuration (
--    config_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    order_id BIGINT NOT NULL,
--    max_hours_per_week INT NOT NULL,
--    requires_training BOOLEAN DEFAULT FALSE,
--    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    FOREIGN KEY (order_id) REFERENCES service_order(order_id)
--);
--
---- 创建 DailyLivingConfiguration 表
--CREATE TABLE IF NOT EXISTS daily_living_configuration (
--    config_id BIGINT AUTO_INCREMENT PRIMARY KEY,
--    order_id BIGINT NOT NULL,
--    provides_home_care BOOLEAN DEFAULT FALSE,
--    max_assistants INT DEFAULT 1,
--    daily_budget_limit DECIMAL(10, 2),
--    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
--    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    FOREIGN KEY (order_id) REFERENCES service_order(order_id)
--);

-- 插入数据到 OrderStatus 表
INSERT INTO order_status (order_status_name, description)
VALUES
    ('initiated', 'The order has been created but not processed yet'),
    ('pending payment', 'The order is waiting for payment'),
    ('fulfilled', 'The order has been completed'),
    ('canceled', 'The order has been canceled')
    ON DUPLICATE KEY UPDATE description = VALUES(description);

-- 插入数据到 OrderType 表
INSERT INTO order_type (order_type_name, description)
VALUES
    ('delivery', 'The order will be delivered to the customer'),
    ('pick-up', 'The customer will pick up the order'),
    ('on-site', 'The service will be provided on-site')
    ON DUPLICATE KEY UPDATE description = VALUES(description);

-- 插入数据到 ServiceType 表
INSERT INTO service_type (service_type_name, description)
VALUES
    ('ClinicalService', 'Clinical services configuration'),
    ('IndependentService', 'Independent services configuration'),
    ('DailyLivingService', 'Daily living services configuration')
    ON DUPLICATE KEY UPDATE description = VALUES(description);

-- 插入用户数据到 User 表
INSERT INTO user (user_name, email, password, full_name, role)
VALUES
    ('john_provider', 'john.provider@gmail.com', 'password', 'John Provider', 'PROVIDER'),
    ('jane_customer', 'jane.customer@gmail.com', 'password', 'Jane Customer', 'CUSTOMER')
    ON DUPLICATE KEY UPDATE email = VALUES(email), full_name = VALUES(full_name);

-- 插入数据到 NDISService 表
INSERT INTO ndis_service (service_name, description, price, service_type_id, provider_id, created_at)
VALUES
    ('Transport Service', 'Reliable transport service for goods and passengers.', 50.00, 1, 1,'2025-02-01 10:00:00'),
    ('Homecare Service', 'Professional homecare for daily living.', 100.00, 3, 1, '2025-02-01 10:00:00'),
    ('Independent Assistance', 'Support for independent living.', 75.00, 2, 1, '2025-02-01 10:00:00')
    ON DUPLICATE KEY UPDATE
                         description = VALUES(description),
                         price = VALUES(price);

-- 插入测试订单到 ServiceOrder 表
INSERT INTO service_order (user_id, order_type_id, order_status_id, service_id, order_date, created_at)
VALUES
    (2, 1, 1, 1, '2025-02-01 10:00:00', '2025-02-01 10:00:00'),
    (2, 2, 2, 2, '2025-02-02 11:00:00', '2025-02-01 10:00:00'),
    (2, 3, 3, 3, '2025-02-03 12:00:00', '2025-02-01 10:00:00')
    ON DUPLICATE KEY UPDATE order_date = VALUES(order_date);

-- 插入 ClinicalServiceConfiguration 数据
INSERT INTO clinical_service_configuration (order_id, max_session_per_week, required_doctor_approval, therapy_duration_minutes, created_at)
VALUES
    (1, 5, TRUE, 60, '2025-02-01 10:00:00')
    ON DUPLICATE KEY UPDATE max_session_per_week = VALUES(max_session_per_week);

---- 插入 IndependentServiceConfiguration 数据
--INSERT INTO independent_service_configuration (order_id, max_hours_per_week, requires_training)
--VALUES
--    (2, 40, TRUE)
--ON DUPLICATE KEY UPDATE max_hours_per_week = VALUES(max_hours_per_week);
--
---- 插入 DailyLivingConfiguration 数据
--INSERT INTO daily_living_configuration (order_id, provides_home_care, max_assistants, daily_budget_limit)
--VALUES
--    (3, TRUE, 2, 500.00)
--ON DUPLICATE KEY UPDATE daily_budget_limit = VALUES(daily_budget_limit);
