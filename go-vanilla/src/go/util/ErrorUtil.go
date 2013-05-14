package util

import (
    "net/http"
    "fmt"
)

func HandleErrors(errs ...error) {
    for _, e := range errs {
        if e != nil {
            panic(e)
        }
    }
}

func HandleBadRequest(err string, w http.ResponseWriter) bool {
    if len(err) > 0 {
        w.WriteHeader(http.StatusBadRequest)
        fmt.Fprintf(w, err)

        return true
    }

    return false
}