var User = require('../data/model/user.js');

function process(excludedUrl, excludedMethod)
{
    return function process(req, res, next) {
        if (req.originalUrl == excludedUrl && req.method.toLocaleLowerCase() == excludedMethod.toLocaleLowerCase()) {
            next();

            return;
        }

        var token = req.headers.authorization.split('Basic ')[1],
            credentials = new Buffer(token, 'base64').toString().split(':'),
            username = credentials[0],
            password = credentials[1];

        User.findByCredentials(username, password, function(createdUser) {
            if (createdUser) {
                req.user = createdUser;
                next();
            }
            else {
                res.writeHead(401);
                res.end();
            }
        });
    }
}

module.exports.process = process;