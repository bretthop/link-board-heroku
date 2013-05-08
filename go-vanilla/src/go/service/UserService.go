package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetUser(username, password string) *model.User {
    return dao.FindByUsernameAndPassword(username, password)
}

func SaveUser(user *model.User) *model.User {
    return dao.SaveUser(user)
}