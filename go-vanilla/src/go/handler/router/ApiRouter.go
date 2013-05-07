package router

import (
    "net/http"
    "go/handler"
)

func ApiRouter(w http.ResponseWriter, r *handler.UserRequest) {
    switch (r.URL.Path) {
        case "/api/users":
            handler.UserHandler(w, r)
        case "/api/links":
            handler.LinkHandler(w, r)
        case "/api/linkGroups":
            handler.LinkGroupHandler(w, r)
        default:
            w.WriteHeader(http.StatusNotFound)
    }
}