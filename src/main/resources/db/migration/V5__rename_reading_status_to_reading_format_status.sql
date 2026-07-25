-- Alter table to rename reading_status
ALTER TYPE reading_status RENAME TO reading_format_status;
ALTER TABLE works RENAME COLUMN reading_status TO reading_format_status;