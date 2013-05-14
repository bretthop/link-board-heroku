package dao

import (
    "go/data/model"
    "go/util"
)

func FindLinkGroupsForUser(user *model.User) *[]model.LinkGroup {
    var result []model.LinkGroup

    db := GetConn()
    defer db.Close()

    rows, err := db.Query("SELECT * FROM link_group WHERE user_account_id = $1::int", user.Id)
    util.HandleErrors(err)

    for rows.Next() {
        var group model.LinkGroup
        var userId_notUsed int64

        err := rows.Scan(&group.Id, &userId_notUsed, &group.Title, &group.Description)
        util.HandleErrors(err)

        result = append(result, group)
    }

    util.HandleErrors(rows.Err())

    return &result
}

func SaveLinkGroup(group *model.LinkGroup) *model.LinkGroup {
    db := GetConn()
    defer db.Close()

    res, err := db.Exec("INSERT INTO link_group (user_account_id, title, description) VALUES ($1::int, $2::text, $3::text)", group.User.Id, group.Title, group.Description)
    group.Id, _ = res.LastInsertId() // TODO: Investigate the error that LastInsertId() throws

    util.HandleErrors(err)

    return group
}

func HasAccessToGroup(userId, groupId int64) bool {
    db := GetConn()
    defer db.Close()

    rows, err := db.Query("SELECT * FROM link_group WHERE id = $1::int AND user_account_id = $2::int", groupId, userId)
    util.HandleErrors(err)

    return rows.Next()
}