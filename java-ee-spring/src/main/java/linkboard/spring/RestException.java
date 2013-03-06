package linkboard.spring;

public class RestException extends RuntimeException
{
    private int status = 500;

    public RestException()
    { }

    public RestException(int status)
    {
        this.status = status;
    }

    public int getStatus()
    {
        return this.status;
    }
}
