package service

import (
    "go/data/dao"
    "go/data/model"
)

func GetLinkGroups() *[]model.LinkGroup {
    return dao.FindLinkGroups()
}

func SaveLinkGroup() {

}