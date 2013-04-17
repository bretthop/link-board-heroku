package router

import (
    "net/http"
    "go/handler"
    "fmt"
)

func ApiRouter(w http.ResponseWriter, r *http.Request) {
    switch (r.URL.Path) {
        case "/api/users":
            handler.UsersHandler(w, r)
        default:
            // TODO: Return 404
    }
}