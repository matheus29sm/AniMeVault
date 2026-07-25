-- Alter table to include soft delete flag
ALTER TABLE works
ADD COLUMN is_active BOOLEAN DEFAULT TRUE;