-- Enums
CREATE TYPE anime_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN');
CREATE TYPE manga_type AS ENUM ('MANGA', 'MANHUA', 'WEBTOON', 'LIGHT_NOVEL', 'UNKNOWN');
CREATE TYPE manga_status AS ENUM ('ONGOING', 'ENDED', 'HIATUS', 'UNKNOWN');
CREATE TYPE notes_status AS ENUM ('ENDED', 'NOT_READING', 'NO_ENDING_AUTHOR_DEATH', 'NO_ENDING', 'NOT_WORTH', 'HIATUS', 'UNKNOWN');

-- Main table
CREATE TABLE works (
    id SERIAL PRIMARY KEY,
    rank INT,
    titulo TEXT NOT NULL,
    anime anime_status,
    manga_form manga_type,
    manga manga_status,
    notes notes_status
);

-- Initial data - last updated on Jan 9, 2020
INSERT INTO works (rank, titulo, anime, manga, manga_form, notes)
VALUES
(1, 'One Piece', 'ONGOING', 'ONGOING', 'MANGA', 'NO_ENDING'),
(2, 'Boku no Hero Academia', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(3, 'Inazuma Eleven', 'ENDED', 'ONGOING', 'MANGA', 'NOT_READING'),
(4, 'Naruto', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(5, 'Fullmetal Alchemist: Brotherhood', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(6, 'Bleach', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(7, 'Saiki Kusuo no Psi-nan', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(8, 'Dragon Ball', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(9, 'Yu Yu Hakusho', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(10, 'Hunter x Hunter', 'ONGOING', 'ONGOING', 'MANGA', 'HIATUS'),
(11, 'Shingeki no Kyojin', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(12, 'Death Note', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(13, 'Black Clover', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(14, 'One Punch-Man', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(15, 'Mob Psycho 100', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(16, 'Nanatsu no Taizai', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(17, 'Sword Art Online', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(18, 'Tengen Toppa Gurren-Lagann', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(19, 'Fairy Tail', 'ONGOING', 'ENDED', 'MANGA', 'UNKNOWN'),
(20, 'Assassination Classroom', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(21, 'Kono Subarashii Sekai ni Shukufuku wo!', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(22, 'That Time I Got Reincarnated as a Slime', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(23, 'The Rising of the Shield Hero', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(24, 'Yakusoku no Neverland', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(25, 'No Game No Life', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(26, 'Goblin Slayer', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(27, 'Rakudai Kishi no Cavalry', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(28, 'Shijou Saikyou no Deshi Kenichi', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(29, 'Ao no Exorcist', 'ONGOING', 'ONGOING', 'MANGA', 'NOT_READING'),
(30, 'Zatch Bell!', 'ENDED', 'ENDED', 'MANGA', 'NO_ENDING'),
(31, 'Pokémon', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(32, 'Digimon', 'ONGOING', 'ONGOING', 'MANGA', 'UNKNOWN'),
(33, 'Overlord', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(34, 'Rosario + Vampire', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(35, 'Isekai Maou to Shoukan Shoujo no Dorei Majutsu', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(36, 'Love Hina', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(37, 'Masamune-kun no Revenge', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(38, 'Dungeon ni Deai wo Motomeru no wa Machigatteiru Darou ka', 'ONGOING', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(39, 'Mirai Nikki', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(40, 'Baki', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(41, 'La storia della Arcana Famiglia', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(42, 'Akame ga Kill!', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(43, 'Highschool of the Dead', 'ENDED', 'ENDED', 'MANGA', 'NO_ENDING_AUTHOR_DEATH'),
(44, 'High School DxD', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(45, 'Go-toubun no Hanayome', 'ONGOING', 'ENDED', 'MANGA', 'NOT_READING'),
(46, 'Akiba''s Trip: The Animation', 'ENDED', 'ONGOING', 'MANGA', 'UNKNOWN'),
(47, 'Bakugan: Guerreiros da Batalha', 'ENDED', 'UNKNOWN', 'MANGA', 'UNKNOWN'),
(48, 'To Love-Ru', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(49, 'Keppeki Danshi! Aoyama-kun', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(50, 'Gamers!', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_READING'),
(51, 'Domestic na Kanojo', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(52, 'Renai Boukun', 'ENDED', 'ONGOING', 'MANGA', 'UNKNOWN'),
(53, 'Okusama ga Seito Kaichō!', 'ENDED', 'ONGOING', 'MANGA', 'UNKNOWN'),
(54, 'Hajimete no Gal', 'ENDED', 'ONGOING', 'MANGA', 'UNKNOWN'),
(55, 'Soul Eater', 'ENDED', 'ENDED', 'MANGA', 'UNKNOWN'),
(56, 'Sakamoto desu ga?', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(57, 'Tsuredure Children', 'ENDED', 'ONGOING', 'MANGA', 'NOT_WORTH'),
(58, 'Eromanga Sensei', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_WORTH'),
(59, 'Tokyo Ghoul', 'ENDED', 'ENDED', 'MANGA', 'NOT_WORTH'),
(60, 'The Testament of Sister New Devil', 'ENDED', 'ONGOING', 'LIGHT_NOVEL', 'NOT_WORTH');

