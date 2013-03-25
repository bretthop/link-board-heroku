var baseEndpoint = require('./base.js'),
    User = require('../data/model/user.js'),
    userValidator = require('./validators/user-account-validator.js');

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
    var valid = userValidator.validate(res, req.body);

    if (valid) {
        res.format({
            'application/json': function() {
                User.save(req.body, function(result) {
                    res.send(201, result);
                });
            }
        });
    }
}

exports.create = baseEndpoint.createEndpoint({methodGet: getMethod, methodPost: postMethod });