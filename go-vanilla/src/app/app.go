package main

import (
	"fmt"
	"net/http"
	"encoding/json"
	"service"
)

func usersHandler(w http.ResponseWriter, r *http.Request) {
    u := service.GetUser()

    if res, err := json.Marshal(u); err != nil {
        fmt.Println("ERROR: ", err)
    } else {
	    fmt.Fprintf(w, string(res))
    }
}

func linksHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Links - TODO!")
}

func linkGroupsHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Link Groups - TODO!")
}

func main() {
    // TODO: Find a better way to ref static files, atm this will only work if you run 'go install app' (it wont work if you run 'go build app')
	webapp_root := "..\\src\\webapp"

	fmt.Println(http.Dir(webapp_root))

	http.HandleFunc("/api/users", usersHandler)
	http.HandleFunc("/api/links", linksHandler)
	http.HandleFunc("/api/linkGroups", linkGroupsHandler)
	
	http.Handle("/", http.FileServer(http.Dir(webapp_root)))
	
	http.ListenAndServe(":8080", nil)
}