package dao

import (
    "data/model"
)

func FindUser() model.User {
    return model.User {
        Id: 1,
        Username: "temp",
        Password: "temp",
        Email: "temp",
        FirstName: "temp",
        LastName: "temp",
    }
}