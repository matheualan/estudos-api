CREATE TABLE IF NOT EXISTS tb_users_role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--CREATE TABLE users_role (
--    id SERIAL PRIMARY KEY,
--    username VARCHAR(50) NOT NULL,
--    password VARCHAR(255) NOT NULL,
--    role VARCHAR(50) NOT NULL,
--    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
--);
--
--CREATE UNIQUE INDEX idx_users_role_username ON users_role(username);