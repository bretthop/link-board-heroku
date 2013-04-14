package service

import (
    "data/dao"
    "data/model"
)

func GetUser() model.User {
    return dao.FindUser()
}