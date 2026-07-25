-- Enums
CREATE TYPE anime_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE reading_format AS ENUM ('MANGA', 'MANHUA', 'WEBTOON', 'LIGHT_NOVEL', 'UNKNOWN');
CREATE TYPE reading_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE notes_status AS ENUM ('FINISHED', 'READING', 'NOT_READING', 'AUTHOR_DEATH', 'NOT_WORTH');

-- Main table
CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    rank INT UNIQUE,
    title VARCHAR(200) NOT NULL,
    anime_status anime_status,
    reading_format reading_format,
    reading_status reading_status,
    notes_status notes_status
);

-- Initial data - last updated rank on Jan 9, 2020
-- New update - last updated rank on Jul 24, 2026
INSERT INTO works (rank, title, anime_status, reading_format, reading_status, notes_status)
VALUES
(1, 'One Piece', 'ONGOING', 'MANGA', 'ONGOING', 'READING'),
(2, 'Inazuma Eleven', 'ENDED', 'MANGA', 'UNKNOWN', 'NOT_READING'),
(3, 'Naruto', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(4, 'Fullmetal Alchemist: Brotherhood', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(5, 'Dragon Ball', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(6, 'Bleach', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(7, 'Saiki Kusuo no Psi-nan', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(8, 'Black Clover', 'ONGOING', 'MANGA', 'ENDED', 'FINISHED'),
(9, 'Boku no Hero Academia', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(10, 'Yu Yu Hakusho', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(11, 'Hunter x Hunter', 'NO_ENDING', 'MANGA', 'ONGOING', 'NOT_READING'),
(12, 'Death Note', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(13, 'Tengen Toppa Gurren-Lagann', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(14, 'One Punch-Man', 'ONGOING', 'MANGA', 'ONGOING', 'NOT_READING'),
(15, 'Mob Psycho 100', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(16, 'Zatch Bell!', 'NO_ENDING', 'MANGA', 'ENDED', 'NOT_READING'),
(17, 'Fairy Tail', 'ENDED', 'MANGA', 'ONGOING', 'NOT_READING'),
(18, 'Shingeki no Kyojin', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(19, 'Sword Art Online', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(20, 'Shijou Saikyou no Deshi Kenichi', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(21, 'Assassination Classroom', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(22, 'Kono Subarashii Sekai ni Shukufuku wo!', 'ONGOING', 'LIGHT_NOVEL', 'ENDED', 'NOT_READING'),
(23, 'Soul Eater', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(24, 'Mirai Nikki', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(25, 'Rakudai Kishi no Cavalry', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(26, 'Akame ga Kill!', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(27, 'Nanatsu no Taizai', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(28, 'Baki', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(29, 'Pokémon', 'ONGOING', 'MANGA', 'ONGOING', 'NOT_READING'),
(30, 'Digimon', 'ONGOING', 'MANGA', 'ONGOING', 'NOT_READING'),
(31, 'Rosario + Vampire', 'ENDED', 'MANGA', 'ENDED', 'FINISHED'),
(32, 'That Time I Got Reincarnated as a Slime', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(33, 'The Rising of the Shield Hero', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(34, 'No Game No Life', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(35, 'Yakusoku no Neverland', 'ONGOING', 'MANGA', 'ONGOING', 'NOT_READING'),
(36, 'Gamers!', 'NO_ENDING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(37, 'To Love-Ru', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(38, 'Love Hina', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(39, 'Masamune-kun no Revenge', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(40, 'Dungeon ni Deai wo Motomeru no wa Machigatteiru Darou ka', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(41, 'Overlord', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(42, 'Goblin Slayer', 'ONGOING', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(43, 'Ao no Exorcist', 'ONGOING', 'MANGA', 'ONGOING', 'NOT_READING'),
(44, 'Highschool of the Dead', 'NO_ENDING', 'MANGA', 'ENDED', 'AUTHOR_DEATH'),
(45, 'High School DxD', 'ENDED', 'LIGHT_NOVEL', 'ONGOING', 'NOT_READING'),
(46, 'Tokyo Ghoul', 'ENDED', 'MANGA', 'ENDED', 'NOT_WORTH'),
(47, 'Go-toubun no Hanayome', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(48, 'Akiba''s Trip: The Animation', 'ENDED', 'MANGA', 'ONGOING', 'NOT_READING'),
(49, 'Bakugan: Guerreiros da Batalha', 'ENDED', 'MANGA', 'UNKNOWN', 'NOT_READING'),
(50, 'Renai Boukun', 'ENDED', 'MANGA', 'ONGOING', 'NOT_WORTH'),
(51, 'Okusama ga Seito Kaichō!', 'ENDED', 'MANGA', 'ONGOING', 'NOT_WORTH'),
(52, 'Hajimete no Gal', 'ENDED', 'MANGA', 'ONGOING', 'NOT_WORTH'),
(53, 'Sakamoto desu ga?', 'ENDED', 'MANGA', 'ONGOING', 'NOT_WORTH'),
(54, 'La storia della Arcana Famiglia', 'ENDED', 'MANGA', 'ENDED', 'NOT_READING'),
(55, 'Isekai Maou to Shoukan Shoujo no Dorei Majutsu', 'ONGOING', 'LIGHT_NOVEL', 'UNKNOWN', 'NOT_READING'),
(56, 'Tsuredure Children', 'ENDED', 'MANGA', 'UNKNOWN', 'NOT_WORTH'),
(57, 'Eromanga Sensei', 'ENDED', 'LIGHT_NOVEL', 'UNKNOWN', 'NOT_WORTH'),
(58, 'Domestic na Kanojo', 'ENDED', 'MANGA', 'ENDED', 'NOT_WORTH'),
(59, 'The Testament of Sister New Devil', 'ENDED', 'LIGHT_NOVEL', 'UNKNOWN', 'NOT_WORTH'),
(60, 'Keppeki Danshi! Aoyama-kun', 'ENDED', 'MANGA', 'ENDED', 'NOT_WORTH');

-- Alter table to include soft delete flag
ALTER TABLE works
ADD COLUMN is_active BOOLEAN DEFAULT TRUE;

-- Alter table to include status user reading
CREATE TYPE user_reading_status AS ENUM ('NOT_STARTED', 'READING', 'PAUSED', 'DROPPED', 'FINISHED');
ALTER TABLE works ADD COLUMN user_reading_status user_reading_status DEFAULT 'NOT_STARTED';

-- Alter table to rename reading_status
ALTER TYPE reading_status RENAME TO reading_format_status;
ALTER TABLE works RENAME COLUMN reading_status TO reading_format_status;