package dao

import (
    pq "github.com/bmizerany/pq"
    "database/sql"
    "os"
)

func GetConn() *sql.DB {
    // Get the connection string from the DATABASE_URL env var
    connString, err := pq.ParseURL(os.Getenv("DATABASE_URL"))
    if err != nil {
        panic(err)
    }

    db, err := sql.Open("postgres", connString + " sslmode=disable")

    if err != nil {
        panic(err)
    }

    return db
}