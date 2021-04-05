ALTER TABLE users
DROP COLUMN is_biometric;

ALTER TABLE settings
ADD COLUMN member_role VARCHAR;