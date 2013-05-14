package handler

import (
    "net/http"
    "encoding/json"
    "go/service"
    "go/data/model"
    "go/util"
)

func LinkGroupHandler(w http.ResponseWriter, r *UserRequest) {
    switch r.Method {
        case "GET":
            lg := service.GetLinkGroupsForUser(r.User)

            util.MarshalToResponseWriter(lg, w)
        case "POST":
            var group model.LinkGroup

            decoder := json.NewDecoder(r.Body)
            decoder.Decode(&group)
            group.User = r.User

            service.SaveLinkGroup(&group)
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}