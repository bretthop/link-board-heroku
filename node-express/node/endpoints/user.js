var baseEndpoint = require('./base.js'),
    User = require('../data/model/user.js');

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

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod });