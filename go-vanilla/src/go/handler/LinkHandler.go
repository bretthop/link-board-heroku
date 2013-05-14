package handler

import (
    "net/http"
    "encoding/json"
    "go/service"
    "go/data/model"
    "go/util"
)

func LinkHandler(w http.ResponseWriter, r *UserRequest) {
    switch r.Method {
        case "GET":
            groupId := util.ParseInt64FromQuery(r.URL, "groupId")

            if service.HasAccessToGroup(r.User, groupId) {
                links := service.GetLinksForGroup(groupId)

                util.MarshalToResponseWriter(links, w)
            } else {
                w.WriteHeader(http.StatusForbidden)
            }
        case "POST":
            var link model.Link

            decoder := json.NewDecoder(r.Body)
            decoder.Decode(&link)

            service.SaveLink(&link)

            w.WriteHeader(http.StatusOK)
        case "DELETE":
            linkId := util.ParseInt64FromQuery(r.URL, "id")

            service.DeleteLink(linkId)
            w.WriteHeader(http.StatusOK)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}