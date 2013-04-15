# Go Backend

Quick and dirty instructions:

1) Open a cmd or shell in the root of this repo, then run:
`go install go\app`
2) Then run:
`\bin.app.exe`

This will install the app (and its dependencies) into the \bin and \pkg folders, and allow you
to run the app from the *\bin\app.exe* runnable.

_Note:_ This application uses a top-level package of *Go*, this is _not_ recommended because it
clashes with a top-level package in the standard lib. However, its going to stay like this until
I choose another name.