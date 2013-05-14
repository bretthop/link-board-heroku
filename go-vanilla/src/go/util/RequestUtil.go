package util

import (
    "net/url"
    "strconv"
)

func ParseInt64FromQuery(i_url *url.URL, param_name string) int64 {
    qryMap, err := url.ParseQuery(i_url.RawQuery)
    HandleErrors(err)

    o_int, err := strconv.Atoi(qryMap.Get(param_name))
    HandleErrors(err)

    return int64(o_int)
}