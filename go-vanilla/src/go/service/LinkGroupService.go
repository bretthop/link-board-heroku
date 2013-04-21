package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLinkGroup() *model.LinkGroup {
    return dao.FindLinkGroup()
}