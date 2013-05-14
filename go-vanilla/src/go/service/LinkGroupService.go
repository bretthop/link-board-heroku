package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLinkGroupsForUser(user *model.User) *[]model.LinkGroup {
    return dao.FindLinkGroupsForUser(user)
}

func SaveLinkGroup(group *model.LinkGroup) *model.LinkGroup {
    return dao.SaveLinkGroup(group)
}