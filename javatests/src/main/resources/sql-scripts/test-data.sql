CREATE TABLE IF NOT EXISTS movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    minutes INT NOT NULL,
    genre VARCHAR(50) NOT NULL,
    director VARCHAR(50) NOT NULL
);

insert into movies (name, minutes, genre, director) values
    ('Dark Knight', 152, 'ACTION', 'Robert'),
    ('Memento', 113, 'THRILLER', 'Ruso'),
    ('Matrix', 136, 'ACTION', 'Thomas'),
    ('Super 8', 137, 'ACTION', 'Carl'),
    ('Super Man', 139, 'ACTION', 'Louis');