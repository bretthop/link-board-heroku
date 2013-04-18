package security

import (
    "net/http"
    "encoding/base64"
    "strings"
    "go/service"
)

func Authenticate(router http.HandlerFunc, excludedPath string, excludedMethod string) http.HandlerFunc {
    return func (w http.ResponseWriter, r *http.Request) {
        // Note: ATM this function assumes the request has a Authorization header, and its type is basic
        authHeader64 := r.Header.Get("Authorization")

        authToken64 := strings.Split(authHeader64, "Basic ")[1]

        authToken, _ := base64.StdEncoding.DecodeString(authToken64)

        username := strings.Split(string(authToken), ":")[0]
        password := strings.Split(string(authToken), ":")[1]

        if user := service.GetUser(username, password); user != nil {
            router(w, r)
        } else {
            w.WriteHeader(http.StatusUnauthorized)
        }
    }
}