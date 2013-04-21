package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
)

func LinkHandler(w http.ResponseWriter, r *http.Request) {
    switch r.Method {
        case "GET":
            l := service.GetLink()

            if res, err := json.Marshal(l); err != nil {
                fmt.Println("ERROR: ", err)
            } else {
                fmt.Fprintf(w, string(res))
            }
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}