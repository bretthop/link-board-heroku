package util

import (
    "fmt"
    "net/http"
    "encoding/json"
)

func MarshalToResponseWriter(obj interface{}, w http.ResponseWriter) {
    res, err := json.Marshal(obj);
    HandleErrors(err)

    resStr := string(res)

    // Note: This is a filthy disgusting hack. However, the json package will marshal
    //       an empty slice into "null", which is pretty bullshit. So this statements checks
    //       to see if the incoming object IS NOT nil and the json result IS null, and if this is true
    //       then we will ASSUME that the incoming obj is an empty slice
    //       and set the json result to an empty array manually (seeing as an empty slice is NOT nil,
    //       but it IS null apparently).
    //       More research/investigation is required to clean this up
    //
    //       See this discussion of the json marshaling behaviour:
    //       https://groups.google.com/forum/?fromgroups=#!topic/golang-nuts/gOHbOk8DsFw
    if obj != nil && resStr == "null" {
        resStr = "[]"
    }

    w.Header().Add("Content-type", "application/json")
    fmt.Fprintf(w, resStr)
}