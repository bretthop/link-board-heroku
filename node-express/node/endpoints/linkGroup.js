var LinkGroup = require('../data/model/linkGroup.js');

exports.init = function(path, app)
{
    if (!path) { path = '/'; }

    app.get (path, getMethod)
       .post(path, postMethod);
};

function getMethod(req, res, next)
{
    res.format({
        'application/json': function() {
            LinkGroup.findByUser(req.user.id, function(result) {
                res.send(result);
            })
        }
    });
}

function postMethod(req, res, next)
{
    res.end('{}');
}