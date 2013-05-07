package dao

import (
    "go/data/model"
)

func FindUser(username, password string) *model.User {
    var user model.User

    db := GetConn()
    defer db.Close()

    row := db.QueryRow("SELECT * FROM user_account WHERE username = $1::text AND password = $2::text", username, password)
    row.Scan(&user.Id, &user.Username, &user.Password, &user.Email, &user.FirstName, &user.LastName)

    if user.Id == 0 {
        return nil
    }
    return &user
}

func SaveUser() {

}