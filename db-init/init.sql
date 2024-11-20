-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255)        NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role  VARCHAR(10)         NOT NULL
);

CREATE TABLE staff
(
    position VARCHAR(255) NOT NULL,
    salary   NUMERIC      NOT NULL
)INHERITS (users);

CREATE TABLE books
(
    id               SERIAL PRIMARY KEY,
    title            VARCHAR(255)       NOT NULL,
    author           VARCHAR(255)       NOT NULL,
    isbn             VARCHAR(13) UNIQUE NOT NULL,
    copies_available INT                NOT NULL
);

CREATE TABLE borrowing_history
(
    id          SERIAL PRIMARY KEY,
    user_id     INT REFERENCES users (id),
    book_id     INT REFERENCES books (id),
    borrow_date DATE NOT NULL,
    return_date DATE
);

-- Indexes
CREATE INDEX idx_books_title_author ON books (title, author);
CREATE INDEX idx_borrowing_history_user_id ON borrowing_history (user_id, book_id);
