package util

import (
    "fmt"
    "net/http"
    "encoding/json"
)

func MarshalToResponseWriter(obj interface{}, w http.ResponseWriter) {
    res, err := json.Marshal(obj);
    HandleErrors(err)

    fmt.Fprintf(w, string(res))
}