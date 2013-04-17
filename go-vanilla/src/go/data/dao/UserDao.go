package dao

import (
    "go/data/model"
)

func FindUser(username, password string) *model.User {
    user := model.User {
        Id: 1,
        Username: "temp",
        Password: "temp",
        Email: "temp",
        FirstName: "temp",
        LastName: "temp",
    }

    if username == user.Username && password == user.Password {
        return &user
    }

    return nil
}