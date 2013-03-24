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
    res.format({
        'application/json': function() {
            req.body.user_account_id = req.user.id;

            LinkGroup.save(req.body, function(result) {
                res.send(result);
            });
        }
    });
}