CREATE TABLE auth_providers (
    id VARCHAR(64) PRIMARY KEY,
    user_id VARCHAR(64) NOT NULL,
    provider ENUM('local', 'google') NOT NULL,
    email VARCHAR(255),
    password_hash VARCHAR(255),
    provider_user_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
