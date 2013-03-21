exports.init = function(path, app)
{
    if (!path) { path = '/'; }

    app.get (path, getMethod)
       .post(path, postMethod);
};

function getMethod(req, res, next)
{
    res.end('{}');
}

function postMethod(req, res, next)
{
    res.end('{}');
}