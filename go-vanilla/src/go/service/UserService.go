package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetUser(username, password string) *model.User {
    return dao.FindUser(username, password)
}