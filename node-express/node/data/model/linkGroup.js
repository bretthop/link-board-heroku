var linkGroupDao = require('../dao/link-group-dao.js');

exports.findByUser = function(userId, callback)
{
    linkGroupDao.findAllByUserId(userId, function(result) {
        callback(result);
    });
};