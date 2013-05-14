# Link Board

A simple application to help manage useful website links (basically a global bookmark manager).

## Project Goal
The real goal of this project is to get familiar with Heroku. The idea is that the backend of this app will be
implemented in several different languages and frameworks, and can be primarily used as a reference.

_Update:_ The goal of this project has changed... it is now not focused on Heroku at all, instead focusing on the different
backend technologies. Heroku intergration will be added eventually... but not any time soon.

## Project Structure
Each backend must behave in the same manor (i.e. they must all implement the same REST endpoints, with the same responses and codes).
This is achieved by first creating a standalone pure-HTML client that is used as-is across all backends. This ensures that the backend is
behaving correctly (because otherwise it just wouldn't work). At the moment, this HTML frontend has been copy-and-pasted into each backend 
implementation, however this will hopefully change in the near future.

Each backend exists in a dir in the root of this repository, with the database scripts also in the root (because 
the DB tables will be the same across all backends). The reason that a separate repo wasn't created for each backend is because I wanted
everything in one place as a reference.

## Backend Definition
This section describes the backend requirements. It is a simple list of URL mappings and a describtion of what is required at that URL.
All authentication is done via the AUTHORIZATION header, using basic auth.

* `/` - The backend must serve the static frontend files from the root URL, with no authentication,
* `/api` - All REST calls will be made with this base URL. All requests to here _must_ be authenticated, with the exception of
   a POST to `/api/users` (as this request is used to created a user),
* `/api/users` - GET: Returns the user object specified by the authorisation header; POST: Creates a new user,
* `/api/links` - TODO
* `/api/linkGroups` - TODO

## Technologies Used
Please refer to the README for each backend (located in the respective dir) for the specific technologies used for that backend. This list contains 
all of the common technologies across all backends.

### Frontend
- RequireJS
- Twitter Bootstrap
- jQuery
- Underscore

### Database
- PostgreSQL

## Completed
This is a list of completed backends:

- Java EE using Servlets and plain JDBC
- Java EE using Spring and Hibernate
- Ruby on Rails
- Node using no web framework (only Connect's Send, which is used to server static files, and another lib for talking to the PostgreSQL DB)
- Node using Express.

## TODO

- Remove the front end code from this repository and load it via a a tool (maybe create one with Rust)
- Add valdiation when creating a user so that the same username can't be used twice
- Replace query params with url parms (i.e. DELETE /api/links?id=1 => DELETE /api/links/1)

### Backend

- Implement a Python backend using the Flask framework
- Implement a Python backend using the Django framework
- Implement a vanilla PHP backend
- Implement a PHP backend using the Yii framework
- Implement a GO backend
- Implement a Rust backend
- Implement a .Net backend

### Functionality

- [x] Add ability to delete Links
- [ ] Add ability to tag links (this will stand as a many-to-many requirement for all backends)
- [ ] Add ability to collapse Groups
- [ ] Add ability to upload an icon for each Link
- [ ] Add ability to upload an icon for each Group
