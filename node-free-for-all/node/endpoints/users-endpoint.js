var userDao = require('../data/dao/user-account-dao.js');

function process(req, res)
{
    switch (req.method) {
        case 'GET':
            res.end(JSON.stringify(req.current_user));

            break;
        case 'POST':
            var new_user = req.body;

            userDao.save(new_user, function(result) {
                res.end(JSON.stringify(new_user));
            });

            break;
        default:
            console.log('unsupported method for usersEndpoint');

            res.writeHead(405);
            res.end();
    }
}

module.exports.process = process;