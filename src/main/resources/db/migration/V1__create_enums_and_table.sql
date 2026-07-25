CREATE TYPE anime_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE reading_format AS ENUM ('MANGA', 'MANHUA', 'WEBTOON', 'LIGHT_NOVEL', 'UNKNOWN');
CREATE TYPE reading_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE notes_status AS ENUM ('FINISHED', 'READING', 'NOT_READING', 'AUTHOR_DEATH', 'NOT_WORTH');

CREATE TABLE works (
    id BIGSERIAL PRIMARY KEY,
    rank BIGINT UNIQUE,
    title VARCHAR(200) NOT NULL,
    anime_status anime_status,
    reading_format reading_format,
    reading_status reading_status,
    notes_status notes_status
);