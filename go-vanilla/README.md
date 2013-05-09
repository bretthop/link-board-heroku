# Go Backend

Quick and dirty instructions:

Open a command prompt in the root of this rep, then run:
`bootstrap.bat`
This script will install the go application (fetch and compile all the dependencies, then build the app) and then run it

This will install the app (and its dependencies) into the \bin and \pkg folders, and allow you
to run the app from the *\bin\app.exe* runnable.

_Note:_ This application uses a top-level package of *Go*, this is _not_ recommended because it
clashes with a top-level package in Go's standard lib. However, its going to stay like this until
I choose another name.

## TODO
* Return groups as an array