package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
)

func UserHandler(w http.ResponseWriter, r *http.Request) {
    switch r.Method {
        case "GET":
            u := service.GetUser("", "")

            if res, err := json.Marshal(u); err != nil {
                fmt.Println("ERROR: ", err)
            } else {
        	    fmt.Fprintf(w, string(res))
            }
        case "POST":
            service.SaveUser()
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}