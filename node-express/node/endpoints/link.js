var baseEndpoint = require('./base.js'),
    Link = require('../data/model/link.js');

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

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod, methodDelete: deleteMethod });