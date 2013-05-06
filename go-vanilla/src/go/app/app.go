package main

import (
	"net/http"
	"go/handler/security"
	"go/handler/router"
)

func main() {
    // TODO: Find a better way of doing this (this will only work if you are in \bin when you run app.exe)
	webapp_root := "..\\src\\webapp"

	http.HandleFunc("/api/", security.Authenticate(router.ApiRouter, "/api/users", "POST"))
	http.Handle("/", http.FileServer(http.Dir(webapp_root)))
	
	http.ListenAndServe(":8080", nil)
}