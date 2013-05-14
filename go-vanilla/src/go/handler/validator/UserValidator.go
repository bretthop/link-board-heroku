package validator

import (
    "go/data/model"
)

func ValidateUser(user *model.User) string {
    var err string

    if user == nil {
        err = "User cannot be null"
    } else if len(user.Username) == 0 {
        err = "Username must be provided"
    } else if len(user.Password) == 0 {
        err = "Password must be provided"
    }

    return err
}