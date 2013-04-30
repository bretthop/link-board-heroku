package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLinks() *[]model.Link {
    return dao.FindLinks()
}

func SaveLink(link *model.Link) {
    dao.SaveLink()
}

func DeleteLink() {
    dao.DeleteLink()
}