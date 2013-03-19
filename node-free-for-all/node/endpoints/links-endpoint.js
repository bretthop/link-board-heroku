var baseEndpoint = require('./base-endpoint.js'),
    linkDao = require('../data/dao/link-dao.js'),
    linkGroupDao = require('../data/dao/link-group-dao.js');

function methodGet(req, res)
{
    doAccessCheck(res, req.query.groupId, req.current_user.id, function() {
        linkDao.findAllByGroupId(req.query.groupId, function(links) {
            res.writeHead(200, { "Content-Type": 'application/json' });
            res.end(JSON.stringify(links));
        });
    });
}

function methodPost(req, res)
{
    var new_link = req.body;

    doAccessCheck(res, new_link.group.id, req.current_user.id, function() {
        linkDao.save(new_link, function(result) {
            res.writeHead(201, { "Content-Type": 'application/json' });
            res.end(JSON.stringify(new_link));
        });
    });
}

function methodDelete(req, res)
{
    linkDao.findById(req.query.id, function(link) {
        if (link) {
            doAccessCheck(res, link.link_group_id, req.current_user.id, function() {
                linkDao.delete(req.query.id, function() {
                    res.end();
                });
            });
        }
        else {
            res.writeHead(404);
            res.end();
        }
    });
}

function doAccessCheck(res, groupId, userId, successCallback)
{
    linkGroupDao.findByIdAndUserId(groupId, userId, function(result) {
        if (result) {
            successCallback();
        }
        else {
            res.writeHead(409);
            res.end('The link group that was contained in the request is not valid for the current logged in user.');
        }
    });
}

module.exports.process = baseEndpoint.createEndpoint({ methodGet: methodGet, methodPost: methodPost, methodDelete: methodDelete });