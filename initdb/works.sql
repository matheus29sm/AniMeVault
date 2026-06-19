-- Enums
CREATE TYPE anime_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE reading_format AS ENUM ('MANGA', 'MANHUA', 'WEBTOON', 'LIGHT_NOVEL', 'UNKNOWN');
CREATE TYPE reading_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN', 'NO_ENDING');
CREATE TYPE notes_status AS ENUM ('FINISHED', 'READING', 'NOT_READING', 'AUTHOR_DEATH', 'NOT_WORTH');

-- Main table
CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    rank INT,
    titulo VARCHAR(200) NOT NULL,
    anime_status anime_status,
    reading_format reading_format,
    reading_status reading_status,
    notes_status notes_status
);

-- Initial data - last updated on Jan 9, 2020
INSERT INTO works (rank, titulo, anime_status, reading_status, reading_format, notes_status)
VALUES
(1, 'One Piece', 'ONGOING', 'ONGOING', 'MANGA', 'READING'),
(2, 'Boku no Hero Academia', 'ONGOING', 'ONGOING', 'MANGA', 'READING'),
(3, 'Inazuma Eleven', 'ENDED', 'ONGOING', 'MANGA', 'NOT_READING'),
(4, 'Naruto', 'ENDED', 'ENDED', 'MANGA', 'FINISHED'),
(5, 'Fullmetal Alchemist: Brotherhood', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(6, 'Bleach', 'ENDED', 'ENDED', 'MANGA', 'FINISHED'),
(7, 'Saiki Kusuo no Psi-nan', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(8, 'Dragon Ball', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(9, 'Yu Yu Hakusho', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(10, 'Hunter x Hunter', 'NO_ENDING', 'HIATUS', 'MANGA', 'NOT_READING'),
(11, 'Shingeki no Kyojin', 'ONGOING', 'ONGOING', 'MANGA', 'READING'),
(12, 'Death Note', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(13, 'Black Clover', 'ONGOING', 'ONGOING', 'MANGA', 'READING'),
(14, 'One Punch-Man', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(15, 'Mob Psycho 100', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(16, 'Nanatsu no Taizai', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(17, 'Sword Art Online', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(18, 'Tengen Toppa Gurren-Lagann', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(19, 'Fairy Tail', 'ONGOING', 'ENDED', 'MANGA', 'READING'),
(20, 'Assassination Classroom', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(21, 'Kono Subarashii Sekai ni Shukufuku wo!', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(22, 'That Time I Got Reincarnated as a Slime', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(23, 'The Rising of the Shield Hero', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(24, 'Yakusoku no Neverland', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(25, 'No Game No Life', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(26, 'Goblin Slayer', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(27, 'Rakudai Kishi no Cavalry', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(28, 'Shijou Saikyou no Deshi Kenichi', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(29, 'Ao no Exorcist', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(30, 'Zatch Bell!', 'NO_ENDING', 'ENDED', 'MANGA', 'NOT_READING'),
(31, 'Pokémon', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(32, 'Digimon', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(33, 'Overlord', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(34, 'Rosario + Vampire', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(35, 'Isekai Maou to Shoukan Shoujo no Dorei Majutsu', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(36, 'Love Hina', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(37, 'Masamune-kun no Revenge', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(38, 'Dungeon ni Deai wo Motomeru no wa Machigatteiru Darou ka', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(39, 'Mirai Nikki', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(40, 'Baki', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(41, 'La storia della Arcana Famiglia', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(42, 'Akame ga Kill!', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(43, 'Highschool of the Dead', 'NO_ENDING', 'ENDED', 'MANGA', 'AUTHOR_DEATH'),
(44, 'High School DxD', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(45, 'Go-toubun no Hanayome', 'ONGOING', 'ENDED', 'MANGA', 'NOT_READING'),
(46, 'Akiba''s Trip: The Animation', 'ENDED', 'ONGOING', 'MANGA', 'NOT_READING'),
(47, 'Bakugan: Guerreiros da Batalha', 'ENDED', 'UNKNOWN', 'MANGA', 'NOT_READING'),
(48, 'To Love-Ru', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(49, 'Keppeki Danshi! Aoyama-kun', 'ENDED', 'ENDED', 'MANGA', 'NOT_WORTH'),
(50, 'Gamers!', 'NO_ENDING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(51, 'Domestic na Kanojo', 'ENDED', 'ENDED', 'MANGA', 'NOT_WORTH'),
(52, 'Renai Boukun', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(53, 'Okusama ga Seito Kaichō!', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(54, 'Hajimete no Gal', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(55, 'Soul Eater', 'ENDED', 'ENDED', 'MANGA', 'NOT_READING'),
(56, 'Sakamoto desu ga?', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(57, 'Tsuredure Children', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(58, 'Eromanga Sensei', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_WORTH'),
(59, 'Tokyo Ghoul', 'ENDED', 'ENDED', 'MANGA', 'NOT_WORTH'),
(60, 'The Testament of Sister New Devil', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_WORTH');

