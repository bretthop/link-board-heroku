package model

type LinkGroup struct {
    Id              int     `json:"id"`
    User            *User   `json:"user"`
    Title           string  `json:"title"`
    Description     string  `json:"description"`
}