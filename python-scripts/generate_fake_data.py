import psycopg2
from faker import Faker
import random
from datetime import datetime, timedelta
from urllib.parse import urlparse

fake = Faker()

result = urlparse("postgresql://myuser:secret@postgres_db/mydatabase")
username = result.username
password = result.password
database = result.path[1:]
hostname = result.hostname
port = result.port



# Connect to PostgreSQL
def connect_to_db():
    try:
        connection = psycopg2.connect(
            database = database,
            user = username,
            password = password,
            host = hostname,
            port = port
        )

        connection.autocommit = True
        return connection
    except Exception as e:
        print(f"Error connecting to database: {e}")
        return None

# Insert fake users
def insert_fake_users(cursor, n):
    roles = ["Admin", "Patron"]
    for i in range(n):
        name = fake.name()
        email = fake.email()
        role = random.choice(roles)
        cursor.execute(
            """
            INSERT INTO users (id, name, email, role)
            VALUES (%s, %s, %s, %s)
            """,
            (i, name, email, role),
        )

# Insert fake books
def insert_fake_books(cursor, n):
    for i in range(n):
        title = fake.sentence(nb_words=3)
        author = fake.name()
        isbn = fake.isbn13()
        copies_available = random.randint(10, 30)
        cursor.execute(
            """
            INSERT INTO books (id, title, author, isbn, copies_available)
            VALUES (%s, %s, %s, %s, %s)
            """,
            (i, title, author, isbn, copies_available),
        )

# Insert fake borrowing history
def insert_fake_borrowing_history(cursor, n, user_ids, book_ids):
    for i in range(n):
        user_id = random.choice(user_ids)
        book_id = random.choice(book_ids)
        borrow_date = fake.date_between(start_date="-1y", end_date="today")
        return_date = borrow_date + timedelta(days=random.randint(1, 30))
        cursor.execute(
            """
            INSERT INTO borrowing_history (id, user_id, book_id, borrow_date, return_date)
            VALUES (%s, %s, %s, %s, %s)
            """,
            (i, user_id, book_id, borrow_date, return_date),
        )


def main():
    #did not use argparse for simplicity
    NUMBER_OF_USERS = 50
    NUMBER_OF_BOOKS = 100
    NUMBER_OF_BORROWING_HISTORY = 200
    connection = connect_to_db()
    if connection is None:
        return

    try:
        cursor = connection.cursor()

        print("Inserting fake users...")
        insert_fake_users(cursor, NUMBER_OF_USERS)

        print("Inserting fake books...")
        insert_fake_books(cursor, NUMBER_OF_BOOKS)


        cursor.execute("SELECT id FROM users")
        user_ids = [row[0] for row in cursor.fetchall()]

        cursor.execute("SELECT id FROM books")
        book_ids = [row[0] for row in cursor.fetchall()]

        print("Inserting fake borrowing history...")
        insert_fake_borrowing_history(cursor, NUMBER_OF_BORROWING_HISTORY, user_ids, book_ids)

        print("Fake data generation complete.")
    except Exception as e:
        print(f"Error while inserting data: {e}")
    finally:
        if connection:
            connection.close()

if __name__ == "__main__":
    main()