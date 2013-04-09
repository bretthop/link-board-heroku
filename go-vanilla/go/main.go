package main

import (
	"net/http"
)

func main() {
	webapp_root := "..\\webapp"
	
	http.ListenAndServe(":8080", http.FileServer(http.Dir(webapp_root)))
}