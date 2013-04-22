package dao

import (
    "go/data/model"
)

func FindLinks() *[]model.Link {
    group := FindLinkGroup()

    links := []model.Link {
        model.Link {
            Id: 1,
            Group: group,
            Title: "temp",
            Href: "temp",
            Description: "temp",
        },
    }

    return &links
}