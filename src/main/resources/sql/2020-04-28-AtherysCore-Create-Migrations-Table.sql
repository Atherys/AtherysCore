CREATE TABLE IF NOT EXISTS AtherysMigrations (
    id SERIAL PRIMARY KEY,
    timeExecuted TIMESTAMP
    name TEXT NOT NULL,
    content TEXT NOT NULL
)