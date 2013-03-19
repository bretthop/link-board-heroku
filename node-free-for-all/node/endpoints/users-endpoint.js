var baseEndpoint = require('./base-endpoint.js'),
    userDao = require('../data/dao/user-account-dao.js');

function methodGet(req, res)
{
    res.writeHead(200, { "Content-Type": 'application/json' });
    res.end(JSON.stringify(req.current_user));
}

function methodPost(req, res)
{
    var new_user = req.body;

    userDao.save(new_user, function(result) {
        res.writeHead(200, { "Content-Type": 'application/json' });
        res.end(JSON.stringify(new_user));
    });
}

module.exports.process = baseEndpoint.createEndpoint({ methodGet: methodGet, methodPost: methodPost });