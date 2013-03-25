var baseEndpoint = require('./base.js'),
    LinkGroup = require('../data/model/linkGroup.js'),
    linkGroupValidator = require('./validators/link-group-validator.js');

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
    req.body.user_account_id = req.user.id;

    var valid = linkGroupValidator.validate(res, req.body);

    if (valid) {
        res.format({
            'application/json': function() {
                LinkGroup.save(req.body, function(result) {
                    res.send(201, result);
                });
            }
        });
    }
}

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod });
