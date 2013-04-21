package model

type Link struct {
    Id              int         `json:"id"`
    Group           *LinkGroup  `json:"group"`
    Title           string      `json:"title"`
    Href            string      `json:"href"`
    Description     string      `json:"description"`
}