package handler

import (
    "fmt"
    "net/http"
    "encoding/json"
    "go/service"
    "go/data/model"
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
            var link model.Link

            // TOFIX: json decoder does not decode "group:{id:1}" into group sub model
            decoder := json.NewDecoder(r.Body)
            decoder.Decode(&link)

            service.SaveLink(&link)

            w.WriteHeader(http.StatusOK)
        case "DELETE":
            service.DeleteLink()
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}