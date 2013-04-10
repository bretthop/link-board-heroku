package main

import (
	"fmt"
	"net/http"
	"encoding/json"
)

// TODO: Move to model package
type User struct {
    Id          int
    Username    string
    Password    string
    Email       string
    FirstName   string
    LastName    string
}

func usersHandler(w http.ResponseWriter, r *http.Request) {
    u := User {
        Id: 1,
        Username: "temp",
        Password: "temp",
        Email: "temp",
        FirstName: "temp",
        LastName: "temp",
    }

    res, err := json.Marshal(u)

    if err != nil {
        fmt.Println("ERROR: ", err)
    }

	fmt.Fprintf(w, string(res))
}

func linksHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Links - TODO!")
}

func linkGroupsHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "Link Groups - TODO!")
}

func main() {
	webapp_root := "..\\webapp"
	
	http.HandleFunc("/api/users", usersHandler)
	http.HandleFunc("/api/links", linksHandler)
	http.HandleFunc("/api/linkGroups", linkGroupsHandler)
	
	http.Handle("/", http.FileServer(http.Dir(webapp_root)))
	
	http.ListenAndServe(":8080", nil)
}