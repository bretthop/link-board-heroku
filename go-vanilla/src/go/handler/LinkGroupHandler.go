package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
    "go/data/model"
)

func LinkGroupHandler(w http.ResponseWriter, r *UserRequest) {
    switch r.Method {
        case "GET":
            lg := service.GetLinkGroups()

            if res, err := json.Marshal(lg); err != nil {
                fmt.Println("ERROR: ", err)
            } else {
                w.Header().Add("Content-type", "application/json")
                fmt.Fprintf(w, string(res))
            }
        case "POST":
            var group model.LinkGroup

            decoder := json.NewDecoder(r.Body)
            decoder.Decode(&group)

            service.SaveLinkGroup(&group)
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}