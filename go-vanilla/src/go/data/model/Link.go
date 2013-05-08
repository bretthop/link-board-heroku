package model

type Link struct {
    Id              int64       `json:"id"`
    Group           *LinkGroup  `json:"group"`
    Title           string      `json:"title"`
    Href            string      `json:"href"`
    Description     string      `json:"description"`
}