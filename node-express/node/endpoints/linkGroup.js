var baseEndpoint = require('./base.js'),
    LinkGroup = require('../data/model/linkGroup.js');

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

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod });
