package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
)

func UsersHandler(w http.ResponseWriter, r *http.Request) {
    u := service.GetUser("", "")

    if res, err := json.Marshal(u); err != nil {
        fmt.Println("ERROR: ", err)
    } else {
	    fmt.Fprintf(w, string(res))
    }
}