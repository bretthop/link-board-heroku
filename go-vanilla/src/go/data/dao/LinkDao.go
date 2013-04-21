package dao

import (
    "go/data/model"
)

func FindLink() *model.Link {
    group := FindLinkGroup()

    link := model.Link {
        Id: 1,
        Group: group,
        Title: "temp",
        Href: "temp",
        Description: "temp",
    }

    return &link
}