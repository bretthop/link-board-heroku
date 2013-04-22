package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
)

func LinkGroupHandler(w http.ResponseWriter, r *http.Request) {
    switch r.Method {
        case "GET":
            lg := service.GetLinkGroups()

            if res, err := json.Marshal(lg); err != nil {
                fmt.Println("ERROR: ", err)
            } else {
                w.Header().Add("Content-type", "application/json")
                fmt.Fprintf(w, string(res))
            }
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}