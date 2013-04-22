package dao

import (
    "go/data/model"
)

func FindLinkGroups() *[]model.LinkGroup {
    user := FindUser("temp", "temp")

    groups := []model.LinkGroup {
        model.LinkGroup {
            Id: 1,
            User: user,
            Title: "temp",
            Description: "temp",
        },
        model.LinkGroup {
            Id: 2,
            User: user,
            Title: "temp2",
            Description: "temp2",
        },
    }

    return &groups
}

func SaveLinkGroup() {

}


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