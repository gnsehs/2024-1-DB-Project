INSERT INTO article (title, content) VALUES
('안녕하세요', '1111111'),
('여기 망헀나요', '22222222'),
('게임추천해주세요', '3333333');

INSERT INTO company (name, nation, founding_date, ceo) VALUES
('A', 'Korea','2000-01-01','Kan'),
('B', 'China','2010-01-01','Kim'),
('C', 'Japan','2020-01-01','Park'),
('D', 'East','2030-01-01','Do');

INSERT INTO game (game_name, release_date, company_id, age_rating) VALUES
('헤일로: 전투 진화', '2001-11-15', 1, 19),
('포르자 호라이즌 4', '2018-10-02', 1, 12),
('기어즈 오브 워', '2006-11-07', 2, 19),
('씨 오브 시브즈', '2018-03-20', 2, 12),
('오리와 눈먼 숲', '2015-03-11', 2, 12),
('페이블', '2004-09-14', 3, 19),
('크랙다운', '2007-02-20', 4, 19);

INSERT INTO device (device_name, made_by, release_date) VALUES
('XBOX','Microsoft','2001-03-30'),
('PS5','Microsoft','2001-03-30'),
('Nintendo Switch','Nintendo','2001-03-30');

INSERT INTO game_on_device (game_id, device_id) VALUES
(1,1),
(1,3),
(2,2),
(2,3),
(3,1),
(4,3),
(5,1),
(5,2),
(5,3),
(6,1),
(6,3),
(7,3);

