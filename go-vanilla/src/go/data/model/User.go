package model

type User struct {
    Id          int     `json:"id"`
    Username    string  `json:"username"`
    Password    string  `json:"password"`
    Email       string  `json:"email"`
    FirstName   string  `json:"firstname"`
    LastName    string  `json:"lastname"`
}