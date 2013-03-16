function process(req, res, next)
{
    var token = req.headers.authorization.split('Basic ')[1],
        credentials = new Buffer(token, 'base64').toString().split(':'),
        username = credentials[0],
        password = credentials[1];


    var tempUser = {username: 'temp', password: 'temp' };

    if (username == tempUser.username && password == tempUser.password) {
        next();
    }
    else {
        res.writeHead(401);
        res.end();
    }
}

module.exports.process = process;