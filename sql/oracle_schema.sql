CREATE TABLE books (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR2(100) NOT NULL,
    author VARCHAR2(100) NOT NULL,
    isbn VARCHAR2(13) UNIQUE,
    available NUMBER(1) DEFAULT 1 CHECK (available IN (0,1))
);

CREATE TABLE members (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE,
    role VARCHAR2(20) DEFAULT 'USER' CHECK (role IN ('USER', 'LIBRARIAN'))
);

CREATE TABLE transactions (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    book_id NUMBER,
    member_id NUMBER,
    borrow_date DATE,
    due_date DATE,
    return_date DATE,
    CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books(id),
    CONSTRAINT fk_member FOREIGN KEY (member_id) REFERENCES members(id)
);

CREATE INDEX idx_books_title_author ON books(title, author);
