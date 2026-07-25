-- Alter table to include status user reading
CREATE TYPE user_reading_status AS ENUM ('NOT_STARTED', 'READING', 'PAUSED', 'DROPPED', 'FINISHED');
ALTER TABLE works ADD COLUMN user_reading_status user_reading_status DEFAULT 'NOT_STARTED';