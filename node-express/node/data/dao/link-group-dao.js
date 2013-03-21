var baseDao = require('./base-dao.js');

module.exports.findAllByUserId = function(userId, callback)
{
    var qry = 'SELECT * FROM link_group WHERE user_account_id = $1';
    var params = [userId];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result.rows);
            }
        }
    });
};

module.exports.findByIdAndUserId = function(groupId, userId, callback)
{
    var qry = 'SELECT * FROM link_group WHERE id = $1 AND user_account_id = $2';
    var params = [groupId, userId];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result.rows[0]);
            }
        }
    });
};

module.exports.save = function(group, callback)
{
    var qry = 'INSERT INTO link_group (user_account_id, title, description) VALUES ($1, $2, $3)';
    var params = [group.user_account_id, group.title, group.description];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result);
            }
        }
    });
};