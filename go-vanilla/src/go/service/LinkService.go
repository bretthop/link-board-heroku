package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLinksForGroup(groupId int64) *[]model.Link {
    return dao.FindLinksForGroup(groupId)
}

func SaveLink(link *model.Link) *model.Link {
    return dao.SaveLink(link)
}

func DeleteLink(linkId int64) {
    dao.DeleteLink(linkId)
}