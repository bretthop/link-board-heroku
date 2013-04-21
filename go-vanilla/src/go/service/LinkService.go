package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLink() *model.Link {
    return dao.FindLink()
}