var userDao = require('../dao/user-account-dao.js');

exports.findByCredentials = function(username, password, callback)
{
    userDao.findByUsernameAndPassword(username, password, function(entity) {
        var bean = entity ?
        {
            id: entity.id,
            username: entity.username,
            password: entity.password,
            email: entity.email,
            firstName: entity.first_name,
            lastName: entity.last_name
        } : null;

        callback(bean);
    });
};

exports.save = function(data, callback)
{
    userDao.save(data, function(result) {
        callback(result);
    });
};