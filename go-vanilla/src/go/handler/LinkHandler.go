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
            l := service.GetLinks()

            if res, err := json.Marshal(l); err != nil {
                fmt.Println("ERROR: ", err)
            } else {
                w.Header().Add("Content-type", "application/json")
                fmt.Fprintf(w, string(res))
            }
        case "POST":
            service.SaveLink()
            w.WriteHeader(http.StatusOK)
        case "DELETE":
            service.DeleteLink()
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}