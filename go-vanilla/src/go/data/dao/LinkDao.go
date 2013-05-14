package dao

import (
    "go/data/model"
    "go/util"
)

func FindLinksForGroup(groupId int64) *[]model.Link {
    var result []model.Link
    db := GetConn()
    defer db.Close()

    rows, err := db.Query("SELECT * FROM link WHERE link_group_id = $1::int", groupId)
    util.HandleErrors(err)

    for rows.Next() {
        var link model.Link
        var groupId_notUsed int64

        err := rows.Scan(&link.Id, &groupId_notUsed, &link.Title, &link.Href, &link.Description)
        util.HandleErrors(err)

        result = append(result, link)
    }

    util.HandleErrors(rows.Err())

    return &result
}

func SaveLink(link *model.Link) *model.Link {
    db := GetConn()
    defer db.Close()

    res, err := db.Exec("INSERT INTO link (link_group_id, title, href, description) VALUES ($1::int, $2::text, $3::text, $4::text)", link.Group.Id, link.Title, link.Href, link.Description)
    link.Id, _ = res.LastInsertId() // TODO: Investigate the error that LastInsertId() throws

    util.HandleErrors(err)

    return link
}

func DeleteLink(linkId int64) {
    db := GetConn()
    defer db.Close()

    _, err := db.Exec("DELETE FROM link WHERE id = $1::int", linkId)
    util.HandleErrors(err)
}

func HasAccessToLink(linkId, userId int64) bool {
    db := GetConn()
    defer db.Close()

    rows, err := db.Query("SELECT * FROM link l, link_group lg WHERE l.id = $1::int AND l.link_group_id = lg.id AND lg.user_account_id = $2::int", linkId, userId)
    util.HandleErrors(err)

    return rows.Next()
}