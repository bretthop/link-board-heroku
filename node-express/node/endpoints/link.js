exports.init = function(path, app)
{
    if (!path) { path = '/'; }

    app.get   (path, getMethod)
       .post  (path, postMethod)
       .delete(path, deleteMethod);
};

function getMethod(req, res, next)
{
    res.end('[]');
}

function postMethod(req, res, next)
{
    res.end('{}');
}

function deleteMethod(req, res, next)
{
    res.end('links delete');
}