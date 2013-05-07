# Go Backend

Quick and dirty instructions:

Open a command prompt in the root of this rep, then run:
1) `go get all` - this imports all external lib code (namely the postgres driver) into our project (this only has to be done once)
2) `go install go\app` - this builds our project
3) `cd bin` - change dir to bin (where the final exe is built)
4) `app.exe` - run the app

This will install the app (and its dependencies) into the \bin and \pkg folders, and allow you
to run the app from the *\bin\app.exe* runnable.

_Note:_ This application uses a top-level package of *Go*, this is _not_ recommended because it
clashes with a top-level package in Go's standard lib. However, its going to stay like this until
I choose another name.

## TODO
* Return groups as an array