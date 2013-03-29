var userDao = require('../data/dao/user-account-dao.js');

module.exports.process = function(excludedUrl, excludedMethod, req, res, next)
{
    if (req.originalUrl == excludedUrl && req.method.toLocaleLowerCase() == excludedMethod.toLocaleLowerCase()) {
        next();

        return;
    }

    var token = req.headers.authorization.split('Basic ')[1],
        credentials = new Buffer(token, 'base64').toString().split(':'),
        username = credentials[0],
        password = credentials[1];

    userDao.findByUsernameAndPassword(username, password, function(createdUser) {
        if (createdUser) {
            req.current_user = createdUser;
            next();
        }
        else {
            res.writeHead(401);
            res.end();
        }
    });
};
