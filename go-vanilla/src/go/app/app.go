package main

import (
	"net/http"
	"go/handler/security"
	"go/handler/router"
)

func main() {
    // This rel path will ONLY work if this project is compiled via 'go install go/app'
    // (so that app.exe ends up in bin\app.exe) AND only if you also cd into bin\ before you
    // run app.exe
    // TODO: Fix this rel path to take it from the exe's location (not the current working dir)
	webapp_root := "..\\src\\webapp"

	http.HandleFunc("/api/", security.Authenticate(router.ApiRouter, "", ""))
	http.Handle("/", http.FileServer(http.Dir(webapp_root)))
	
	http.ListenAndServe(":8080", nil)
}