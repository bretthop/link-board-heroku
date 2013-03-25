var baseEndpoint = require('./base.js'),
    Link = require('../data/model/link.js'),
    LinkGroup = require('../data/model/linkGroup.js'),
    linkValidator = require('./validators/link-validator.js');

function getMethod(req, res, next)
{
    doAccessCheck(res, req.query.groupId, req.user.id, function() {
        res.format({
            'application/json': function() {
                Link.findByGroup(req.query.groupId, function(result) {
                    res.send(result);
                });
            }
        });
    });
}

function postMethod(req, res, next)
{
    var valid = linkValidator.validate(res, req.body);

    if (valid) {
        doAccessCheck(res, req.body.group.id, req.user.id, function() {
            res.format({
                'application/json': function() {
                    Link.save(req.body, function(result) {
                        res.send(201, result);
                    });
                }
            });
        });
    }
}

function deleteMethod(req, res, next)
{
    Link.findById(req.query.id, function(link) {
        if (link) {
            doAccessCheck(res, link.link_group_id, req.user.id, function() {
                res.format({
                    'application/json': function() {
                        Link.delete(req.query.id, function(result) {
                            res.send(result);
                        });
                    }
                });
            });
        }
        else {
            res.send(404, 'Link with id' + req.query.id + ' was not found.');
        }
    });
}

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod, methodDelete: deleteMethod });

function doAccessCheck(res, groupId, userId, successCallback)
{
    LinkGroup.findByIdAndUser(groupId, userId, function(result) {
        if (result) {
            successCallback();
        }
        else {
            res.writeHead(409);
            res.end('The link group that was contained in the request is not valid for the current logged in user.');
        }
    });
}