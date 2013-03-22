var LinkGroup = require('../data/model/linkGroup.js');

exports.init = function(path, app)
{
    if (!path) { path = '/'; }

    app.get (path, getMethod)
       .post(path, postMethod);
};

function getMethod(req, res, next)
{
    var groups = LinkGroup.findByUser(req.user.id);

    return JSON.stringify(groups);
}

function postMethod(req, res, next)
{
    res.end('{}');
}