var linkGroupDao = require('../data/dao/link-group-dao.js');

function process(req, res)
{
    switch (req.method) {
        case 'GET':
            linkGroupDao.findAllByUserId(req.current_user.id, function(linkGroups) {
                res.writeHead(200, { "Content-Type": 'application/json' });
                res.end(JSON.stringify(linkGroups));
            });

            break;
        case 'POST':
            var new_group = req.body;

            new_group.user_account_id = req.current_user.id;

            linkGroupDao.save(new_group, function(result) {
                res.writeHead(200, { "Content-Type": 'application/json' });
                res.end(JSON.stringify(new_group));
            });

            break;
        default:
            console.log('unsupported method for link groups');

            res.writeHead(405);
            res.end();
    }
}

module.exports.process = process;