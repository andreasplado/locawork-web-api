ALTER TABLE users
ADD COLUMN is_expired BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE users
ADD COLUMN is_credentials_none_expired BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE users
ADD COLUMN is_locked BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE users
ADD COLUMN is_enabled BOOLEAN NOT NULL DEFAULT FALSE;