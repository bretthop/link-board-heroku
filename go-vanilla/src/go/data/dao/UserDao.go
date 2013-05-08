package dao

import (
    "go/data/model"
    "go/util"
)

func FindByUsernameAndPassword(username, password string) *model.User {
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

func SaveUser(user *model.User) *model.User {
    db := GetConn()
    defer db.Close()

    res, err_1 := db.Exec("INSERT INTO user_account (username, password, email, first_name, last_name) VALUES ($1::text, $2::text, $3::text, $4::text, $5::text)", user.Username, user.Password, user.Email, user.FirstName, user.LastName)
    user.Id, _ = res.LastInsertId() // TODO: Investigate the error that LastInsertId() throws

    util.HandleErrors(err_1)

    return user
}