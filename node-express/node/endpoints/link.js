var Link = require('../data/model/link.js');

exports.init = function(path, app)
{
    if (!path) { path = '/'; }

    app.get   (path, getMethod)
       .post  (path, postMethod)
       .delete(path, deleteMethod);
};

function getMethod(req, res, next)
{
    res.format({
        'application/json': function() {
            Link.findByGroup(req.query.groupId, function(result) {
                res.send(result);
            });
        }
    });
}

function postMethod(req, res, next)
{
    res.format({
        'application/json': function() {
            Link.save(req.body, function(result) {
                res.send(result);
            });
        }
    });
}

function deleteMethod(req, res, next)
{
    res.format({
        'application/json': function() {
            Link.delete(req.query.id, function(result) {
                res.send(result);
            });
        }
    });
}