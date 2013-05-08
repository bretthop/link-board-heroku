package model

type LinkGroup struct {
    Id              int64   `json:"id,string"` // 'string' lets JSON know to convert an incorming string to int
    User            *User   `json:"user"`
    Title           string  `json:"title"`
    Description     string  `json:"description"`
}