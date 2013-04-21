package dao

import (
    "go/data/model"
)

func FindLinkGroup() *model.LinkGroup {
    user := FindUser("temp", "temp")

    group := model.LinkGroup {
        Id: 1,
        User: user,
        Title: "temp",
        Description: "temp",
    }

    return &group
}