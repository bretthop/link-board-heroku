package util

func HandleErrors(errs ...error) {
    for _, e := range errs {
        if e != nil {
            panic(e)
        }
    }
}