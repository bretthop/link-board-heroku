package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetUser() model.User {
    return dao.FindUser()
}