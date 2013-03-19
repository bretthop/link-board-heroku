var baseEndpoint = require('./base-endpoint.js'),
    linkDao = require('../data/dao/link-dao.js');

function methodGet(req, res)
{
    linkDao.findAllByGroupId(req.query.groupId, function(links) {
        res.writeHead(200, { "Content-Type": 'application/json' });
        res.end(JSON.stringify(links));
    });
}

function methodPost(req, res)
{
    var new_link = req.body;

    linkDao.save(new_link, function(result) {
        res.writeHead(201, { "Content-Type": 'application/json' });
        res.end(JSON.stringify(new_link));
    });
}

function methodDelete(req, res)
{
    linkDao.delete(req.query.id, function() {
        res.end();
    });
}

module.exports.process = baseEndpoint.createEndpoint({ methodGet: methodGet, methodPost: methodPost, methodDelete: methodDelete });