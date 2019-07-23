
CREATE TABLE BookStatus (
                BookStatusId INT AUTO_INCREMENT NOT NULL,
                BookStatusName VARCHAR(50) NOT NULL,
                PRIMARY KEY (BookStatusId)
);


CREATE TABLE Author (
                AuthorId INT AUTO_INCREMENT NOT NULL,
                LastName VARCHAR(25) NOT NULL,
                FirstName VARCHAR(25) NOT NULL,
                PRIMARY KEY (AuthorId)
);


CREATE TABLE Genre (
                GenreId INT AUTO_INCREMENT NOT NULL,
                GenreName VARCHAR(100) NOT NULL,
                PRIMARY KEY (GenreId)
);


CREATE TABLE BookType (
                BookTypeId INT AUTO_INCREMENT NOT NULL,
                BookTypeName VARCHAR(50) NOT NULL,
                PRIMARY KEY (BookTypeId)
);


CREATE TABLE Book (
                BookId INT AUTO_INCREMENT NOT NULL,
                BookName VARCHAR(100) NOT NULL,
                Image LONGBLOB NOT NULL,
                ISBN CHAR(13) NOT NULL,
                AuthorId INT NOT NULL,
                BookStatusId INT NOT NULL,
                BookTypeId INT NOT NULL,
                GenreId INT NOT NULL,
                PRIMARY KEY (BookId)
);


ALTER TABLE Book ADD CONSTRAINT bookstatus_book_fk
FOREIGN KEY (BookStatusId)
REFERENCES BookStatus (BookStatusId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Book ADD CONSTRAINT author_book_fk
FOREIGN KEY (AuthorId)
REFERENCES Author (AuthorId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Book ADD CONSTRAINT genre_book_fk
FOREIGN KEY (GenreId)
REFERENCES Genre (GenreId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE Book ADD CONSTRAINT booktype_book_fk
FOREIGN KEY (BookTypeId)
REFERENCES BookType (BookTypeId)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
