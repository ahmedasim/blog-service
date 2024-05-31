CREATE TABLE IF NOT EXISTS authors (
    author_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS posts (
    post_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    author_id BIGINT,
    text VARCHAR(255),
    is_deleted BIT NOT NULL,
    INDEX idx_author_id (author_id),
    CONSTRAINT fk_author
        FOREIGN KEY (author_id) 
        REFERENCES authors(author_id)
        ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=UTF8;


# Insert authors data
INSERT INTO authors (name) VALUES 
('Asim Ahmed'),
('Asadullah Khan'),
('Sarim Balkhi'),
('Faraz Siddiqui'),
('Komal Haroon'),
('Syed Hammad'),
('Shakaib Shaikh'),
('Rafia Manzoor'),
('Sana Javed');

#Insert 50 posts data
INSERT INTO posts (author_id, text, is_deleted) VALUES 
(1, 'This is a post by Asim Ahmed.', 0),
(1, 'Another post by Asim Ahmed.', 0),
(1, 'Third post by Asim Ahmed.', 0),
(1, 'Yet another post by Asim Ahmed.', 0),
(1, 'Fifth post by Asim Ahmed.', 0),
(2, 'A post by Asadullah Khan.', 0),
(2, 'Another post by Asadullah Khan.', 0),
(2, 'Third post by Asadullah Khan.', 0),
(2, 'Yet another post by Asadullah Khan.', 0),
(2, 'Fifth post by Asadullah Khan.', 0),
(3, 'Sarim Balkhi\'s first post.', 1),
(3, 'Sarim Balkhi\'s second post.', 0),
(3, 'Third post by Sarim Balkhi.', 0),
(3, 'Yet another post by Sarim Balkhi.', 0),
(3, 'Fifth post by Sarim Balkhi.', 0),
(4, 'Faraz Siddiqui writes a post.', 0),
(4, 'Another post by Faraz Siddiqui.', 1),
(4, 'Third post by Faraz Siddiqui.', 0),
(4, 'Yet another post by Faraz Siddiqui.', 0),
(4, 'Fifth post by Faraz Siddiqui.', 0),
(5, 'Komal Haroon posts something.', 0),
(5, 'Another post by Komal Haroon.', 0),
(5, 'Third post by Komal Haroon.', 0),
(5, 'Yet another post by Komal Haroon.', 0),
(5, 'Fifth post by Komal Haroon.', 0),
(6, 'Syed Hammad shares a thought.', 0),
(6, 'Syed Hammad\'s latest post.', 1),
(6, 'Third post by Syed Hammad.', 0),
(6, 'Yet another post by Syed Hammad.', 0),
(6, 'Fifth post by Syed Hammad.', 0),
(7, 'Shakaib Shaikh\'s post.', 0),
(7, 'Another post by Shakaib Shaikh.', 0),
(7, 'Third post by Shakaib Shaikh.', 0),
(7, 'Yet another post by Shakaib Shaikh.', 0),
(7, 'Fifth post by Shakaib Shaikh.', 0),
(8, 'Rafia Manzoor writes about a topic.', 0),
(8, 'Another post by Rafia Manzoor.', 0),
(8, 'Third post by Rafia Manzoor.', 0),
(8, 'Yet another post by Rafia Manzoor.', 0),
(8, 'Fifth post by Rafia Manzoor.', 0),
(9, 'Sana Javed\'s post.', 0),
(9, 'Another post by Sana Javed.', 0),
(9, 'Third post by Sana Javed.', 0),
(9, 'Yet another post by Sana Javed.', 0),
(9, 'Fifth post by Sana Javed.', 0);