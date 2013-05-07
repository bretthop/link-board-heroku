package dao

import (
    "go/data/model"
)

func FindLinkGroups() *[]model.LinkGroup {
    groups := []model.LinkGroup {
        model.LinkGroup {
            Id: 1,
            User: nil,
            Title: "temp",
            Description: "temp",
        },
        model.LinkGroup {
            Id: 2,
            User: nil,
            Title: "temp2",
            Description: "temp2",
        },
    }

    return &groups
}

func SaveLinkGroup() {

}

func FindLinkGroup() *model.LinkGroup {
    group := model.LinkGroup {
        Id: 1,
        User: nil,
        Title: "temp",
        Description: "temp",
    }

    return &group
}