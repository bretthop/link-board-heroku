var linkGroupDao = require('../dao/link-group-dao.js');

exports.findByUser = function(userId, callback)
{
    linkGroupDao.findAllByUserId(userId, function(result) {
        callback(result);
    });
};

exports.save = function(data, callback)
{
    linkGroupDao.save(data, function(result) {
        callback(result);
    });
};