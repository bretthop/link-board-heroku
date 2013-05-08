package handler

import (
    "net/http"
    "encoding/json"
    "go/service"
    "go/data/model"
    "go/util"
)

func UserHandler(w http.ResponseWriter, r *UserRequest) {
    switch r.Method {
        case "GET":
            util.MarshalToResponseWriter(r.User, w)
        case "POST":
            var user model.User

            decoder := json.NewDecoder(r.Body)
            decoder.Decode(&user)

            user = *service.SaveUser(&user)

            w.WriteHeader(http.StatusCreated)
            util.MarshalToResponseWriter(user, w)
        default:
            w.WriteHeader(http.StatusMethodNotAllowed)
    }
}