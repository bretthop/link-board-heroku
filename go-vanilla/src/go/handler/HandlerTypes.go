package handler

import (
    "net/http"
    "go/data/model"
)

/**
* Describes a router function, which is the same as http.HandlerFunc EXCEPT that
* the request object is of type UserRequest and not http.Request.
*
* See UserRequest for information on how it differs from http.Request.
*/
type RouterFunc func(http.ResponseWriter, *UserRequest)

/**
* Wrapper struct for http.Request. All this adds is
* a 'User' property that the security.Authenticate filter can use to add
* the current user to the request object.
*/
type UserRequest struct {
    http.Request
    User *model.User
}