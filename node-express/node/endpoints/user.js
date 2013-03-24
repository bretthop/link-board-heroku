var User = require('../data/model/user.js');

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
            res.send(req.user);
        }
    });
}

function postMethod(req, res, next)
{
    res.format({
        'application/json': function() {
            User.save(req.body, function(result) {
                res.send(result);
            });
        }
    });
}