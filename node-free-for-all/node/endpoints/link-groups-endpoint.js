var baseEndpoint = require('./base-endpoint.js'),
    linkGroupDao = require('../data/dao/link-group-dao.js'),
    linkGroupValidator = require('../validators/link-group-validator.js');

function methodGet(req, res)
{
    linkGroupDao.findAllByUserId(req.current_user.id, function(linkGroups) {
        res.writeHead(200, { "Content-Type": 'application/json' });
        res.end(JSON.stringify(linkGroups));
    });
}

function methodPost(req, res)
{
    var new_group = req.body;

    new_group.user_account_id = req.current_user.id;
    var valid = baseEndpoint.handleValidationResult(res, linkGroupValidator.validate(new_group));

    if (valid) {
        linkGroupDao.save(new_group, function(result) {
            res.writeHead(201, { "Content-Type": 'application/json' });
            res.end(JSON.stringify(new_group));
        });
    }
}

module.exports.process = baseEndpoint.createEndpoint({ methodGet: methodGet, methodPost: methodPost });