var baseDao = require('./base-dao.js');
var userAccountMapper = require('../mapping/user-account-mapping.js');

module.exports.findByUsernameAndPassword = function(username, password, callback)
{
    var qry = 'SELECT * FROM user_account WHERE username = $1 AND password = $2';
    var params = [username, password];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                var bean = userAccountMapper.fromEntity(result.rows[0]);
                callback(bean);
            }
        }
    });
};

module.exports.save = function(user, callback)
{
    var qry = 'INSERT INTO user_account (username, password, email, first_name, last_name) VALUES ($1, $2, $3, $4, $5)';
    var params = [user.username, user.password, user.email, user.firstName, user.lastName];

    baseDao.executeQuery(qry, params, function(result) {
        if (result) {
            if (callback) {
                callback(result);
            }
        }
    });
};